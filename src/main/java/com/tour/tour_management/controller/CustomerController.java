package com.tour.tour_management.controller;

import com.tour.tour_management.dto.request.customer.CustomerCreateRequest;
import com.tour.tour_management.dto.request.customer.CustomerUpdateRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.customer.CustomerResponse;
import com.tour.tour_management.dto.response.customer.GetCustomerResponse;
import com.tour.tour_management.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // khai bao controller
@RequestMapping("/customer") // api mac dinh, de kh can khai bao o funtion
// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {

    CustomerService customerService;

    @GetMapping
    public  ApiResponse<List<CustomerResponse>> getCustomers() {
        return ApiResponse.<List<CustomerResponse>>builder()
                .result( customerService.getCustomers())
                .build();
    }

//    getDeletedCategories
    @GetMapping("/getDeletedCategories")
    public  ApiResponse<List<CustomerResponse>> getDeletedCategories() {
    return ApiResponse.<List<CustomerResponse>>builder()
            .result( customerService.getDeletedCustomers())
            .build();
    }

    @GetMapping("/{customer_url}")
    public  ApiResponse<GetCustomerResponse> getCustomer(@PathVariable String customer_url) {
        return ApiResponse.<GetCustomerResponse>builder()
                .result( customerService.getCustomer(customer_url))
                .build();
    }

    @PostMapping
    public ApiResponse<CustomerResponse> createCustomer (@RequestBody @Valid CustomerCreateRequest customerCreateRequest) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.createCustomer(customerCreateRequest))
                .build();
    }

    @PutMapping("/{customer_url}")
    public ApiResponse<CustomerResponse> updateCustomer (@PathVariable String customer_url
            ,@RequestBody @Valid CustomerUpdateRequest customerUpdateRequest){
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.updateCustomer(customer_url,customerUpdateRequest))
                .build();
    }

    @PutMapping("/undoCustomer/{customer_url}")
    public ApiResponse<CustomerResponse> undoCustomer (@PathVariable String customer_url){
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.undoCustomer(customer_url))
                .build();
    }

    @DeleteMapping("/{customer_url}")
    public ApiResponse deleteCustomer (@PathVariable String customer_url) {
        return ApiResponse.builder()
                .result( customerService.deleteCustomer(customer_url))
                .build();
    }

}
