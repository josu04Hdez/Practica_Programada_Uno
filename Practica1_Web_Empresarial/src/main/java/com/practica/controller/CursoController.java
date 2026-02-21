package com.practica.controller;
import com.practica.domain.Curso;
import com.practica.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;
import java.util.Optional;


@Controller
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;
    private final MessageSource messageSource;

    public CursoController(CursoService cursoService, MessageSource messageSource) {
        this.cursoService = cursoService;
        this.messageSource = messageSource;
    }

    @GetMapping("/listado")
    public String listado(Model model){
        model.addAttribute("cursos", cursoService.getCursos());
        model.addAttribute("curso", new Curso());
        return "curso/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Curso curso, RedirectAttributes redirectAttributes){
        if(curso.getEstado()==null){
            curso.setEstado(true);
        }
        cursoService.save(curso);
        redirectAttributes.addFlashAttribute("Salvado", "Curso guardado exitosamente");
        return "redirect:/cursos/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idCurso, RedirectAttributes redirectAttributes){
        String titulo ="todoOk";
        String detalle = "Curso eliminado correctamente";

        try{
            cursoService.delete(idCurso);

        } catch (IllegalArgumentException e){
            titulo = "error";
            detalle = "El curso no existe";=
        } catch (Exception e){
            titulo = "error";
            detalle = "Error desconocido";
        }

        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/cursos/listado";
    }

    @GetMapping("/modificar/{idCurso}")
    public String modificar(@PathVariable Integer idCurso, Model model, RedirectAttributes redirectAttributes){
        Optional<Curso> cursoOpt = cursoService.getCurso(idCurso);
        if(cursoOpt.isEmpty()){
            redirectAttributes.addFlashAttribute("error", "El curso no existe");
            return "redirect:/cursos/listado";
        }
        model.addAttribute("curso",cursoOpt.get());
        return "curso/modificar";
    }

}
