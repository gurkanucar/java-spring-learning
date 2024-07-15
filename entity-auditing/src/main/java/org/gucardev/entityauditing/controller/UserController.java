package org.gucardev.entityauditing.controller;

import org.gucardev.entityauditing.dto.UserDTO;
import org.gucardev.entityauditing.dto.UserHistoryDTO;
import org.gucardev.entityauditing.dto.request.UserRequest;
import org.gucardev.entityauditing.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping()
    public ResponseEntity<UserDTO> update(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/revision/{id}")
    public ResponseEntity<List<UserHistoryDTO>> getUserRevisions(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserHistory(id));
    }
}
