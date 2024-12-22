package com.bank.kata.service;

import com.bank.kata.dto.StatementResponse;

import java.util.List;

public interface AccountService {
    void deposit(double amount);

    void withdraw(double amount);

    List<StatementResponse> getStatement();
}
