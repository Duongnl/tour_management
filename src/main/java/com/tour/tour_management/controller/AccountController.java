package com.tour.tour_management.controller;


import com.tour.tour_management.dto.request.account.AccountCreateRequest;
import com.tour.tour_management.dto.request.airline.AirlineCreateRequest;
import com.tour.tour_management.dto.request.tour.TourCreateRequest;
import com.tour.tour_management.dto.response.AirlineResponse;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.account.AccountResponse;
import com.tour.tour_management.dto.response.account.GetAccountResponse;
import com.tour.tour_management.dto.response.tour.TourResponse;
import com.tour.tour_management.service.AccountService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // khai bao controller
@RequestMapping("/account") // api mac dinh, de kh can khai bao o funtion
// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    @GetMapping
    public ApiResponse<List<AccountResponse>> getTours() {
        return ApiResponse.<List<AccountResponse>>builder()
                .result( accountService.getAccounts())
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<GetAccountResponse> getMyInfo () {
        return ApiResponse.<GetAccountResponse>builder()
                .result(accountService.getMyInfo())
                .build();
    }

    @PostMapping
    public ApiResponse<GetAccountResponse> createTour (@RequestBody AccountCreateRequest accountCreateRequest) {
        return ApiResponse.<GetAccountResponse>builder()
                .result(accountService.createAccount(accountCreateRequest))
                .build();
    }

}
