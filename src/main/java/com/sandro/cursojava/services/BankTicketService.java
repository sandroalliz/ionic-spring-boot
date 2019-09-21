package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.PaymentWithBankTicket;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BankTicketService {

    public void setPaymentWithBankTicket(PaymentWithBankTicket payment, Date instant) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instant);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        payment.setPaymentDate(cal.getTime());
    }
}
