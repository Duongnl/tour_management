package com.tour.tour_management.controller;


import com.tour.tour_management.dto.request.airline.AirlineCreateRequest;
import com.tour.tour_management.dto.response.AirlineResponse;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.service.AirlineService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // khai bao controller
@RequestMapping("/airline") // api mac dinh, de kh can khai bao o funtion
// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AirlineController {
    AirlineService airlineService;

    @GetMapping
    public ApiResponse<List<AirlineResponse>> getAirlines (){
        return ApiResponse.<List<AirlineResponse>>builder()
                .result(airlineService.getAirlines())
                .build();
    }

    @GetMapping("/active")
    public ApiResponse<List<AirlineResponse>> getAirlinesActive (){
        return ApiResponse.<List<AirlineResponse>>builder()
                .result(airlineService.getAirlines(1))
                .build();
    }@GetMapping("/locked")
    public ApiResponse<List<AirlineResponse>> getAirlinesLocked (){
        return ApiResponse.<List<AirlineResponse>>builder()
                .result(airlineService.getAirlines(0))
                .build();
    }

    @PostMapping
    public ApiResponse<AirlineResponse> createAirline(@RequestBody @Valid AirlineCreateRequest airlineCreateRequest){
        return ApiResponse.<AirlineResponse>builder()
                .result(airlineService.createAirline(airlineCreateRequest))
                .build();
    }

    @PutMapping("/{airline_id}")
    public ApiResponse<AirlineResponse> updateAirline(@PathVariable Integer airline_id,
                                                  @RequestBody @Valid AirlineCreateRequest airlineCreateRequest){
        return ApiResponse.<AirlineResponse>builder()
                .result(airlineService.updateAirline(airline_id, airlineCreateRequest))
                .build();
    }

    @DeleteMapping ("/{airline_id}")
    public ApiResponse deleteAirline(@PathVariable Integer airline_id) {
        airlineService.deleteAirline(airline_id);
        return ApiResponse.builder().build();
    }


}
