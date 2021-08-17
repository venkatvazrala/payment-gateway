package com.payment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.model.Card;
import com.payment.model.CardHolder;
import com.payment.model.Invoice;
import com.payment.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {

    @MockBean
    private InvoiceRepository invoiceRepository;

    @Autowired
    private TestRestTemplate restTemplate;

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
        //assertThat(response.getBody().equals(new SuperHero("Rob", "Mannon", "RobotMan")));
    }

    @Test
    public void whenInvoicedPresent1_thenReturn() throws JsonProcessingException {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Invoice invoice = create();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(invoice);
        HttpEntity<Invoice> requestEntity = new HttpEntity(json, headers);

        System.out.println(json);
        // when
        ResponseEntity<Invoice> response = restTemplate.postForEntity("/api/v1/payment/validate",
                requestEntity, Invoice.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        //assertThat(response.getBody().equals(new SuperHero("Rob", "Mannon", "RobotMan")));
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


