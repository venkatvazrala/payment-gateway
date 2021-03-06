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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Test
    public void saveTest() {
        Card card = new Card();
        card.setCard_id("100");
        card.setCvv("890");
        card.setExpiry("0912");
        card.setPan("1234567812345678");
        cardRepository.save(card);
        Iterable<Card> findAll = cardRepository.findAll();
        assertThat(findAll).hasSize(1);
        cardRepository.deleteAll();
    }
}
