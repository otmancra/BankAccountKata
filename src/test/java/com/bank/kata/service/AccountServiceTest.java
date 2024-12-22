package com.bank.kata.service;

import com.bank.kata.exception.InvalidTransactionException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void testDeposit() {
        accountService.deposit(100.0);
    }

    @Test
    void deposit_ShouldThrowException_WhenAmountIsZeroOrNegative() {
        final double invalidAmount = 0.0;

        assertThrows(InvalidTransactionException.class, () -> accountService.deposit(invalidAmount),
                "An exception should be thrown for zero or negative amounts");

        final double invalidAmount2 = -50.0;
        assertThrows(InvalidTransactionException.class, () -> accountService.deposit(invalidAmount2),
                "An exception should be thrown for zero or negative amounts");
    }
}
