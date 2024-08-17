package com.tour.tour_management.service;


import com.tour.tour_management.dto.request.customer.CustomerCreateRequest;
import com.tour.tour_management.dto.request.customer.CustomerUpdateRequest;
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


    public List<CustomerResponse> getCustomers(){
        List<Customer> customerList =  customerRepository.findAll();
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerList.forEach(
                customer -> {
                        customerResponseList.add(customerMapper.toCustomerResponse(customer));
                });
        return customerResponseList;
    }

    public List<CustomerResponse> getDeletedCustomers(){
        List<Customer> customerList =  customerRepository.findByStatus(0);
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerList.forEach(
                customer -> {
                        customerResponseList.add(customerMapper.toCustomerResponse(customer));
                });
        return customerResponseList;
    }

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
    public CustomerDetailResponse createCustomer(CustomerCreateRequest customerCreateRequest) {
        // check phone number
        if (customerRepository.existsByPhone_number(customerCreateRequest.getPhone_number())) {
            throw new AppException(CustomerErrorCode.CUSTOMER_PHONE_NUMBER_EXISTED);
        }

        // chech customer relationship
        Customer customerParent= null;
        if(customerCreateRequest.getCustomer_rel_id()!=null){
            customerParent=customerRepository.findById(customerCreateRequest.getCustomer_rel_id()).orElseThrow(
                () -> new  AppException(CustomerErrorCode.CUSTOMER_RELATIONSHIP_ID_DIDNT_EXIST)
            );
        }

        Customer customer = customerMapper.toCustomer(customerCreateRequest);
        customer.setCustomer(customerParent);
        customer.setStatus(1);
        LocalDateTime localDateTime = LocalDateTime.now();
        customer.setTime(localDateTime);

        customer.setCustomer_name(customer.getCustomer_name());
        return customerMapper.toCustomerDetailResponse(customerRepository.save(customer));
    }

    public CustomerDetailResponse updateCustomer(String CUSTOMER_url , CustomerUpdateRequest customerUpdateRequest) {

        // check phone number
        if (customerRepository.existsByPhone_number(customerUpdateRequest.getPhone_number())) {
            throw new AppException(CustomerErrorCode.CUSTOMER_PHONE_NUMBER_EXISTED);
        }

        // chech customer relationship
        Customer customerParent= null;
        if(customerUpdateRequest.getCustomer_rel_id()!=null){
            customerParent=customerRepository.findById(customerUpdateRequest.getCustomer_rel_id()).orElseThrow(
                () -> new  AppException(CustomerErrorCode.CUSTOMER_RELATIONSHIP_ID_DIDNT_EXIST)
            );
        }

        Customer customer = customerRepository.findById(StringUtils.getIdFromUrl(CUSTOMER_url))
                .orElseThrow(() -> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));

        customerMapper.updateCustomer(customer, customerUpdateRequest);
        return customerMapper.toCustomerDetailResponse(customerRepository.save(customer));
    }

    public CustomerResponse undoCustomer(String customer_url) {
        if (StringUtils.getIdFromUrl(customer_url) == -1) {
            throw new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND);
        }

        Customer customer = customerRepository.findById(StringUtils.getIdFromUrl(customer_url))
                .orElseThrow(() -> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));
        // hiển thị lại các tour trong danh mục đó
        
        // hiển thị lại tour
        customer.setStatus(1);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }
    
    public CustomerResponse deleteCustomer(String CUSTOMER_url) {
        if (StringUtils.getIdFromUrl(CUSTOMER_url) == -1) {
            throw new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND);
        }

        Customer customer = customerRepository.findById(StringUtils.getIdFromUrl(CUSTOMER_url))
                .orElseThrow(() -> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));

        customer.setStatus(0);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }
}
