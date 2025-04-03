package com.endorodrigo.task.repository;

import com.endorodrigo.task.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
}
