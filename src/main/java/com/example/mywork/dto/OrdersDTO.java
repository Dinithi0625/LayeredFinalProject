package com.example.mywork.dto;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrdersDTO {
    private String orderId;
    private String date;
    private String custId;
}


