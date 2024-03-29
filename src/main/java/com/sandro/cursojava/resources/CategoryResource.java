package com.sandro.cursojava.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.dto.CategoryDTO;
import com.sandro.cursojava.services.CategoryService;

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
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO dto){
		Category domain = categoryService.fromDTO(dto);
		domain = categoryService.insert(domain);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(domain.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO dto){
		Category category = categoryService.fromDTO(dto);
		category.setId(id);
		category = categoryService.update(category);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> list() {
		List<Category> domains = categoryService.list();
		List<CategoryDTO> categories = domains.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
		return ResponseEntity.ok(categories);
	}

	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> listByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
														@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
														@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
														@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Category> domains = categoryService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> categories = domains.map(obj -> new CategoryDTO(obj));

		return ResponseEntity.ok(categories);
	}

}
