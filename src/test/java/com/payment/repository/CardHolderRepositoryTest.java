package com.payment.repository;

import com.payment.model.CardHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardHolderRepositoryTest {

    @Autowired
    private CardHolderRepository cardHolderRepository;

    @Test
    public void saveTest() {
        CardHolder cardHolder = new CardHolder();
        cardHolder.setName("admin12334");
        cardHolder.setEmail("a@gmail.com");
        cardHolderRepository.save(cardHolder);
        Iterable<CardHolder> findAll = cardHolderRepository.findAll();
        assertThat(findAll).hasSize(1);
        cardHolderRepository.deleteAll();
    }
}
