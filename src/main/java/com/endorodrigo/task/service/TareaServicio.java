package com.endorodrigo.task.service;

import com.endorodrigo.task.model.Tarea;
import com.endorodrigo.task.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaServicio implements ITareaServicio {

    public final TareaRepository tareaRepository;

    public TareaServicio(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public void guardarTarea(Tarea tarea) {
        tareaRepository.save(tarea);
    }

    @Override
    public void eliminarTarea(Integer id) {
        tareaRepository.deleteById(id);
    }

    @Override
    public List<Tarea> listarTarea() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea listarTareaPorID(Integer id) {
        return tareaRepository.findById(id).orElse(null);
    }
}
