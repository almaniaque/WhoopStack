package com.whoopstack.devis.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoopstack.devis.ressource.DashboardStatsDto;
import com.whoopstack.devis.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/users/{userId}/dashboard")
    public DashboardStatsDto getDashboardByUserId(@PathVariable Long userId) {

        return dashboardService.getStatsByUserId(userId);
    }
}
