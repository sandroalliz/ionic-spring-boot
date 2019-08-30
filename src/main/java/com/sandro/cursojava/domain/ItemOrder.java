package com.sandro.cursojava.domain;

import org.aspectj.weaver.ast.Or;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ItemOrderPK id = new ItemOrderPK();

    private Double descont;
    private Integer quantity;
    private Double price;

    public ItemOrder(){

    }

    public ItemOrder(Order order, Product product, Double descont, Integer quantity, Double price) {
        id.setProduct(product);
        id.setOrder(order);
        this.descont = descont;
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder(){
        return id.getOrder();
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public ItemOrderPK getId() {
        return id;
    }

    public void setId(ItemOrderPK id) {
        this.id = id;
    }

    public Double getDescont() {
        return descont;
    }

    public void setDescont(Double descont) {
        this.descont = descont;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return id.equals(itemOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
