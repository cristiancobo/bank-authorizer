package com.bank.authorizer.service.implementation.account;

import com.bank.authorizer.service.speout.AccountSpecOutDto;
import com.bank.authorizer.controller.dto.account.stdin.AccountStdInDto;
import com.bank.authorizer.controller.dto.account.stdout.AccountStdOutDto;
import com.bank.authorizer.model.Account;
import com.bank.authorizer.model.Transaction;
import com.bank.authorizer.repository.account.IAccountRepository;
import com.bank.authorizer.repository.transaction.ITransactionRepository;
import com.bank.authorizer.service.interfaces.account.IAccountService;
import com.bank.authorizer.service.mapper.account.IAccountMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Method that represents service of an account
 */


@Service
public class AccountServiceImpl implements IAccountService {

    private IAccountRepository iAccountRepository;
    private ITransactionRepository iTransactionRepository;


    /**
     * Constructor
     * @param iAccountRepository
     * @param iTransactionRepository
     */
    @Autowired
    public AccountServiceImpl(IAccountRepository iAccountRepository, ITransactionRepository iTransactionRepository) {
        this.iAccountRepository = iAccountRepository;
        this.iTransactionRepository = iTransactionRepository;
    }

    /**
     * Method that get an account by id
     * @param id
     * @return
     */
    @Override
    public AccountStdOutDto getAccount(long id) {
        if(!iAccountRepository.existsById(id)){
        }
        Account account = iAccountRepository.findById(id).get();
        AccountStdOutDto accountStdOutDto = IAccountMapperImpl.INSTANCE.asAccountToAccountStdoutDto(account);
        for (Transaction transaction : account.getTransactions()) {
            accountStdOutDto.addTransaction(transaction);
        }
        return accountStdOutDto;
    }

    /**
     * Method that save an account
     * @param accountStdInDto
     * @return
     */
    @Override
    public AccountSpecOutDto saveAccount(AccountStdInDto accountStdInDto) {
        Account account = IAccountMapperImpl.INSTANCE.asAccountStdInDtotoAccount(accountStdInDto);
        AccountSpecOutDto accountSpecOutDto = IAccountMapperImpl.INSTANCE.asAccountToAccountSpecOutDto(account);
        if(!iAccountRepository.existsById(account.getId())){
            iAccountRepository.save(account);
            return accountSpecOutDto;
        }
        accountSpecOutDto.addViolation(Account.ACCOUNT_ALREADY_INITIALIZED);
        return accountSpecOutDto;

    }

    /**
     * Method that get all accounts
     * @return
     */
    @Override
    public List<AccountStdOutDto> getAll() {
        List<Account> accounts = iAccountRepository.findAll();
        List<AccountStdOutDto> accountStdOutDtos = IAccountMapperImpl.INSTANCE.asAccountListEntitiesToAccountListStdOutDto(accounts);
        for (int i =0 ; i < accountStdOutDtos.size() ; i++){
            accountStdOutDtos.get(i).setTransactionsAccount(accounts.get(i).getTransactions());
        }
        return accountStdOutDtos;
    }
}