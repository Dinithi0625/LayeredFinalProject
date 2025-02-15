package com.example.mywork.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Order {
    private String orderId;
    private String date;
    private String custId;
}


