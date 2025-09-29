package com.example.stocklab_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
@Tag(name = "健康检查", description = "系统健康检查相关接口")
public class HealthController {

    @GetMapping
    @Operation(summary = "健康检查", description = "检查系统运行状态")
    public Map<String, Object> health() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "UP");
        result.put("message", "StockLab Service is running");
        result.put("timestamp", LocalDateTime.now());
        result.put("version", "1.0.0");
        return result;
    }

    @GetMapping("/ping")
    @Operation(summary = "Ping测试", description = "简单的连通性测试")
    public Map<String, Object> ping() {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "pong");
        result.put("timestamp", LocalDateTime.now());
        return result;
    }
}