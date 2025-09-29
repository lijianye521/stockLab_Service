package com.example.stocklab_service.mapper;

import com.example.stocklab_service.entity.PlatformService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlatformServiceMapper {
    
    /**
     * 根据ID查询平台服务 
     */
    PlatformService selectById(@Param("id") Long id);
    
    /**
     * 根据服务代码查询平台服务
     */
    PlatformService selectByServiceCode(@Param("serviceCode") String serviceCode);
    
    /**
     * 查询所有平台服务
     */
    List<PlatformService> selectAll();
    
    /**
     * 根据服务类型查询平台服务
     */
    List<PlatformService> selectByServiceType(@Param("serviceType") String serviceType);
    
    /**
     * 根据可见状态查询平台服务
     */
    List<PlatformService> selectByIsVisible(@Param("isVisible") Boolean isVisible);
    
    /**
     * 根据URL类型查询平台服务
     */
    List<PlatformService> selectByUrlType(@Param("urlType") String urlType);
    
    /**
     * 查询可见的平台服务，按排序权重排序
     */
    List<PlatformService> selectVisibleOrderBySortOrder();
    
    /**
     * 根据服务类型查询可见的平台服务，按排序权重排序
     */
    List<PlatformService> selectVisibleByServiceTypeOrderBySortOrder(@Param("serviceType") String serviceType);
    
    /**
     * 插入平台服务
     */
    int insert(PlatformService platformService);
    
    /**
     * 更新平台服务信息
     */
    int updateById(PlatformService platformService);
    
    /**
     * 更新可见状态
     */
    int updateIsVisible(@Param("id") Long id, @Param("isVisible") Boolean isVisible);
    
    /**
     * 更新排序权重
     */
    int updateSortOrder(@Param("id") Long id, @Param("sortOrder") Integer sortOrder);
    
    /**
     * 根据ID删除平台服务
     */
    int deleteById(@Param("id") Long id);
    
    /**
     * 根据服务代码删除平台服务
     */
    int deleteByServiceCode(@Param("serviceCode") String serviceCode);
    
    /**
     * 统计平台服务总数
     */
    int count();
    
    /**
     * 根据服务类型统计
     */
    int countByServiceType(@Param("serviceType") String serviceType);
    
    /**
     * 根据服务代码统计
     */
    int countByServiceCode(@Param("serviceCode") String serviceCode);
}