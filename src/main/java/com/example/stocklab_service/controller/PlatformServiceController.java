package com.example.stocklab_service.controller;

import com.example.stocklab_service.domain.dto.PlatformServiceCreateDTO;
import com.example.stocklab_service.domain.dto.PlatformServiceUpdateDTO;
import com.example.stocklab_service.domain.vo.PlatformServiceVO;
import com.example.stocklab_service.service.PlatformServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platform-services")
@Tag(name = "平台服务管理", description = "平台服务相关接口")
public class PlatformServiceController {

    @Autowired
    private PlatformServiceService platformServiceService;

    @GetMapping
    @Operation(summary = "获取所有平台服务", description = "获取系统中所有平台服务列表")
    public ResponseEntity<List<PlatformServiceVO>> getAllServices() {
        List<PlatformServiceVO> services = platformServiceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/visible")
    @Operation(summary = "获取可见平台服务", description = "获取所有可见的平台服务，按排序权重排序")
    public ResponseEntity<List<PlatformServiceVO>> getVisibleServices() {
        List<PlatformServiceVO> services = platformServiceService.getVisibleServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取平台服务", description = "根据服务ID获取平台服务详细信息")
    public ResponseEntity<PlatformServiceVO> getServiceById(
            @Parameter(description = "服务ID", required = true)
            @PathVariable Long id) {
        PlatformServiceVO service = platformServiceService.getServiceById(id);
        if (service != null) {
            return ResponseEntity.ok(service);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/code/{serviceCode}")
    @Operation(summary = "根据服务代码获取平台服务", description = "根据服务代码获取平台服务详细信息")
    public ResponseEntity<PlatformServiceVO> getServiceByCode(
            @Parameter(description = "服务代码", required = true)
            @PathVariable String serviceCode) {
        PlatformServiceVO service = platformServiceService.getServiceByCode(serviceCode);
        if (service != null) {
            return ResponseEntity.ok(service);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/type/{serviceType}")
    @Operation(summary = "根据服务类型获取平台服务", description = "根据服务类型获取平台服务列表")
    public ResponseEntity<List<PlatformServiceVO>> getServicesByType(
            @Parameter(description = "服务类型（platform/service/agent）", required = true)
            @PathVariable String serviceType) {
        List<PlatformServiceVO> services = platformServiceService.getServicesByType(serviceType);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/type/{serviceType}/visible")
    @Operation(summary = "根据服务类型获取可见平台服务", description = "根据服务类型获取可见的平台服务，按排序权重排序")
    public ResponseEntity<List<PlatformServiceVO>> getVisibleServicesByType(
            @Parameter(description = "服务类型（platform/service/agent）", required = true)
            @PathVariable String serviceType) {
        List<PlatformServiceVO> services = platformServiceService.getVisibleServicesByType(serviceType);
        return ResponseEntity.ok(services);
    }

    @PostMapping
    @Operation(summary = "创建平台服务", description = "创建新的平台服务")
    public ResponseEntity<PlatformServiceVO> createService(
            @Parameter(description = "平台服务信息", required = true)
            @Valid @RequestBody PlatformServiceCreateDTO createDTO) {
        try {
            PlatformServiceVO service = platformServiceService.createService(createDTO);
            return ResponseEntity.ok(service);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}")
    @Operation(summary = "更新平台服务", description = "根据ID更新平台服务信息")
    public ResponseEntity<PlatformServiceVO> updateService(
            @Parameter(description = "服务ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "操作类型", required = true)
            @RequestParam String _method,
            @Parameter(description = "平台服务信息", required = true)
            @Valid @RequestBody PlatformServiceUpdateDTO updateDTO) {
        if (!"update".equals(_method)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            PlatformServiceVO service = platformServiceService.updateService(id, updateDTO);
            return ResponseEntity.ok(service);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/visibility")
    @Operation(summary = "更新服务可见性", description = "更新平台服务的可见状态")
    public ResponseEntity<Void> updateServiceVisibility(
            @Parameter(description = "服务ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "操作类型", required = true)
            @RequestParam String _method,
            @Parameter(description = "可见状态", required = true)
            @RequestParam Boolean isVisible) {
        if (!"updateVisibility".equals(_method)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            platformServiceService.updateServiceVisibility(id, isVisible);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/sort-order")
    @Operation(summary = "更新排序权重", description = "更新平台服务的排序权重")
    public ResponseEntity<Void> updateSortOrder(
            @Parameter(description = "服务ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "操作类型", required = true)
            @RequestParam String _method,
            @Parameter(description = "排序权重", required = true)
            @RequestParam Integer sortOrder) {
        if (!"updateSortOrder".equals(_method)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            platformServiceService.updateSortOrder(id, sortOrder);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/delete")
    @Operation(summary = "删除平台服务", description = "根据ID删除平台服务")
    public ResponseEntity<Void> deleteService(
            @Parameter(description = "服务ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "操作类型", required = true)
            @RequestParam String _method) {
        if (!"delete".equals(_method)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            platformServiceService.deleteService(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    @Operation(summary = "获取服务总数", description = "获取系统中平台服务总数")
    public ResponseEntity<Integer> getServiceCount() {
        int count = platformServiceService.getServiceCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/type/{serviceType}")
    @Operation(summary = "根据类型获取服务数量", description = "根据服务类型获取平台服务数量")
    public ResponseEntity<Integer> getServiceCountByType(
            @Parameter(description = "服务类型", required = true)
            @PathVariable String serviceType) {
        int count = platformServiceService.getServiceCountByType(serviceType);
        return ResponseEntity.ok(count);
    }
}