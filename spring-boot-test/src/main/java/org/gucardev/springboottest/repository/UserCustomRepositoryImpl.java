package org.gucardev.springboottest.repository;

import org.gucardev.springboottest.model.projection.UsernameLengthProjection;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

  @PersistenceContext EntityManager entityManager;

  @Override
  public List<UsernameLengthProjection> getUserNamesListWithLengthGreaterThan(int length) {
    String queryString =
        "SELECT u.username AS username, u.id AS id, LENGTH(u.username) AS length, u.email AS email "
            + "FROM user_table u WHERE LENGTH(u.username) > :length";
    Query query = entityManager.createNativeQuery(queryString);
    query.setParameter("length", length);

    List<Object[]> results = query.getResultList();

    return results.stream()
        .map(
            result ->
                new UsernameLengthProjection() {
                  private final String username = (String) result[0];
                  private final Long id = ((Long) result[1]);
                  private final Integer length = ((Long) result[2]).intValue();
                  private final String email = (String) result[3];

                  @Override
                  public String getUsername() {
                    return username;
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
                    return email;
                  }
                })
        .collect(Collectors.toList());
  }
}
