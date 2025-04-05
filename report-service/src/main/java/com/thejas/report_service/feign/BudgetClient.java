package com.thejas.report_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.thejas.report_service.model.Budget;

import java.util.List;

@FeignClient(name = "budget-service", path = "/budgets")
public interface BudgetClient {
    @GetMapping
    List<Budget> getAllBudgets();
}