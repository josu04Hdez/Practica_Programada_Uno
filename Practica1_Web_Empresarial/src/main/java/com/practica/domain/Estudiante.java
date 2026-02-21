package com.practica.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.*;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "estudiante")
public class Estudiante {

    //Definicion de atributos de clase clasicos de JPA, de jo listo la auto incrementacion del id.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    @NotBlank
    @Email
    @Size(max = 100)
    private String correo;

    @NotNull
    @Min(1)
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;




}
