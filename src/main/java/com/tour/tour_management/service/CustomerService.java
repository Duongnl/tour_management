package com.tour.tour_management.service;


import com.tour.tour_management.dto.request.customer.CustomerCreateRequest;
import com.tour.tour_management.dto.request.customer.CustomerUpdateRequest;
import com.tour.tour_management.dto.response.customer.CustomerResponse;
import com.tour.tour_management.dto.response.customer.GetCustomerResponse;
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
import java.util.ArrayList;
import java.util.List;


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

    public GetCustomerResponse getCustomer(String customer_url) {

        if (StringUtils.getIdFromUrl(customer_url) == -1) {
            throw new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND);
        }

        Customer customer = customerRepository.findById(StringUtils.getIdFromUrl(customer_url))
                .orElseThrow(() -> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));
        return customerMapper.toGetCustomerResponse(customer);
    }

    public CustomerResponse createCustomer(CustomerCreateRequest customerCreateRequest) {
        Customer customer = customerMapper.toCustomer(customerCreateRequest);
        customer.setStatus(1);
        LocalDateTime localDateTime = LocalDateTime.now();
        customer.setTime(localDateTime);

        customer.setCustomer_name(customer.getCustomer_name());
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public CustomerResponse updateCustomer(String CUSTOMER_url , CustomerUpdateRequest customerUpdateRequest) {

        if (StringUtils.getIdFromUrl(CUSTOMER_url) == -1) {
            throw new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND);
        }

        Customer customer = customerRepository.findById(StringUtils.getIdFromUrl(CUSTOMER_url))
                .orElseThrow(() -> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));

        customerMapper.updateCustomer(customer, customerUpdateRequest);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
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
