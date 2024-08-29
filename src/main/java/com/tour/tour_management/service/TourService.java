package com.tour.tour_management.service;

import com.tour.tour_management.dto.request.tour.TourCreateRequest;
import com.tour.tour_management.dto.request.tour.TourUpdateRequest;
import com.tour.tour_management.dto.request.tourtime.TourTimeCreateRequest;
import com.tour.tour_management.dto.request.tourtime.TourTimeUpdateRequest;
import com.tour.tour_management.dto.response.tour.TourResponse;
import com.tour.tour_management.dto.response.tour.TourDetailResponse;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.entity.TourTime;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CategoryErrorCode;
import com.tour.tour_management.exception.TourErrorCode;
import com.tour.tour_management.mapper.TourMapper;
import com.tour.tour_management.repository.AirlineRepository;
import com.tour.tour_management.repository.CategoryRepository;
import com.tour.tour_management.repository.TourRepository;
import com.tour.tour_management.repository.TourTimeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class TourService {
    TourRepository tourRepository;
    CategoryRepository categoryRepository;
    TourMapper tourMapper;
    AirlineRepository airlineRepository;
    TourTimeRepository tourTimeRepository;


    public List<TourResponse> getTours() {
        List<Tour> tourList = tourRepository.findAll();
        List<TourResponse> tourResponseList = new ArrayList<>();
        tourList.forEach(tour -> {
            tourResponseList.add(tourMapper.toTourResponse(tour));
        });
        return tourResponseList;
    }


    public TourDetailResponse getTour(int tour_id) {
        return tourMapper.toTourDetailResponse(tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND)));
    }

    public TourDetailResponse createTour(TourCreateRequest tourCreateRequest) {
        Category category = categoryRepository.findById(tourCreateRequest.getCategory_id())
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        Tour tour = tourMapper.toTour(tourCreateRequest);

        tour.setCategory(category);
        tour.setStatus(1);

        tour.getTourTimes().forEach(tourTime -> {
            tourTime.setTour(tour);
            tourTime.setStatus(1);
            tourTime.getDepartureAirline().setStatus(1);
            tourTime.getReturnAirline().setStatus(1);
            airlineRepository.save(tourTime.getDepartureAirline());
            airlineRepository.save(tourTime.getReturnAirline());

        });

        return tourMapper.toTourDetailResponse(tourRepository.save(tour));
    }

    public TourDetailResponse createTourTime(int tour_id, TourTimeCreateRequest tourTimeCreateRequest) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        TourTime tourTime = new TourTime();
        tourMapper.CreateTourTime(tourTime, tourTimeCreateRequest);
        tourTime.setTour(tour);
        tourTime.setStatus(1);

        tourTimeRepository.save(tourTime);
        tour.getTourTimes().add(tourTime);
        tourRepository.save(tour);

        return tourMapper.toTourDetailResponse(tour);
    }

    public TourDetailResponse updateTourTime(int tour_id, int tourtime_id, TourTimeUpdateRequest tourTimeUpdateRequest) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));


        Set<TourTime> tourTimes = tour.getTourTimes();

        AtomicBoolean tourTimeFound = new AtomicBoolean(false);
        tourTimes.forEach(tourTime -> {
            if (tourTime.getTour_time_id() == tourtime_id) {
                tourTime.setTour(tour);
                tourMapper.updateTourTime(tourTime, tourTimeUpdateRequest);
                tourTimeFound.set(true);
                tourTimeRepository.save(tourTime);
            }
        });
        if (!tourTimeFound.get()) {
            throw new AppException(TourErrorCode.TOUR_TOURTIME_NOT_FOUND);
        }
        return tourMapper.toTourDetailResponse(tour);
    }

    public TourDetailResponse updateTour(int tour_id, TourUpdateRequest tourUpdateRequest) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        Category category = categoryRepository.findById(tourUpdateRequest.getCategory_id())
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        tourMapper.updateTour(tour, tourUpdateRequest);
        tour.setCategory(category);

        return tourMapper.toTourDetailResponse(tourRepository.save(tour));
    }

    public TourDetailResponse deleteTour(int tour_id) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));
        tour.setStatus(0);
        return tourMapper.toTourDetailResponse(tourRepository.save(tour));
    }

    public List<TourResponse> getActiveTours() {
        Sort sort = Sort.by(Sort.Direction.DESC, "tour_id");

        List<Tour> tourList = tourRepository.findByStatusWithSorting(1, sort);
        List<TourResponse> tourResponseList = new ArrayList<>();

        tourList.forEach(tour -> {
            tourResponseList.add(tourMapper.toTourResponse(tour));
        });
        return tourResponseList;
    }

    public List<TourResponse> getDeletedTours() {
        Sort sort = Sort.by(Sort.Direction.DESC, "tour_id");

        List<Tour> tourList = tourRepository.findByStatusWithSorting(0, sort);
        List<TourResponse> tourResponseList = new ArrayList<>();

        tourList.forEach(tour -> {
            tourResponseList.add(tourMapper.toTourResponse(tour));
        });
        return tourResponseList;
    }

    public TourDetailResponse deleteTourTime(Integer tour_id, Integer tourtime_id) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        Set<TourTime> tourTimes = tour.getTourTimes();

        AtomicBoolean tourTimeFound = new AtomicBoolean(false);
        tourTimes.forEach(tourTime -> {
            if (tourTime.getTour_time_id() == tourtime_id) {
                tourTime.setTour(tour);
                tourTime.setStatus(0);
                tourTimeFound.set(true);
                tourTimeRepository.save(tourTime);
            }
        });
        if (!tourTimeFound.get()) {
            throw new AppException(TourErrorCode.TOUR_TOURTIME_NOT_FOUND);
        }
        return tourMapper.toTourDetailResponse(tour);
    }

    public TourDetailResponse changeStatusTour(Integer tour_id) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        if (tour.getStatus() == 0) {
            tour.setStatus(1);
        } else {
            tour.setStatus(0);
        }
        return tourMapper.toTourDetailResponse(tourRepository.save(tour));

    }

    public TourDetailResponse changeStatusTourTime(Integer tour_id,Integer tourtime_id) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        Set<TourTime> tourTimes = tour.getTourTimes();

        AtomicBoolean tourTimeFound = new AtomicBoolean(false);
        tourTimes.forEach(tourTime -> {
            if (tourTime.getTour_time_id() == tourtime_id) {
                tourTime.setTour(tour);
                if (tourTime.getStatus() == 0) {
                    tourTime.setStatus(1);
                } else {
                    tourTime.setStatus(0);
                }
                tourTimeFound.set(true);
                tourTimeRepository.save(tourTime);
            }
        });
        if (!tourTimeFound.get()) {
            throw new AppException(TourErrorCode.TOUR_TOURTIME_NOT_FOUND);
        }
        return tourMapper.toTourDetailResponse(tour);

    }
}
