package com.thejas.report_service.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    private Long id;
    private Double amount;
    private String type;  // EXPENSE or INCOME
    private String category;
    private String date;
   
}