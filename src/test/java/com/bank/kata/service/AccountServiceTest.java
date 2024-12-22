package com.bank.kata.service;

import com.bank.kata.exception.InsufficientFundsException;
import com.bank.kata.exception.InvalidTransactionException;
import com.bank.kata.model.OperationType;
import com.bank.kata.model.Transaction;
import com.bank.kata.service.imp.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountServiceTest {
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
    }

    @Test
    void deposit_ShouldIncreaseBalanceAndAddTransaction_WhenAmountIsValid() throws Exception {
        double amount = 200.0;

        accountService.deposit(amount);

        double balance = getPrivateField(accountService, "balance");
        List<Transaction> transactions = getPrivateField(accountService, "transactions");

        assertEquals(200.0, balance, "Balance should be updated after deposit");
        assertEquals(1, transactions.size(), "There should be one transaction recorded");

        Transaction transaction = transactions.get(0);
        assertEquals(OperationType.DEPOSIT, transaction.getOperationType());
        assertEquals(200.0, transaction.getAmount());
        assertEquals(200.0, transaction.getBalance());
    }

    @Test
    void withdraw_ShouldDecreaseBalanceAndAddTransaction_WhenAmountIsValid() throws Exception {
        accountService.deposit(300.0);
        double withdrawAmount = 100.0;

        accountService.withdraw(withdrawAmount);

        double balance = getPrivateField(accountService, "balance");
        List<Transaction> transactions = getPrivateField(accountService, "transactions");

        assertEquals(200.0, balance, "Balance should decrease after withdrawal");

        assertEquals(2, transactions.size(), "There should be two transactions recorded");

        Transaction transaction = transactions.get(1);
        assertEquals(OperationType.WITHDRAW, transaction.getOperationType());
        assertEquals(100.0, transaction.getAmount());
        assertEquals(200.0, transaction.getBalance());
    }

    @Test
    void deposit_ShouldThrowException_WhenAmountIsZeroOrNegative() {
        assertThrows(InvalidTransactionException.class, () -> accountService.deposit(0),
                "An exception should be thrown for zero deposit");
        assertThrows(InvalidTransactionException.class, () -> accountService.deposit(-100),
                "An exception should be thrown for negative deposit");
    }

    @Test
    void withdraw_ShouldThrowException_WhenAmountExceedsBalance() {
        accountService.deposit(100.0);

        assertThrows(InsufficientFundsException.class, () -> accountService.withdraw(200),
                "An exception should be thrown when withdrawing more than the balance");
    }

    @Test
    void withdraw_ShouldThrowException_WhenAmountIsZeroOrNegative() {
        assertThrows(InvalidTransactionException.class, () -> accountService.withdraw(0),
                "An exception should be thrown for zero withdrawal");
        assertThrows(InvalidTransactionException.class, () -> accountService.withdraw(-50),
                "An exception should be thrown for negative withdrawal");
    }

    @SuppressWarnings("unchecked")
    private <T> T getPrivateField(Object instance, String fieldName) throws Exception {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(instance);
    }
}