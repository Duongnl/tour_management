package com.tour.tour_management.service;

import com.tour.tour_management.dto.request.TourRequest;
import com.tour.tour_management.dto.request.TourUpdateRequest;
import com.tour.tour_management.dto.response.TourResponse;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CategoryErrorCode;
import com.tour.tour_management.exception.TourErrorCode;
import com.tour.tour_management.mapper.TourMapper;
import com.tour.tour_management.repository.CategoryRepository;
import com.tour.tour_management.repository.TourRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class TourService {
    TourRepository tourRepository;
    CategoryRepository categoryRepository;
    TourMapper tourMapper;

    public List<TourResponse> getAll(){
        List<Tour> tourList = tourRepository.findAll();
        List<TourResponse> tourResponseList = new ArrayList<>();
        tourList.forEach( tour ->{tourResponseList.add(tourMapper.toTourResponse(tour));});

        tourResponseList.forEach(tour1 -> System.out.println(tour1.getTour_name()));
        return tourResponseList;
    }

    public TourResponse getTour(String tour_id){
        return tourMapper.toTourResponse(tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND)));
    }

    public TourResponse createTour(TourRequest tourRequest) {
        Category category = categoryRepository.findById(tourRequest.getCategory_id())
                .orElseThrow(()-> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        Tour tour = tourMapper.toTour(tourRequest);
        tour.setCategory(category);
        return  tourMapper.toTourResponse(tourRepository.save(tour));
    }

    public TourResponse updateTour(String tour_id, TourUpdateRequest tourUpdateRequest){

        int quantity = tourUpdateRequest.getQuantity_sell() + tourUpdateRequest.getQuantity_reserve();
        if (tourUpdateRequest.getQuantity() < quantity){
            throw  new AppException(TourErrorCode.QUANTITY_ERROR);
        }

        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        Category category = categoryRepository.findById(tourUpdateRequest.getCategory_id())
                .orElseThrow(()-> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));

        tourMapper.updateTour(tour, tourUpdateRequest);
        tour.setCategory(category);
        tour.setQuantity_left(
                tour.getQuantity() - (tour.getQuantity_sell() + tour.getQuantity_reserve())
        );

        return tourMapper.toTourResponse(tourRepository.save(tour));
    }

    public void deleteTour(String tour_id){
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(()-> new AppException(TourErrorCode.TOUR_NOT_FOUND));
        tourRepository.delete(tour);
    }

}
