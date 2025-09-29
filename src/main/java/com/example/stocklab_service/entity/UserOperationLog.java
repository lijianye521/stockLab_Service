package com.example.stocklab_service.entity;

import java.time.LocalDateTime;

public class UserOperationLog {
    private Long id;
    private Long userId;
    private String operationType;
    private String serviceCode;
    private String operationDetail;
    private String ipAddress;
    private String userAgent;
    private LocalDateTime createdAt;

    public UserOperationLog() {}

    public UserOperationLog(Long userId, String operationType, String serviceCode, 
                           String operationDetail, String ipAddress, String userAgent) {
        this.userId = userId;
        this.operationType = operationType;
        this.serviceCode = serviceCode;
        this.operationDetail = operationDetail;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(String operationDetail) {
        this.operationDetail = operationDetail;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserOperationLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", operationType='" + operationType + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}