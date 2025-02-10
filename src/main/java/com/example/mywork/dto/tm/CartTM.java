package com.example.mywork.dto.tm;

import java.sql.Date;

import javafx.scene.control.Button;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTM {
    private String customerId;
    private String productId;
    private int quantity;
    private String Date;
    private double amount;
}
