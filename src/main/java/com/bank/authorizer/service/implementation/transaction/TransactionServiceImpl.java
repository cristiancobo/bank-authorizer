package com.bank.authorizer.service.implementation.transaction;

import com.bank.authorizer.controller.dto.transaction.stdin.TransactionStdInDto;
import com.bank.authorizer.controller.dto.transaction.stdout.TransactionStdOutDto;
import com.bank.authorizer.model.Account;
import com.bank.authorizer.model.Transaction;
import com.bank.authorizer.repository.account.IAccountRepository;
import com.bank.authorizer.repository.transaction.ITransactionRepository;
import com.bank.authorizer.service.interfaces.transaction.ITransactionService;
import com.bank.authorizer.service.mapper.transaction.ITransactionMapperImpl;
import com.bank.authorizer.service.speout.AccountSpecOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class that represents service of a transaction
 */
@Service
public class TransactionServiceImpl implements ITransactionService {

    ITransactionRepository iTransactionRepository;
    IAccountRepository iAccountRepository;

    /**
     * Constructor
     * @param iTransactionRepository
     * @param iAccountRepository
     */
    @Autowired
    public TransactionServiceImpl(ITransactionRepository iTransactionRepository, IAccountRepository iAccountRepository) {
        this.iTransactionRepository = iTransactionRepository;
        this.iAccountRepository = iAccountRepository;
    }

    /**
     * Method that get a transaction by id
     * @param id
     * @return
     */
    @Override
    public TransactionStdOutDto getTransaction(long id) {
        if(!iTransactionRepository.existsById(id)){
        }
        Transaction transaction = iTransactionRepository.findById(id).get();
        TransactionStdOutDto transactionStdOutDto = ITransactionMapperImpl.INSTANCE.asTransactionToTransactionStdoutDto(transaction);
        return transactionStdOutDto;
    }

    /**
     * Method that save a transaction
     * @param transactionStdInDto
     * @return
     */
    @Override
    public AccountSpecOutDto saveTransaction(TransactionStdInDto transactionStdInDto) {
        Transaction transaction = ITransactionMapperImpl.INSTANCE.asTransactionStdInDtoToTransaction(transactionStdInDto);
        AccountSpecOutDto accountSpecOutDto =  new AccountSpecOutDto();;
        boolean existsAccount = iAccountRepository.existsById(transactionStdInDto.getAccountId());
        if(existsAccount){
            if(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getActiveCard()){
                if(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getAvailableLimit()-transactionStdInDto.getAmount()>=0){
                    if(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getTransactions().size()>=3){
                        Transaction toCompareTransaction =iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getTransactions().get(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getTransactions().size()-3);
                        if(transaction.isRejected(toCompareTransaction.getTime())){
                            accountSpecOutDto.addViolation(Transaction.HIGH_FREQUENCY_SMALL_INTERVAL);
                        }else{
                            if(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().haveSimilarTransactions(transactionStdInDto.getTime(),transactionStdInDto.getTime(),transactionStdInDto.getAmount(),transactionStdInDto.getMerchant())){
                                accountSpecOutDto.addViolation(Transaction.DOUBLED_TRANSACTION);
                            }else{
                                iAccountRepository.findById(transactionStdInDto.getAccountId()).get().addTransaction(transaction);
                                iTransactionRepository.save(transaction);
                                iAccountRepository.findById(transaction.getAccount().getId()).get().decreaseAmount(transactionStdInDto.getAmount());
                                accountSpecOutDto.setAvailableLimit(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getAvailableLimit());
                                accountSpecOutDto.setActiveCard(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getActiveCard());
                                accountSpecOutDto.setAvailableLimit(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getAvailableLimit());
                            }
                        }
                    }else{
                        if(iAccountRepository.findById(transaction.getAccount().getId()).get().haveSimilarTransactions(transactionStdInDto.getTime(),transactionStdInDto.getTime(),transactionStdInDto.getAmount(),transactionStdInDto.getMerchant())){
                            accountSpecOutDto.addViolation(Transaction.DOUBLED_TRANSACTION);
                        }else{
                            iAccountRepository.findById(transactionStdInDto.getAccountId()).get().addTransaction(transaction);
                            iTransactionRepository.save(transaction);
                            iAccountRepository.findById(transaction.getAccount().getId()).get().decreaseAmount(transactionStdInDto.getAmount());
                            accountSpecOutDto.setAvailableLimit(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getAvailableLimit());
                            accountSpecOutDto.setActiveCard(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getActiveCard());
                            accountSpecOutDto.setAvailableLimit(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getAvailableLimit());
                        }
                    }
                }else{
                    accountSpecOutDto.addViolation(Account.INSUFFICIENT_LIMIT);
                    accountSpecOutDto.setActiveCard(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getActiveCard());
                    accountSpecOutDto.setAvailableLimit(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getAvailableLimit());

                }
            }else{
                accountSpecOutDto.addViolation(Account.CARD_NOT_ACTIVE);
                accountSpecOutDto.setActiveCard(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getActiveCard());
                accountSpecOutDto.setAvailableLimit(iAccountRepository.findById(transactionStdInDto.getAccountId()).get().getAvailableLimit());

            }
        }else{
            accountSpecOutDto.addViolation(Account.ACCOUNT_NOT_INITIALIZED);
        }
        return accountSpecOutDto;
    }

    /**
     * Method that get all transactions
     * @return
     */
    @Override
    public List<TransactionStdOutDto> getAll() {
        List<Transaction> transactions = iTransactionRepository.findAll();
        List<TransactionStdOutDto> transactionsStdOutDtos = ITransactionMapperImpl.INSTANCE.asTransactionListEntitiesToTransactionListStdOutDto(transactions);
        return transactionsStdOutDtos;
    }
}