package com.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.model.Card;
import com.payment.model.CardHolder;
import com.payment.model.Invoice;
import com.payment.repository.InvoiceRepository;
import com.payment.service.PaymentResponseFormatter;
import com.payment.validator.AttributeValidator;
import com.payment.validator.PaymentRequestValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = PaymentController.class)
public class PaymentControllerTest {

    @MockBean
    private InvoiceRepository invoiceRepository;


    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    PaymentRequestValidator paymentRequestValidator;

    @MockBean
    private PaymentResponseFormatter paymentResponseFormatter;

    @MockBean
    AttributeValidator attributeValidator;

    @Test
    public void givenValidInvoice_whenCallingMethod_thenReturnCreatedStatus() throws Exception {

        Invoice invoice = create();

        Mockito.when(invoiceRepository.save(Mockito.any(Invoice.class))).thenReturn(invoice);
        Mockito.when(paymentRequestValidator.validate(Mockito.any(String.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payment/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(invoice).getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Assertions.assertThat(result).isNotNull();

    }

    @Test
    public void givenValidInvoiceId_whenCallingMethod_thenReturnOkStatus() throws Exception {

        Mockito.when(invoiceRepository.findById(any())).thenReturn(Optional.of(create()));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/payment/transaction/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
    }
    private Invoice create(){
        Card card = new Card();
        card.setCard_id("100");
        card.setCvv("890");
        card.setExpiry("1021");
        card.setPan("12345678903555");

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


