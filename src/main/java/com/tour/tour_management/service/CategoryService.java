package com.tour.tour_management.service;


import com.tour.tour_management.dto.response.CategoryResponse;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.mapper.CategoryMapper;
import com.tour.tour_management.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public List<CategoryResponse> getAll(){
        List<Category> categoryList =  categoryRepository.findAll();
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        categoryList.forEach( category ->{categoryResponseList.add(categoryMapper.toCategoryResponse(category));});
        return categoryResponseList;
    }


}
