package com.thejas.report_service.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {
    private Long id;
    private String category;
    private Double limitAmount;
    private String month;
    private Long userId;
}