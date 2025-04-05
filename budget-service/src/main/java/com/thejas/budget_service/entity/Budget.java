package com.thejas.budget_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"category", "month"})
})
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category; // e.g., "Food", "Rent"
    
    private Double limitAmount; // Budget limit for the category

    private String month; // e.g., "2025-03"

    private Long userId;
}
