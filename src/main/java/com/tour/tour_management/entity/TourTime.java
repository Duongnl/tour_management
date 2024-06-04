package com.tour.tour_management.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
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
public class TourTime {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String tour_time_id;
    String time_name;
    LocalDateTime departure_time;
    LocalDateTime return_time;
    Date visa_expire;
    int quantity;
    int quantity_reserve;
    int quantity_sell;
    int quantity_left;
    int price_min;
    int commission;
    int status;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false, referencedColumnName = "tour_id")
    @JsonBackReference
    Tour tour;

    @ManyToOne
    @JoinColumn(name = "departure_airline_id", nullable = false, referencedColumnName = "airline_id")
    @JsonBackReference
    Airline departure_airline;

    @ManyToOne
    @JoinColumn(name = "return_airline_id", nullable = false, referencedColumnName = "airline_id")
    @JsonBackReference
    Airline return_airline;

    @OneToMany(mappedBy = "tourTime", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Reserve> reserves;




}
