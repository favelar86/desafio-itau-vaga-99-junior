package com.itau.desafioprogramacao.mapper;

import com.itau.desafioprogramacao.dto.TransacaoDTO;
import com.itau.desafioprogramacao.model.Transacao;

import java.time.OffsetDateTime;

public class TransacaoMapper {

    public static Transacao toTransacao(TransacaoDTO transacaoDTO) {

        Transacao transacao = new Transacao(Double.parseDouble(transacaoDTO.getValor()), OffsetDateTime.parse(transacaoDTO.getDataHora()));

        return transacao;
    }
}
