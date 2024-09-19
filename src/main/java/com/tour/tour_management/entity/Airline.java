package com.tour.tour_management.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
//tao get set
@Getter
@Setter
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer airline_id;
    String airline_name;
    String airline_detail;
    int status;

    @OneToMany(mappedBy = "departureAirline", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<TourTime> departureTourTimes;

    @OneToMany(mappedBy = "returnAirline", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<TourTime> returnTourTimes;


}
