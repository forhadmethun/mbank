package com.forhadmethun.accountservice;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MbankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbankAccountServiceApplication.class, args);
    }

}
