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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer customer_id;
    String customer_name;
    int sex;
    String relationship_name;

    @Column(name = "phone_number")
    String phone_number;
    String email;
    String address;
    @Column(name = "birthday", columnDefinition = "date")
    Date birthday;
    @Column(name = "visa_expire", columnDefinition = "date")
    Date visa_expire;
    LocalDateTime time;
    int status;

    //    Người đại diện
    @ManyToOne
    @JoinColumn(name ="customer_rel_id" , nullable = true, referencedColumnName = "customer_id")
    @JsonBackReference
    Customer customer;

    //    Người liên quan
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Customer> customers;

    //   đặt vé
    @OneToMany(mappedBy = "customer",  cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Reserve> reserves;

}
