package br.com.futurodev.jmt.exerciciosS08.controllers;

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
    public List<Organization> get(String search){
        return service.findAll(search);
    }

    @GetMapping("{id}")
    public Organization getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Organization post(@RequestBody Organization organization){
        organization.setId(null);
        return service.save(organization);
    }

    @PutMapping("{id}")
    public Organization put(@PathVariable Long id, @RequestBody Organization organization){
        organization.setId(id);
        return service.save(organization);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
