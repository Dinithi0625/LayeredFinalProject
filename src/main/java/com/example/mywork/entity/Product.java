package com.example.mywork.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private String productId;
    private String name;
    private double price;
    private String description;
    private int qty;
}
