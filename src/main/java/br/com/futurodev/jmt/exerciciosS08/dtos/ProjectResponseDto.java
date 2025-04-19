package br.com.futurodev.jmt.exerciciosS08.dtos;

import br.com.futurodev.jmt.exerciciosS08.entities.Organization;

public record ProjectResponseDto(
        Long id,
        String name,
        String description,
        String region,
        Double reductionCo2,
        Organization organization
) {
}
