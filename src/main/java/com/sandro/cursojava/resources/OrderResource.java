package com.sandro.cursojava.resources;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.domain.Order;
import com.sandro.cursojava.services.CategoryService;
import com.sandro.cursojava.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {

	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Order> get(@PathVariable Integer id) {
		Order order = orderService.get(id);
		return ResponseEntity.ok(order);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Order order){
		order = orderService.insert(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(order.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
}
