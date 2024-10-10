package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.response.history.HistoryResponse;
import com.tour.tour_management.entity.History;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

//history.getAccount().getAccount_name()
public interface HistoryMapper {
// mapping từ cái history ( gọi đối tượng account ) tới cái history response
    @Mapping(source = "account.account_name", target = "account_name")
    HistoryResponse toHistoryResponse(History history);
}
