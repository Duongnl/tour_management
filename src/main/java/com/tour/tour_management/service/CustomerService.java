package com.tour.tour_management.service;


import com.tour.tour_management.dto.request.customer.CustomerRequest;
import com.tour.tour_management.dto.request.history.HistoryRequest;
import com.tour.tour_management.dto.response.customer.CustomerResponse;
import com.tour.tour_management.dto.response.customer.CustomerDetailResponse;
import com.tour.tour_management.entity.Customer;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CustomerErrorCode;
import com.tour.tour_management.mapper.CustomerMapper;
import com.tour.tour_management.repository.CustomerRepository;
import com.tour.tour_management.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;
    HistoryService historyService;

    /**
     * Lấy danh sách khách hàng
     *
     * @return danh sách khách hàng
     */
    @PreAuthorize("hasRole('ACCESS_CUSTOMER')")
    public List<CustomerResponse> getCustomers(){
        List<Customer> customerList =  customerRepository.findAllOrderedByDateTime();
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerList.forEach(
                customer -> {
                        customerResponseList.add(customerMapper.toCustomerResponse(customer));
                });
        return customerResponseList;
    }


    /**
     * Lấy khách hàng còn hoạt đông
     *
     * @return danh sách khách hàng còn còn hoạt động
     */
    @PreAuthorize("hasRole('ACCESS_CUSTOMER')")
    public List<CustomerResponse> getActiveCustomers () {
        Sort sort = Sort.by(Sort.Direction.DESC, "time");

        List<Customer> customerList = customerRepository.findByStatusWithSorting(1,sort);
        List<CustomerResponse> customerResponseList = new ArrayList<>();

        customerList.forEach( customer ->{
                customerResponseList.add(customerMapper.toCustomerResponse(customer));
        });
        return customerResponseList;
    }

    /**
     * Lấy khách hàng đã bị xóa
     *
     * @return danh sách khách hàng dã bị xóa
     */
    @PreAuthorize("hasRole('ACCESS_CUSTOMER')")
    public List<CustomerResponse> getLockedCustomers () {
        Sort sort = Sort.by(Sort.Direction.DESC, "time");

        List<Customer> customerList = customerRepository.findByStatusWithSorting(0,sort);
        List<CustomerResponse> customerResponseList = new ArrayList<>();

        customerList.forEach( customer ->{
            customerResponseList.add(customerMapper.toCustomerResponse(customer));
        });
        return customerResponseList;
    }

    /**
     * Lấy khách hàng có thể là người đại diện
     *
     * @return danh sách dữ liệu khách hàng
     */
    @PreAuthorize("hasRole('ACCESS_CUSTOMER')")
    public List<CustomerResponse> getCustomersParent(){
        List<Customer> customerList =  customerRepository.findAllOrderedByDateTime();
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerList.forEach(
                customer -> {
                    if(customer.getCustomer()==null) {
                        customerResponseList.add(customerMapper.toCustomerResponse(customer));
                    }
                });
        return customerResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_CUSTOMER')")
    public CustomerDetailResponse getCustomer(String customer_url) {
        Integer id = StringUtils.getIdFromUrl(customer_url);

        // lay thông tin người dùng hiện tại
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));

        CustomerDetailResponse c = customerMapper.toCustomerDetailResponse(customer);

        // lấy thông tin người đại diện
        Customer customerParent=null;
        // kiểm tra người đại diện 
        if(customer.getCustomer()!=null) {
            // nếu có người đại diện thì tìm các nhóm của người đại diện
            customerParent = customerRepository.findById(customer.getCustomer().getCustomer_id())
                    .orElseThrow(() -> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));
        }
        else{
            // nếu không có người đại diện thì bản thân  là người đại diện 
            // tìm nhóm người đại diện
            customerParent=customer;
            c.setCustomerParent(customerMapper.toCustomerResponse(customerParent));
        }

        // thêm thông tin nhóm người vào response
        Set<CustomerResponse> customerResponses = new HashSet<>();
        customerParent.getCustomers().forEach(
                ct -> {
                    customerResponses.add(customerMapper.toCustomerResponse(ct));
                }
        );

        c.setCustomerGroup(customerResponses);
        return c;
    }

    @PreAuthorize("hasRole('CREATE_CUSTOMER')")
    public CustomerDetailResponse createCustomer(CustomerRequest customerRequest) {
        // check phone number
        if (customerRepository.existsByPhone_number(customerRequest.getPhone_number())) {
            throw new AppException(CustomerErrorCode.CUSTOMER_PHONE_NUMBER_EXISTED);
        }
        Customer customer = customerMapper.toCustomer(customerRequest);
        // check customer relationship
        if(customerRequest.getCustomer_rel_id()!=null){
            Customer customerParent= null;
            customerParent=customerRepository.findById(customerRequest.getCustomer_rel_id()).orElseThrow(
                () -> new  AppException(CustomerErrorCode.CUSTOMER_RELATIONSHIP_ID_NOT_FOUND)
            );
            customer.setCustomer(customerParent);
        }
        else{
            customer.setCustomer(null);
        }
        customer.setStatus(1);
        LocalDateTime localDateTime = LocalDateTime.now();
        customer.setTime(localDateTime);

        customer.setCustomer_name(customer.getCustomer_name());
        customerRepository.save(customer);
        
        historyService.createHistory("Created customer: "+customer.getCustomer_name()+" : "+customer.getCustomer_id());

        return customerMapper.toCustomerDetailResponse(customer);
    }

    @PreAuthorize("hasRole('UPDATE_CUSTOMER')")
    public CustomerDetailResponse updateCustomer(String CUSTOMER_url , CustomerRequest customerRequest) {

        Customer customer = customerRepository.findById(StringUtils.getIdFromUrl(CUSTOMER_url))
                .orElseThrow(() -> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));

        // check phone number
        if(customerRequest.getPhone_number()!=null){
            String oldPhoneNumber = customer.getPhone_number();
            String newPhoneNumber = customerRequest.getPhone_number();
            if (!newPhoneNumber.equals(oldPhoneNumber))
                if (customerRepository.existsByPhone_number(customerRequest.getPhone_number())) {
                    throw new AppException(CustomerErrorCode.CUSTOMER_PHONE_NUMBER_EXISTED);
                }

        }

        // check customer relationship
        Customer customerParent= null;
        if(customerRequest.getCustomer_rel_id()!=null){
            customerParent=customerRepository.findById(customerRequest.getCustomer_rel_id()).orElseThrow(
                () -> new  AppException(CustomerErrorCode.CUSTOMER_RELATIONSHIP_ID_NOT_FOUND)
            );
            customer.setCustomer(customerParent);
        }
        else{
            customer.setCustomer(null);
        }

        customerMapper.updateCustomer(customer, customerRequest);
        customerRepository.save(customer);
        historyService.createHistory("Updated customer: "+customer.getCustomer_name()+" : "+customer.getCustomer_id());
        return customerMapper.toCustomerDetailResponse(customer);
    }

    @PreAuthorize("hasRole('CHANGE_CUSTOMER_STATUS')")
    public CustomerDetailResponse changeStatus (String customer_url) {
        if (StringUtils.getIdFromUrl(customer_url) == -1) {
            throw new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND);
        }

        Customer customer = customerRepository.findById(StringUtils.getIdFromUrl(customer_url))
                .orElseThrow(() -> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));

        if (customer.getStatus() == 0) {
            customer.setStatus(1);
        } else {
            customer.setStatus(0);
        }
        customerRepository.save(customer);
        historyService.createHistory("Changed status customer: "+customer.getCustomer_name()+" : "+customer.getCustomer_id());
        return customerMapper.toCustomerDetailResponse(customer);
    }
}
