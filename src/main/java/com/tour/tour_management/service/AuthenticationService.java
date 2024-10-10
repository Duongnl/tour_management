package com.tour.tour_management.service;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tour.tour_management.dto.request.AuthenticationRequest;
import com.tour.tour_management.dto.request.IntrospectRequest;
import com.tour.tour_management.dto.response.AuthenticationResponse;
import com.tour.tour_management.dto.response.IntrospectResponse;
import com.tour.tour_management.entity.Account;
import com.tour.tour_management.exception.AccountErrorCode;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.mapper.RoleMapper;
import com.tour.tour_management.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

// kiem tra mat khau tai khoan, neu dung thi ra token
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthenticationService {

    AccountRepository accountRepository;

    @NonFinal// de no kh inject vao contructe cua clombok
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY ;

    RoleMapper roleMapper;

// kiem tra xem token co hop le khong
    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

//        token cua  ng dung
        SignedJWT signedJWT = SignedJWT.parse(token);

//        lay ra han cua token
        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();


//        tra ve true hoac flase
        var verified =  signedJWT.verify(verifier);

//         tra ve introspectresponse
        return  IntrospectResponse.builder()
                .valid( verified && expityTime.after(new Date()))
                .build();
    }

//    kiem tra mk tk neu dung thi cho ra token
    public AuthenticationResponse authenticate (AuthenticationRequest authenticationRequest) {
//        tim kiem account name
            Account account = accountRepository.findByAccountName(authenticationRequest.getAccount_name())
                .orElseThrow(() -> new AppException(AccountErrorCode.ACCOUNT_NOT_FOUND));

//       so sanh password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated =  passwordEncoder.matches(authenticationRequest.getPassword(),
                account.getPassword());

//        neu password sai
        if (!authenticated) {
            throw new AppException(AccountErrorCode.UNAUTHENTICATED);
        } // neu password dung
        else  {

            if (account.getStatus() != 1 ){
                throw new AppException(AccountErrorCode.ACCOUNT_LOCKED);
            }


            String token = generateToken(account);


           return AuthenticationResponse.builder()
                   .token(token)
                   .authenticated(true)
                   .build();
        }
    }

//    tao token
    public String generateToken (Account account) {
        //        tao header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        //      tao body
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getAccount_name())
                .issuer("tour_management.com") // token nay dc issuer tu ai
                .issueTime(new Date()) // thoi diem hien tai
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS)
                                .toEpochMilli())) // thoi han cua token, het han sau 1h
                .claim("scope",builtScope(account) )
                .build();

        //      tao pay load
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        //        built thong tin token
        JWSObject jwsObject = new JWSObject(header,payload);

        //      ky token
        try {
//            hash content nay
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
//            tra ve kieu string
            return jwsObject.serialize();
        }catch (JOSEException e){
//            log.error("Cannnot create token",e);
            throw new RuntimeException(e);
        }
    }

//    built cái scope permission cho token
    private String builtScope (Account account) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(account.getRole().getPermissions())) {
            account.getRole().getPermissions().forEach(permission -> {
                stringJoiner.add(permission.getPermission_id());
            });
        }
        return stringJoiner.toString();
    }

    public Account getAuthenticatedAccount() {

        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Account account = accountRepository.findByAccountName(name).orElseThrow(
                () -> new AppException(AccountErrorCode.ACCOUNT_NOT_FOUND));
        if (account.getStatus() != 1) {
            throw new AppException(AccountErrorCode.ACCOUNT_LOCKED);
        }

        return account;
    }
}
