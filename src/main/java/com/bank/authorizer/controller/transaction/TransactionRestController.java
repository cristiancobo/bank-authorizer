package com.bank.authorizer.controller.transaction;

import com.bank.authorizer.controller.dto.transaction.stdin.TransactionStdInDto;
import com.bank.authorizer.controller.dto.transaction.stdout.TransactionStdOutDto;
import com.bank.authorizer.service.interfaces.transaction.ITransactionService;
import com.bank.authorizer.service.speout.AccountSpecOutDto;
import com.bank.authorizer.util.exception.InvalidDataException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * class that represents a transaction controller
 */
@RestController
@RequestMapping("/api/v1.0/transactions")
public class TransactionRestController {

    private ITransactionService iTransactionService;
    /**
     * Methods
     */
    @Autowired
    public TransactionRestController(ITransactionService iTransactionService) {
        this.iTransactionService = iTransactionService;
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a transaction by id")
    public ResponseEntity<TransactionStdOutDto> getTransaction(@PathVariable int id){
        return new ResponseEntity<TransactionStdOutDto>(iTransactionService.getTransaction(id), HttpStatus.OK);

    }
    @PostMapping("/")
    @ApiOperation(value = "Save a transaction")
    public ResponseEntity<AccountSpecOutDto> saveTransaction(@Valid @RequestBody TransactionStdInDto transactionStdInDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            throw new InvalidDataException(bindingResult);
        }
        return new ResponseEntity<AccountSpecOutDto>(iTransactionService.saveTransaction(transactionStdInDto),HttpStatus.OK);
    }
    @GetMapping("/")
    @ApiOperation(value = "Get all transactions")
    public ResponseEntity<List<TransactionStdOutDto>> getAll(){
        return new ResponseEntity<List<TransactionStdOutDto>>(iTransactionService.getAll(), HttpStatus.OK);
    }
}