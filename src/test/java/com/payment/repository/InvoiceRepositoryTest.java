package com.payment.repository;

import com.payment.model.Card;
import com.payment.model.CardHolder;
import com.payment.model.Invoice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    public void saveTest() {
        Card card = new Card();
        card.setCvv("890");
        card.setExpiry("0912");
        card.setPan("1234567812345678");

        CardHolder cardHolder = new CardHolder();
        cardHolder.setName("admin12334");
        cardHolder.setEmail("a@gmail.com");

        Invoice invoice = new Invoice();
        invoice.setInvoice_id(100);
        invoice.setAmount(100);
        invoice.setCurrency("EUR");
        invoice.setCard(card);
        invoice.setCardHolder(cardHolder);

        invoiceRepository.save(invoice);

        Assert.assertNotNull(invoiceRepository.findById(100L));
    }
}
