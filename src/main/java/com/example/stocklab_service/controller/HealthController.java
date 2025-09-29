package com.example.stocklab_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "系统运行正常",
            content = @Content(mediaType = "application/json",
                schema = @Schema(type = "object"))),
        @ApiResponse(responseCode = "500", description = "系统异常")
    })
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "连通性测试成功",
            content = @Content(mediaType = "application/json",
                schema = @Schema(type = "object"))),
        @ApiResponse(responseCode = "500", description = "系统异常")
    })
    public Map<String, Object> ping() {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "pong");
        result.put("timestamp", LocalDateTime.now());
        return result;
    }
}