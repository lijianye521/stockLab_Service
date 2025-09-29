package com.example.stocklab_service.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "平台服务更新DTO")
public class PlatformServiceUpdateDTO {

    @Schema(description = "服务名称", example = "股票分析")
    private String serviceName;

    @Schema(description = "服务描述", example = "提供专业的股票分析服务")
    private String serviceDescription;

    @Schema(description = "服务类型 (platform/service/agent)", example = "service")
    private String serviceType;

    @Schema(description = "服务URL", example = "/stock/analysis")
    private String serviceUrl;

    @Schema(description = "图标名称", example = "ChartBar")
    private String iconName;

    @Schema(description = "颜色样式", example = "bg-blue-500")
    private String colorClass;

    @Schema(description = "URL类型", example = "internal")
    private String urlType;

    @Schema(description = "其他信息", example = "扩展配置信息")
    private String otherInformation;

    @Schema(description = "排序权重", example = "10")
    private Integer sortOrder;

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
}
