package com.tour.tour_management.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer employee_id;
    String employee_name;
    @Column(name = "birthday", columnDefinition = "date")
    Date birthday;
    @Column(name = "total_commission", columnDefinition = "bigint")
    BigInteger total_commission;
    @Column(name = "total_sales", columnDefinition = "bigint")
    BigInteger total_sales;
    int status;

    @OneToMany(mappedBy = "employee",  cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Reserve> reserves;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    Account account;




}
