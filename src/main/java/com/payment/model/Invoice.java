package com.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payment.audit.Auditable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "INVOICE")
@Slf4j
@Builder
public class Invoice extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoice_id;

    @Min(value = 0)
    private int amount;

    @NotBlank(message = "Currency is mandatory")
    private String currency;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "invoice_cardholder", joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "card_holder_id"))
    private CardHolder cardHolder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "invoice_card", joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"))
    private Card card;


}
