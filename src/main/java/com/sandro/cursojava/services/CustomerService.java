package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.repository.CategoryRepository;
import com.sandro.cursojava.repository.CustomerRepository;
import com.sandro.cursojava.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer get(Integer id){
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato! Id: " + id + ", Tipo: " + Customer.class.getName()));
    }
}
