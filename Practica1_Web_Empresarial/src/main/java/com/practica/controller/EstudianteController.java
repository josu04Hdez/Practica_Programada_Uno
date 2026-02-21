package com.practica.controller;
import com.practica.domain.Estudiante;
import com.practica.service.EstudianteService;
import com.practica.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    public final EstudianteService estudianteService;
    public final CursoService cursoService;

    public EstudianteController(EstudianteService estudianteService, CursoService cursoService) {
        this.estudianteService = estudianteService;
        this.cursoService = cursoService;
    }

    @GetMapping("/listado")
    public String listado(Model model){
        var estudiantes = estudianteService.getEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("totalEstudiantes", estudiantes.size());

        var cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);
        return "estudiante/listado";

    }

    @PostMapping("/guardar")
    public String guardar(@Valid Estudiante estudiante, @RequestParam Integer idCurso, RedirectAttributes redirectAttributes){
        var cursoOpt = cursoService.getCurso(idCurso);
        if(cursoOpt.isEmpty()){
            redirectAttributes.addFlashAttribute("Error","No existe el curso que se esta seleccionando");
            return "redirect:/estudiantes/listado";
        }
        estudiante.setCurso(cursoOpt.get());

        estudianteService.save(estudiante);
        redirectAttributes.addFlashAttribute("Salvado","Estudiante guardado exitosamente");
        return "redirect:/estudiantes/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idEstudiante, RedirectAttributes redirectAttributes){
        String titulo ="todoOk";
        String detalle = "Estudiante eliminado correctamente";
        try{
            estudianteService.delete(idEstudiante);
        } catch (IllegalArgumentException e){
            titulo = "Error";
            detalle = "El estudiante no existe";
        } catch (Exception e){
            titulo = "Error";
            detalle = "Error desconocido";
        }
        redirectAttributes.addFlashAttribute(titulo,detalle);
        return "redirect:/estudiantes/listado";
    }

    @GetMapping("/modificar/{idEstudiante}")
    public String modificar(@PathVariable("idEstudiante") Integer idEstudiante, Model model, RedirectAttributes redirectAttributes){
        Optional<Estudiante> estudianteOpt = estudianteService.getEstudiante(idEstudiante);
        if(estudianteOpt.isEmpty()){
            redirectAttributes.addFlashAttribute("error", "El estudiante no existe");
            return "redirect:/estudiantes/listado";
        }
        model.addAttribute("estudiante",estudianteOpt.get());
        model.addAttribute("cursos", cursoService.getCursos());
        return "estudiante/modificar";
    }

    @GetMapping("/por-curso/{idCurso}")
    public String porCurso(@PathVariable("idCurso") Integer idCurso, Model model, RedirectAttributes redirectAttributes){
        var cursoOpt = cursoService.getCurso(idCurso);
        if(cursoOpt.isEmpty()){
            redirectAttributes.addFlashAttribute("error", "El curso no existe");
            return "redirect:/estudiantes/listado";
        }

        model.addAttribute("curso", cursoOpt.get());
        model.addAttribute("estudiantes", estudianteService.getEstudiantesbyCurso(idCurso));
        return "estudiante/porCurso";
    }


}
