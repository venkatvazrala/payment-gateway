package com.payment.validator;

import com.payment.model.Invoice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class AttributeValidator {
    public void validate(Invoice invoice, Errors errors) {
        if (StringUtils.isBlank(invoice.getCardHolder().getEmail())) {
            errors.reject("Email should not be empty");

        }
        if (StringUtils.isBlank(invoice.getCard().getPan())) {
            errors.reject("Card should not be empty");

        }
        if (StringUtils.isBlank(invoice.getCard().getExpiry())) {
            errors.reject("Expiry should not be empty");

        }
        if (StringUtils.isBlank(invoice.getCard().getCvv())) {
            errors.reject("CVV should not be empty");

        }
        if (StringUtils.isBlank(invoice.getCurrency())) {
            errors.reject("Currency should not be empty");

        }
        if (StringUtils.isBlank(invoice.getCardHolder().getName())) {
            errors.reject("Name should not be empty");

        }
    }
}
