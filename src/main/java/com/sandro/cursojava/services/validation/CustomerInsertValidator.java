package com.sandro.cursojava.services.validation;

import com.sandro.cursojava.domain.enums.CustomerType;
import com.sandro.cursojava.dto.CustomerNewDTO;
import com.sandro.cursojava.resources.exception.FieldMessage;
import com.sandro.cursojava.services.validation.utils.BR;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerNewDTO> {

    @Override
    public void initialize(CustomerInsert ann) {
    }

    @Override
    public boolean isValid(CustomerNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getType().equals(CustomerType.PESSOA_FISICA.getCode()) && !BR.isValidCpf(objDto.getCpfOrCpnj())){
            list.add(new FieldMessage("cpfOrCpnj", "CPF inválido"));
        }

        if(objDto.getType().equals(CustomerType.PESSOA_JURIDICA.getCode()) && !BR.isValidCnpj(objDto.getCpfOrCpnj())){
            list.add(new FieldMessage("cpfOrCpnj", "CNPJ inválido"));
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
