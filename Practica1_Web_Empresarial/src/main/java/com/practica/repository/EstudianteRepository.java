package com.practica.repository;
import com.practica.domain.Estudiante;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    List<Estudiante> findByCursoIdCurso(Integer idCurso);

}
