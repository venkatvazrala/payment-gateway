package com.payment.repository;

import com.payment.model.CardHolder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        Assert.assertNotNull(cardHolderRepository.findByName("admin12334"));
    }
}
