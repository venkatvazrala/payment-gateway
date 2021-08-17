package com.payment.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvoiceTest {
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void whenCurrencyIsNotPresent_thenReturnViolation() {
        Invoice invoice = new Invoice();
        invoice.setAmount(0);
        invoice.setCurrency("");
        Set<ConstraintViolation<Invoice>> violations = validator.validate(invoice);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenCurrencyIsPresent_thenShouldNotReturnViolation() {
        Invoice invoice = new Invoice();
        invoice.setAmount(0);
        invoice.setCurrency("1111");
        Set<ConstraintViolation<Invoice>> violations = validator.validate(invoice);
        assertTrue(violations.isEmpty());
    }
}
