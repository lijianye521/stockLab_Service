package com.example.stocklab_service.repository.mapper;

import com.example.stocklab_service.entity.UserOperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserOperationLogMapper {

    /**
     * 根据ID查询操作日志
     */
    UserOperationLog selectById(@Param("id") Long id);

    /**
     * 查询所有操作日志
     */
    List<UserOperationLog> selectAll();

    /**
     * 根据用户ID查询操作日志
     */
    List<UserOperationLog> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据操作类型查询操作日志
     */
    List<UserOperationLog> selectByOperationType(@Param("operationType") String operationType);

    /**
     * 根据服务代码查询操作日志
     */
    List<UserOperationLog> selectByServiceCode(@Param("serviceCode") String serviceCode);

    /**
     * 根据用户ID和操作类型查询操作日志
     */
    List<UserOperationLog> selectByUserIdAndOperationType(@Param("userId") Long userId,
                                                          @Param("operationType") String operationType);

    /**
     * 根据时间范围查询操作日志
     */
    List<UserOperationLog> selectByDateRange(@Param("startDate") LocalDateTime startDate,
                                             @Param("endDate") LocalDateTime endDate);

    /**
     * 根据用户ID和时间范围查询操作日志
     */
    List<UserOperationLog> selectByUserIdAndDateRange(@Param("userId") Long userId,
                                                      @Param("startDate") LocalDateTime startDate,
                                                      @Param("endDate") LocalDateTime endDate);

    /**
     * 分页查询操作日志，按时间倒序
     */
    List<UserOperationLog> selectWithPagination(@Param("offset") int offset,
                                                @Param("limit") int limit);

    /**
     * 根据用户ID分页查询操作日志，按时间倒序
     */
    List<UserOperationLog> selectByUserIdWithPagination(@Param("userId") Long userId,
                                                        @Param("offset") int offset,
                                                        @Param("limit") int limit);

    /**
     * 插入操作日志
     */
    int insert(UserOperationLog userOperationLog);

    /**
     * 批量插入操作日志
     */
    int batchInsert(@Param("logs") List<UserOperationLog> logs);

    /**
     * 根据ID删除操作日志
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据用户ID删除操作日志
     */
    int deleteByUserId(@Param("userId") Long userId);

    /**
     * 删除指定日期之前的操作日志
     */
    int deleteBeforeDate(@Param("date") LocalDateTime date);

    /**
     * 统计操作日志总数
     */
    int count();

    /**
     * 根据用户ID统计操作日志数量
     */
    int countByUserId(@Param("userId") Long userId);

    /**
     * 根据操作类型统计操作日志数量
     */
    int countByOperationType(@Param("operationType") String operationType);

    /**
     * 根据时间范围统计操作日志数量
     */
    int countByDateRange(@Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);
}
