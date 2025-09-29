package com.example.stocklab_service.service;

import com.example.stocklab_service.domain.dto.UserCreateDTO;
import com.example.stocklab_service.domain.dto.UserUpdateDTO;
import com.example.stocklab_service.domain.dto.UserStatusUpdateDTO;
import com.example.stocklab_service.domain.vo.UserVO;
import com.example.stocklab_service.entity.User;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 获取所有用户
     */
    List<UserVO> getAllUsers();

    /**
     * 根据ID获取用户
     */
    UserVO getUserById(Long id);

    /**
     * 根据用户名获取用户
     */
    UserVO getUserByUsername(String username);

    /**
     * 根据角色获取用户
     */
    List<UserVO> getUsersByRole(String role);

    /**
     * 创建用户
     */
    UserVO createUser(UserCreateDTO userCreateDTO);

    /**
     * 更新用户信息
     */
    UserVO updateUser(Long id, UserUpdateDTO userUpdateDTO);

    /**
     * 更新用户状态
     */
    void updateUserStatus(Long id, UserStatusUpdateDTO statusUpdateDTO);

    /**
     * 删除用户
     */
    void deleteUser(Long id);

    /**
     * 获取用户总数
     */
    int getUserCount();
}
