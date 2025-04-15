package br.com.futurodev.jmt.exerciciosS08.repositories;


import br.com.futurodev.jmt.exerciciosS08.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p WHERE UPPER(p.region) LIKE '%' ||UPPER(:search)|| '%' OR UPPER(p.organization) LIKE '%' ||UPPER(:search)|| '%' ")
    List<Project> findByNameOrDescription(String search);
}