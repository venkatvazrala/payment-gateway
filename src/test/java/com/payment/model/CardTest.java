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

public class CardTest {
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void whenCurrencyIsNotPresent_thenReturnViolation() {
        Card card = new Card();
        card.setPan("");
        card.setExpiry("");
        card.setCvv("");
        Set<ConstraintViolation<Card>> violations = validator.validate(card);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenCurrencyIsPresent_thenShouldNotReturnViolation() {
        Card card = new Card();
        card.setPan("1234567812345678");
        card.setExpiry("0921");
        card.setCvv("789");
        Set<ConstraintViolation<Card>> violations = validator.validate(card);
        assertTrue(violations.isEmpty());
    }
}
