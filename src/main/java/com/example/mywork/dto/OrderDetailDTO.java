package com.example.mywork.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString

    public class OrderDetailDTO {
        private String orderId;
        private String productId;
        private String date;
        private int qty;
        private double price;
    }
