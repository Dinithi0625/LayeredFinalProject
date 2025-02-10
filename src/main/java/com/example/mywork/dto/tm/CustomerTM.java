package com.example.mywork.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerTM extends ProductTM {
    private String customerId;
    private String name;
    private String Address;
    private String Contact;
}


