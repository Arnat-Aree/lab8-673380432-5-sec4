package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component
public class MemberDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.90;
    }
}