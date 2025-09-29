package com.example.stocklab_service.controller;

import com.example.stocklab_service.domain.dto.UserCreateDTO;
import com.example.stocklab_service.domain.dto.UserUpdateDTO;
import com.example.stocklab_service.domain.dto.UserStatusUpdateDTO;
import com.example.stocklab_service.domain.vo.UserVO;
import com.example.stocklab_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取用户列表",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserVO.class))),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<List<UserVO>> getAllUsers() {
        List<UserVO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取用户", description = "根据用户ID获取用户详细信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取用户信息",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserVO.class))),
        @ApiResponse(responseCode = "404", description = "用户不存在"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取用户信息",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserVO.class))),
        @ApiResponse(responseCode = "404", description = "用户不存在"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取用户列表",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserVO.class))),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<List<UserVO>> getUsersByRole(
            @Parameter(description = "用户角色", required = true)
            @PathVariable String role) {
        List<UserVO> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @Operation(summary = "创建用户", description = "创建新用户")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功创建用户",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserVO.class))),
        @ApiResponse(responseCode = "400", description = "请求参数错误"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
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

    @PostMapping("/{id}")
    @Operation(summary = "更新用户", description = "根据ID更新用户信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功更新用户",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserVO.class))),
        @ApiResponse(responseCode = "400", description = "请求参数错误"),
        @ApiResponse(responseCode = "404", description = "用户不存在"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<UserVO> updateUser(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "操作类型", required = true)
            @RequestParam String _method,
            @Parameter(description = "用户信息", required = true)
            @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        if (!"update".equals(_method)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            UserVO user = userService.updateUser(id, userUpdateDTO);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/status")
    @Operation(summary = "更新用户状态", description = "更新用户的激活状态")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功更新用户状态"),
        @ApiResponse(responseCode = "400", description = "请求参数错误"),
        @ApiResponse(responseCode = "404", description = "用户不存在"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<Void> updateUserStatus(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "操作类型", required = true)
            @RequestParam String _method,
            @Parameter(description = "激活状态", required = true)
            @Valid @RequestBody UserStatusUpdateDTO statusUpdateDTO) {
        if (!"updateStatus".equals(_method)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            userService.updateUserStatus(id, statusUpdateDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/delete")
    @Operation(summary = "删除用户", description = "根据ID删除用户")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功删除用户"),
        @ApiResponse(responseCode = "400", description = "请求参数错误"),
        @ApiResponse(responseCode = "404", description = "用户不存在"),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "操作类型", required = true)
            @RequestParam String _method) {
        if (!"delete".equals(_method)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    @Operation(summary = "获取用户总数", description = "获取系统中用户总数")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取用户总数",
            content = @Content(mediaType = "application/json",
                schema = @Schema(type = "integer", format = "int32"))),
        @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<Integer> getUserCount() {
        int count = userService.getUserCount();
        return ResponseEntity.ok(count);
    }
}