package com.tour.tour_management.service;

import com.tour.tour_management.dto.request.history.HistoryDateRequest;
import com.tour.tour_management.dto.response.history.HistoryResponse;
import com.tour.tour_management.entity.History;
import com.tour.tour_management.mapper.HistoryMapper;
import com.tour.tour_management.repository.HistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class HistoryService {
    // khai báo cái kho chứ dữ liệu history
    HistoryRepository historyRepository;
    // khai báo chức năng đổi từ entity history sang Historyreponse
    HistoryMapper historyMapper;

    public List<HistoryResponse> getHistories()
    {
        // lấy tất cả thằng history
        List<History> histories =  historyRepository.findAll();
        // khai báo mảng historyResponse
        List<HistoryResponse> historyResponses = new ArrayList<>();
        // lặp mảng historties
        histories.forEach(history ->{
            System.out.println(history.getAccount().getAccount_name());
            // add vào List historyResponeses
            historyResponses.add(
                    // Chuyển đổi từ entiry history sang HistoryResponse
                    historyMapper.toHistoryResponse(history));
        } );
        // chả về List historyResponses

        return historyResponses;
    }

    public List<HistoryResponse> getHistoriesByDate(HistoryDateRequest historyDateRequest)
    {
        List<History> histories =  historyRepository.findHistoryByTime(historyDateRequest.getStartTime() , historyDateRequest.getEndTime() );

        List<HistoryResponse> historyResponses = new ArrayList<>();
        // lặp mảng historties
        histories.forEach(history ->{
//            System.out.println(history.getAccount().getAccount_name());
            // add vào List historyResponeses
            historyResponses.add(
                    // Chuyển đổi từ entiry history sang HistoryResponse
                    historyMapper.toHistoryResponse(history));
        } );
        // chả về List historyResponses

        return historyResponses;

    }
}
