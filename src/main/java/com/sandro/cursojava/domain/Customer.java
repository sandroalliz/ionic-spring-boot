package com.sandro.cursojava.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sandro.cursojava.domain.enums.CustomerType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class  Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String cpfOrCpnj;
    private Integer type;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONE")
    private Set<String> phones = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Customer(){

    }

    public Customer(Integer id, String name, String email, String cpfOrCpnj, CustomerType tipo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCpnj = cpfOrCpnj;
        this.type = tipo.getCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public CustomerType getType() {
        return CustomerType.toEnum(this.type);
    }

    public void setType(CustomerType type) {
        this.type = type.getCode();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}