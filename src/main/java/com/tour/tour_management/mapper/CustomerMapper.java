package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.request.customer.CustomerRequest;
import com.tour.tour_management.dto.response.customer.CustomerDetailResponse;
import com.tour.tour_management.dto.response.customer.CustomerResponse;
import com.tour.tour_management.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

// khoi tao mapper
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // thực hiện đổi tên trong response trả về
    @Mapping(source = "customer", target = "customerParent")
    CustomerDetailResponse toCustomerDetailResponse(Customer customer);

    CustomerResponse toCustomerResponse(Customer customer);

    Customer toCustomer(CustomerRequest customerRequest);

    void updateCustomer(@MappingTarget Customer customer, CustomerRequest customerUpdateRequest);

    Customer toCustomerFromResponse(CustomerResponse customerResponse);
}
