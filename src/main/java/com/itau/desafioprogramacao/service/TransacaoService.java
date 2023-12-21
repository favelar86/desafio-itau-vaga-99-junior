package com.itau.desafioprogramacao.service;

import com.itau.desafioprogramacao.dto.TransacaoDTO;
import com.itau.desafioprogramacao.exceptions.ValidacaoException;
import com.itau.desafioprogramacao.mapper.TransacaoMapper;
import com.itau.desafioprogramacao.model.Transacao;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class TransacaoService {

    private static final Map<Long, Transacao> transacoes = new HashMap<>();

    public Transacao salvar(TransacaoDTO transacaoDTO) {

        validaRequest(transacaoDTO);

        Transacao transacao = TransacaoMapper.toTransacao(transacaoDTO);
        transacoes.put(transacao.getId(), transacao);
        return transacao;
    }

    public void validaRequest(TransacaoDTO transacaoDTO) {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        var dataAgora = formatter.format(offsetDateTime);
        var dataTransacao = formatter.format(offsetDateTime.parse(transacaoDTO.getDataHora()));

        var data = dataTransacao.compareTo(dataAgora);

        if(data > 0) {
            throw new ValidacaoException();
        }

        if(Double.parseDouble(transacaoDTO.getValor()) < 0) {
            throw new ValidacaoException();
        }

    }
}
