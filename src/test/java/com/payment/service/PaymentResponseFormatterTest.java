package com.payment.service;

import com.payment.model.Card;
import com.payment.model.CardHolder;
import com.payment.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;


public class PaymentResponseFormatterTest {

    PaymentResponseFormatter paymentResponseFormatter ;

    @Before
    public void setUp() {
        paymentResponseFormatter = new PaymentResponseFormatter();
    }

    @Test
    public void whenNameAndEmailIsNotPresent_thenReturnViolation() throws Exception {
        Invoice formatInvoice =paymentResponseFormatter.format(create());
        assertThat(formatInvoice.getCard().getExpiry()).contains("*");

    }

    @Test
    public void givenName_whenCallingMask_thenReturn() throws Exception {
        Invoice formatInvoice =paymentResponseFormatter.format(create());
        assertThat(formatInvoice.getCardHolder().getName()).contains("*");

    }

    @Test
    public void givenCard_whenCallingMask_thenReturn() throws Exception {
        Invoice formatInvoice =paymentResponseFormatter.format(create());
        assertThat(formatInvoice.getCard().getPan()).contains("*");

    }

    private Invoice create(){
        Card card = new Card();
        card.setCard_id("100");
        card.setCvv("890");
        card.setExpiry("1912");
        card.setPan("1234567812345678");

        CardHolder cardHolder = new CardHolder();
        cardHolder.setName("admin12334");
        cardHolder.setCard_holder_id(123);
        cardHolder.setEmail("a@gmail.com");

        Invoice invoice = new Invoice();
        invoice.setInvoice_id(1);
        invoice.setAmount(100);
        invoice.setCurrency("EUR");
        invoice.setCard(card);
        invoice.setCardHolder(cardHolder);

        return invoice;
    }
}
