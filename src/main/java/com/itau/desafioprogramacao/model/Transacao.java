package com.itau.desafioprogramacao.model;

import java.time.OffsetDateTime;

public class Transacao {

    private static Long sequencia = 1L;

    private Long id;

    private double valor;
    private OffsetDateTime dataHora;

    public Transacao(double valor, OffsetDateTime dataHora) {
        this.id = sequencia++;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
