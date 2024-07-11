package com.gucardev.springlearning.parent_child_caching;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServicee {

    private final UserRepositoryy userRepositoryy;
    private final UserMapperr userMapperr;

    public UserServicee(UserRepositoryy userRepositoryy, UserMapperr userMapperr) {
        this.userRepositoryy = userRepositoryy;
        this.userMapperr = userMapperr;
    }

    public Userr getUserById(Long id) {
        return userRepositoryy.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Cacheable(value = "users", key = "#id")
    public UserDto getUserDtoById(Long id) {
        Userr user = getUserById(id);
        return userMapperr.userToUserDto(user);
    }

    @CacheEvict(value = "users", key = "#userr.id")
    public Userr updateUser(Userr userr) {
        return userRepositoryy.save(userr);
    }


}
