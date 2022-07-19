package com.bank.authorizer.service.mapper.transaction;

import com.bank.authorizer.controller.dto.transaction.stdin.TransactionStdInDto;
import com.bank.authorizer.controller.dto.transaction.stdout.TransactionStdOutDto;
import com.bank.authorizer.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ITransactionMapper {

    ITransactionMapper INSTANCE = Mappers.getMapper(ITransactionMapper.class);
    TransactionStdOutDto asTransactionToTransactionStdoutDto(Transaction transaction);
    Transaction asTransactionStdInDtoToTransaction(TransactionStdInDto transactionStdInDto);
    List<TransactionStdOutDto> asTransactionListEntitiesToTransactionListStdOutDto(List<Transaction> transactions);
}
