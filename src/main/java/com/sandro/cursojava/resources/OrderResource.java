package com.sandro.cursojava.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sandro.cursojava.domain.Order;
import com.sandro.cursojava.services.OrderService;

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
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Order>> listByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
														@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
														@RequestParam(value = "orderBy", defaultValue = "instant") String orderBy,
														@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Order> orders = orderService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(orders);
	}
}
