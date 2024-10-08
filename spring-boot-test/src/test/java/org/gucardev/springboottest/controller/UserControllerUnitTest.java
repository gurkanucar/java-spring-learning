package org.gucardev.springboottest.controller;

import org.gucardev.springboottest.dto.UserDTO;
import org.gucardev.springboottest.dto.request.UserRequest;
import org.gucardev.springboottest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerUnitTest {

  @Mock private UserService userService;
  private UserController userController;

  @BeforeEach
  public void setup() {
    // MockitoAnnotations.openMocks(this); // or use class annotation
    userController = new UserController(userService);
  }

  @Test
  void getAllPageable_givenSortFieldAndSortDirection_returnsUsers() {
    Pageable pageable = PageRequest.of(0, 20);
    Page<UserDTO> userDTOPage = Page.empty(pageable);
    when(userService.getAllPageable(any(), any(), any(), any())).thenReturn(userDTOPage);
    ResponseEntity<Page<UserDTO>> response =
        userController.getAllPageable("searchTerm", "name", "ASC", pageable);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userDTOPage, response.getBody());
  }

  @Test
  void getById_givenUserId_returnsUser() {
    UserDTO userDTO = new UserDTO();
    when(userService.getByIdDTO(anyLong())).thenReturn(userDTO);
    ResponseEntity<UserDTO> response = userController.getById(1L);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userDTO, response.getBody());
  }

  @Test
  void createUser_givenUserRequest_createsUser() {
    UserDTO userDTO = new UserDTO();
    when(userService.create(any())).thenReturn(userDTO);
    ResponseEntity<UserDTO> response = userController.createUser(any());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(userDTO, response.getBody());
  }

  @Test
  void updateUser_givenUserRequest_updatesUser() {
    UserDTO userDTO = new UserDTO();
    when(userService.update(any())).thenReturn(userDTO);
    ResponseEntity<UserDTO> response = userController.updateUser(any(UserRequest.class));
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userDTO, response.getBody());
  }
}
