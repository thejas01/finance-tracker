package com.thejas.report_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.thejas.report_service.feign.BudgetClient;
import com.thejas.report_service.feign.TransactionClient;
import com.thejas.report_service.model.Budget;
import com.thejas.report_service.model.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.*;



@Service
@RequiredArgsConstructor
public class ReportService {
        private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private final TransactionClient transactionClient;
    private final BudgetClient budgetClient;

    public Map<String, Object> getMonthlyReport(String month) {
        List<Transaction> transactions = transactionClient.getAllTransactions();
        List<Budget> budgets = budgetClient.getAllBudgets();

        // Filter transactions for the requested month
        List<Transaction> monthlyTransactions = transactions.stream()
                .filter(t -> t.getDate().startsWith(month)) // Check "YYYY-MM"
                .collect(Collectors.toList());

        // Group transactions by category and sum their amounts
        Map<String, Double> totalSpendingByCategory = monthlyTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));

        // Prepare budget comparison
        Map<String, Map<String, Double>> budgetComparison = new HashMap<>();
        for (Budget budget : budgets) {
            if (budget.getMonth().equals(month)) {
                double spent = totalSpendingByCategory.getOrDefault(budget.getCategory(), 0.0);
                double remaining = budget.getLimitAmount() - spent;

                budgetComparison.put(budget.getCategory(), Map.of(
                        "Budget Limit", budget.getLimitAmount(),
                        "Spent", spent,
                        "Remaining", remaining
                ));
            }
        }

        // Prepare the report
        Map<String, Object> report = new HashMap<>();
        report.put("month", month);
        report.put("totalIncome", transactions.stream()
                .filter(t -> t.getDate().startsWith(month) && t.getType().equals("INCOME"))
                .mapToDouble(Transaction::getAmount)
                .sum());
        report.put("totalExpense", transactions.stream()
                .filter(t -> t.getDate().startsWith(month) && t.getType().equals("EXPENSE"))
                .mapToDouble(Transaction::getAmount)
                .sum());
        report.put("budgetComparison", budgetComparison);

        return report;
    }
    public Map<String, Double> getUserSpendingSummary(Long userId) {
        logger.info("Fetching spending summary for user ID: {}", userId);
        List<Transaction> transactions = transactionClient.getTransactionsByUserId(userId);
        if (transactions == null || transactions.isEmpty()) {
                logger.warn("No transactions found for user ID: {}", userId);
                return Collections.emptyMap();
            }

        return transactions.stream()
                .filter(t -> t.getType().equals("EXPENSE"))
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));
    }
}