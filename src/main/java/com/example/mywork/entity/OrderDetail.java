package com.example.mywork.entity;

import lombok.*;

@Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString

    public class OrderDetail {
        private String orderId;
        private String productId;
        private String date;
        private int qty;
        private double price;
    }
