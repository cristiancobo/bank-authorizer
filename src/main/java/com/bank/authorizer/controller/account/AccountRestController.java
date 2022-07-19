package com.bank.authorizer.controller.account;

import com.bank.authorizer.controller.dto.account.stdin.AccountStdInDto;
import com.bank.authorizer.controller.dto.account.stdout.AccountStdOutDto;
import com.bank.authorizer.service.interfaces.account.IAccountService;
import com.bank.authorizer.service.speout.AccountSpecOutDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class that represents accont controller
 */
@RestController
@RequestMapping("/api/v1.0/accounts")
public class AccountRestController {
    private IAccountService iAccountService;

    /**
     * Methods
     */
    @Autowired
    public AccountRestController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Get an account by id")
    public ResponseEntity<AccountStdOutDto> getAccount(@PathVariable long id){
        return new ResponseEntity<AccountStdOutDto>(iAccountService.getAccount(id), HttpStatus.OK);
    }
    @ApiOperation(value = "Save an account")
    @PostMapping("/")
    public ResponseEntity<AccountSpecOutDto> saveAccount(@RequestBody AccountStdInDto accountStdInDto){
        return new ResponseEntity<AccountSpecOutDto>(iAccountService.saveAccount(accountStdInDto),HttpStatus.OK);
    }
    @ApiOperation(value = "Get all accounts ")
    @GetMapping("/")
    public ResponseEntity<List<AccountStdOutDto>> getAll(){
        return new ResponseEntity<List<AccountStdOutDto>>(iAccountService.getAll(), HttpStatus.OK);
    }

}