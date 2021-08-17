package com.payment.model;

import com.payment.audit.Auditable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="CARD_HOLDER")
@Slf4j
public class CardHolder extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long card_holder_id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

}
