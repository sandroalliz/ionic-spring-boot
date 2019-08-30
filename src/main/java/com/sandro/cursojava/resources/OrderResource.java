package com.sandro.cursojava.resources;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.domain.Order;
import com.sandro.cursojava.services.CategoryService;
import com.sandro.cursojava.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {

	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Integer id) {
		Order order = orderService.get(id);
		return ResponseEntity.ok(order);
	}
}
