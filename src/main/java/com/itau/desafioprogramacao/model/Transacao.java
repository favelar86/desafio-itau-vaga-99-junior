package com.itau.desafioprogramacao.model;

import java.time.OffsetDateTime;

public class Transacao implements Comparable<Transacao> {

    private double valor;
    private OffsetDateTime dataHora;

    public Transacao(double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public int compareTo(Transacao transacao) {

        if (this.dataHora.isBefore(transacao.getDataHora())) {
            return 1;
        }

        if (this.dataHora.isAfter(transacao.getDataHora())) {
            return -1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "valor=" + valor +
                ", dataHora=" + dataHora +
                '}';
    }
}
