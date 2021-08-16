package com.payment.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Card {

    private String pan;
    private String expiry;
    private String cvv;
}
