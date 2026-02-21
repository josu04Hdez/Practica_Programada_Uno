package com.practica.service;


import com.practica.domain.Estudiante;
import com.practica.repository.EstudianteRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Transactional(readOnly = true)
    public List<Estudiante> getEstudiantes(){
        return estudianteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Estudiante> getEstudiantesbyCurso(Integer idCurso){
        return estudianteRepository.findByCursoIdCurso(idCurso);
    }

    @Transactional(readOnly = true)
    public Optional<Estudiante> getEstudiante(Integer idEstudiante){
        return estudianteRepository.findById(idEstudiante);
    }

    @Transactional
    public Estudiante save(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    @Transactional
    public void delete(Integer idEstudiante){
        if(estudianteRepository.existsById(idEstudiante)){
            estudianteRepository.deleteById(idEstudiante);
        } else {
            throw new IllegalArgumentException("No existe el " + idEstudiante);
        }
    }
}
