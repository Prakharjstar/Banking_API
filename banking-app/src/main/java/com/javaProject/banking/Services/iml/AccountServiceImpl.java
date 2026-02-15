package com.javaProject.banking.Services.iml;

import com.javaProject.banking.Entity.Account;
import com.javaProject.banking.Mapper.AccountMapper;
import com.javaProject.banking.Repositories.AccountRepository;
import com.javaProject.banking.Services.AccountService;
import com.javaProject.banking.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountdto) {
        Account account = AccountMapper.maptoAccount(accountdto);
        Account saveAccount =  accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account =accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not Exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not Exist"));
        double total= account.getBalance() + amount;
         account.setBalance(total);
         Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account =accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient Amount");
        }
        double total = account.getBalance()-amount;
         account.setBalance(total);
         Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public List<AccountDto> getAll() {
        List<Account> accounts = accountRepository.findAll();
      return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public AccountDto deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
        accountRepository.deleteById(id);
        return AccountMapper.mapToAccountDto(account);
    }
}
