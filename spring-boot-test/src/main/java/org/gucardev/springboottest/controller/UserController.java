package org.gucardev.springboottest.controller;

import org.gucardev.springboottest.dto.UserDTO;
import org.gucardev.springboottest.dto.request.UserRequest;
import org.gucardev.springboottest.model.projection.MailUserNameProjection;
import org.gucardev.springboottest.model.projection.UsernameLengthProjection;
import org.gucardev.springboottest.service.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Page<UserDTO>> getAllPageable(
      @RequestParam(required = false) String searchTerm,
      @RequestParam(defaultValue = "name", required = false) String sortField,
      @RequestParam(defaultValue = "ASC", required = false) String sortDirection,
      @PageableDefault(size = 20) Pageable pageable) {
    Sort.Direction direction = Sort.Direction.fromString(sortDirection.toUpperCase());
    Page<UserDTO> result = userService.getAllPageable(searchTerm, sortField, direction, pageable);
    return ResponseEntity.ok(result);
  }

  @RateLimiter(name = "basic")
  @GetMapping("/mail-username")
  public ResponseEntity<List<MailUserNameProjection>> getMailAndUsernames() {
    return ResponseEntity.ok(userService.getMailAndUsernames());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getByIdDTO(id));
  }

  @PostMapping
  public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserRequest userRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequest));
  }

  @PutMapping
  public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserRequest userRequest) {
    return ResponseEntity.ok(userService.update(userRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/different-users")
  public ResponseEntity<List<UserDTO>> differentUsers() {
    return ResponseEntity.ok(userService.getDifferentUsers());
  }

  @GetMapping("/username-length/{length}")
  public ResponseEntity<List<UsernameLengthProjection>> getById(@PathVariable Integer length) {
    return ResponseEntity.ok(userService.getUserNamesListWithLengthGreaterThan(length));
  }
}
