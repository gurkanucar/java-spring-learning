package org.gucardev.springboottest.repository;

import org.gucardev.springboottest.model.User;
import org.gucardev.springboottest.model.projection.MailUserNameProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {

  boolean existsById(Long id);

  boolean existsByUsernameIgnoreCase(String username);

  Page<User> findAll(Specification<User> spec, Pageable pageable);

  @Query(
      value = "select u.username as username, u.email as email from user_table u",
      nativeQuery = true)
  List<MailUserNameProjection> findAllMailAndUserName();

  @Query("SELECT u FROM User u WHERE u.username NOT IN :usernames")
  List<User> findUsersNotInUsernameList(@Param("usernames") List<String> usernames);


}
