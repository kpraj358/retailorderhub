package com.training.retailorderhub.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * TRAINING NOTE (Day 2):
 * - Extracted from OrderManager in the Day 2 demo (SRP): started as a single
 *   charge() method with an if/else chain.
 * - Rewritten here per Lab 2 (OCP): Spring collects every PaymentStrategy
 *   bean into this Map, keyed by the @Component name each strategy was given
 *   (CREDIT_CARD, PAYPAL, GIFT_CARD, APPLE_PAY, ...). Adding a payment method
 *   never requires touching this file again - see ApplePayStrategy.
 */
@Service
public class PaymentService {

    private final Map<String, PaymentStrategy> strategies;

    public PaymentService(Map<String, PaymentStrategy> strategies) {
        this.strategies = strategies;
    }

    public boolean charge(String paymentMethod, double amount) {
        PaymentStrategy strategy = strategies.get(paymentMethod);
        if (strategy == null) {
            System.out.println("Unknown payment method: " + paymentMethod);
            return false;
        }
        return strategy.charge(amount);
    }
}
