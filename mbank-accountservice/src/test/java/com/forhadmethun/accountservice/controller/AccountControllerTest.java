package com.forhadmethun.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forhadmethun.accountservice.db.entity.Account;
import com.forhadmethun.accountservice.db.services.AccountService;
import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/accounts", 42L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(
                        CustomerDto.builder()
                                .customerId(1L)
                                .country("Bangladesh")
                                .currencies(Arrays.asList("EUR"))
                                .build()
                )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.balances", notNullValue()));
    }

    @Test
    void getAccount() throws Exception {
        Account account = new Account(1L, 1L);
        accountService.createAccount(account);
        mockMvc.perform(get("/accounts/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId", is(1)))
                .andExpect(jsonPath("$.balances", notNullValue()));
    }
}
