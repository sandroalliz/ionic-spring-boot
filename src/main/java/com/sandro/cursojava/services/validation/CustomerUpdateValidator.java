package com.sandro.cursojava.services.validation;

import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.domain.enums.CustomerType;
import com.sandro.cursojava.dto.CustomerDTO;
import com.sandro.cursojava.dto.CustomerNewDTO;
import com.sandro.cursojava.repository.CustomerRepository;
import com.sandro.cursojava.resources.exception.FieldMessage;
import com.sandro.cursojava.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {


    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void initialize(CustomerUpdate ann) {
    }

    @Override
    public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>) servletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer id = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Customer customer = customerRepository.findByEmail(objDto.getEmail());
        if(customer != null && !customer.getId().equals(id)){
            list.add(new FieldMessage("email", "Email já existente"));
        }

        // inclua os testes aqui, inserindo erros na lista
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
