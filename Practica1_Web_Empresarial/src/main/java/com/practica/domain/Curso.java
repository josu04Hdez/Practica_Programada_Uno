package com.practica.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name = "curso")
@Data
public class Curso {

    //Definicion de atributos de clase clasicos de JPA, de jo listo la auto incrementacion del id.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer idCurso;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 255)
    private String descripcion;

    @NotNull
    @Min(0)
    private Integer creditos;

    @NotNull
    private Boolean estado;

    @Size(max = 255)
    private String imagen;

    @OneToMany(mappedBy = "curso")
    private List<Estudiante> estudiantes;


}
