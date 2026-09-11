package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component
public class DiscountContext {

    private final NoDiscountStrategy noDiscountStrategy;
    private final MemberDiscountStrategy memberDiscountStrategy;
    private final SeasonalSaleStrategy seasonalSaleStrategy;

    public DiscountContext(NoDiscountStrategy noDiscountStrategy,
                            MemberDiscountStrategy memberDiscountStrategy,
                            SeasonalSaleStrategy seasonalSaleStrategy) {
        this.noDiscountStrategy = noDiscountStrategy;
        this.memberDiscountStrategy = memberDiscountStrategy;
        this.seasonalSaleStrategy = seasonalSaleStrategy;
    }

    public double calculatePrice(String discountType, double price) {
        DiscountStrategy strategy = switch (discountType) {
            case "MEMBER" -> memberDiscountStrategy;
            case "SEASONAL" -> seasonalSaleStrategy;
            default -> noDiscountStrategy;
        };
        return strategy.applyDiscount(price);
    }
}