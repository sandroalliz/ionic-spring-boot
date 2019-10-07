package com.sandro.cursojava.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javafx.util.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "ORDER_TABLE")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "id.order")
    private Set<ItemOrder> items = new HashSet<>();

    public Order(){

    }

    public Order(Integer id, Date instant, Customer customer, Address address) {
        this.id = id;
        this.instant = instant;
        this.customer = customer;
        this.address = address;
    }

    public double getTotal(){
        double total = 0.0;

        for(ItemOrder item: items){
            total += item.getSubTotal();
        }

        return total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<ItemOrder> getItems() {
        return items;
    }

    public void setItems(Set<ItemOrder> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido número: ");
        builder.append(getId());
        builder.append(", Instante: ");
        builder.append(sdf.format(getInstant()));
        builder.append(", Cliente: ");
        builder.append(getCustomer().getName());
        builder.append(", Situação do pagamento: ");
        builder.append(getPayment().getStatus().getDescription());
        builder.append("\nDetalhes:\n");
        for (ItemOrder i: getItems()){
            builder.append(i.toString());
        }
        builder.append("Valor total: ");
        builder.append(nf.format(getTotal()));
        return builder.toString();
    }
}
