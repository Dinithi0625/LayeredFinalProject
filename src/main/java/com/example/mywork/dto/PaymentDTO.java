package com.example.mywork.dto;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentDTO {
   private double Amount;
   private String date;
   private String orderId;
}
