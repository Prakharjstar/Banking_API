package com.javaProject.banking.Repositories;

import com.javaProject.banking.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
