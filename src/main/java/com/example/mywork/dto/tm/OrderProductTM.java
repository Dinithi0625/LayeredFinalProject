package com.example.mywork.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderProductTM {
    private String orderId;
    private String productId;
    private String date;
    private int qty;
    private double price;
}
