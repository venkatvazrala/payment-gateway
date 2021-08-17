package com.payment.controller;

import com.payment.model.Card;
import com.payment.model.CardHolder;
import com.payment.model.Invoice;
import com.payment.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {

    @MockBean
    private InvoiceRepository invoiceRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PaymentController paymentController;

    @Test
    public void whenInvoicedPresent_thenReturn() {
        // given
         Invoice invoice = create();

        given(invoiceRepository.findById(35678966L))
                .willReturn(java.util.Optional.of(invoice));

        // when
        ResponseEntity<Invoice> response = restTemplate.getForEntity("/api/v1/payment/transaction/35678966", Invoice.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    private Invoice create(){
        Card card = new Card();
        card.setCard_id("100");
        card.setCvv("890");
        card.setExpiry("0912");
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


