package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.request.customer.CustomerCreateRequest;
import com.tour.tour_management.dto.request.customer.CustomerUpdateRequest;
import com.tour.tour_management.dto.response.customer.CustomerResponse;
import com.tour.tour_management.dto.response.customer.GetCustomerResponse;
import com.tour.tour_management.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

// khoi tao mapper
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    GetCustomerResponse toGetCustomerResponse(Customer customer);

    CustomerResponse toCustomerResponse(Customer customer);

    Customer toCustomer(CustomerCreateRequest customerCreateRequest);

    void updateCustomer(@MappingTarget Customer customer, CustomerUpdateRequest customerUpdateRequest);


}
