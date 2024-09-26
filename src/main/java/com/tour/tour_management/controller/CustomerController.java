package com.tour.tour_management.controller;

import com.tour.tour_management.dto.request.customer.CustomerRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.customer.CustomerDetailResponse;
import com.tour.tour_management.dto.response.customer.CustomerResponse;
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

    @GetMapping("/active")
    public  ApiResponse<List<CustomerResponse>> getActiveCustomers() {
        return ApiResponse.<List<CustomerResponse>>builder()
                .result( customerService.getActiveCustomers())
                .build();
    }

    @GetMapping("/locked")
    public  ApiResponse<List<CustomerResponse>> getLockedCustomers() {
        return ApiResponse.<List<CustomerResponse>>builder()
                .result( customerService.getLockedCustomers())
                .build();
    }

    @GetMapping("/parent")
    public  ApiResponse<List<CustomerResponse>> getCustomersParent() {
        return ApiResponse.<List<CustomerResponse>>builder()
                .result( customerService.getCustomersParent())
                .build();
    }



    @GetMapping("/{customer_url}")
    public  ApiResponse<CustomerDetailResponse> getCustomer(@PathVariable String customer_url) {
        return ApiResponse.<CustomerDetailResponse>builder()
                .result( customerService.getCustomer(customer_url))
                .build();
    }

    @PostMapping
    public ApiResponse<CustomerDetailResponse> createCustomer (@RequestBody @Valid CustomerRequest customerRequest) {
        return ApiResponse.<CustomerDetailResponse>builder()
                .result(customerService.createCustomer(customerRequest))
                .build();
    }

    @PutMapping("/{customer_url}")
    public ApiResponse<CustomerDetailResponse> updateCustomer (@PathVariable String customer_url
            ,@RequestBody @Valid CustomerRequest customerRequest){
        return ApiResponse.<CustomerDetailResponse>builder()
                .result(customerService.updateCustomer(customer_url,customerRequest))
                .build();
    }

    @PutMapping ("/change-status/{customer_url}")
    public  ApiResponse<CustomerDetailResponse> changeStatus(@PathVariable String customer_url ) {
        return ApiResponse.<CustomerDetailResponse>builder()
                .result(customerService.changeStatus(customer_url))
                .build();
    }

}
