package com.bank.authorizer.service.interfaces.account;

import com.bank.authorizer.service.speout.AccountSpecOutDto;
import com.bank.authorizer.controller.dto.account.stdin.AccountStdInDto;
import com.bank.authorizer.controller.dto.account.stdout.AccountStdOutDto;

import java.util.List;

public interface IAccountService {

    public AccountStdOutDto getAccount(long id);

    public AccountSpecOutDto saveAccount(AccountStdInDto accountStdInDto);

    public List<AccountStdOutDto> getAll();
}
