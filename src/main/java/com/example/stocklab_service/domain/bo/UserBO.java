package com.example.stocklab_service.domain.bo;

import com.example.stocklab_service.domain.dto.UserCreateDTO;
import com.example.stocklab_service.domain.dto.UserUpdateDTO;
import com.example.stocklab_service.domain.vo.UserVO;
import com.example.stocklab_service.entity.User;

/**
 * 用户业务对象
 * 用于处理用户相关的复杂业务逻辑
 */
public class UserBO {

    /**
     * 验证用户创建数据
     */
    public static void validateUserCreateDTO(UserCreateDTO userCreateDTO) {
        if (userCreateDTO == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }
        if (userCreateDTO.getUsername() == null || userCreateDTO.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (userCreateDTO.getPassword() == null || userCreateDTO.getPassword().length() < 6) {
            throw new IllegalArgumentException("密码长度不能少于6位");
        }
        if (userCreateDTO.getEmail() == null || !userCreateDTO.getEmail().contains("@")) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
        if (userCreateDTO.getRole() == null || userCreateDTO.getRole().trim().isEmpty()) {
            throw new IllegalArgumentException("用户角色不能为空");
        }
    }

    /**
     * 验证用户更新数据
     */
    public static void validateUserUpdateDTO(UserUpdateDTO userUpdateDTO) {
        if (userUpdateDTO == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }
        if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().contains("@")) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
    }

    /**
     * 检查用户名是否符合规范
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        // 用户名长度3-20位，只能包含字母、数字和下划线
        return username.matches("^[a-zA-Z0-9_]{3,20}$");
    }

    /**
     * 检查密码强度
     */
    public static boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        // 密码必须包含至少一个大写字母、一个小写字母和一个数字
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        return hasUpper && hasLower && hasDigit;
    }

    /**
     * 生成用户显示名称
     */
    public static String generateDisplayName(UserVO userVO) {
        if (userVO.getNickname() != null && !userVO.getNickname().trim().isEmpty()) {
            return userVO.getNickname();
        }
        return userVO.getUsername();
    }

    /**
     * 判断用户是否有管理员权限
     */
    public static boolean isAdmin(UserVO userVO) {
        return "admin".equals(userVO.getRole()) || "super_admin".equals(userVO.getRole());
    }

    /**
     * 判断用户是否激活
     */
    public static boolean isActive(UserVO userVO) {
        return userVO.getIsActive() != null && userVO.getIsActive();
    }
}
