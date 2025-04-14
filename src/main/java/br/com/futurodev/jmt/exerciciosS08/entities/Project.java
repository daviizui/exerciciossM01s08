package br.com.futurodev.jmt.exerciciosS08.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(length = 225)
    private String description;
    private String region;
    private Double reductionCo2;

    @ManyToOne
    @JoinColumn(name = "organozation_id", nullable = false)
    private Organization organization;


}
