package com.forhadmethun.accountservice.controller;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/health")
    public String healthCheck() {
        return "OK - mbank.accountservice";
    }
}
