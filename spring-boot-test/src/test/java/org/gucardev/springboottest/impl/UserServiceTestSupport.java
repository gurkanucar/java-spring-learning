package org.gucardev.springboottest.impl;

import org.gucardev.springboottest.dto.UserDTO;
import org.gucardev.springboottest.dto.request.UserRequest;
import org.gucardev.springboottest.model.User;
import org.gucardev.springboottest.model.projection.MailUserNameProjection;
import org.gucardev.springboottest.model.projection.UsernameLengthProjection;

public abstract class UserServiceTestSupport {

  protected User user1, user2, user3, existingUser, updatedUser;
  protected UserDTO userDto1, userDto2, userDto3, updatedUserDto;
  protected UserRequest userRequest;

  void setupTestData() {
    user1 = createUser(1L, "User1", "user1@test.com", "user1");
    user2 = createUser(2L, "User2", "user2@test.com", "username2");
    user3 = createUser(3L, "User3", "user3@test.com", "username3");
    userDto1 = createUserDto(user1.getId(), user1.getName(), user1.getEmail(), user1.getUsername());
    userDto2 = createUserDto(user2.getId(), user2.getName(), user2.getEmail(), user2.getUsername());
    userDto3 = createUserDto(user3.getId(), user3.getName(), user3.getEmail(), user3.getUsername());

    existingUser = createUser(1L, "Existing User", "existing@test.com", "existingUser");
    existingUser.setId(1L);
    updatedUser = createUser(1L, "Updated User", "updated@test.com", user1.getUsername());
    updatedUserDto =
        createUserDto(
            updatedUser.getId(),
            updatedUser.getName(),
            updatedUser.getEmail(),
            updatedUser.getUsername());

    userRequest = createUserRequest(1L, "Request User", "request@test.com", "requestUser");
  }

  protected User createUser(Long id, String name, String email, String username) {
    return User.builder().id(id).name(name).email(email).username(username).build();
  }

  protected UserDTO createUserDto(Long id, String name, String email, String username) {
    return UserDTO.builder().id(id).name(name).email(email).username(username).build();
  }

  protected UserRequest createUserRequest(Long id, String name, String email, String username) {
    UserRequest userRequest = new UserRequest();
    userRequest.setId(id);
    userRequest.setName(name);
    userRequest.setEmail(email);
    userRequest.setUsername(username);
    return userRequest;
  }

  protected MailUserNameProjection getMailUsernameProjection(String mail, String username) {
    return new MailUserNameProjection() {
      @Override
      public String getUsername() {
        return mail;
      }

      @Override
      public String getEmail() {
        return username;
      }
    };
  }

  protected UsernameLengthProjection getUsernameLengthProjection(
      Long id, String username, String mail, Integer length) {
    return new UsernameLengthProjection() {
      @Override
      public String getUsername() {
        return mail;
      }

      @Override
      public Long getId() {
        return id;
      }

      @Override
      public Integer getLength() {
        return length;
      }

      @Override
      public String getEmail() {
        return username;
      }
    };
  }
}
