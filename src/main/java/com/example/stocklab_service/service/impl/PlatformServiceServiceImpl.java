package com.example.stocklab_service.service.impl;

import com.example.stocklab_service.domain.bo.PlatformServiceBO;
import com.example.stocklab_service.domain.dto.PlatformServiceCreateDTO;
import com.example.stocklab_service.domain.dto.PlatformServiceUpdateDTO;
import com.example.stocklab_service.domain.vo.PlatformServiceVO;
import com.example.stocklab_service.entity.PlatformService;
import com.example.stocklab_service.repository.mapper.PlatformServiceMapper;
import com.example.stocklab_service.service.PlatformServiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 平台服务实现类
 */
@Service
public class PlatformServiceServiceImpl implements PlatformServiceService {

    @Autowired
    private PlatformServiceMapper platformServiceMapper;

    @Override
    public List<PlatformServiceVO> getAllServices() {
        List<PlatformService> services = platformServiceMapper.selectAll();
        return services.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlatformServiceVO> getVisibleServices() {
        List<PlatformService> services = platformServiceMapper.selectVisibleOrderBySortOrder();
        return services.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public PlatformServiceVO getServiceById(Long id) {
        PlatformService service = platformServiceMapper.selectById(id);
        return service != null ? convertToVO(service) : null;
    }

    @Override
    public PlatformServiceVO getServiceByCode(String serviceCode) {
        PlatformService service = platformServiceMapper.selectByServiceCode(serviceCode);
        return service != null ? convertToVO(service) : null;
    }

    @Override
    public List<PlatformServiceVO> getServicesByType(String serviceType) {
        List<PlatformService> services = platformServiceMapper.selectByServiceType(serviceType);
        return services.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlatformServiceVO> getVisibleServicesByType(String serviceType) {
        List<PlatformService> services = platformServiceMapper.selectVisibleByServiceTypeOrderBySortOrder(serviceType);
        return services.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public PlatformServiceVO createService(PlatformServiceCreateDTO createDTO) {
        // 验证输入数据
        PlatformServiceBO.validatePlatformServiceCreateDTO(createDTO);

        // 检查服务代码是否已存在
        PlatformService existingService = platformServiceMapper.selectByServiceCode(createDTO.getServiceCode());
        if (existingService != null) {
            throw new RuntimeException("服务代码已存在");
        }

        // 验证服务代码格式
        if (!PlatformServiceBO.isValidServiceCode(createDTO.getServiceCode())) {
            throw new RuntimeException("服务代码格式不正确，只能包含大写字母、数字和下划线，长度2-50位");
        }

        // 验证服务URL格式
        if (!PlatformServiceBO.isValidServiceUrl(createDTO.getServiceUrl(), createDTO.getServiceType())) {
            throw new RuntimeException("服务URL格式不正确");
        }

        // 创建服务实体
        PlatformService service = new PlatformService();
        BeanUtils.copyProperties(createDTO, service);
        service.setIconName("Settings"); // 默认图标
        service.setColorClass("bg-blue-500"); // 默认颜色
        service.setUrlType("internal"); // 默认URL类型
        service.setSortOrder(0); // 默认排序权重
        service.setIsVisible(true); // 默认可见
        service.setCreatedAt(LocalDateTime.now());
        service.setUpdatedAt(LocalDateTime.now());

        // 插入数据库
        int result = platformServiceMapper.insert(service);
        if (result > 0) {
            return convertToVO(service);
        } else {
            throw new RuntimeException("创建服务失败");
        }
    }

    @Override
    public PlatformServiceVO updateService(Long id, PlatformServiceUpdateDTO updateDTO) {
        PlatformService existingService = platformServiceMapper.selectById(id);
        if (existingService == null) {
            throw new RuntimeException("服务不存在");
        }

        // 更新服务信息
        BeanUtils.copyProperties(updateDTO, existingService);
        existingService.setUpdatedAt(LocalDateTime.now());

        int result = platformServiceMapper.updateById(existingService);
        if (result > 0) {
            return convertToVO(existingService);
        } else {
            throw new RuntimeException("更新服务失败");
        }
    }

    @Override
    public void updateServiceVisibility(Long id, Boolean isVisible) {
        PlatformService existingService = platformServiceMapper.selectById(id);
        if (existingService == null) {
            throw new RuntimeException("服务不存在");
        }

        int result = platformServiceMapper.updateIsVisible(id, isVisible);
        if (result <= 0) {
            throw new RuntimeException("更新服务可见性失败");
        }
    }

    @Override
    public void updateSortOrder(Long id, Integer sortOrder) {
        PlatformService existingService = platformServiceMapper.selectById(id);
        if (existingService == null) {
            throw new RuntimeException("服务不存在");
        }

        int result = platformServiceMapper.updateSortOrder(id, sortOrder);
        if (result <= 0) {
            throw new RuntimeException("更新排序权重失败");
        }
    }

    @Override
    public void deleteService(Long id) {
        PlatformService existingService = platformServiceMapper.selectById(id);
        if (existingService == null) {
            throw new RuntimeException("服务不存在");
        }

        int result = platformServiceMapper.deleteById(id);
        if (result <= 0) {
            throw new RuntimeException("删除服务失败");
        }
    }

    @Override
    public int getServiceCount() {
        return platformServiceMapper.count();
    }

    @Override
    public int getServiceCountByType(String serviceType) {
        return platformServiceMapper.countByServiceType(serviceType);
    }

    /**
     * 将PlatformService实体转换为PlatformServiceVO
     */
    private PlatformServiceVO convertToVO(PlatformService service) {
        PlatformServiceVO serviceVO = new PlatformServiceVO();
        BeanUtils.copyProperties(service, serviceVO);
        return serviceVO;
    }
}
