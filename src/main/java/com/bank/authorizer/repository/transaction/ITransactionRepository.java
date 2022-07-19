package com.bank.authorizer.repository.transaction;

import com.bank.authorizer.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<Transaction,Long> {
}
