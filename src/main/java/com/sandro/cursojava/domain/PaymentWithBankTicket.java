package com.sandro.cursojava.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sandro.cursojava.domain.enums.StatusPayment;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentWithBankTicket extends Payment{

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public PaymentWithBankTicket(){

    }

    public PaymentWithBankTicket(Integer id, StatusPayment status, Order order, Date dueDate, Date paymentDate){
        super(id, status, order);
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
