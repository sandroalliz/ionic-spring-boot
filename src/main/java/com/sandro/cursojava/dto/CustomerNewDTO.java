package com.sandro.cursojava.dto;

import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.services.validation.CustomerInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@CustomerInsert
public class CustomerNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "O nome é obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String name;

    @NotEmpty(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "O CPF ou CNPJ é obrigatório")
    private String cpfOrCpnj;
    private Integer type;

    @NotEmpty(message = "O logradouro é obrigatório")
    private String street;

    @NotEmpty(message = "O número é obrigatório")
    private String number;

    private String complement;
    private String neighborhood;

    @NotEmpty(message = "O CEP é obrigatório")
    private String zipCode;

    @NotEmpty(message = "O Telefone é obrigatório")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cidadeId;

    public CustomerNewDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCpnj() {
        return cpfOrCpnj;
    }

    public void setCpfOrCpnj(String cpfOrCpnj) {
        this.cpfOrCpnj = cpfOrCpnj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
