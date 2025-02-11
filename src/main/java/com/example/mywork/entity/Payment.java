package com.example.mywork.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Payment {
   private double Amount;
   private String date;
   private String orderId;
}
