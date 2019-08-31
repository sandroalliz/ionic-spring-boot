package com.sandro.cursojava.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.sandro.cursojava.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sandro.cursojava.domain.Category;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Category category){
		category = categoryService.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(category.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
}
