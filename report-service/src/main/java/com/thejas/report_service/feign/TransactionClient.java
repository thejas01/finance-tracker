package com.thejas.report_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.thejas.report_service.model.Transaction;

import java.util.List;

@FeignClient(name = "transaction-service", path = "/transactions")
public interface TransactionClient {
    @GetMapping
    List<Transaction> getAllTransactions();

    @GetMapping("/byType")
    List<Transaction> getTransactionsByType(@RequestParam String type);

     @GetMapping("/user/{userId}")
    List<Transaction> getTransactionsByUserId(@PathVariable ("userId") Long userId);
}
