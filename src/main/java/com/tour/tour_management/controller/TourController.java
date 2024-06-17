package com.tour.tour_management.controller;

import com.tour.tour_management.dto.request.tour.TourCreateRequest;
import com.tour.tour_management.dto.request.tour.TourUpdateRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.tour.GetTourResponse;
import com.tour.tour_management.dto.response.tour.TourResponse;
import com.tour.tour_management.mapper.TourMapper;
import com.tour.tour_management.repository.CategoryRepository;
import com.tour.tour_management.service.TourService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public ApiResponse<List<TourResponse>> getTours() {
       return ApiResponse.<List<TourResponse>>builder()
               .result( tourService.getTours())
               .build();
    }

    @GetMapping("/getDeletedTours")
    public ApiResponse<List<TourResponse>> getDeletedTours() {
        return ApiResponse.<List<TourResponse>>builder()
                .result( tourService.getDeletedTours())
                .build();
    }

    @GetMapping("/{tour_id}")
    public ApiResponse<GetTourResponse> getTour(@PathVariable Integer tour_id){
        return ApiResponse.<GetTourResponse>builder()
                .result(tourService.getTour(tour_id))
                .build();
    }


    @PostMapping
    public ApiResponse<TourResponse> createTour (@RequestBody @Valid TourCreateRequest tourCreateRequest) {
       return ApiResponse.<TourResponse>builder()
               .result(tourService.createTour(tourCreateRequest))
               .build();

    }


    @PutMapping("/{tour_id}")
    public ApiResponse<TourResponse> updateTour (@PathVariable Integer tour_id, @RequestBody @Valid TourUpdateRequest tourUpdateRequest) {
        return ApiResponse.<TourResponse>builder()
                .result(tourService.updateTour(tour_id ,tourUpdateRequest))
                .build();
    }

    @DeleteMapping("/{tour_id}")
    public ApiResponse deleteTour (@PathVariable Integer tour_id){
        tourService.deleteTour(tour_id);
        return ApiResponse.builder()
                .build();
    }



}
