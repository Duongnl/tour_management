package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.request.account.AccountCreateRequest;
import com.tour.tour_management.dto.response.account.AccountResponse;
import com.tour.tour_management.dto.response.account.GetAccountResponse;
import com.tour.tour_management.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount (AccountCreateRequest accountCreateRequest);

    GetAccountResponse toGetAccountResponse (Account account);

    AccountResponse toAccountResponse (Account account);
}
