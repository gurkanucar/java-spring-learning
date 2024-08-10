package org.gucardev.springshell.service;

import org.gucardev.springshell.model.Todo;
import org.gucardev.springshell.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;

  public List<Todo> getAllTodos() {
    return todoRepository.findAll();
  }

  public Todo createTodo(Todo todo) {
    return todoRepository.save(todo);
  }

  public void deleteTodo(Long id) {
    todoRepository.deleteById(id);
  }

  public Todo toggleTodoStatus(Long id) {
    Optional<Todo> optionalTodo = todoRepository.findById(id);
    if (optionalTodo.isPresent()) {
      Todo todo = optionalTodo.get();
      todo.setCompleted(!todo.isCompleted());
      return todoRepository.save(todo);
    } else {
      throw new EntityNotFoundException("entity not found by given id!");
    }
  }
}
