package com.payment.model;

import com.payment.audit.Auditable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "CARD")
@Slf4j
public class Card extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String card_id;
    @Max(value = 16)
    private String pan;
    private String expiry;
    @Transient
    private String cvv;

    @PrePersist
    private void validationBeforePersist(){

        log.info("validationBeforePersist called");


    }
}
