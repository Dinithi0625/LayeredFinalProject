package com.example.mywork.dto.tm;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentTM {
    private String paymentId;
    private String paymentAmount;
    private String date;
    private String orderId;
}
