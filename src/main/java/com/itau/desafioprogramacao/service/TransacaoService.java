package com.itau.desafioprogramacao.service;

import com.itau.desafioprogramacao.dto.TransacaoDTO;
import com.itau.desafioprogramacao.mapper.TransacaoMapper;
import com.itau.desafioprogramacao.model.Transacao;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TransacaoService {

    private static final Map<Long, Transacao> transacoes = new HashMap<>();

    public Transacao salvar(TransacaoDTO transacaoDTO) {

        Transacao transacao = TransacaoMapper.toTransacao(transacaoDTO);
        transacoes.put(transacao.getId(), transacao);
        return transacao;
    }
}
