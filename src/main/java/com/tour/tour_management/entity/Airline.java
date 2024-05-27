package com.tour.tour_management.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    String airline_id;
    String airline_name;
    String airline_detail;
    LocalDateTime departure_time;
    LocalDateTime return_time;
    int status;

    @OneToMany(mappedBy = "departure_airline", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<TourTime> departureTourTimes;

    @OneToMany(mappedBy = "return_airline", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<TourTime> returnTourTimes;


}
