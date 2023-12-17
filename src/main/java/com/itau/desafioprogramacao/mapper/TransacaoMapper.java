package com.itau.desafioprogramacao.mapper;

import com.itau.desafioprogramacao.dto.TransacaoDTO;
import com.itau.desafioprogramacao.model.Transacao;

public class TransacaoMapper {

    public static Transacao toTransacao(TransacaoDTO transacaoDTO) {

        Transacao transacao = new Transacao(transacaoDTO.getValor(), transacaoDTO.getDataHora());

        return transacao;
    }
}
