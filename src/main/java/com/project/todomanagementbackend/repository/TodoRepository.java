package com.project.todomanagementbackend.repository;

import com.project.todomanagementbackend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
