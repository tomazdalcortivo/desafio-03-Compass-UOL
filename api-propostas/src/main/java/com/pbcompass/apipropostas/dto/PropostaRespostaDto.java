package com.pbcompass.apipropostas.dto;

import com.pbcompass.apipropostas.entities.Funcionario;
import java.util.Date;

public class PropostaRespostaDto {

    private Long id;
    private String nome;
    private String descricao;
    private Funcionario criador;
    private Integer duracaoEmMinutos;
    private Date inicioVotacao;
}
