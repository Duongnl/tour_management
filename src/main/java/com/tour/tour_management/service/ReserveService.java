package com.tour.tour_management.service;


import com.tour.tour_management.dto.request.reserve.ReserveRequests;
import com.tour.tour_management.dto.request.reserve.ReserveStatusRequest;
import com.tour.tour_management.dto.request.reserve.ReserveTourFilterRequest;
import com.tour.tour_management.dto.response.reserve.ReserveResponse;
import com.tour.tour_management.dto.response.reserve.ReserveTourResponse;
import com.tour.tour_management.entity.*;
import com.tour.tour_management.exception.*;
import com.tour.tour_management.mapper.CustomerMapper;
import com.tour.tour_management.mapper.ReserveMapper;
import com.tour.tour_management.mapper.TourMapper;
import com.tour.tour_management.repository.*;
import com.tour.tour_management.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class ReserveService {

    TourRepository tourRepository;
    ReserveMapper reserveMapper;
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;
    EmployeeRepository employeeRepository;
    TourTimeRepository tourTimeRepository;
    TourMapper tourMapper;


    private final ReserveRepository reserveRepository;

    public List<ReserveTourResponse> getReserveTours () {
        List<Tour> tourList = tourRepository.findAll();
        List<ReserveTourResponse> reserveTourList = new ArrayList<>();
        tourList.forEach(tour -> {

            tour.getTourTimes().forEach(tourTime -> {
                ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                reserveTourList.add(reserveTour);
            });
        });
        return reserveTourList;
    }

    public ReserveTourResponse getReserveTour (String slug) {
        if (StringUtils.getIdFromUrl(slug) == -1) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }

        List<Tour> tourList = tourRepository.findAll();
        List<ReserveTourResponse> getReserveTour = new ArrayList<>();
        tourList.forEach(tour -> {
            tour.getTourTimes().forEach(tourTime -> {
                if (tourTime.getTour_time_id() == StringUtils.getIdFromUrl(slug)) {
                    ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                    reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                    getReserveTour.add(reserveTour);
                }


            });
        });
        if(getReserveTour.isEmpty()) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }

        return getReserveTour.getFirst();
    }

    public   List<ReserveResponse> getReserves (String slug) {
        if (StringUtils.getIdFromUrl(slug) == -1) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }

        TourTime tourTime = tourTimeRepository.findById(StringUtils.getIdFromUrl(slug))
                .orElseThrow(()-> new AppException(TourTimeErrorCode.TIME_NOT_FOUND));

        List<ReserveResponse> reserveResponses = new ArrayList<>();

        tourTime.getReserves().forEach(reserve -> {
            reserveResponses.add(reserveMapper.toReserveResponse(reserve));
        });

        return reserveResponses;

    }

    public   List<ReserveResponse> getPaidReserves (String slug) {
        if (StringUtils.getIdFromUrl(slug) == -1) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }

        TourTime tourTime = tourTimeRepository.findById(StringUtils.getIdFromUrl(slug))
                .orElseThrow(()-> new AppException(TourTimeErrorCode.TIME_NOT_FOUND));

        List<ReserveResponse> reserveResponses = new ArrayList<>();

        tourTime.getReserves().forEach(reserve -> {
            if (reserve.getStatus() == 1) {
                reserveResponses.add(reserveMapper.toReserveResponse(reserve));
            }
        });

        return reserveResponses;

    }

    public   List<ReserveResponse> getUnpaidReserves (String slug) {
        if (StringUtils.getIdFromUrl(slug) == -1) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }

        TourTime tourTime = tourTimeRepository.findById(StringUtils.getIdFromUrl(slug))
                .orElseThrow(()-> new AppException(TourTimeErrorCode.TIME_NOT_FOUND));

        List<ReserveResponse> reserveResponses = new ArrayList<>();

        tourTime.getReserves().forEach(reserve -> {
            if (reserve.getStatus() == 2) {
                reserveResponses.add(reserveMapper.toReserveResponse(reserve));
            }
        });

        return reserveResponses;

    }

    public   List<ReserveResponse> getCanceledReserves (String slug) {
        if (StringUtils.getIdFromUrl(slug) == -1) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }

        TourTime tourTime = tourTimeRepository.findById(StringUtils.getIdFromUrl(slug))
                .orElseThrow(()-> new AppException(TourTimeErrorCode.TIME_NOT_FOUND));

        List<ReserveResponse> reserveResponses = new ArrayList<>();

        tourTime.getReserves().forEach(reserve -> {
            if (reserve.getStatus() == 0) {
                reserveResponses.add(reserveMapper.toReserveResponse(reserve));
            }
        });

        return reserveResponses;

    }




    public boolean isDateInRange(LocalDate departureDate, LocalDate startDate, LocalDate endDate) {
        return !departureDate.isBefore(startDate) && !departureDate.isAfter(endDate);
    }

    public List<ReserveTourResponse> filterReserveTour (ReserveTourFilterRequest reserveTourFilterRequest) {

        System.out.println("data reserve" + reserveTourFilterRequest);
        List<Tour> tourList = new ArrayList<>();
        if (!reserveTourFilterRequest.getCategory_slug().isEmpty()) {
           tourList = tourRepository.daysFilterReserveTours(reserveTourFilterRequest.getStart_date(), reserveTourFilterRequest.getEnd_date(),  StringUtils.getIdFromUrl(reserveTourFilterRequest.getCategory_slug()));
        } else {
           tourList = tourRepository.daysFilterReserveTours(reserveTourFilterRequest.getStart_date(), reserveTourFilterRequest.getEnd_date(), null);
        }

        List<ReserveTourResponse> reserveTourList = new ArrayList<>();

            if (reserveTourFilterRequest.getStart_date() !=null  && reserveTourFilterRequest.getEnd_date() != null ) {
                tourList.forEach(tour -> {
                    tour.getTourTimes().forEach(tourTime -> {
                        if(isDateInRange(tourTime.getDeparture_date(), reserveTourFilterRequest.getStart_date(),reserveTourFilterRequest.getEnd_date())) {
                            ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                            reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                            reserveTourList.add(reserveTour);
                        }
                    });
                });
            } else {
                tourList.forEach(tour -> {
                    tour.getTourTimes().forEach(tourTime -> {
                        ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                        reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                        reserveTourList.add(reserveTour);
                    });
                });
            }

        return reserveTourList;
    }


    public String reserveTour (ReserveRequests reserveRequests) {

        List<Reserve> reserveList = new ArrayList<>();
        Employee employee = employeeRepository.findById(reserveRequests.getReserveRequests().getFirst().getEmployee_id())
                .orElseThrow(()-> new AppException(EmployeeErrorCode.EMPLOYEE_NOT_FOUND));
        TourTime tourTime = tourTimeRepository.findById(reserveRequests.getReserveRequests().getFirst().getTour_time_id())
                .orElseThrow(()-> new AppException(TourTimeErrorCode.TIME_NOT_FOUND));

        if (tourTime.getQuantity_left() < reserveRequests.getReserveRequests().size()) {
            throw new AppException(ReserveErrorCode.RESERVE_QUANTITY_NOT_ENOUGH);
        }

        reserveRequests.getReserveRequests().forEach(reserveRequest -> {
            Reserve reserve = new Reserve();

            reserve = reserveMapper.toReserve(reserveRequest);


            reserve.setTourTime(tourTime);
            reserve.setEmployee(employee);
            reserve.setStatus(2);
            reserve.setPay(0);
            // neu khach hang co san
            if(reserveRequest.getCustomerResponse().getCustomer_id() != null) {
                Customer customer = customerRepository.findById(reserveRequest.getCustomerResponse().getCustomer_id())
                        .orElseThrow(()-> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));
                reserve.setCustomer(customer);

            }
            else {
               Customer customer = customerMapper.toCustomerFromResponse(reserveRequest.getCustomerResponse());
               customer.setRelationship_name("Chủ hộ");
               customer.setStatus(1);
               reserve.setCustomer(customer);
            }
            LocalDateTime localDateTime = LocalDateTime.now();
            reserve.setTime(localDateTime);
            reserveList.add(reserve);
        });
        reserveList.forEach(reserve -> {customerRepository.save(reserve.getCustomer());});
        reserveRepository.saveAll(reserveList);

        updateQuantitySell(reserveRequests.getReserveRequests().getFirst().getTour_time_id());
        updateQuantityReserve(reserveRequests.getReserveRequests().getFirst().getTour_time_id());
        updateQuantityLeft(reserveRequests.getReserveRequests().getFirst().getTour_time_id());
        updateTotalCommissionEmployee(reserveRequests.getReserveRequests().getFirst().getEmployee_id());
        updateTotalSell(reserveRequests.getReserveRequests().getFirst().getEmployee_id());

        return "success";
    }

    public ReserveResponse changeStatusReserve (ReserveStatusRequest reserveStatusRequest) {
        Reserve reserve = reserveRepository.findById(reserveStatusRequest.getReserve_id())
                .orElseThrow( () -> new AppException(ReserveErrorCode.RESERVE_NOT_FOUND));

        reserve.setStatus(reserveStatusRequest.getStatus());


        updateQuantitySell(reserve.getTourTime().getTour_time_id());
        updateQuantityReserve(reserve.getTourTime().getTour_time_id());
        updateQuantityLeft(reserve.getTourTime().getTour_time_id());
        updateTotalCommissionEmployee(reserve.getEmployee().getEmployee_id());
        updateTotalSell(reserve.getEmployee().getEmployee_id());

        return reserveMapper.toReserveResponse( reserveRepository.save(reserve));
    }

    public void updateQuantityLeft (Integer tour_time_id) {
        TourTime tourTime = tourTimeRepository.findById(tour_time_id).
                orElseThrow( () -> new AppException(TourTimeErrorCode.TIME_NOT_FOUND));

        tourTime.setQuantity_left(tourTime.getQuantity() -
                (tourTime.getQuantity_reserve() + tourTime.getQuantity_sell()));
        tourTimeRepository.save(tourTime);
    }

    public void updateQuantityReserve (Integer tour_time_id) {
        TourTime tourTime = tourTimeRepository.findById(tour_time_id).
                orElseThrow( () -> new AppException(TourTimeErrorCode.TIME_NOT_FOUND));

        int quantity_reserve = 0;
        for (Reserve reserve : tourTime.getReserves()) {
            if (reserve.getStatus() == 2) {
                quantity_reserve++;
            }
        }
        tourTime.setQuantity_reserve(quantity_reserve);
        tourTimeRepository.save(tourTime);
    }

    public void updateQuantitySell (Integer tour_time_id) {
        TourTime tourTime = tourTimeRepository.findById(tour_time_id).
                orElseThrow( () -> new AppException(TourTimeErrorCode.TIME_NOT_FOUND));

        int quantity_sell = 0;
        for (Reserve reserve : tourTime.getReserves()) {
            if (reserve.getStatus() == 1) {
                quantity_sell++;
            }
        }
        tourTime.setQuantity_sell(quantity_sell);
        tourTimeRepository.save(tourTime);
    }

    public void updateTotalCommissionEmployee (Integer employee_id) {
        Employee employee = employeeRepository.findById(employee_id)
                .orElseThrow( () -> new AppException(EmployeeErrorCode.EMPLOYEE_NOT_FOUND));
        BigInteger total_commission = new BigInteger("0");
        for (Reserve reserve : employee.getReserves()) {
            if (reserve.getStatus() == 1){
            total_commission = total_commission.add(BigInteger.valueOf(reserve.getCommission()));
            }
        }
        employee.setTotal_commission(total_commission);
        employeeRepository.save(employee);
    }

    public void updateTotalSell (Integer employee_id) {
        Employee employee = employeeRepository.findById(employee_id)
                .orElseThrow( () -> new AppException(EmployeeErrorCode.EMPLOYEE_NOT_FOUND));
        BigInteger total_sell = new BigInteger("0");
        for (Reserve reserve : employee.getReserves()) {
            if (reserve.getStatus() == 1) {
                total_sell = total_sell.add(BigInteger.valueOf(reserve.getPrice()));
            }
        }
        employee.setTotal_sales(total_sell);
        employeeRepository.save(employee);
    }


}
