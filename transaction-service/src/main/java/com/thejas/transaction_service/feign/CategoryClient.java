package com.thejas.transaction_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "category-service", path = "/categories")
public interface CategoryClient {
    @GetMapping("/exists")
    boolean categoryExists(@RequestParam String name, @RequestParam String type);
}