package com.payment.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "INVOICE")
public class Invoice {

    @Id
    @GeneratedValue
    private long  invoice_id;
    private int amount;
    private String currency;

}
