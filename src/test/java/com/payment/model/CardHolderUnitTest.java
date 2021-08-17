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

public class CardHolderUnitTest {
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void whenNameAndEmailIsNotPresent_thenReturnViolation() {
        CardHolder cardHolder = new CardHolder();
        cardHolder.setName("");
        cardHolder.setEmail("");
       
        Set<ConstraintViolation<CardHolder>> violations = validator.validate(cardHolder);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenNameIsNotPresent_thenReturnViolation() {
        CardHolder cardHolder = new CardHolder();
        cardHolder.setName("");
        cardHolder.setEmail("tom@gmail.com");

        Set<ConstraintViolation<CardHolder>> violations = validator.validate(cardHolder);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenEmailIsNotPresent_thenReturnViolation() {
        CardHolder cardHolder = new CardHolder();
        cardHolder.setName("Tom");
        cardHolder.setEmail("");

        Set<ConstraintViolation<CardHolder>> violations = validator.validate(cardHolder);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenNameAndEmailIsPresent_thenReturnViolation() {
        CardHolder cardHolder = new CardHolder();
        cardHolder.setName("Tom");
        cardHolder.setEmail("tom@gmail.com");

        Set<ConstraintViolation<CardHolder>> violations = validator.validate(cardHolder);
        assertTrue(violations.isEmpty());
    }
}
