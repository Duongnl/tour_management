package com.tour.tour_management.service;
import com.tour.tour_management.dto.request.account.AccountRequest;
import com.tour.tour_management.dto.request.account.AccountUpdateRequest;
import com.tour.tour_management.dto.request.account.EmployeeRequest;
import com.tour.tour_management.dto.response.account.AccountResponse;
import com.tour.tour_management.dto.response.account.EmployeeResponse;
import com.tour.tour_management.dto.response.account.GetAccountResponse;
import com.tour.tour_management.dto.response.tour.TourDetailResponse;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class AccountService {

    AccountRepository accountRepository;
    RoleRepository roleRepository;
    AccountMapper accountMapper;
    EmployeeRepository employeeRepository;

    @PreAuthorize("hasRole('ACCESS_ACCOUNT')")
    public List<AccountResponse> getAccounts () {
       List<Account> accountList = accountRepository.findAllOrderedByDateTime();
       List<AccountResponse> accountResponseList = new ArrayList<>();
        log.info("In method get user");
       accountList.forEach( account ->{
            accountResponseList.add(accountMapper.toAccountResponse(account));
        });
        return accountResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_ACCOUNT')")
    public List<AccountResponse> getActiveAccounts () {
        List<Account> accountList = accountRepository.findAllOrderedByDateTime();
//        double totalAccounts = accountList.size();
//        int numberPages = (int) Math.ceil(totalAccounts / 8.0);
        List<AccountResponse> accountResponseList = new ArrayList<>();

        accountList.forEach( account ->{
            if (account.getStatus()==1) {
                accountResponseList.add(accountMapper.toAccountResponse(account));
            }
        });
        return accountResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_ACCOUNT')")
    public List<AccountResponse> getLockedAccounts () {
        List<Account> accountList = accountRepository.findAllOrderedByDateTime();
//        double totalAccounts = accountList.size();
//        int numberPages = (int) Math.ceil(totalAccounts / 8.0);
        List<AccountResponse> accountResponseList = new ArrayList<>();

        accountList.forEach( account ->{
            if (account.getStatus()==0) {
                accountResponseList.add(accountMapper.toAccountResponse(account));
            }
        });
        return accountResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_ACCOUNT')")
    public GetAccountResponse getAccount(String url){
        return accountMapper.toGetAccountResponse(accountRepository.findById(StringUtils.getUUIDFromUrl(url))
                .orElseThrow(() -> new AppException(AccountErrorCode.ACCOUNT_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('CREATE_ACCOUNT')")
    public GetAccountResponse createAccount (AccountRequest accountRequest) {
        if(accountRepository.existedAccountName(accountRequest.getAccount_name())){
            throw new AppException(AccountErrorCode.ACCOUNT_EXISTED);
        }

        Role role = roleRepository.findById(accountRequest.getRole_id())
                .orElseThrow(()-> new AppException(RoleErrorCode.ROLE_NOT_FOUND));


        Account account = accountMapper.toAccount(accountRequest);
        account.setRole(role);

        LocalDateTime localDateTime = LocalDateTime.now();
        account.setTime(localDateTime);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        account.setStatus(1);

        account.getEmployee().setStatus(1);
        account.getEmployee().setAccount(account);

        employeeRepository.save(account.getEmployee());

        return accountMapper.toGetAccountResponse(accountRepository.save(account));
    }

    @PreAuthorize("hasRole('UPDATE_ACCOUNT')")
    public GetAccountResponse updateEmployee (String url, EmployeeRequest employeeRequest) {
        Account account =  accountRepository.findById(StringUtils.getUUIDFromUrl(url)).orElseThrow(
                () -> new AppException(AccountErrorCode.ACCOUNT_NOT_FOUND));

        accountMapper.updateEmployee(account.getEmployee(),employeeRequest);

        employeeRepository.save(account.getEmployee());

        return accountMapper.toGetAccountResponse( account );

    }


    @PreAuthorize("hasRole('UPDATE_ACCOUNT')")
    public GetAccountResponse updateAccount (String url, AccountUpdateRequest accountUpdateRequest) {
        Account account =  accountRepository.findById(StringUtils.getUUIDFromUrl(url)).orElseThrow(
                () -> new AppException(AccountErrorCode.ACCOUNT_NOT_FOUND));

        System.out.println(accountUpdateRequest.getAccount_name());
        System.out.println(account.getAccount_name());
        if(accountRepository.existedAccountName(accountUpdateRequest.getAccount_name()) &&
                !accountUpdateRequest.getAccount_name().equals(account.getAccount_name())){
            throw new AppException(AccountErrorCode.ACCOUNT_EXISTED);
        }

        Role role = roleRepository.findById(accountUpdateRequest.getRole_id())
                .orElseThrow(()-> new AppException(RoleErrorCode.ROLE_NOT_FOUND));

        accountMapper.updateAccount(account,accountUpdateRequest);
        account.setRole(role);


//        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
//        ZonedDateTime currentTimeInZone = ZonedDateTime.now();
//        account.setTime(currentTimeInZone);

        if (accountUpdateRequest.getPassword() != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            account.setPassword(passwordEncoder.encode(accountUpdateRequest.getPassword()));
        }

//        account.getEmployee().setAccount(account);
//
//        employeeRepository.save(account.getEmployee());

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

    @PreAuthorize("hasRole('CHANGE_ACCOUNT_STATUS')")
    public  GetAccountResponse changeStatus (String account_id) {
        Account account =   accountRepository.findById(account_id).orElseThrow(
                () -> new AppException(AccountErrorCode.ACCOUNT_NOT_FOUND));

        if (account.getStatus() == 0) {
            account.setStatus(1);
            account.getRole().setStatus(1);
        } else {
            account.setStatus(0);
        }
        return accountMapper.toGetAccountResponse(accountRepository.save(account));
    }




}
