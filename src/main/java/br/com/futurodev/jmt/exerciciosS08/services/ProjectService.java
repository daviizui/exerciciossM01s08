package br.com.futurodev.jmt.exerciciosS08.services;

import br.com.futurodev.jmt.exerciciosS08.entities.Organization;
import br.com.futurodev.jmt.exerciciosS08.entities.Project;
import br.com.futurodev.jmt.exerciciosS08.entities.Project;
import br.com.futurodev.jmt.exerciciosS08.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;
    @Autowired
    private OrganizationService organizationService;

    public List<Project> findAll( String search) {

        List<Project> projects;
        if (search == null || search.isEmpty()){
            projects = repository.findAll();
        } else {
            projects = repository.findByNameOrDescription(search);
        }
        List<Project> response = new ArrayList<>();
        for (Project project : projects){
            response.addAll(projects);
        }

        return response;
    }

    public Project findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Project save(Project project) {
        if (project.getId() != null){
            Project old = findById(project.getId());
            if (old != null){
                project.setId((old.getId()));
            }else {
                project.setId(null);
            }
        }

        Organization organization = organizationService.findById(project.getOrganization().getId());
        if (project == null){
            return null;
        }
        project.setOrganization(organization);

        return repository.save(project);
        
        
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
