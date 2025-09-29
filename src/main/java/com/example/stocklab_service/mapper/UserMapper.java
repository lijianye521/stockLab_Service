package com.example.stocklab_service.mapper;

import com.example.stocklab_service.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    
    /**
     * 根据ID查询用户
     */
    User selectById(@Param("id") Long id);
    
    /**
     * 根据用户名查询用户
     */
    User selectByUsername(@Param("username") String username);
    
    /**
     * 查询所有用户
     */
    List<User> selectAll();
    
    /**
     * 根据活跃状态查询用户
     */
    List<User> selectByIsActive(@Param("isActive") Boolean isActive);
    
    /**
     * 根据角色查询用户
     */
    List<User> selectByRole(@Param("role") String role);
    
    /**
     * 插入用户
     */
    int insert(User user);
    
    /**
     * 更新用户信息
     */
    int updateById(User user);
    
    /**
     * 更新用户密码
     */
    int updatePassword(@Param("id") Long id, @Param("password") String password);
    
    /**
     * 更新用户活跃状态
     */
    int updateIsActive(@Param("id") Long id, @Param("isActive") Boolean isActive);
    
    /**
     * 根据ID删除用户
     */
    int deleteById(@Param("id") Long id);
    
    /**
     * 统计用户总数
     */
    int count();
    
    /**
     * 根据用户名统计
     */
    int countByUsername(@Param("username") String username);
}