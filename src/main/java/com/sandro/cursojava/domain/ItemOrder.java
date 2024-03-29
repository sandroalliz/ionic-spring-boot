package com.sandro.cursojava.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.aspectj.weaver.ast.Or;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class ItemOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ItemOrderPK id = new ItemOrderPK();

    private Double descont;
    private Integer quantity;
    private Double price;

    public ItemOrder() {

    }

    public ItemOrder(Order order, Product product, Double descont, Integer quantity, Double price) {
        id.setProduct(product);
        id.setOrder(order);
        this.descont = descont;
        this.quantity = quantity;
        this.price = price;
    }

    public double getSubTotal() {
        return (price - descont) * quantity;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Product getProduct() {
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

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
       StringBuilder builder = new StringBuilder();
       builder.append(getProduct().getName());
       builder.append(", Qte: ");
       builder.append(getQuantity());
       builder.append(", Preço unitário: ");
       builder.append(nf.format(getPrice()));
       builder.append(", Subtotal: ");
       builder.append(nf.format(getSubTotal()));
       builder.append("\n");
       return builder.toString();
    }
}
