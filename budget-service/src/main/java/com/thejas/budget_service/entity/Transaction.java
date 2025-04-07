package com.thejas.budget_service.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    private Long id;
    private Long userId;
    private Double amount;
    private String type;  // EXPENSE or INCOME
    private String category;
    private String date;
}