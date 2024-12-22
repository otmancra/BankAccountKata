package com.bank.kata.controller;

import com.bank.kata.dto.StatementResponse;
import com.bank.kata.dto.TransactionRequest;
import com.bank.kata.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody TransactionRequest request) {
        accountService.deposit(request.getAmount());
        return ResponseEntity.ok("Deposit successful");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody TransactionRequest request) {
        accountService.withdraw(request.getAmount());
        return ResponseEntity.ok("Withdrawal successful");
    }

    @GetMapping("/statement")
    public List<StatementResponse> getStatement() {
        return accountService.getStatement();
    }
}
