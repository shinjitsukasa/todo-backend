package com.project.todomanagementbackend.service.impl;

import com.project.todomanagementbackend.dto.TodoDto;
import com.project.todomanagementbackend.entity.Todo;
import com.project.todomanagementbackend.exception.ResourceNotFoundException;
import com.project.todomanagementbackend.repository.TodoRepository;
import com.project.todomanagementbackend.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert TodoDto into Todo Jpa entity

        Todo todo = modelMapper.map(todoDto, Todo.class);

        // Todo Jpa entity
        Todo savedTodo = todoRepository.save(todo);

        // Convert saved Todo Jpa entity object into TodoDto object
        TodoDto savedToDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedToDto;
    }

    @Override
    public TodoDto getTodo(Long id) {

        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with the id: " + id));

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(Long todoId, TodoDto updateTodo) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));

        todo.setTitle(updateTodo.getTitle());
        todo.setDescription(updateTodo.getDescription());
        todo.setCompleted(updateTodo.isCompleted());

        Todo updatedTodoObj = todoRepository.save(todo);

        return modelMapper.map(updatedTodoObj, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long todoId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo not found with the id: " + todoId));

        todoRepository.deleteById(todoId);

    }

    @Override
    public TodoDto completeTodo(Long todoId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo not found with the id: " + todoId));

        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long todoId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo not found with the id: " + todoId));

        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
