package com.project.todomanagementbackend.service;

import com.project.todomanagementbackend.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(Long todoId, TodoDto updateTodo);

    void deleteTodo(Long todoId);

    TodoDto completeTodo(Long todoId);

    TodoDto incompleteTodo(Long todoId);
}
