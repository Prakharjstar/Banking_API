package com.javaProject.banking.Services;
import com.javaProject.banking.dto.AccountDto;

public interface AccountService {

    AccountDto createAccount(AccountDto accountdto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id , double amount);
}
