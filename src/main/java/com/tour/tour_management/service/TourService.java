package com.tour.tour_management.service;

import com.tour.tour_management.dto.request.tour.TourCreateRequest;
import com.tour.tour_management.dto.request.tour.TourUpdateRequest;
import com.tour.tour_management.dto.response.tour.GetTourResponse;
import com.tour.tour_management.dto.response.tour.TourResponse;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CategoryErrorCode;
import com.tour.tour_management.exception.TourErrorCode;
import com.tour.tour_management.mapper.TourMapper;
import com.tour.tour_management.repository.AirlineRepository;
import com.tour.tour_management.repository.CategoryRepository;
import com.tour.tour_management.repository.TourRepository;
import jakarta.transaction.Transactional;
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
    AirlineRepository airlineRepository;


    public List<TourResponse> getTours(){
        List<Tour> tourList = tourRepository.findAll();
        List<TourResponse> tourResponseList = new ArrayList<>();
        tourList.forEach( tour ->{
                tourResponseList.add(tourMapper.toTourResponse(tour));
        });
        return tourResponseList;
    }

    public List<TourResponse> getDeletedTours(){
        List<Tour> tourList =  tourRepository.findByStatus(0);
        List<TourResponse> tourResponseList = new ArrayList<>();
        tourList.forEach( tour ->{
                tourResponseList.add(tourMapper.toTourResponse(tour));
        });
        return tourResponseList;
    }

    public GetTourResponse getTour(int tour_id){
        return tourMapper.toGetTourResponse(tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND)));
    }


    @Transactional
    public TourResponse createTour(TourCreateRequest tourCreateRequest) {
        Category category = categoryRepository.findById(tourCreateRequest.getCategory_id())
                .orElseThrow(()-> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        Tour tour = tourMapper.toTour(tourCreateRequest);

        tour.setCategory(category);
        tour.setStatus(1);

        tour.getTourTimes().forEach(tourTime -> {
            tourTime.setTour(tour);
            tourTime.getDepartureAirline().setStatus(1);
            tourTime.getReturnAirline().setStatus(1);
            airlineRepository.save(tourTime.getDepartureAirline());
            airlineRepository.save(tourTime.getReturnAirline());

        });


        return  tourMapper.toTourResponse(tourRepository.save(tour));
    }

    public TourResponse updateTour(int tour_id, TourUpdateRequest tourUpdateRequest){

//        int quantity = tourUpdateRequest.getQuantity_sell() + tourUpdateRequest.getQuantity_reserve();
//        if (tourUpdateRequest.getQuantity() < quantity){
//            throw  new AppException(TourErrorCode.QUANTITY_ERROR);
//        }

        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        Category category = categoryRepository.findById(tourUpdateRequest.getCategory_id())
                .orElseThrow(()-> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));

        tourMapper.updateTour(tour, tourUpdateRequest);
        tour.setCategory(category);

        tour.getTourTimes().forEach(tourTime -> {
            tourTime.setTour(tour);
            tourTime.getDepartureAirline().setStatus(1);
            tourTime.getReturnAirline().setStatus(1);
            airlineRepository.save(tourTime.getDepartureAirline());
            airlineRepository.save(tourTime.getReturnAirline());

        });
//        tour.setQuantity_left(
//                tour.getQuantity() - (tour.getQuantity_sell() + tour.getQuantity_reserve())
//        );

        return tourMapper.toTourResponse(tourRepository.save(tour));
    }

    public void deleteTour(int tour_id){
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(()-> new AppException(TourErrorCode.TOUR_NOT_FOUND));
        tourRepository.delete(tour);
    }

}
