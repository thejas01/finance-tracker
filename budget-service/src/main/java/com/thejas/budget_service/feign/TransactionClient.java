package com.thejas.budget_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thejas.budget_service.entity.Transaction;

import java.util.List;

@FeignClient(name = "transaction-service", path = "/transactions")
public interface TransactionClient {
    @GetMapping("/user/{userId}")
    List<Transaction> getTransactionsByUserId(@PathVariable Long userId);
}