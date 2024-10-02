package com.tour.tour_management.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.tour.tour_management.dto.request.reserve.ReserveRequests;
import com.tour.tour_management.dto.request.reserve.ReserveTourFilterRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.category.CategoryResponse;
import com.tour.tour_management.dto.response.reserve.ReserveTourResponse;
import com.tour.tour_management.entity.Reserve;
import com.tour.tour_management.service.ReserveService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // khai bao controller
@RequestMapping("/reserve") // api mac dinh, de kh can khai bao o funtion
// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReserveController {
    ReserveService reserveService;

    @GetMapping("/tour")
    public ApiResponse<List<ReserveTourResponse>> getReserveTour () {
        return ApiResponse.<List<ReserveTourResponse>>builder()
                .result(reserveService.getReserveTours())
                .build();
    }

    @PostMapping("/filter-reserve-tour")
    public ApiResponse<List<ReserveTourResponse>> filterReserveTour (@RequestBody @Valid ReserveTourFilterRequest reserveTourFilterRequest) {
        System.out.println("Filter : " + reserveTourFilterRequest);
        return ApiResponse.<List<ReserveTourResponse>>builder()
                .result(reserveService.filterReserveTour(reserveTourFilterRequest))
                .build();
    }

    @GetMapping("/{url}")
    public  ApiResponse<ReserveTourResponse> getReserveTour(@PathVariable String url) {
        return ApiResponse.<ReserveTourResponse>builder()
                .result( reserveService.getReserveTour(url))
                .build();
    }

    @PostMapping("/reserve-tour")
    public ApiResponse<String>  reserveTour (@RequestBody @Valid ReserveRequests reserveRequests) {
        System.out.println("Đã vào");
       reserveService.reserveTour(reserveRequests);
        return ApiResponse.<String>builder()
                .build();
    }


}
