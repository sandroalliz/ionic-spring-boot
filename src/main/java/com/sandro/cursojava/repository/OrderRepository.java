package com.sandro.cursojava.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Transactional(readOnly = true)
	Page<Order> findByCustomer(Customer customer, Pageable pageRequest);
}
