package com.sandro.cursojava.repository;

import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.domain.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer> {
}
