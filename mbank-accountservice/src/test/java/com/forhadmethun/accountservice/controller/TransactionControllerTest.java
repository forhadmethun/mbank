package com.forhadmethun.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forhadmethun.accountservice.db.services.AccountService;
import com.forhadmethun.accountservice.db.services.CustomerService;
import com.forhadmethun.accountservice.db.services.TransactionService;
import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;
import com.forhadmethun.accountservice.utility.dto.model.DirectionOfTransaction;
import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;
import com.forhadmethun.accountservice.utility.io.AccountOperationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTransaction() throws Exception {
        AccountOperationResponse createdAccountObject =
                customerService.createCustomer(
                        CustomerDto.builder()
                                .customerId(1L)
                                .country("Bangladesh")
                                .currencies(Arrays.asList("EUR"))
                                .build()
                );
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/transactions", 42L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(
                        TransactionDto.builder()
                                .accountId(createdAccountObject.getAccountId())
                                .amount(BigDecimal.valueOf(50L))
                                .currency("EUR")
                                .directionOfTransaction(DirectionOfTransaction.IN)
                                .description("Cash in")
                                .build()
                )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currency", is("EUR")))
                .andReturn();
        String contentAsString = mockMvcResult.getResponse().getContentAsString();
        TransactionDto response = objectMapper.readValue(contentAsString, TransactionDto.class);
        assertEquals(response.getAccountId(), createdAccountObject.getAccountId());
        assertEquals(response.getAmount(), BigDecimal.valueOf(50L));
        assertEquals(response.getDirectionOfTransaction(), DirectionOfTransaction.IN);
    }

    @Test
    void getTransactions() throws Exception {
        AccountOperationResponse accountOperation =
                customerService.createCustomer(
                        CustomerDto.builder()
                                .customerId(1L)
                                .country("Bangladesh")
                                .currencies(Arrays.asList("EUR"))
                                .build()
                );

        TransactionDto transactionDto =
                TransactionDto.builder()
                        .accountId(accountOperation.getAccountId())
                        .amount(BigDecimal.valueOf(50L))
                        .currency("EUR")
                        .directionOfTransaction(DirectionOfTransaction.IN)
                        .description("Cash in")
                        .build();

        TransactionDto savedTransactionDto = transactionService.createTransaction(transactionDto);
        mockMvc.perform(get("/transactions/" + savedTransactionDto.getAccountId()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].currency", is("EUR")))
                .andExpect(jsonPath("$.[0].description", is("Cash in")));

    }
}
