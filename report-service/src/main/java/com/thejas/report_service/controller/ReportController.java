package com.thejas.report_service.controller;

import java.util.Map;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thejas.report_service.service.ReportService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    
    private final ReportService reportService;

    @GetMapping("/{month}")
    public Map<String, Object> getMonthlyReport(@PathVariable String month) {
        return reportService.getMonthlyReport(month);
    }

    @GetMapping("/user/{userId}")
public Map<String, Double> getUserSpendingSummary(@PathVariable Long userId) {
    System.out.println("Fetching spending summary for user ID: " + userId);
   
    return reportService.getUserSpendingSummary(userId);
}
}