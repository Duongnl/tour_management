package com.tour.tour_management.controller;

import com.tour.tour_management.dto.request.tour.TourCreateRequest;
import com.tour.tour_management.dto.request.tour.TourUpdateRequest;
import com.tour.tour_management.dto.request.tourtime.TourTimeRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.tour.TourResponse;
import com.tour.tour_management.dto.response.tour.TourDetailResponse;
import com.tour.tour_management.service.TourService;
import jakarta.validation.Valid;
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

    @GetMapping
    public ApiResponse<List<TourResponse>> getTours() {
       return ApiResponse.<List<TourResponse>>builder()
               .result( tourService.getTours())
               .build();
    }

    @GetMapping("/category/{category_id}")
    public ApiResponse<List<TourResponse>> getToursCategory(@PathVariable Integer category_id) {
       return ApiResponse.<List<TourResponse>>builder()
               .result( tourService.getToursCategory(category_id,-1))
               .build();
    }

    @GetMapping("/category/{category_id}/active")
    public ApiResponse<List<TourResponse>> getToursCategoryActive(@PathVariable Integer category_id) {
       return ApiResponse.<List<TourResponse>>builder()
               .result( tourService.getToursCategory(category_id,1))
               .build();
    }

    @GetMapping("/category/{category_id}/locked")
    public ApiResponse<List<TourResponse>> getToursCategoryLocked(@PathVariable Integer category_id) {
       return ApiResponse.<List<TourResponse>>builder()
               .result( tourService.getToursCategory(category_id,0))
               .build();
    }

    @GetMapping("/active")
    public  ApiResponse<List<TourResponse>> getActiveCustomers() {
        return ApiResponse.<List<TourResponse>>builder()
                .result( tourService.getActiveTours())
                .build();
    }

    @GetMapping("/locked")
    public  ApiResponse<List<TourResponse>> getLockedCustomers() {
        return ApiResponse.<List<TourResponse>>builder()
                .result( tourService.getLockedTours())
                .build();
    }

    @GetMapping("/{tour_id}")
    public ApiResponse<TourDetailResponse> getTour(@PathVariable Integer tour_id){
        return ApiResponse.<TourDetailResponse>builder()
                .result(tourService.getTour(tour_id))
                .build();
    }

    @PostMapping
    public ApiResponse<TourDetailResponse> createTour (@RequestBody @Valid TourCreateRequest tourCreateRequest) {
       return ApiResponse.<TourDetailResponse>builder()
               .result(tourService.createTour(tourCreateRequest))
               .build();

    }

    @PostMapping("/{tour_id}")
    public ApiResponse<TourDetailResponse> createTourTime (@PathVariable Integer tour_id, @RequestBody @Valid TourTimeRequest tourTimeRequest) {
       return ApiResponse.<TourDetailResponse>builder()
               .result(tourService.createTourTime(tour_id, tourTimeRequest))
               .build();

    }


    @PutMapping("/{tour_id}")
    public ApiResponse<TourDetailResponse> updateTour (@PathVariable Integer tour_id, @RequestBody @Valid TourUpdateRequest tourUpdateRequest) {
        return ApiResponse.<TourDetailResponse>builder()
                .result(tourService.updateTour(tour_id ,tourUpdateRequest))
                .build();
    }
    @PutMapping("/{tour_id}/{tourtime_id}")
    public ApiResponse<TourDetailResponse> updateTourTime (@PathVariable Integer tour_id,@PathVariable Integer tourtime_id, @RequestBody @Valid TourTimeRequest tourTimeRequest) {
        return ApiResponse.<TourDetailResponse>builder()
                .result(tourService.updateTourTime(tour_id,tourtime_id ,tourTimeRequest))
                .build();
    }

    @PutMapping ("/change-status/{tour_id}")
    public  ApiResponse<TourDetailResponse> changeStatusTour(@PathVariable Integer tour_id ) {
        return ApiResponse.<TourDetailResponse>builder()
                .result(tourService.changeStatusTour(tour_id))
                .build();
    }

    @PutMapping ("/change-status/{tour_id}/{tourtime_id}")
    public  ApiResponse<TourDetailResponse> changeStatusTourTime(@PathVariable Integer tour_id,@PathVariable Integer tourtime_id ) {
        return ApiResponse.<TourDetailResponse>builder()
                .result(tourService.changeStatusTourTime(tour_id,tourtime_id))
                .build();
    }



}
