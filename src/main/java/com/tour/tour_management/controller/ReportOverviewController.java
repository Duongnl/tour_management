package com.tour.tour_management.controller;


import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.overview.ReportPerEmployeeInYearResponse;
import com.tour.tour_management.dto.response.overview.ReportPerMonthResponse;
import com.tour.tour_management.service.OverviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/overview")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportOverviewController {

    OverviewService overviewService;

    @GetMapping("/report/commission")
    public ApiResponse<List<ReportPerEmployeeInYearResponse>> getReportCommissionEmployee(@RequestParam(value = "year", required = false) Integer year) {
        return ApiResponse.<List<ReportPerEmployeeInYearResponse>>builder().result(overviewService.reportCommissionEmployee(year)).build();
    }

    @GetMapping("/report/sale")
    public ApiResponse<List<ReportPerEmployeeInYearResponse>> getReportSaleEmployee(@RequestParam(value = "year", required = false) Integer year) {
        return ApiResponse.<List<ReportPerEmployeeInYearResponse>>builder().result(overviewService.reportSaleEmployee(year)).build();
    }

    @GetMapping("/sale")
    public ApiResponse<List<ReportPerMonthResponse>> getSale(@RequestParam(value = "year", required = false) Integer year,
                                                             @RequestParam(value = "from_month", required = false) Integer fromMonth,
                                                             @RequestParam(value = "to_month", required = false) Integer toMonth) {
        return ApiResponse.<List<ReportPerMonthResponse>>builder().result(overviewService.reportSale(year, fromMonth, toMonth)).build();
    }

    @GetMapping("/commission")
    public ApiResponse<List<ReportPerMonthResponse>> getCommission(@RequestParam(value = "year", required = false) Integer year,
                                                                   @RequestParam(value = "from_month", required = false) Integer fromMonth,
                                                                   @RequestParam(value = "to_month", required = false) Integer toMonth) {
        return ApiResponse.<List<ReportPerMonthResponse>>builder().result(overviewService.reportCommission(year, fromMonth, toMonth)).build();
    }

}
