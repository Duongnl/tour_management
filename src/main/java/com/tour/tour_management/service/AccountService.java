package com.tour.tour_management.service;
import com.tour.tour_management.dto.request.account.AccountRequest;
import com.tour.tour_management.dto.response.account.AccountResponse;
import com.tour.tour_management.dto.response.account.GetAccountResponse;
import com.tour.tour_management.dto.response.tour.GetTourResponse;
import com.tour.tour_management.entity.Account;
import com.tour.tour_management.entity.Role;
import com.tour.tour_management.exception.AccountErrorCode;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.RoleErrorCode;
import com.tour.tour_management.exception.TourErrorCode;
import com.tour.tour_management.mapper.AccountMapper;
import com.tour.tour_management.repository.AccountRepository;
import com.tour.tour_management.repository.EmployeeRepository;
import com.tour.tour_management.repository.RoleRepository;
import com.tour.tour_management.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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


    public GetAccountResponse getAccount(String url){
        return accountMapper.toGetAccountResponse(accountRepository.findById(StringUtils.getUUIDFromUrl(url))
                .orElseThrow(() -> new AppException(AccountErrorCode.ACCOUNT_NOT_FOUND)));
    }


    public GetAccountResponse createAccount (AccountRequest accountRequest) {
        if(accountRepository.existedAccountName(accountRequest.getAccount_name())){
            throw new AppException(AccountErrorCode.ACCOUNT_EXISTED);
        }

        Role role = roleRepository.findById(accountRequest.getRole_id())
                .orElseThrow(()-> new AppException(RoleErrorCode.ROLE_NOT_FOUND));


        Account account = accountMapper.toAccount(accountRequest);
        account.setRole(role);

//        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime currentTimeInZone = ZonedDateTime.now();
        account.setTime(currentTimeInZone);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setStatus(1);

        account.getEmployee().setStatus(1);
        account.getEmployee().setAccount(account);

        employeeRepository.save(account.getEmployee());

        return accountMapper.toGetAccountResponse(accountRepository.save(account));
    }


    public GetAccountResponse updateAccount (String url, AccountRequest accountRequest) {
        Account account =  accountRepository.findById(StringUtils.getUUIDFromUrl(url)).orElseThrow(
                () -> new AppException(AccountErrorCode.ACCOUNT_NOT_FOUND));


        if(accountRepository.existedAccountName(accountRequest.getAccount_name())){
            throw new AppException(AccountErrorCode.ACCOUNT_EXISTED);
        }

        Role role = roleRepository.findById(accountRequest.getRole_id())
                .orElseThrow(()-> new AppException(RoleErrorCode.ROLE_NOT_FOUND));

        accountMapper.updateAccount(account,accountRequest);
        account.setRole(role);

//        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
//        ZonedDateTime currentTimeInZone = ZonedDateTime.now();
//        account.setTime(currentTimeInZone);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        account.getEmployee().setAccount(account);

        employeeRepository.save(account.getEmployee());

        return accountMapper.toGetAccountResponse(accountRepository.save(account));
    }


    //    lay thong tin cua user dang dang nhap
    public GetAccountResponse getMyInfo () {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Account account =   accountRepository.findByAccountName(name).orElseThrow(
                () -> new AppException(AccountErrorCode.ACCOUNT_NOT_FOUND));
        if (account.getStatus() != 1 ){
             throw new AppException(AccountErrorCode.ACCOUNT_LOCKED);
        }

        return accountMapper.toGetAccountResponse(account);
    }

    public  GetAccountResponse changeStatus (String account_id) {
        Account account =   accountRepository.findById(account_id).orElseThrow(
                () -> new AppException(AccountErrorCode.ACCOUNT_NOT_FOUND));

        if (account.getStatus() == 0) {
            account.setStatus(1);
        } else {
            account.setStatus(0);
        }
        return accountMapper.toGetAccountResponse(accountRepository.save(account));
    }



}
