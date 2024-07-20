package org.gucardev.entityrelationshipexamples.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.gucardev.entityrelationshipexamples.dto.UserDTO;
import org.gucardev.entityrelationshipexamples.mapper.UserMapper;
import org.gucardev.entityrelationshipexamples.model.User;
import org.gucardev.entityrelationshipexamples.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //    private final CityService cityService;
    private final LookupValueService lookupValueService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        return userMapper.userToUserDTO(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User not found with id " + id);
        }

        User existingUser = optionalUser.get();

        // update fields and ignore entity-relation fields
        userMapper.updateUserFromDto(updatedUserDTO, existingUser);

        // set related fields
        if (updatedUserDTO.getOccupationId() != null) {
            existingUser.setOccupation(lookupValueService.getValueById(updatedUserDTO.getOccupationId()));
        } else {
            existingUser.setOccupation(null);
        }

        if (updatedUserDTO.getStatusId() != null) {
            existingUser.setStatus(lookupValueService.getValueById(updatedUserDTO.getStatusId()));
        } else {
            existingUser.setStatus(null);
        }


        return userMapper.userToUserDTO(userRepository.save(existingUser));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
