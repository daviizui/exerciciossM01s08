package br.com.futurodev.jmt.exerciciosS08.controllers;

import br.com.futurodev.jmt.exerciciosS08.dtos.OrganizationRequestDto;
import br.com.futurodev.jmt.exerciciosS08.dtos.OrganizationResponseDto;
import br.com.futurodev.jmt.exerciciosS08.entities.Organization;
import br.com.futurodev.jmt.exerciciosS08.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService service;

    @GetMapping
    public List<OrganizationResponseDto> get(String search){
        return service.findAll(search);
    }

    @GetMapping("{id}")
    public OrganizationResponseDto getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationResponseDto post(@RequestBody OrganizationRequestDto dto){
        return service.save(dto);
    }

    @PutMapping("{id}")
    public OrganizationResponseDto put(@PathVariable Long id, @RequestBody OrganizationRequestDto dto){
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
