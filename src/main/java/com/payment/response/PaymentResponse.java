package com.payment.response;

import lombok.*;

import javax.validation.constraints.NotBlank;


@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NotBlank
public class PaymentResponse {
    private String approve;

}
