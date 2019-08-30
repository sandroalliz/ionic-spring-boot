package com.sandro.cursojava.domain;

import com.sandro.cursojava.domain.enums.StatusPayment;

import javax.persistence.Entity;

@Entity
public class PaymentWithCard extends Payment{

    private static final long serialVersionUID = 1L;
    private Integer numberInstallments;

    public PaymentWithCard(){

    }

    public PaymentWithCard(Integer id, StatusPayment status, Order order, Integer numberInstallments){
        super(id, status, order);
        this.numberInstallments = numberInstallments;
    }

    public Integer getNumberInstallments() {
        return numberInstallments;
    }

    public void setNumberInstallments(Integer numberInstallments) {
        this.numberInstallments = numberInstallments;
    }
}
