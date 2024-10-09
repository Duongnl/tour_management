package com.tour.tour_management.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.tour.tour_management.dto.request.tour.TourCreateRequest;
import com.tour.tour_management.dto.request.tour.TourUpdateRequest;
import com.tour.tour_management.dto.request.tourtime.TourTimeRequest;
import com.tour.tour_management.dto.response.tour.TourDetailResponse;
import com.tour.tour_management.dto.response.tour.TourResponse;
import com.tour.tour_management.entity.Airline;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.entity.TourTime;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CategoryErrorCode;
import com.tour.tour_management.exception.TourErrorCode;
import com.tour.tour_management.exception.TourTimeErrorCode;
import com.tour.tour_management.mapper.TourMapper;
import com.tour.tour_management.repository.AirlineRepository;
import com.tour.tour_management.repository.CategoryRepository;
import com.tour.tour_management.repository.TourRepository;
import com.tour.tour_management.repository.TourTimeRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

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
    HistoryService historyService;

    @PreAuthorize("hasRole('ACCESS_TOUR')")
    public TourDetailResponse getTour(int tour_id) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));
        return tourMapper.toTourDetailResponse(tourSorted(tour));
    }

    @PreAuthorize("hasRole('ACCESS_TOUR')")
    public List<TourResponse> getTours() {
        List<Tour> tourList = tourRepository.findAll();
        List<TourResponse> tourResponseList = new ArrayList<>();
        tourList.forEach(tour -> {
            tourResponseList.add(tourMapper.toTourResponse(tourSorted(tour)));
        });
        return tourResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_TOUR')")
    public List<TourResponse> getToursCategory(Integer category_id, int active) {
        List<Tour> tourList = tourRepository.findByCategoryId(category_id);
        List<TourResponse> tourResponseList = new ArrayList<>();
        tourList.forEach(tour -> {
            if (active == -1)
                tourResponseList.add(tourMapper.toTourResponse(tourSorted(tour)));
            else if (tour.getStatus() == active) {
                tourResponseList.add(tourMapper.toTourResponse(tourSorted(tour)));
            }
        });
        return tourResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_TOUR')")
    public List<TourResponse> getActiveTours() {
        Sort sort = Sort.by(Sort.Direction.DESC, "tour_id");

        List<Tour> tourList = tourRepository.findByStatusWithSorting(1, sort);
        List<TourResponse> tourResponseList = new ArrayList<>();

        tourList.forEach(tour -> {
            tourResponseList.add(tourMapper.toTourResponse(tourSorted(tour)));
        });
        return tourResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_TOUR')")
    public List<TourResponse> getLockedTours() {
        Sort sort = Sort.by(Sort.Direction.DESC, "tour_id");

        List<Tour> tourList = tourRepository.findByStatusWithSorting(0, sort);
        List<TourResponse> tourResponseList = new ArrayList<>();

        tourList.forEach(tour -> {
            tourResponseList.add(tourMapper.toTourResponse(tourSorted(tour)));
        });
        return tourResponseList;
    }

    @PreAuthorize("hasRole('CREATE_TOUR')")
    public TourDetailResponse createTour(TourCreateRequest tourCreateRequest) {
        Category category = categoryRepository.findById(tourCreateRequest.getCategory_id())
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        Tour tour = tourMapper.toTour(tourCreateRequest);
        tour.setCategory(category);
        tour.setStatus(1);

        Set<TourTime> tourTimes = new HashSet<>();

        tourCreateRequest.getTourTimes().forEach(tourTimeRequest -> {
            TourTime tourTime = tourMapper.toTourTime(tourTimeRequest);

            tourTime.setTour(tour);
            tourTime.setStatus(1);

            if (tourTimeRequest.getReturn_airline_id() != null) {
                Airline airline = null;
                airline = airlineRepository.findById(tourTimeRequest.getReturn_airline_id()).orElseThrow(
                        () -> new AppException(TourErrorCode.TOUR_NOT_FOUND)
                );
                tourTime.setReturnAirline(airline);
            } else {
                tourTime.setReturnAirline(null);
            }
            if (tourTimeRequest.getDeparture_airline_id() != null) {
                Airline airline = null;
                airline = airlineRepository.findById(tourTimeRequest.getDeparture_airline_id()).orElseThrow(
                        () -> new AppException(TourErrorCode.TOUR_NOT_FOUND)
                );
                tourTime.setDepartureAirline(airline);
            } else {
                tourTime.setDepartureAirline(null);
            }

            tourTimes.add(tourTime);
        });
        tour.setTourTimes(tourTimes);
        Tour tourSaved =tourRepository.save(tour);
        historyService.createHistory("Create tour " + tourSaved.getTour_name());
        return tourMapper.toTourDetailResponse((tourSorted(tourSaved)));
    }


     Tour tourSorted(@NotNull Tour tour) {
        tour.setTourTimes(
                tourTimeRepository.findAllOrderedByTourId(
                        tour.getTour_id()
                )
        );
        return tour;
    }

    @PreAuthorize("hasRole('CREATE_TOUR')")
    public TourDetailResponse createTourTime(int tour_id, TourTimeRequest tourTimeRequest) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        TourTime tourTime = new TourTime();
        tourMapper.CreateTourTime(tourTime, tourTimeRequest);
        tourTime.setTour(tour);
        tourTime.setStatus(1);

        if (tourTimeRequest.getReturn_airline_id() != null) {
            Airline airline = null;
            airline = airlineRepository.findById(tourTimeRequest.getReturn_airline_id()).orElseThrow(
                    () -> new AppException(TourErrorCode.TOUR_NOT_FOUND)
            );
            tourTime.setReturnAirline(airline);
        } else {
            tourTime.setReturnAirline(null);
        }

        if (tourTimeRequest.getDeparture_airline_id() != null) {
            Airline airline = null;
            airline = airlineRepository.findById(tourTimeRequest.getDeparture_airline_id()).orElseThrow(
                    () -> new AppException(TourErrorCode.TOUR_NOT_FOUND)
            );
            tourTime.setDepartureAirline(airline);
        } else {
            tourTime.setDepartureAirline(null);
        }

        tourTimeRepository.save(tourTime);
        tour.getTourTimes().add(tourTime);
        Tour tourSaved=tourRepository.save(tour);
        historyService.createHistory("Create tour time: tour "+tour_id+" tour time " + tourTime.getTime_name());
        return tourMapper.toTourDetailResponse(tourSaved);
    }

    @PreAuthorize("hasRole('UPDATE_TOUR')")
    public TourDetailResponse updateTourTime(int tour_id, int tourTime_id, TourTimeRequest tourTimeRequest) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));
        TourTime tourTimeOld=new TourTime();
        TourTime tourTimeSaved = new TourTime();

        for (TourTime tourTime : tour.getTourTimes()) {
            if (tourTime.getTour_time_id().equals(tourTime_id)) {
                tourTimeOld = tourMapper.copy(tourTime);
                tourMapper.CreateTourTime(tourTime, tourTimeRequest);
                tourTime.setTour(tour);
                tourTimeSaved=tourTimeRepository.save(tourTime);

                break;
            }
        }
        if (tourTimeSaved.getTour_time_id()!=tourTime_id) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }
        historyService.createHistory(getTourTimeChangedString(tourTimeOld,tourTimeSaved));
        return tourMapper.toTourDetailResponse(tour);
    }

    @PreAuthorize("hasRole('UPDATE_TOUR')")
    public TourDetailResponse updateTour(int tour_id, TourUpdateRequest tourUpdateRequest) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        Tour tourOld=tourMapper.copy(tour);

        Category category = categoryRepository.findById(tourUpdateRequest.getCategory_id())
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));

        tour.setCategory(category);
        tourMapper.updateTour(tour, tourUpdateRequest);
        Tour tourSaved=tourRepository.save(tour);

        historyService.createHistory(getTourChangedString(tourOld,tourSaved));

        return tourMapper.toTourDetailResponse(tourSorted(tourSaved));
    }
    private static @NotNull String getTourChangedString(Tour tour, Tour tourSaved) {
        StringBuilder tourChangedString = new StringBuilder("Update tour: "+tour.getTour_id()+" ");

        // lấy tất cả các field trong các entity
        Field[] fields = Tour.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                // Lấy giá trị của field cho cả hai object
                field.setAccessible(true);
                Object value1 = field.get(tour);
                Object value2 = field.get(tourSaved);

                // So sánh giá trị, nếu khác nhau thì thêm vào chuỗi thay đổi
                if (!Objects.equals(value1, value2)) {
                    tourChangedString.append(" ")
                            .append(field.getName())
                            .append(": ")
                            .append(value1)
                            .append(" -> ")
                            .append(value2)
                            .append(";");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Xử lý lỗi nếu có
            }
        }

        return tourChangedString.toString();
    }

    private static @NotNull String getTourTimeChangedString(TourTime tourTime, TourTime tourTimeSaved) {
        StringBuilder tourTimeChangedString = new StringBuilder("Update tourTime: "+tourTime.getTour_time_id()+" ");

        Field[] fields = Tour.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                // Lấy giá trị của field cho cả hai object
                field.setAccessible(true);
                Object value1 = field.get(tourTime);
                Object value2 = field.get(tourTimeSaved);

                // So sánh giá trị, nếu khác nhau thì thêm vào chuỗi thay đổi
                if (!Objects.equals(value1, value2)) {
                    tourTimeChangedString.append(" ")
                            .append(field.getName())
                            .append(": ")
                            .append(value1)
                            .append(" -> ")
                            .append(value2)
                            .append(";");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Xử lý lỗi nếu có
            }
        }

        return tourTimeChangedString.toString();
    }

    @PreAuthorize("hasRole('CHANGE_TOUR_STATUS')")
    public TourDetailResponse changeStatusTour(Integer tour_id) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        if (tour.getStatus() == 0) {
            tour.setStatus(1);
        } else {
            tour.setStatus(0);
        }
        Tour tourSaved=tourRepository.save(tour);
        historyService.createHistory("Changed status tour  "+tourSaved.getTour_name());
        return tourMapper.toTourDetailResponse(tourSorted(tour));

    }

    @PreAuthorize("hasRole('CHANGE_TOUR_STATUS')")
    public TourDetailResponse changeStatusTourTime(Integer tour_id, Integer tourTime_id) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        TourTime tourTimeSaved=new TourTime();

        for (TourTime tourTime : tour.getTourTimes()) {
            if (tourTime.getTour_time_id().equals(tourTime_id)) {
                tourTime.setTour(tour);

                if (tourTime.getStatus() == 0) {
                    tourTime.setStatus(1);
                } else {
                    tourTime.setStatus(0);
                }
                tourTimeSaved=tourTimeRepository.save(tourTime);
                break;
            }
        }
        if (!Objects.equals(tourTimeSaved.getTour_time_id(), tourTime_id)) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }
        historyService.createHistory("Changed status tour  "+tour.getTour_name()+" tour_time_id "+tourTimeSaved.getTime_name());
        return tourMapper.toTourDetailResponse(tourSorted(tour));

    }
}
