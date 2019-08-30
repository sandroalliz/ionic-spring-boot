package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.domain.Order;
import com.sandro.cursojava.repository.CustomerRepository;
import com.sandro.cursojava.repository.OrderRepository;
import com.sandro.cursojava.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order get(Integer id){
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato! Id: " + id + ", Tipo: " + Order.class.getName()));
    }
}
