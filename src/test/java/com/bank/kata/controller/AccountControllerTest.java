package com.bank.kata.controller;

import com.bank.kata.dto.StatementResponse;
import com.bank.kata.dto.TransactionRequest;
import com.bank.kata.model.OperationType;
import com.bank.kata.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @Test
    public void testDeposit() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        TransactionRequest request = new TransactionRequest();
        request.setAmount(100.0);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/account/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void testWithdraw() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        ObjectMapper objectMapper = new ObjectMapper();
        TransactionRequest withdrawRequest = new TransactionRequest();
        withdrawRequest.setAmount(50.0);

        mockMvc.perform(post("/api/account/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(withdrawRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetStatement() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        StatementResponse statement1 = new StatementResponse(
                LocalDate.now(),
                OperationType.DEPOSIT,
                100.0,
                100.0
        );

        StatementResponse statement2 = new StatementResponse(
                LocalDate.now(),
                OperationType.WITHDRAW,
                50.0,
                50.0
        );

        List<StatementResponse> mockStatement = List.of(statement1, statement2);
        when(accountService.getStatement()).thenReturn(mockStatement);

        mockMvc.perform(get("/api/account/statement")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)) // Validate the size of the response
                .andExpect(jsonPath("$[0].operationType").value("DEPOSIT"))
                .andExpect(jsonPath("$[0].amount").value(100.0))
                .andExpect(jsonPath("$[0].balance").value(100.0))
                .andExpect(jsonPath("$[1].operationType").value("WITHDRAW"))
                .andExpect(jsonPath("$[1].amount").value(50.0))
                .andExpect(jsonPath("$[1].balance").value(50.0));
    }
}