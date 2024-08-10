package org.gucardev.springshell.repository;

import org.gucardev.springshell.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
