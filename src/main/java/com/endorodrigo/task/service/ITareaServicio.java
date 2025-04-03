package com.endorodrigo.task.service;

import com.endorodrigo.task.model.Tarea;

import java.util.List;

public interface ITareaServicio {
    public void guardarTarea(Tarea tarea);
    public void eliminarTarea(Integer id);
    public List<Tarea> listarTarea();
    public Tarea listarTareaPorID(Integer id);
}
