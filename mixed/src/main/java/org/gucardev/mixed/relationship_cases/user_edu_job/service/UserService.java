package org.gucardev.mixed.relationship_cases.user_edu_job.service;

import org.gucardev.mixed.relationship_cases.user_edu_job.dto.UserDTO;
import org.gucardev.mixed.relationship_cases.user_edu_job.entity.User;
import org.gucardev.mixed.relationship_cases.user_edu_job.mapper.UserMapper;
import org.gucardev.mixed.relationship_cases.user_edu_job.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(x -> userMapper.toDTO(x))
                .toList();
    }
}
