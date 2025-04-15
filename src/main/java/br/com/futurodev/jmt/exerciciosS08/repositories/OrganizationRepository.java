package br.com.futurodev.jmt.exerciciosS08.repositories;

import br.com.futurodev.jmt.exerciciosS08.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("SELECT o FROM Organization o WHERE UPPER(o.name) LIKE '%' ||UPPER(:search)|| '%' OR UPPER(o.contact) LIKE '%' ||UPPER(:search)|| '%' ")
    List<Organization> findByNameOrDescription(String search);
    List<Organization> findByNameContainingIgnoreCase(String search);
}
