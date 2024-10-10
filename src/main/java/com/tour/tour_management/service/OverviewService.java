package com.tour.tour_management.service;

import com.tour.tour_management.dto.response.overview.ReportPerEmployeeInYearResponse;
import com.tour.tour_management.dto.response.overview.ReportPerMonthResponse;
import com.tour.tour_management.mapper.OverviewMapper;
import com.tour.tour_management.repository.OverviewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.Month;


import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class OverviewService {

    OverviewRepository overviewRepository;
    OverviewMapper overviewMapper;

    @PreAuthorize("hasRole('ACCESS_DASHBOARD')")
    public List<ReportPerEmployeeInYearResponse> reportCommissionEmployee(Integer year) {
        int yearFinal = (year == null) ? Year.now().getValue() : year;
        return overviewMapper.mapTupleToReportPerEmployeeInYearResponse(overviewRepository.getReportCommissionPerEmployeeAsTuple(yearFinal), yearFinal);
    }

    @PreAuthorize("hasRole('ACCESS_DASHBOARD')")
    public List<ReportPerEmployeeInYearResponse> reportSaleEmployee(Integer year) {
        int yearFinal = (year == null) ? Year.now().getValue() : year;
        return overviewMapper.mapTupleToReportPerEmployeeInYearResponse(overviewRepository.getReportSalePerEmployeeAsTuple(yearFinal), yearFinal);
    }

    @PreAuthorize("hasRole('ACCESS_DASHBOARD')")
    public List<ReportPerMonthResponse> reportSale(Integer year, Integer fromMonth, Integer toMonth) {
        int yearFinal = filterYear(year);
        int fromMonthFinal = filterMonth(fromMonth, true);
        int toMonthFinal = filterMonth(toMonth, false);

        return overviewMapper.mapTupleToReportPerMonthResponse(overviewRepository.getReportSaleAsTuple(yearFinal, fromMonthFinal, toMonthFinal), yearFinal, fromMonthFinal,toMonthFinal);
    }

    @PreAuthorize("hasRole('ACCESS_DASHBOARD')")
    public List<ReportPerMonthResponse> reportCommission(Integer year, Integer fromMonth, Integer toMonth) {
        int yearFinal = filterYear(year);
        int fromMonthFinal = filterMonth(fromMonth, true);
        int toMonthFinal = filterMonth(toMonth, false);
        if (fromMonthFinal > toMonthFinal) {
            throw new IllegalArgumentException("From month cannot be greater than to month.");
        }
        return overviewMapper.mapTupleToReportPerMonthResponse(
                overviewRepository.getReportCommissionAsTuple(yearFinal, fromMonthFinal, toMonthFinal), yearFinal, fromMonthFinal,toMonthFinal
        );
    }

    private int filterYear(Integer year) {
        return (year == null) ? Year.now().getValue() : year;
    }

    private int filterMonth(Integer month, boolean isFromMonth) {
        if (month == null) {
            if (isFromMonth) {
                return Month.from(LocalDate.now().minusMonths(3)).getValue();
            } else {
                return Month.from(LocalDate.now()).getValue();
            }
        } else if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12.");
        }
        return month;
    }
}