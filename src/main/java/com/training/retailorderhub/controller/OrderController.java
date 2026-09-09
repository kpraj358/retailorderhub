package com.training.retailorderhub.controller;

import com.training.retailorderhub.repository.OrderRepository;
import com.training.retailorderhub.repository.ProductRepository;
import com.training.retailorderhub.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TRAINING NOTE (Day 2, Lab 1, Step 3):
 * Every reference to OrderManager has been replaced with OrderService - the
 * field, the constructor parameter, and the method call
 * (orderManager.processOrder(...) -> orderService.processOrder(...)).
 * This is the most common place to miss a rename after Step 3.
 */
@Controller
public class OrderController {

    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService,
                            ProductRepository productRepository,
                            OrderRepository orderRepository) {
        this.orderService = orderService;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @PostMapping("/order")
    public String placeOrder(@RequestParam String customerId,
                              @RequestParam String items,
                              @RequestParam String paymentMethod,
                              @RequestParam double amount,
                              Model model) {
        List<String> itemNames = Arrays.stream(items.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        boolean success = orderService.processOrder(customerId, itemNames, paymentMethod, amount);
        model.addAttribute("success", success);
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @GetMapping("/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }
}
