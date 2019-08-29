package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.repository.CategoryRepository;
import com.sandro.cursojava.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category get(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato! Id: " + id + ", Tipo: " + Category.class.getName()));
    }
}
