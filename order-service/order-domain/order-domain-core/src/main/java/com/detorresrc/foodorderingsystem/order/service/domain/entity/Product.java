package com.detorresrc.foodorderingsystem.order.service.domain.entity;

import com.detorresrc.foodorderingsystem.entity.BaseEntity;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import com.detorresrc.foodorderingsystem.valueobject.ProductId;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;

    public Product(ProductId id, String name, Money price) {
        super.setId(id);
        this.name = name;
        this.price = price;
    }

    public Product(ProductId id) {
        super.setId(id);
    }

    public Product(UUID id) {
        super.setId(new ProductId(id));
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}
