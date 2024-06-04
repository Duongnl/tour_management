package com.tour.tour_management.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String reserve_id;
    String note;
    int price;
    int price_min;
    int commission;
    int pay;
    LocalDateTime time;
    int status;


    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, referencedColumnName = "employee_id")
    @JsonManagedReference
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "tour_time_id", nullable = false, referencedColumnName = "tour_time_id")
    @JsonManagedReference
    TourTime tourTime;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customer_id")
    @JsonManagedReference
    Customer customer;

}
