package br.com.futurodev.jmt.exerciciosS08.services;

import br.com.futurodev.jmt.exerciciosS08.dtos.OrganizationRequestDto;
import br.com.futurodev.jmt.exerciciosS08.dtos.OrganizationResponseDto;
import br.com.futurodev.jmt.exerciciosS08.entities.Organization;
import br.com.futurodev.jmt.exerciciosS08.repositories.OrganizationRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    public List<OrganizationResponseDto> findAll(String search) {
        List<Organization> organizations;
        if (search == null || search.isEmpty()){
            organizations = repository.findAll();
        } else {
            organizations = repository.findByNameOrDescription(search);
        }
            List<OrganizationResponseDto> response = new ArrayList<>();
           for (Organization organization : organizations){
               response.add(new OrganizationResponseDto(organization.getId(), organization.getName(), organization.getContact()));
           }

        return response;
    }

    public OrganizationResponseDto findById(Long id) {
        Optional <Organization> organizationOptional = repository.findById(id);
        if (organizationOptional.isPresent()){
            Organization organization = organizationOptional.get();
            return new OrganizationResponseDto(organization.getId(), organization.getName(), organization.getContact());
        }
        return null;
    }

    public OrganizationResponseDto save(OrganizationRequestDto dto) {
        Organization organization = new Organization();
        organization.setName(dto.name());
        organization.setContact(dto.contact());

        organization = repository.save(organization);

        return new OrganizationResponseDto(organization.getId(),organization.getName(), organization.getContact());

    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public OrganizationResponseDto update(Long id, OrganizationRequestDto dto) {
       Organization organization = findByEntityById(id);

       if (organization != null){
           organization.setId(id);
           organization.setName(dto.name());
           organization.setContact(dto.contact());
           return save(new OrganizationRequestDto(organization.getName(), organization.getContact()));
       }
       return null;
    }

    public Organization findByEntityById(Long id){
        Optional<Organization> organizationOptional = repository.findById(id);
        if (organizationOptional.isPresent()){
            return organizationOptional.get();
        }
        return null;
    }
}
