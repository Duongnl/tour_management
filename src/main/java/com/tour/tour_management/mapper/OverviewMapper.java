package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.response.overview.ReportPerEmployeeInYearResponse;
import com.tour.tour_management.dto.response.overview.ReportPerMonthResponse;
import jakarta.persistence.Tuple;
import org.mapstruct.Mapper;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Mapper(componentModel = "spring")
public interface OverviewMapper {
    default List<ReportPerEmployeeInYearResponse> mapTupleToReportPerEmployeeInYearResponse(List<Tuple> tuples, Integer year) {
        return tuples.stream().map(tuple -> mapTupleToReportPerEmployeeInYearResponse(tuple, year)).collect(Collectors.toList());
    }

    // Phương thức để ánh xạ từng Tuple thành ReportEmployeeResponse
    default ReportPerEmployeeInYearResponse mapTupleToReportPerEmployeeInYearResponse(Tuple tuple, Integer year) {
        Long[] months = new Long[12];  // Mảng chứa các giá trị ngày
        for (int i = 1; i <= 12; i++) {
            months[i - 1] = tuple.get("Month" + i, Long.class);  // Lấy giá trị từ Tuple
        }
        return new ReportPerEmployeeInYearResponse(tuple.get("employeeName", String.class), months, tuple.get("Total", Long.class), year.toString()

        );
    }

    default List<ReportPerMonthResponse> mapTupleToReportPerMonthResponse(List<Tuple> tuples, Integer year, Integer fromMonth, Integer toMonth) {
        return IntStream.range(0, toMonth - fromMonth + 1).mapToObj(index -> {
            int currentMonth = fromMonth + index;

            Tuple matchingTuple = tuples.stream().filter(tuple -> currentMonth == tuple.get("Month", Integer.class)).findFirst().orElse(null);

            return mapTupleToReportPerMonthResponse(matchingTuple, year, currentMonth);
        }).collect(Collectors.toList());
    }

    // Ánh xạ từng Tuple thành ReportPerMonthResponse cho từng tháng
    default ReportPerMonthResponse mapTupleToReportPerMonthResponse(Tuple tuple, Integer year, Integer month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();

        Long[] days = new Long[daysInMonth];  // Mảng chứa các giá trị ngày

        // Nếu Tuple không có dữ liệu, gán giá trị mặc định là null
        if (tuple != null) {

            for (int i = 1; i <= daysInMonth; i++) {
                days[i - 1] = tuple.get("Day" + i, Long.class);  // Lấy giá trị từ Tuple
            }
            return new ReportPerMonthResponse(days, tuple.get("Total", Long.class),
                    String.valueOf(month), year.toString());


        }
        else {
            Arrays.fill(days, 0L);
            return new ReportPerMonthResponse(days, 0L,
                    String.valueOf(month), year.toString());
        }
    }
}
