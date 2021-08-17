package com.payment.model;

import com.payment.audit.Auditable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
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


    @NotBlank(message = "PAN is mandatory")
    private String pan;

    @NotBlank(message = "Expiry is mandatory")
    private String expiry;

    @Transient
    private String cvv;


}
