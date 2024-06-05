package com.pbcompass.apifuncionarios.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemErroPadrao implements Serializable {

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
