package com.thejas.transaction_service.service;

import org.springframework.stereotype.Service;

import lombok.*;
import com.thejas.transaction_service.entity.Transaction;
import com.thejas.transaction_service.entity.TransactionType;
import com.thejas.transaction_service.feign.BudgetClient;
//import com.thejas.transaction_service.feign.CategoryClient;
import com.thejas.transaction_service.repository.TransactionRepository;

import feign.FeignException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    //private final CategoryClient categoryClient;

    // public Transaction saveTransaction(Transaction transaction) {
    //     boolean categoryExists = categoryClient.categoryExists(transaction.getCategory(), transaction.getType().name());
    //     if (!categoryExists) {
    //         throw new IllegalArgumentException("Invalid category: " + transaction.getCategory());
    //     }
    //     return transactionRepository.save(transaction);
    // }
    private final BudgetClient budgetClient;
     
    public Transaction saveTransaction(Transaction transaction) {
        String month = transaction.getDate().toString().substring(0, 7); // "YYYY-MM"
        Optional<Double> budgetLimitOptional;
    
        try {
            budgetLimitOptional = budgetClient.getBudget(transaction.getCategory(), month);
        } catch (FeignException e) {
            // Log the error and rethrow a custom exception or handle it gracefully
            throw new IllegalStateException("Failed to fetch budget from budget-service: " + e.getMessage(), e);
        }
    
        if (budgetLimitOptional.isPresent()) {
            Double budgetLimit = budgetLimitOptional.get();
            BigDecimal budgetLimitBigDecimal = BigDecimal.valueOf(budgetLimit); // Convert Double to BigDecimal
            if (transaction.getAmount().compareTo(budgetLimitBigDecimal) > 0) { // Use compareTo for BigDecimal comparison
                throw new IllegalArgumentException("Transaction exceeds budget limit for category: " + transaction.getCategory());
            }
        }
    
        return transactionRepository.save(transaction);
    }
 
    // public Transaction saveTransaction(Transaction transaction) {
    //     return transactionRepository.save(transaction);
    // }


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByType(String type) {
         try {
            TransactionType transactionType = TransactionType.valueOf(type.toUpperCase());
            return transactionRepository.findByType(transactionType);
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided type is not a valid TransactionType
            // You might want to log this error or throw a custom exception
            System.err.println("Invalid transaction type: " + type);
            return List.of(); // Or throw an exception, e.g., throw new InvalidTransactionTypeException(type);
        }
    }
}
