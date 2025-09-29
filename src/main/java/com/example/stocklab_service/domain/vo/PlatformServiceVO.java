package com.example.stocklab_service.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "平台服务视图对象")
public class PlatformServiceVO {

    @Schema(description = "服务ID", example = "1")
    private Long id;

    @Schema(description = "服务代码", example = "STOCK_ANALYSIS")
    private String serviceCode;

    @Schema(description = "服务名称", example = "股票分析")
    private String serviceName;

    @Schema(description = "服务描述", example = "提供专业的股票分析服务")
    private String serviceDescription;

    @Schema(description = "服务类型", example = "service")
    private String serviceType;

    @Schema(description = "图标名称", example = "ChartBar")
    private String iconName;

    @Schema(description = "颜色样式", example = "bg-blue-500")
    private String colorClass;

    @Schema(description = "服务URL", example = "/stock/analysis")
    private String serviceUrl;

    @Schema(description = "URL类型", example = "internal")
    private String urlType;

    @Schema(description = "其他信息", example = "扩展配置信息")
    private String otherInformation;

    @Schema(description = "排序权重", example = "10")
    private Integer sortOrder;

    @Schema(description = "可见状态", example = "true")
    private Boolean isVisible;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间", example = "2024-01-01 12:00:00")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间", example = "2024-01-01 12:00:00")
    private LocalDateTime updatedAt;

    public PlatformServiceVO() {}

    public PlatformServiceVO(Long id, String serviceCode, String serviceName, String serviceDescription,
                           String serviceType, String serviceUrl, String urlType, Integer sortOrder,
                           Boolean isVisible, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.serviceType = serviceType;
        this.serviceUrl = serviceUrl;
        this.urlType = urlType;
        this.sortOrder = sortOrder;
        this.isVisible = isVisible;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getColorClass() {
        return colorClass;
    }

    public void setColorClass(String colorClass) {
        this.colorClass = colorClass;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
