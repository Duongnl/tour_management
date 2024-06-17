package com.tour.tour_management.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tour_time_id;
    String time_name;
    @Column(name = "departure_time", columnDefinition = "date")
    Date departure_time;
    @Column(name = "return_time", columnDefinition = "date")
    Date return_time;
    @Column(name = "visa_expire", columnDefinition = "date")
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
    @JsonIgnoreProperties("departureTourTimes")
//    @JsonBackReference
    Airline departureAirline;

    @ManyToOne
    @JoinColumn(name = "return_airline_id", nullable = false, referencedColumnName = "airline_id")
//    @JsonBackReference
    @JsonIgnoreProperties("returnTourTimes")
    Airline returnAirline;

    @OneToMany(mappedBy = "tourTime", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Reserve> reserves;




}
