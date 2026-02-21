package com.practica.repository;
import com.practica.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
     public Curso findByNombre(String nombre);

}
