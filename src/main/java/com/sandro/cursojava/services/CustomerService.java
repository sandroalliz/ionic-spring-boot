package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.domain.enums.CustomerType;
import com.sandro.cursojava.dto.CategoryDTO;
import com.sandro.cursojava.dto.CustomerDTO;
import com.sandro.cursojava.repository.CategoryRepository;
import com.sandro.cursojava.repository.CustomerRepository;
import com.sandro.cursojava.services.exceptions.DataIntegrityException;
import com.sandro.cursojava.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Customer update(Customer customer) {
        Customer customerUpdated = get(customer.getId());
        customer.setCpfOrCpnj(customerUpdated.getCpfOrCpnj());
        customer.setType(customerUpdated.getType());
        return customerRepository.save(customer);
    }

    public void delete(Integer id){
        get(id);
        try {
            customerRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
        }
    }

    public List<Customer> list(){
        return customerRepository.findAll();
    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return customerRepository.findAll(pageRequest);
    }

    public Customer fromDTO(CustomerDTO dto){
        return new Customer(dto.getId(), dto.getName(), dto.getEmail(), null, null);
    }
}
