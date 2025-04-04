
package com.thejas.transaction_service.feign;


import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "budget-service", path = "/budgets")
public interface BudgetClient {
    @GetMapping("/{category}/{month}")
    // Double getBudget(@PathVariable String category, @PathVariable String month);
    Optional<Double> getBudget(@PathVariable String category, @PathVariable String month);
     
}


