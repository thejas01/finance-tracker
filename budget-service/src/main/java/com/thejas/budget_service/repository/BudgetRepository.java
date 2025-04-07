package com.thejas.budget_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thejas.budget_service.entity.Budget;

import java.util.List;
//import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    //Optional<Budget> findByCategoryAndMonth(String category, String month);
    List<Budget> findByCategoryAndMonth(String category, String month);
    List<Budget> findByUserId(Long userId);

}