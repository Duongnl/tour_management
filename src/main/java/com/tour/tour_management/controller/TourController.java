package com.tour.tour_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tour")
public class TourController {

    @GetMapping
    public String getAll() {
        return "Hello tour management";
    }

}
