package com.bank.authorizer.service.mapper.account;

import com.bank.authorizer.service.speout.AccountSpecOutDto;
import com.bank.authorizer.controller.dto.account.stdin.AccountStdInDto;
import com.bank.authorizer.controller.dto.account.stdout.AccountStdOutDto;
import com.bank.authorizer.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IAccountMapper {
    IAccountMapper INSTANCE = Mappers.getMapper(IAccountMapper.class);
    AccountStdOutDto asAccountToAccountStdoutDto(Account account);
    AccountSpecOutDto asAccountToAccountSpecOutDto(Account account);
    Account asAccountStdInDtotoAccount(AccountStdInDto accountStdInDto);
    List<AccountStdOutDto> asAccountListEntitiesToAccountListStdOutDto(List<Account> accounts);
    List<AccountSpecOutDto> asAccountListEntitiesToAccountListSpecOutDto(List<Account> accounts);
}
