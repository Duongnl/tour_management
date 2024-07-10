package com.tour.tour_management.service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jwt.JWTClaimsSet;
import com.tour.tour_management.dto.request.account.AccountCreateRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.account.AccountResponse;
import com.tour.tour_management.dto.response.account.GetAccountResponse;
import com.tour.tour_management.dto.response.tour.TourResponse;
import com.tour.tour_management.entity.Account;
import com.tour.tour_management.entity.Role;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.exception.AccountErrorCode;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CategoryErrorCode;
import com.tour.tour_management.exception.RoleErrorCode;
import com.tour.tour_management.mapper.AccountMapper;
import com.tour.tour_management.repository.AccountRepository;
import com.tour.tour_management.repository.EmployeeRepository;
import com.tour.tour_management.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class AccountService {

    AccountRepository accountRepository;
    RoleRepository roleRepository;
    AccountMapper accountMapper;
    EmployeeRepository employeeRepository;

    public List<AccountResponse> getAccounts () {
       List<Account> accountList = accountRepository.findAll();
       List<AccountResponse> accountResponseList = new ArrayList<>();

       accountList.forEach( account ->{
            accountResponseList.add(accountMapper.toAccountResponse(account));
        });
        return accountResponseList;
    }

    public GetAccountResponse createAccount (AccountCreateRequest accountCreateRequest) {
        if(accountRepository.existedAccountName(accountCreateRequest.getAccount_name())){
            throw new AppException(AccountErrorCode.ACCOUNT_EXISTED);
        }

        Role role = roleRepository.findById(accountCreateRequest.getRole_id())
                .orElseThrow(()-> new AppException(RoleErrorCode.ROLE_NOT_FOUND));


        Account account = accountMapper.toAccount(accountCreateRequest);
        account.setRole(role);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setStatus(1);

        account.getEmployee().setStatus(1);
        account.getEmployee().setAccount(account);

        employeeRepository.save(account.getEmployee());

        return accountMapper.toGetAccountResponse(accountRepository.save(account));
    }




}
