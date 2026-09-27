package com.wizard.paymentservice.controllers;

import com.wizard.paymentservice.dtos.InitiatePaymentDtoo;
import com.wizard.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentDtoo initiatePaymentDtoo){
        return paymentService.getPaymentLink(
                initiatePaymentDtoo.getAmount(),
                initiatePaymentDtoo.getPhoneNumber(),
                initiatePaymentDtoo.getName(),
                initiatePaymentDtoo.getEmail(),
                initiatePaymentDtoo.getOrderId()
        );
    }
}
