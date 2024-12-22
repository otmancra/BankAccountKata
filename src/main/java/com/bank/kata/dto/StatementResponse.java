package com.bank.kata.dto;

import com.bank.kata.model.OperationType;

import java.time.LocalDate;

public class StatementResponse {
    private LocalDate date;
    private OperationType operationType;
    private double amount;
    private double balance;

    public StatementResponse(LocalDate date, OperationType operationType, double amount, double balance) {
        this.date = date;
        this.operationType = operationType;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
