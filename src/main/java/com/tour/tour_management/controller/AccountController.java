package com.tour.tour_management.controller;


import com.tour.tour_management.dto.request.account.AccountRequest;
import com.tour.tour_management.dto.request.account.AccountUpdateRequest;
import com.tour.tour_management.dto.request.account.EmployeeRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.account.AccountResponse;
import com.tour.tour_management.dto.response.account.GetAccountResponse;
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
    public ApiResponse<List<AccountResponse>> getAccounts() {
        return ApiResponse.<List<AccountResponse>>builder()
                .result( accountService.getAccounts())
                .build();
    }

    @GetMapping("/locked")
    public ApiResponse<List<AccountResponse>> getLockedAccounts() {
        return ApiResponse.<List<AccountResponse>>builder()
                .result( accountService.getLockedAccounts())
                .build();
    }

    @GetMapping("/active")
    public ApiResponse<List<AccountResponse>> getActiveAccounts() {
        return ApiResponse.<List<AccountResponse>>builder()
                .result( accountService.getActiveAccounts())
                .build();
    }

//    @GetMapping
//    public Integer getAccountsFollowPage(@RequestParam(defaultValue = "1") Integer page) {
//        return page;
//    }

    @GetMapping("/my-info")
    ApiResponse<GetAccountResponse> getMyInfo () {
        return ApiResponse.<GetAccountResponse>builder()
                .result(accountService.getMyInfo())
                .build();
    }

    @GetMapping("/{url}")
    public ApiResponse<GetAccountResponse> getAccount (@PathVariable String url) {
        return ApiResponse.<GetAccountResponse>builder()
                .result(accountService.getAccount(url))
                .build();
    }

    @PostMapping
    public ApiResponse<GetAccountResponse> createAccount (@RequestBody @Valid AccountRequest  accountRequest) {
        return ApiResponse.<GetAccountResponse>builder()
                .result(accountService.createAccount(accountRequest))
                .build();
    }

    @PutMapping("/{url}")
    public ApiResponse<GetAccountResponse> updateAccount (@RequestBody @Valid AccountUpdateRequest accountUpdateRequest, @PathVariable String url) {
        return ApiResponse.<GetAccountResponse>builder()
                .result(accountService.updateAccount(url,accountUpdateRequest))
                .build();
    }

    @PutMapping("/employee/{url}")
    public ApiResponse<GetAccountResponse> updateEmployee (@RequestBody @Valid EmployeeRequest employeeRequest, @PathVariable String url) {
        return ApiResponse.<GetAccountResponse>builder()
                .result(accountService.updateEmployee(url,employeeRequest))
                .build();
    }



    @PutMapping ("/change-status/{account_id}")
    public  ApiResponse<GetAccountResponse> changeStatus(@PathVariable String account_id) {
        return ApiResponse.<GetAccountResponse>builder()
                .result(accountService.changeStatus(account_id))
                .build();
    }


}
