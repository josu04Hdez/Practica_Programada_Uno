package com.practica.service;


import com.practica.domain.Curso;
import com.practica.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Transactional(readOnly = true)
    public List<Curso> getCursos(){
        return cursoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Curso> getCurso(Integer idCurso){
        return cursoRepository.findById(idCurso);
    }

    @Transactional
    public Curso save(Curso curso){
        return cursoRepository.save(curso);
    }

    @Transactional
    public void delete(Integer idCurso){
        if(cursoRepository.existsById(idCurso)){
            cursoRepository.deleteById(idCurso);
        } else {
            throw new IllegalArgumentException("No existe el " + idCurso);
        }
    }
}
