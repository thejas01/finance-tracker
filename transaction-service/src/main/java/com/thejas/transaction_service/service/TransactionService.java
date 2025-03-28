package com.thejas.transaction_service.service;

import org.springframework.stereotype.Service;

import lombok.*;
import com.thejas.transaction_service.entity.Transaction;
import com.thejas.transaction_service.entity.TransactionType;
import com.thejas.transaction_service.repository.TransactionRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

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