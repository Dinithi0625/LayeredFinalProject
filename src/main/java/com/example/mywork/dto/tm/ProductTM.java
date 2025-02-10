package com.example.mywork.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductTM {
    private String productId;
    private String name;
    private double price;
    private String description;
    private int qty;
}
