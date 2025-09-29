package com.example.stocklab_service.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "平台服务创建DTO")
public class PlatformServiceCreateDTO {

    @NotBlank(message = "服务代码不能为空")
    @Schema(description = "服务代码", example = "STOCK_ANALYSIS")
    private String serviceCode;

    @NotBlank(message = "服务名称不能为空")
    @Schema(description = "服务名称", example = "股票分析")
    private String serviceName;

    @Schema(description = "服务描述", example = "提供专业的股票分析服务")
    private String serviceDescription;

    @NotBlank(message = "服务类型不能为空")
    @Schema(description = "服务类型 (platform/service/agent)", example = "service")
    private String serviceType;

    @NotBlank(message = "服务URL不能为空")
    @Schema(description = "服务URL", example = "/stock/analysis")
    private String serviceUrl;

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

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
}
