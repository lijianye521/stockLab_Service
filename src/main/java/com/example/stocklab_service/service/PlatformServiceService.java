package com.example.stocklab_service.service;

import com.example.stocklab_service.domain.dto.PlatformServiceCreateDTO;
import com.example.stocklab_service.domain.dto.PlatformServiceUpdateDTO;
import com.example.stocklab_service.domain.vo.PlatformServiceVO;
import com.example.stocklab_service.entity.PlatformService;

import java.util.List;

/**
 * 平台服务接口
 */
public interface PlatformServiceService {

    /**
     * 获取所有平台服务
     */
    List<PlatformServiceVO> getAllServices();

    /**
     * 获取可见平台服务
     */
    List<PlatformServiceVO> getVisibleServices();

    /**
     * 根据ID获取平台服务
     */
    PlatformServiceVO getServiceById(Long id);

    /**
     * 根据服务代码获取平台服务
     */
    PlatformServiceVO getServiceByCode(String serviceCode);

    /**
     * 根据服务类型获取平台服务
     */
    List<PlatformServiceVO> getServicesByType(String serviceType);

    /**
     * 根据服务类型获取可见平台服务
     */
    List<PlatformServiceVO> getVisibleServicesByType(String serviceType);

    /**
     * 创建平台服务
     */
    PlatformServiceVO createService(PlatformServiceCreateDTO createDTO);

    /**
     * 更新平台服务
     */
    PlatformServiceVO updateService(Long id, PlatformServiceUpdateDTO updateDTO);

    /**
     * 更新服务可见性
     */
    void updateServiceVisibility(Long id, Boolean isVisible);

    /**
     * 更新排序权重
     */
    void updateSortOrder(Long id, Integer sortOrder);

    /**
     * 删除平台服务
     */
    void deleteService(Long id);

    /**
     * 获取服务总数
     */
    int getServiceCount();

    /**
     * 根据类型获取服务数量
     */
    int getServiceCountByType(String serviceType);
}
