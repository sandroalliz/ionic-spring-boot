package com.sandro.cursojava.resources;

import java.util.ArrayList;
import java.util.List;

import com.sandro.cursojava.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sandro.cursojava.domain.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Integer id) {
		Category category = categoryService.get(id);
		
		return ResponseEntity.ok(category);
	}

}
