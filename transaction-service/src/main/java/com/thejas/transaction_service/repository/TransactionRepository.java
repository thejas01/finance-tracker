package com.thejas.transaction_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thejas.transaction_service.entity.Transaction;
import com.thejas.transaction_service.entity.TransactionType;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByUserId(Long userId);
}