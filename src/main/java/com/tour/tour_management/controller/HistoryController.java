package com.tour.tour_management.controller;

import com.tour.tour_management.dto.request.role.RoleRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.history.HistoryResponse;
import com.tour.tour_management.service.HistoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import com.tour.tour_management.dto.request.history.HistoryDateRequest;

import java.util.List;

@RestController // khai bao controller
@RequestMapping("/history") // api mac dinh, de kh can khai bao o funtion
// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HistoryController {

    HistoryService historyService;

    @GetMapping
    public ApiResponse<List<HistoryResponse>> getHistories()
    {
        return ApiResponse.<List<HistoryResponse>>builder()
                .result(historyService.getHistories())
                .build();
    }

    @GetMapping("/order-by-time")
    public ApiResponse<List<HistoryResponse>> getHistoresOrderByTime(){
        return ApiResponse.<List<HistoryResponse>>builder()
                .result(historyService.getHistoriesOrderedByDateTime())
                .build();
    }

    @PostMapping("/find-history-byTime")
    public ApiResponse<List<HistoryResponse>> getHistoriesByDate(@RequestBody @Valid HistoryDateRequest historyDateRequest)
    {
        return ApiResponse.<List<HistoryResponse>>builder()
                .result(historyService.getHistoriesByDate(historyDateRequest))
                .build();
    }


}
