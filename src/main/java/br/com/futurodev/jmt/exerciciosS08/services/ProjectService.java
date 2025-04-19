package br.com.futurodev.jmt.exerciciosS08.services;

import br.com.futurodev.jmt.exerciciosS08.dtos.ProjectRequestDto;
import br.com.futurodev.jmt.exerciciosS08.dtos.ProjectResponseDto;
import br.com.futurodev.jmt.exerciciosS08.entities.Organization;
import br.com.futurodev.jmt.exerciciosS08.entities.Project;
import br.com.futurodev.jmt.exerciciosS08.repositories.OrganizationRepository;
import br.com.futurodev.jmt.exerciciosS08.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    OrganizationRepository organizationRepository;

    public List<ProjectResponseDto> findAll(String search) {

        List<Project> projects;
        if (search == null || search.isEmpty()){
            projects = repository.findAll();
        } else {
            projects = repository.findByNameOrDescription(search);
        }
        List<ProjectResponseDto> response = new ArrayList<>();
        for (Project project : projects){
            response.add(new ProjectResponseDto(
                    project.getId(),
                    project.getName(),
                    project.getDescription(),
                    project.getRegion(),
                    project.getReductionCo2(),
                    project.getOrganization())
            );
        }

        return response;
    }

    public ProjectResponseDto findById(Long id) {
        Optional<Project> projectOptional = repository.findById(id);
        if (projectOptional.isPresent()){
            Project project = projectOptional.get();
            return new ProjectResponseDto(
                    project.getId(),
                    project.getName(),
                    project.getDescription(),
                    project.getRegion(),
                    project.getReductionCo2(),
                    project.getOrganization()
            );
        }
        return null;
    }

    public ProjectResponseDto create(ProjectRequestDto dto) {
        return save(new Project(), dto);    }


    public ProjectResponseDto update(Long id, ProjectRequestDto dto) {
        Project project = findEntityById(id);

        if (project !=null){
            save(project, dto);
        }
        return null;
    }

    private Project findEntityById (Long id){
        Optional <Project> projectOptional = repository.findById(id);
        if (projectOptional.isPresent()){
            return projectOptional.get();
        }
        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ProjectResponseDto save(Project project, ProjectRequestDto dto) {
        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setRegion(dto.region());
        project.setReductionCo2(dto.reductionCo2());

        Organization organization = organizationService.findByEntityById(dto.organizationId());
        project.setOrganization(organization);

        repository.save(project);

        return new ProjectResponseDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getRegion(),
                project.getReductionCo2(),
                project.getOrganization()
        );
    }
}
