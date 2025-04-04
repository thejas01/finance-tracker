package com.thejas.budget_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.thejas.budget_service.entity.Budget;
import com.thejas.budget_service.repository.BudgetRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;

    public Budget setBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    // public Optional<Budget> getBudgetForCategory(String category, String month) {
    //     return budgetRepository.findByCategoryAndMonth(category, month);
    // }

    // public Optional<Budget> getBudgetForCategory(String category, String month) {
    //     List<Budget> budgets = budgetRepository.findByCategoryAndMonth(category, month);
    //     if (budgets.size() > 1) {
    //         throw new IllegalStateException("Multiple budgets found for category: " + category + " and month: " + month);
    //     }
    //     return budgets.stream().findFirst();
    // }
    public Optional<Double> getBudgetForCategory(String category, String month) {
        List<Budget> budgets = budgetRepository.findByCategoryAndMonth(category, month);
        if (budgets.size() > 1) {
            throw new IllegalStateException("Multiple budgets found for category: " + category + " and month: " + month);
        }
        return budgets.stream().findFirst().map(Budget::getLimitAmount);
    }
}