package com.itau.desafioprogramacao.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class TransacaoDTO {

    private double valor;
    private OffsetDateTime dataHora;

    public TransacaoDTO(double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
