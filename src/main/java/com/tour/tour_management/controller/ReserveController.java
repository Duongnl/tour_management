package com.tour.tour_management.controller;


import com.tour.tour_management.dto.request.reserve.ReserveRequests;
import com.tour.tour_management.dto.request.reserve.ReserveStatusRequest;
import com.tour.tour_management.dto.request.reserve.ReserveTourFilterRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.reserve.ReserveResponse;
import com.tour.tour_management.dto.response.reserve.ReserveTourResponse;
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

    @GetMapping("/booked/{url}")
    public  ApiResponse< List<ReserveResponse> > getReserves (@PathVariable String url) {
        return ApiResponse.< List<ReserveResponse> >builder()
                .result( reserveService.getReserves(url))
                .build();
    }

    @GetMapping("/booked/paid/{url}")
    public  ApiResponse< List<ReserveResponse> > getPaidReserves (@PathVariable String url) {
        return ApiResponse.< List<ReserveResponse> >builder()
                .result( reserveService.getPaidReserves(url))
                .build();
    }

    @GetMapping("/booked/unpaid/{url}")
    public  ApiResponse< List<ReserveResponse> > getUnpaidReserves (@PathVariable String url) {
        return ApiResponse.< List<ReserveResponse> >builder()
                .result( reserveService.getUnpaidReserves(url))
                .build();
    }

    @GetMapping("/booked/canceled/{url}")
    public  ApiResponse< List<ReserveResponse> > getCanceledReserves (@PathVariable String url) {
        return ApiResponse.< List<ReserveResponse> >builder()
                .result( reserveService.getCanceledReserves(url))
                .build();
    }



    @PostMapping("/reserve-tour")
    public ApiResponse<String>  reserveTour (@RequestBody @Valid ReserveRequests reserveRequests) {
       reserveService.reserveTour(reserveRequests);
        return ApiResponse.<String>builder()
                .build();
    }

    @PostMapping("/change-status-reserve")
    public  ApiResponse<ReserveResponse> changeStatusReserve (@RequestBody @Valid ReserveStatusRequest reserveStatusRequest) {
        return ApiResponse.<ReserveResponse >builder()
                .result( reserveService.changeStatusReserve(reserveStatusRequest))
                .build();
    }


}
