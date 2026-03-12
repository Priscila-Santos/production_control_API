package com.production.production_control.controller;

import com.production.production_control.dto.response.DashboardResponse;
import com.production.production_control.service.DashboardService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard(){
        return dashboardService.getDashboard();
    }
}