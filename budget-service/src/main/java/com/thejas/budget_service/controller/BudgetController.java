package com.thejas.budget_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.thejas.budget_service.entity.Budget;
import com.thejas.budget_service.service.BudgetService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.setBudget(budget);
    }

    @GetMapping("/{category}/{month}")
    public Optional<Double> getBudget(@PathVariable String category, @PathVariable String month) {
        return budgetService.getBudgetForCategory(category, month);
    }
    @GetMapping // This is the new endpoint
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }
    @GetMapping("/user/{userId}")
    public List<Budget> getUserBudgets(@PathVariable Long userId) {
        return budgetService.getUserBudgets(userId);
    }
    @GetMapping("/status/{userId}")
    public Map<String, Double> getBudgetStatus(@PathVariable Long userId) {
        return budgetService.getBudgetStatus(userId);
    }

    @GetMapping("/alerts/{userId}")
    public List<String> checkOverspending(@PathVariable Long userId) {
        return budgetService.checkOverspending(userId);
    }
    


}