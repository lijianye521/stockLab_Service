package com.example.stocklab_service.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "用户状态更新DTO")
public class UserStatusUpdateDTO {

    @NotNull(message = "激活状态不能为空")
    @Schema(description = "激活状态", example = "true")
    private Boolean isActive;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
