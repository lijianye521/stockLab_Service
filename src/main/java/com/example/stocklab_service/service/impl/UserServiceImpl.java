package com.example.stocklab_service.service.impl;

import com.example.stocklab_service.domain.bo.UserBO;
import com.example.stocklab_service.domain.dto.UserCreateDTO;
import com.example.stocklab_service.domain.dto.UserUpdateDTO;
import com.example.stocklab_service.domain.dto.UserStatusUpdateDTO;
import com.example.stocklab_service.domain.vo.UserVO;
import com.example.stocklab_service.entity.User;
import com.example.stocklab_service.repository.mapper.UserMapper;
import com.example.stocklab_service.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVO> getAllUsers() {
        List<User> users = userMapper.selectAll();
        return users.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public UserVO getUserById(Long id) {
        User user = userMapper.selectById(id);
        return user != null ? convertToVO(user) : null;
    }

    @Override
    public UserVO getUserByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        return user != null ? convertToVO(user) : null;
    }

    @Override
    public List<UserVO> getUsersByRole(String role) {
        List<User> users = userMapper.selectByRole(role);
        return users.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public UserVO createUser(UserCreateDTO userCreateDTO) {
        // 验证输入数据
        UserBO.validateUserCreateDTO(userCreateDTO);

        // 检查用户名是否已存在
        User existingUser = userMapper.selectByUsername(userCreateDTO.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 验证用户名格式
        if (!UserBO.isValidUsername(userCreateDTO.getUsername())) {
            throw new RuntimeException("用户名格式不正确，只能包含字母、数字和下划线，长度3-20位");
        }

        // 验证密码强度
        if (!UserBO.isStrongPassword(userCreateDTO.getPassword())) {
            throw new RuntimeException("密码强度不够，密码必须包含至少一个大写字母、一个小写字母和一个数字，且长度不少于8位");
        }

        // 创建用户实体
        User user = new User();
        BeanUtils.copyProperties(userCreateDTO, user);
        user.setPassword(DigestUtils.md5DigestAsHex(userCreateDTO.getPassword().getBytes())); // 密码加密
        user.setIsActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // 插入数据库
        int result = userMapper.insert(user);
        if (result > 0) {
            return convertToVO(user);
        } else {
            throw new RuntimeException("创建用户失败");
        }
    }

    @Override
    public UserVO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 更新用户信息
        BeanUtils.copyProperties(userUpdateDTO, existingUser);
        existingUser.setUpdatedAt(LocalDateTime.now());

        int result = userMapper.updateById(existingUser);
        if (result > 0) {
            return convertToVO(existingUser);
        } else {
            throw new RuntimeException("更新用户失败");
        }
    }

    @Override
    public void updateUserStatus(Long id, UserStatusUpdateDTO statusUpdateDTO) {
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }

        int result = userMapper.updateIsActive(id, statusUpdateDTO.getIsActive());
        if (result <= 0) {
            throw new RuntimeException("更新用户状态失败");
        }
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }

        int result = userMapper.deleteById(id);
        if (result <= 0) {
            throw new RuntimeException("删除用户失败");
        }
    }

    @Override
    public int getUserCount() {
        return userMapper.count();
    }

    /**
     * 将User实体转换为UserVO
     */
    private UserVO convertToVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}
