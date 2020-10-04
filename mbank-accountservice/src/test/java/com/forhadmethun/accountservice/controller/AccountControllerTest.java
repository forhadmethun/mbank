package com.forhadmethun.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forhadmethun.accountservice.db.services.CustomerService;
import com.forhadmethun.accountservice.utility.dto.model.CustomerDto;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAccount() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders
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
                .andReturn();
        String contentAsString = mockMvcResult.getResponse().getContentAsString();
        AccountOperationResponse response = objectMapper.readValue(contentAsString, AccountOperationResponse.class);
        assertEquals(1L, response.getCustomerId());
        assertEquals(1, response.getBalances().size());
        assertEquals("EUR", response.getBalances().get(0).getCurrency());
        assertEquals(BigDecimal.ZERO, response.getBalances().get(0).getBalance());
    }

    @Test
    void getAccount() throws Exception {
        AccountOperationResponse createdAccountObject =
                customerService.createCustomer(
                        CustomerDto.builder()
                                .customerId(36L)
                                .country("Bangladesh")
                                .currencies(Arrays.asList("EUR"))
                                .build()
                );
        MvcResult mockMvcResult  =
                mockMvc.perform(get("/accounts/" + createdAccountObject.getAccountId()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balances[0].currency", is("EUR"))
                ).andReturn();
        String contentAsString = mockMvcResult.getResponse().getContentAsString();
        AccountOperationResponse response = objectMapper.readValue(contentAsString, AccountOperationResponse.class);
        assertEquals(response.getAccountId(), createdAccountObject.getAccountId());
        assertEquals(response.getCustomerId(), createdAccountObject.getCustomerId());
        assertEquals(response.getBalances().size(), createdAccountObject.getBalances().size());
        assertEquals("EUR", response.getBalances().get(0).getCurrency());
    }
}
