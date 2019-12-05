package com.sandro.cursojava.resources;

import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.dto.CustomerDTO;
import com.sandro.cursojava.dto.CustomerNewDTO;
import com.sandro.cursojava.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/customers")
public class CustomerResource {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Customer> get(@PathVariable Integer id) {
		Customer customer = customerService.get(id);
		return ResponseEntity.ok(customer);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CustomerDTO dto){
		Customer customer = customerService.fromDTO(dto);
		customer.setId(id);
		customer = customerService.update(customer);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		customerService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CustomerDTO>> list() {
		List<Customer> domains = customerService.list();
		List<CustomerDTO> customers = domains.stream().map(customer -> new CustomerDTO(customer)).collect(Collectors.toList());
		return ResponseEntity.ok(customers);
	}

	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CustomerDTO>> listByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
														@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
														@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
														@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Customer> domains = customerService.findPage(page, linesPerPage, orderBy, direction);
		Page<CustomerDTO> customers = domains.map(obj -> new CustomerDTO(obj));

		return ResponseEntity.ok(customers);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CustomerNewDTO dto){
		Customer domain = customerService.fromDTO(dto);
		domain = customerService.insert(domain);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(domain.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
}
