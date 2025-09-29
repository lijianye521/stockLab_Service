package com.example.stocklab_service.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Schema(description = "用户更新DTO")
public class UserUpdateDTO {

    @Size(max = 50, message = "昵称长度不能超过50字符")
    @Schema(description = "用户昵称", example = "张三")
    private String nickname;

    @Email(message = "邮箱格式不正确")
    @Schema(description = "邮箱地址", example = "zhangsan@example.com")
    private String email;

    @Schema(description = "用户角色", example = "admin")
    private String role;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
