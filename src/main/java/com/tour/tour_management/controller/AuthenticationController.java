package com.tour.tour_management.controller;


import com.nimbusds.jose.JOSEException;
import com.tour.tour_management.dto.request.AuthenticationRequest;
import com.tour.tour_management.dto.request.IntrospectRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.AuthenticationResponse;
import com.tour.tour_management.dto.response.IntrospectResponse;
import com.tour.tour_management.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

// class nay se kiem tra mk tk, neu dung thi tao ra token
@RestController // khai bao controller
@RequestMapping("/auth") // api mac dinh, de kh can khai bao o funtion
// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

//    dang nhap, neu dung thi tao ra token
    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate (@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        System.out.println("hello");
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(authenticationRequest))
                .build();

    }

//    kiem tra token xem co hop le khong
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result =  authenticationService.introspect(request); // tra ve tru thi dung, false thi sai
//        truyen result ben tren vao aauthenticationrespone r truyen  authenticationresponse vao result cua ApiResponse
//        kieu khoi tao apirespone voi  result la result ben tren
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }




}
