package com.example.mywork.dto.tm;

import com.example.mywork.dto.OrdersDTO;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersTM {
    private String orderId;
    private String custId;
}
