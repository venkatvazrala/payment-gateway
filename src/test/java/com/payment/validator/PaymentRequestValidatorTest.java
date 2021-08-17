package com.payment.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

;

public class PaymentRequestValidatorTest {

    PaymentRequestValidator paymentRequestValidator ;

    @Before
    public void setUp() {
        paymentRequestValidator = new PaymentRequestValidator();
    }

    @Test
    public void givenValidCard_whenCallingMethod_thenReturn() throws Exception {

        boolean validCard =paymentRequestValidator.validate("1456789");
        assertTrue(validCard);

    }

    @Test
    public void givenInValidCard_whenCallingMethod_thenReturn() throws Exception {

        boolean validCard =paymentRequestValidator.validate("1234567812345678");
        assertFalse(validCard);

    }

    @Test
    public void givenPastExpiryDate_whenCallingMethod_thenReturn() throws Exception {

        boolean validateExpiry =paymentRequestValidator.validateExpiry("0621");
        assertTrue(validateExpiry);

    }

    @Test
    public void givenFutureExpiryDate_whenCallingMethod_thenReturn() throws Exception {
        boolean validateExpiry =paymentRequestValidator.validateExpiry("1021");
        assertFalse(validateExpiry);

    }

    @Test
    public void givenCurrentExpiryDate_whenCallingMethod_thenReturn() throws Exception {
        boolean validateExpiry = paymentRequestValidator.validateExpiry("0821");
        assertFalse(validateExpiry);

    }
}
