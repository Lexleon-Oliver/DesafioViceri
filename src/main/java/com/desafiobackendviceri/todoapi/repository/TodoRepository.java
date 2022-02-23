package com.desafiobackendviceri.todoapi.repository;

import com.desafiobackendviceri.todoapi.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
