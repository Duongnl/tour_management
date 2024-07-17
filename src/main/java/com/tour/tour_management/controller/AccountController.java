package com.tour.tour_management.controller;


import com.tour.tour_management.dto.request.account.AccountRequest;
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
    public ApiResponse<List<AccountResponse>> getTours() {
        return ApiResponse.<List<AccountResponse>>builder()
                .result( accountService.getAccounts())
                .build();
    }

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
    public ApiResponse<GetAccountResponse> updateAccount (@RequestBody @Valid AccountRequest  accountRequest,@PathVariable String url) {
        return ApiResponse.<GetAccountResponse>builder()
                .result(accountService.updateAccount(url,accountRequest))
                .build();
    }



    @PutMapping ("/change-status/{account_id}")
    public  ApiResponse<GetAccountResponse> changeStatus(@PathVariable String account_id) {
        return ApiResponse.<GetAccountResponse>builder()
                .result(accountService.changeStatus(account_id))
                .build();
    }

}
