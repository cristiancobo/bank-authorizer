package com.bank.authorizer.service.interfaces.transaction;

import com.bank.authorizer.service.speout.AccountSpecOutDto;
import com.bank.authorizer.controller.dto.transaction.stdin.TransactionStdInDto;
import com.bank.authorizer.controller.dto.transaction.stdout.TransactionStdOutDto;

import java.util.List;

public interface ITransactionService {

    public TransactionStdOutDto getTransaction(long id);
    public AccountSpecOutDto saveTransaction(TransactionStdInDto transactionStdInDto);
    public List<TransactionStdOutDto> getAll();
}
