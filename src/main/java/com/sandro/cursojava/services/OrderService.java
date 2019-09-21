package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.domain.ItemOrder;
import com.sandro.cursojava.domain.Order;
import com.sandro.cursojava.domain.PaymentWithBankTicket;
import com.sandro.cursojava.domain.enums.StatusPayment;
import com.sandro.cursojava.repository.*;
import com.sandro.cursojava.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BankTicketService bankTicketService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    public Order get(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato! Id: " + id + ", Tipo: " + Order.class.getName()));
    }

    public Order insert(Order order) {
        order.setId(null);
        order.setInstant(new Date());
        order.getPayment().setStatus(StatusPayment.PENDENTE);
        order.getPayment().setOrder(order);
        if (order.getPayment() instanceof PaymentWithBankTicket) {
            PaymentWithBankTicket payment = (PaymentWithBankTicket) order.getPayment();
            bankTicketService.setPaymentWithBankTicket(payment, order.getInstant());

        }
        order = orderRepository.save(order);
        paymentRepository.save(order.getPayment());
        for (ItemOrder itemOrder : order.getItems()) {
            itemOrder.setDescont(0.0);
            itemOrder.setPrice(productService.get(itemOrder.getProduct().getId()).getPrice());
            itemOrder.setOrder(order);
        }
        itemOrderRepository.saveAll(order.getItems());
        return order;
    }
}
