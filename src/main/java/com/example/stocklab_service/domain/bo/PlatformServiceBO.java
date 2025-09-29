package com.example.stocklab_service.domain.bo;

import com.example.stocklab_service.domain.dto.PlatformServiceCreateDTO;
import com.example.stocklab_service.domain.dto.PlatformServiceUpdateDTO;
import com.example.stocklab_service.domain.vo.PlatformServiceVO;

/**
 * 平台服务业务对象
 * 用于处理平台服务相关的复杂业务逻辑
 */
public class PlatformServiceBO {

    /**
     * 验证平台服务创建数据
     */
    public static void validatePlatformServiceCreateDTO(PlatformServiceCreateDTO createDTO) {
        if (createDTO == null) {
            throw new IllegalArgumentException("平台服务信息不能为空");
        }
        if (createDTO.getServiceCode() == null || createDTO.getServiceCode().trim().isEmpty()) {
            throw new IllegalArgumentException("服务代码不能为空");
        }
        if (createDTO.getServiceName() == null || createDTO.getServiceName().trim().isEmpty()) {
            throw new IllegalArgumentException("服务名称不能为空");
        }
        if (createDTO.getServiceType() == null || createDTO.getServiceType().trim().isEmpty()) {
            throw new IllegalArgumentException("服务类型不能为空");
        }
        if (createDTO.getServiceUrl() == null || createDTO.getServiceUrl().trim().isEmpty()) {
            throw new IllegalArgumentException("服务URL不能为空");
        }

        // 验证服务类型
        if (!isValidServiceType(createDTO.getServiceType())) {
            throw new IllegalArgumentException("服务类型必须是 platform、service 或 agent");
        }
    }

    /**
     * 验证平台服务更新数据
     */
    public static void validatePlatformServiceUpdateDTO(PlatformServiceUpdateDTO updateDTO) {
        if (updateDTO == null) {
            throw new IllegalArgumentException("平台服务信息不能为空");
        }

        // 验证邮箱格式（如果提供了邮箱）
        if (updateDTO.getServiceType() != null && !isValidServiceType(updateDTO.getServiceType())) {
            throw new IllegalArgumentException("服务类型必须是 platform、service 或 agent");
        }
    }

    /**
     * 验证服务类型
     */
    public static boolean isValidServiceType(String serviceType) {
        return "platform".equals(serviceType) ||
               "service".equals(serviceType) ||
               "agent".equals(serviceType);
    }

    /**
     * 检查服务代码是否符合规范
     */
    public static boolean isValidServiceCode(String serviceCode) {
        if (serviceCode == null || serviceCode.trim().isEmpty()) {
            return false;
        }
        // 服务代码由大写字母、数字和下划线组成，长度2-50位
        return serviceCode.matches("^[A-Z0-9_]{2,50}$");
    }

    /**
     * 生成服务显示名称
     */
    public static String generateServiceDisplayName(PlatformServiceVO serviceVO) {
        if (serviceVO.getServiceName() != null && !serviceVO.getServiceName().trim().isEmpty()) {
            return serviceVO.getServiceName();
        }
        return serviceVO.getServiceCode();
    }

    /**
     * 判断服务是否可见
     */
    public static boolean isVisible(PlatformServiceVO serviceVO) {
        return serviceVO.getIsVisible() != null && serviceVO.getIsVisible();
    }

    /**
     * 获取服务优先级权重
     */
    public static int getServicePriority(PlatformServiceVO serviceVO) {
        return serviceVO.getSortOrder() != null ? serviceVO.getSortOrder() : 0;
    }

    /**
     * 检查服务是否为内部服务
     */
    public static boolean isInternalService(PlatformServiceVO serviceVO) {
        return "internal".equals(serviceVO.getUrlType());
    }

    /**
     * 检查服务是否为外部服务
     */
    public static boolean isExternalService(PlatformServiceVO serviceVO) {
        return "external".equals(serviceVO.getUrlType());
    }

    /**
     * 验证服务URL格式
     */
    public static boolean isValidServiceUrl(String serviceUrl, String urlType) {
        if (serviceUrl == null || serviceUrl.trim().isEmpty()) {
            return false;
        }

        if ("internal".equals(urlType)) {
            // 内部服务URL应该是相对路径，以/开头
            return serviceUrl.startsWith("/");
        } else if ("external".equals(urlType)) {
            // 外部服务URL应该是完整的URL
            return serviceUrl.startsWith("http://") || serviceUrl.startsWith("https://");
        }

        return false;
    }
}
