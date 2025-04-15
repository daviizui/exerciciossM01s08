package br.com.futurodev.jmt.exerciciosS08.services;

import br.com.futurodev.jmt.exerciciosS08.entities.Organization;
import br.com.futurodev.jmt.exerciciosS08.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    public List<Organization> findAll(String search) {
        List<Organization> organizations;
        if (search == null || search.isEmpty()){
            organizations = repository.findAll();
        } else {
            organizations = repository.findByNameOrDescription(search);
        }
            List<Organization> response = new ArrayList<>();
           for (Organization organization : organizations){
               response.addAll(organizations);
           }

        return response;
    }

    public Organization findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Organization save(Organization organization) {
        if (organization.getId() != null){
            Organization old = findById(organization.getId());
            if (old != null){
                organization.setId((old.getId()));
            }else {
                organization.setId(null);
            }
        }
        return repository.save(organization);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
