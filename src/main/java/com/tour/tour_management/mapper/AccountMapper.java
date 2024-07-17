package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.request.account.AccountRequest;
import com.tour.tour_management.dto.request.tour.TourUpdateRequest;
import com.tour.tour_management.dto.response.account.AccountResponse;
import com.tour.tour_management.dto.response.account.GetAccountResponse;
import com.tour.tour_management.entity.Account;
import com.tour.tour_management.entity.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount (AccountRequest accountRequest);

    GetAccountResponse toGetAccountResponse (Account account);

    @Mapping(source = "role.role_name", target = "role_name")
    @Mapping(source = "employee.employee_name", target = "employee_name")
    AccountResponse toAccountResponse (Account account);

    void updateAccount (@MappingTarget Account account, AccountRequest accountRequest);
}
