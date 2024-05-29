package com.tour.tour_management.controller;

import com.tour.tour_management.dto.request.TourRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.TourResponse;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.mapper.TourMapper;
import com.tour.tour_management.service.TourService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // khai bao controller
@RequestMapping("/tour") // api mac dinh, de kh can khai bao o funtion
// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourController {
    TourService tourService;
    TourMapper tourMapper;

    @GetMapping
    public ApiResponse<List<TourResponse>> getAll() {
       return ApiResponse.<List<TourResponse>>builder()
               .result( tourService.getAll())
               .build();
    }

    @PostMapping
    public ApiResponse<TourResponse> createTour (@RequestBody TourRequest tourRequest) {
       return ApiResponse.<TourResponse>builder()
               .result(tourService.createTour(tourRequest))
               .build();

    }

}
