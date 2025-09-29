package com.example.stocklab_service.controller;

import com.example.stocklab_service.domain.dto.UserCreateDTO;
import com.example.stocklab_service.domain.dto.UserUpdateDTO;
import com.example.stocklab_service.domain.dto.UserStatusUpdateDTO;
import com.example.stocklab_service.domain.vo.UserVO;
import com.example.stocklab_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "用户管理", description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "获取所有用户", description = "获取系统中所有用户列表")
    public ResponseEntity<List<UserVO>> getAllUsers() {
        List<UserVO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取用户", description = "根据用户ID获取用户详细信息")
    public ResponseEntity<UserVO> getUserById(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        UserVO user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "根据用户名获取用户", description = "根据用户名获取用户详细信息")
    public ResponseEntity<UserVO> getUserByUsername(
            @Parameter(description = "用户名", required = true)
            @PathVariable String username) {
        UserVO user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role/{role}")
    @Operation(summary = "根据角色获取用户", description = "根据用户角色获取用户列表")
    public ResponseEntity<List<UserVO>> getUsersByRole(
            @Parameter(description = "用户角色", required = true)
            @PathVariable String role) {
        List<UserVO> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @Operation(summary = "创建用户", description = "创建新用户")
    public ResponseEntity<UserVO> createUser(
            @Parameter(description = "用户信息", required = true)
            @Valid @RequestBody UserCreateDTO userCreateDTO) {
        try {
            UserVO user = userService.createUser(userCreateDTO);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新用户", description = "根据ID更新用户信息")
    public ResponseEntity<UserVO> updateUser(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "用户信息", required = true)
            @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            UserVO user = userService.updateUser(id, userUpdateDTO);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "更新用户状态", description = "更新用户的激活状态")
    public ResponseEntity<Void> updateUserStatus(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "激活状态", required = true)
            @Valid @RequestBody UserStatusUpdateDTO statusUpdateDTO) {
        try {
            userService.updateUserStatus(id, statusUpdateDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户", description = "根据ID删除用户")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    @Operation(summary = "获取用户总数", description = "获取系统中用户总数")
    public ResponseEntity<Integer> getUserCount() {
        int count = userService.getUserCount();
        return ResponseEntity.ok(count);
    }
}