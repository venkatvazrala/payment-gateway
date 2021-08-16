package com.payment.repository;

import com.payment.model.CardHolder;
import com.payment.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardHolderRepository extends CrudRepository<CardHolder,Long> {
}
