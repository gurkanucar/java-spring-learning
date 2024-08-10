package org.gucardev.securityexample3.controller;

import org.gucardev.securityexample3.dto.RoleDto;
import org.gucardev.securityexample3.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RoleDto userDto) {
        return ResponseEntity.ok(roleService.createRole(userDto));
    }
}
