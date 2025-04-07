package com.thejas.budget_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.thejas.budget_service.entity.Budget;
import com.thejas.budget_service.entity.Transaction;
import com.thejas.budget_service.feign.TransactionClient;
import com.thejas.budget_service.repository.BudgetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final TransactionClient transactionClient;

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
//     public Budget getBudgetForCategory(String category, String month) {
//     List<Budget> budgets = budgetRepository.findByCategoryAndMonth(category, month);
//     if (budgets.isEmpty()) {
//         throw new NoSuchElementException("No budget found for category: " + category + " and month: " + month);
//     } else if (budgets.size() > 1) {
//         throw new IllegalStateException("Multiple budgets found for category: " + category + " and month: " + month);
//     }
//     return budgets.get(0);
// }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public List<Budget> getUserBudgets(Long userId) {
        return budgetRepository.findByUserId(userId);
    }

    public Map<String, Double> getBudgetStatus(Long userId) {
        List<Budget> budgets = budgetRepository.findByUserId(userId);
        List<Transaction> transactions = transactionClient.getTransactionsByUserId(userId);

        Map<String, Double> totalSpent = transactions.stream()
                .filter(t -> t.getType().equals("EXPENSE"))
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));

        return budgets.stream().collect(Collectors.toMap(
            Budget::getCategory,
            budget -> totalSpent.getOrDefault(budget.getCategory(), 0.0) - budget.getLimitAmount()
        ));
    }
    // public Map<String, Double> getBudgetStatus(Long userId) {
    //     return budgets.stream()
    //         .filter(budget -> budget.getUserId().equals(userId))
    //         .collect(Collectors.toMap(
    //             Budget::getCategory, 
    //             Budget::getAmount, 
    //             (existingValue, newValue) -> existingValue // Keep the existing value in case of duplicates
    //         ));
    // }
public List<String> checkOverspending(Long userId) {
        Map<String, Double> budgetStatus = getBudgetStatus(userId);
        List<String> alerts = new ArrayList<>();

        for (Map.Entry<String, Double> entry : budgetStatus.entrySet()) {
            if (entry.getValue() > 0) {
                alerts.add("⚠️ Warning: You have exceeded your " + entry.getKey() + " budget by ₹" + entry.getValue());
            }
        }
        return alerts;
    }

}