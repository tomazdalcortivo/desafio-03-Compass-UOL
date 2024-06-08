package com.pbcompass.apipropostas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Embedded
    private Funcionario criador;

    @Column(name = "duracao")
    private Integer duracao = 60000;

    private Date inicioVotacao;

    @OneToMany(mappedBy = "id", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Voto> votos = new ArrayList<>();
}
