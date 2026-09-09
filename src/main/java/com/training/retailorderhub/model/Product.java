package com.training.retailorderhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * DISPLAY-ONLY, mirrors Day 1's Product entity so the order form can show
 * a Product Catalog table (same purpose as Day 1's: let people see valid
 * item names, prices, and stock before submitting an order). Mapped to the
 * same columns as Day 1's entity (id, name, price, quantity). This entity
 * is separate from the SOLID lab surface (InventoryRepository /
 * InventoryService / JpaInventoryRepository), which is untouched.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
