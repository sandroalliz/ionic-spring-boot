package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category get(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }
}
