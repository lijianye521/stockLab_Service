package com.example.stocklab_service.controller;

import com.example.stocklab_service.entity.User;
import com.example.stocklab_service.repository.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "用户管理", description = "用户相关接口")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @Operation(summary = "获取所有用户", description = "获取系统中所有用户列表")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userMapper.selectAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取用户", description = "根据用户ID获取用户详细信息")
    public ResponseEntity<User> getUserById(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        User user = userMapper.selectById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "根据用户名获取用户", description = "根据用户名获取用户详细信息")
    public ResponseEntity<User> getUserByUsername(
            @Parameter(description = "用户名", required = true)
            @PathVariable String username) {
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role/{role}")
    @Operation(summary = "根据角色获取用户", description = "根据用户角色获取用户列表")
    public ResponseEntity<List<User>> getUsersByRole(
            @Parameter(description = "用户角色", required = true)
            @PathVariable String role) {
        List<User> users = userMapper.selectByRole(role);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @Operation(summary = "创建用户", description = "创建新用户")
    public ResponseEntity<User> createUser(
            @Parameter(description = "用户信息", required = true)
            @RequestBody User user) {
        try {
            int result = userMapper.insert(user);
            if (result > 0) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新用户", description = "根据ID更新用户信息")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "用户信息", required = true)
            @RequestBody User user) {
        user.setId(id);
        int result = userMapper.updateById(user);
        if (result > 0) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "更新用户状态", description = "更新用户的激活状态")
    public ResponseEntity<Void> updateUserStatus(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "激活状态", required = true)
            @RequestParam Boolean isActive) {
        int result = userMapper.updateIsActive(id, isActive);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户", description = "根据ID删除用户")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        int result = userMapper.deleteById(id);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    @Operation(summary = "获取用户总数", description = "获取系统中用户总数")
    public ResponseEntity<Integer> getUserCount() {
        int count = userMapper.count();
        return ResponseEntity.ok(count);
    }
}