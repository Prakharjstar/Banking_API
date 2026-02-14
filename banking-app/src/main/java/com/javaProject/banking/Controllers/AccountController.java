package com.javaProject.banking.Controllers;

import com.javaProject.banking.Entity.Account;
import com.javaProject.banking.Services.AccountService;
import com.javaProject.banking.dto.AccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Get Account by Id
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //Add Account
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody  AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto) , HttpStatus.CREATED);
    }

    //Deposit
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit( @PathVariable Long id , @RequestBody  Map<String,Double> request){

        Double amount = request.get("amount");
       AccountDto accountDto= accountService.deposit(id,amount);
       return ResponseEntity.ok(accountDto);

    }
}
