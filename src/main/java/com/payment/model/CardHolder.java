package com.payment.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CardHolder {

    private long id;
    private String name;
    private String email;
}
