package com.sandro.cursojava.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sandro.cursojava.domain.Address;
import com.sandro.cursojava.domain.City;
import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.domain.enums.CustomerType;
import com.sandro.cursojava.domain.enums.Profile;
import com.sandro.cursojava.dto.CustomerDTO;
import com.sandro.cursojava.dto.CustomerNewDTO;
import com.sandro.cursojava.repository.AddressRepository;
import com.sandro.cursojava.repository.CityRepository;
import com.sandro.cursojava.repository.CustomerRepository;
import com.sandro.cursojava.security.UserSS;
import com.sandro.cursojava.services.exceptions.AuthorizationException;
import com.sandro.cursojava.services.exceptions.DataIntegrityException;
import com.sandro.cursojava.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private BCryptPasswordEncoder bp;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Customer get(Integer id) {
    	UserSS user = UserService.authenticated();
    	
    	if(Objects.isNull(user) || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
    		throw new AuthorizationException("Acesso negado");
    	}
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato! Id: " + id + ", Tipo: " + Customer.class.getName()));
    }

    public Customer insert(Customer customer) {
        customer.setId(null);
        customer = customerRepository.save(customer);
        addressRepository.saveAll(customer.getAddresses());
        return customer;
    }

    public Customer update(Customer customer) {
        Customer customerUpdated = get(customer.getId());
        customer.setCpfOrCpnj(customerUpdated.getCpfOrCpnj());
        customer.setType(customerUpdated.getType());
        return customerRepository.save(customer);
    }

    public void delete(Integer id) {
        get(id);
        try {
            customerRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir porque há pedidos.");
        }
    }

    public List<Customer> list() {
        return customerRepository.findAll();
    }
    
    public Customer findByEmail(String email) {
    	UserSS user = UserService.authenticated();
    	
    	if(Objects.isNull(user) || !user.hasRole(Profile.ADMIN) || !email.equals(user.getUsername())) {
    		throw new AuthorizationException("Acesso negado");
    	}
    	
    	Customer customer = customerRepository.findByEmail(email);
    	
    	if(Objects.isNull(customer)) {
    		throw new ObjectNotFoundException("Objeto não encontrado! id: " + user.getId());
    	}
    	return customer;
    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return customerRepository.findAll(pageRequest);
    }

    public Customer fromDTO(CustomerDTO dto) {
        return new Customer(dto.getId(), dto.getName(), dto.getEmail(), null, null, null);
    }

    public Customer fromDTO(CustomerNewDTO dto) {
        Customer customer = new Customer(null, dto.getName(), dto.getEmail(), dto.getCpfOrCpnj(), CustomerType.toEnum(dto.getType()), bp.encode(dto.getPassword()));
        City city = cityRepository.getOne(dto.getCidadeId());
        Address address = new Address(null, dto.getStreet(), dto.getNumber(), dto.getComplement(), dto.getNeighborhood(), dto.getZipCode(), customer, city);
        customer.getAddresses().add(address);

        customer.getPhones().add(dto.getPhone1());

        if (dto.getPhone2() != null) {
            customer.getPhones().add(dto.getPhone2());
        }

        if (dto.getPhone3() != null) {
            customer.getPhones().add(dto.getPhone3());
        }

        return customer;
    }
}
