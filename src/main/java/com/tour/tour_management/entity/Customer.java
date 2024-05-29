package com.tour.tour_management.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String customer_id;
    String relationship_name;
    String customer_name;
    String sex;
    int phone_number;
    String email;
    String address;
    Date birthday;
    Date visa_expire;
    int status;

//    chính
    @ManyToOne
    @JoinColumn(name ="customer_rel_id" , nullable = true, referencedColumnName = "customer_id")
    @JsonBackReference
    Customer customer;

//    nguoi lien quan
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Customer> customers;

    @OneToMany(mappedBy = "customer",  cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Reserve> reserves;

}
