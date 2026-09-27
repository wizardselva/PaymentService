package com.wizard.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentDtoo {
    Long amount;
    String phoneNumber;
    String name;
    String email;
    String orderId;
}
