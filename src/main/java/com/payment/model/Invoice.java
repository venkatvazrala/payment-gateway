package com.payment.model;

import com.payment.audit.Auditable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "INVOICE")
@Slf4j
public class Invoice extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long  invoice_id;
    private int amount;
    private String currency;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="invoice_cardholder", joinColumns = @JoinColumn(name="invoice_id"),
            inverseJoinColumns = @JoinColumn(name="card_holder_id"))
    private CardHolder cardHolder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="invoice_card", joinColumns = @JoinColumn(name="invoice_id"),
            inverseJoinColumns = @JoinColumn(name="card_id"))
    private Card card;

    @PrePersist
    private void validationBeforePersist(){

        log.info("validationBeforePersist called");


    }


}
