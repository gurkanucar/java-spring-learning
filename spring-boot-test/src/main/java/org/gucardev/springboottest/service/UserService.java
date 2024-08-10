package org.gucardev.springboottest.service;

import org.gucardev.springboottest.dto.UserDTO;
import org.gucardev.springboottest.dto.request.UserRequest;
import org.gucardev.springboottest.model.User;
import org.gucardev.springboottest.model.projection.MailUserNameProjection;
import org.gucardev.springboottest.model.projection.UsernameLengthProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {

  Page<UserDTO> getAllPageable(
      String searchTerm, String sortField, Sort.Direction sortDirection, Pageable pageable);

  User getById(Long id);

  Boolean userExistsById(Long id);

  UserDTO getByIdDTO(Long id);

  UserDTO create(UserRequest userRequest);

  UserDTO update(UserRequest userRequest);

  void delete(Long id);

  List<UsernameLengthProjection> getUserNamesListWithLengthGreaterThan(Integer length);

  List<MailUserNameProjection> getMailAndUsernames();

  List<UserDTO> getDifferentUsers();
}
