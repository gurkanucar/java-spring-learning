package org.gucardev.authmicro.repository;

import org.gucardev.authmicro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsernameAndIsEnabledTrue(String username);
}
