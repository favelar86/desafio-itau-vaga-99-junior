package com.itau.desafioprogramacao.service;

import com.itau.desafioprogramacao.dto.EstatísticasDTO;
import com.itau.desafioprogramacao.dto.TransacaoDTO;
import com.itau.desafioprogramacao.exceptions.ValidacaoException;
import com.itau.desafioprogramacao.mapper.TransacaoMapper;
import com.itau.desafioprogramacao.model.Transacao;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

@Component
public class TransacaoService {

    private static List<Transacao> transacoes =  new ArrayList<>();

    public Transacao salvar(TransacaoDTO transacaoDTO) {

        validaRequest(transacaoDTO);

        Transacao transacao = TransacaoMapper.toTransacao(transacaoDTO);
        transacoes.add(transacao);

        return transacao;
    }

    public void deletar() {

        int indice = 0;
        while (!transacoes.isEmpty()) {
            transacoes.remove(indice);
            indice++;
        }
    }

    public EstatísticasDTO estatisticas() {

        List<Double> estatisticas = new ArrayList<>();
        OffsetDateTime dataAtual = OffsetDateTime.now();

        Collections.reverse(transacoes);
        for (Transacao transacao : transacoes) {

            var duration = Duration.between(transacao.getDataHora(), dataAtual);
            if (duration.getSeconds() <= 60) {
                estatisticas.add(transacao.getValor());
            }
        }

        EstatísticasDTO estatísticasDTO = new EstatísticasDTO();

        estatísticasDTO.setCount(estatisticas.size());

        estatísticasDTO.setAverage(estatisticas.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0));

        estatísticasDTO.setSum(estatisticas.stream()
                .mapToDouble(d -> d)
                .sum());

        estatísticasDTO.setMin(estatisticas.stream()
                .mapToDouble(d -> d)
                .min()
                .orElse(0.0));

        estatísticasDTO.setMax(estatisticas.stream()
                .mapToDouble(d -> d)
                .max()
                .orElse(0.0));

        System.out.println("Count = " + estatisticas.size());
        System.out.println("Sum = " + estatísticasDTO.getSum());
        System.out.println("Average = " + estatísticasDTO.getAverage());
        System.out.println("Min = " + estatísticasDTO.getMin());
        System.out.println("Max = " + estatísticasDTO.getMax());

        return estatísticasDTO;
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
