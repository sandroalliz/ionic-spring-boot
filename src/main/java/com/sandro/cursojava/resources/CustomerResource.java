package com.sandro.cursojava.resources;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.services.CategoryService;
import com.sandro.cursojava.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/clientes")
public class CustomerResource {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Integer id) {
		Customer customer = customerService.get(id);
		return ResponseEntity.ok(customer);
	}
}
