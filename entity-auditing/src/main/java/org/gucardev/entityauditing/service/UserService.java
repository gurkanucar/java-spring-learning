package org.gucardev.entityauditing.service;

import org.gucardev.entityauditing.dto.UserDTO;
import org.gucardev.entityauditing.dto.UserHistoryDTO;
import org.gucardev.entityauditing.dto.request.UserRequest;
import org.gucardev.entityauditing.model.User;

import java.util.List;

public interface UserService {

  UserDTO create(UserRequest userRequest);

  List<UserDTO> getAll();

  UserDTO update(UserRequest userRequest);

  UserDTO getById(Long id);

  void delete(Long id);

  User findUserById(Long id);

  List<UserHistoryDTO> getUserHistory(Long userId);
}
