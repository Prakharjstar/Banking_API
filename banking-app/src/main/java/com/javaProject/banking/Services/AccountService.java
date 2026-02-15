package com.javaProject.banking.Services;
import com.javaProject.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountdto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id , double amount);
    AccountDto withdraw(Long id , double amount);
    List<AccountDto> getAll();
    AccountDto deleteAccount(Long id);
}
