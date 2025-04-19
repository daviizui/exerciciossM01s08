package br.com.futurodev.jmt.exerciciosS08.controllers;

import br.com.futurodev.jmt.exerciciosS08.dtos.ProjectRequestDto;
import br.com.futurodev.jmt.exerciciosS08.dtos.ProjectResponseDto;
import br.com.futurodev.jmt.exerciciosS08.entities.Project;
import br.com.futurodev.jmt.exerciciosS08.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    public List<ProjectResponseDto> get(String search){
        return service.findAll(search);
    }

    @GetMapping("{id}")
    public ProjectResponseDto getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDto post(@RequestBody ProjectRequestDto dto){
        return service.create(dto);
    }

    @PutMapping("{id}")
    public ProjectResponseDto put(@PathVariable Long id, @RequestBody ProjectRequestDto dto){
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
