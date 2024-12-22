package com.bank.kata.model;

import java.time.LocalDate;

public class Transaction {
    private OperationType operationType;
    private double amount;
    private double balance;
    private LocalDate date;

    public Transaction(OperationType operationType, double amount, double balance, LocalDate date) {
        this.operationType = operationType;
        this.amount = amount;
        this.balance = balance;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
