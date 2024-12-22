package com.bank.kata.service.imp;

import com.bank.kata.dto.StatementResponse;
import com.bank.kata.exception.InsufficientFundsException;
import com.bank.kata.exception.InvalidTransactionException;
import com.bank.kata.model.OperationType;
import com.bank.kata.model.Transaction;
import com.bank.kata.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private double balance = 0.0;
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void deposit(double amount) {
        validateAmount(amount);
        balance += amount;
        transactions.add(new Transaction(OperationType.DEPOSIT, amount, balance, LocalDate.now()));
    }

    @Override
    public void withdraw(double amount) {
        validateAmount(amount);
        if (balance < amount) throw new InsufficientFundsException("Insufficient balance");
        balance -= amount;
        transactions.add(new Transaction(OperationType.WITHDRAW, amount, balance, LocalDate.now()));
    }

    @Override
    public List<StatementResponse> getStatement() {
        return transactions.stream()
                .map(t -> new StatementResponse(t.getDate(), t.getOperationType(), t.getAmount(), t.getBalance()))
                .collect(Collectors.toList());
    }
    private void validateAmount(double amount) {
        if (amount <= 0) throw new InvalidTransactionException("Amount must be greater than 0");
    }
}
