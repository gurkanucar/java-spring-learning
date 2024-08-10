package org.gucardev.springboottest.repository;

import org.gucardev.springboottest.model.projection.UsernameLengthProjection;

import java.util.List;

public interface UserCustomRepository {

  List<UsernameLengthProjection> getUserNamesListWithLengthGreaterThan(int length);
}
