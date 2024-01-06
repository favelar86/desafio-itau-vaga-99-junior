package com.itau.desafioprogramacao.service;

import com.itau.desafioprogramacao.dto.EstatísticasDTO;
import com.itau.desafioprogramacao.dto.TransacaoDTO;
import com.itau.desafioprogramacao.exceptions.ValidacaoException;
import com.itau.desafioprogramacao.model.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransacaoServiceTest {

    TransacaoDTO transacaoDTO;

    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    public void setup() {
        transacaoService.deletar();
    }

    @Test
    public void testaSalvaTransacao() {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("100.00", formatter.format(offsetDateTime));

        Transacao transacao = transacaoService.salvar(transacaoDTO);

        assertEquals(Double.parseDouble(transacaoDTO.getValor()), transacao.getValor());
        assertEquals(OffsetDateTime.parse(transacaoDTO.getDataHora()), transacao.getDataHora());
    }

    @Test
    public void testaDeletarTransacao() {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("100.00", formatter.format(offsetDateTime));

        transacaoService.salvar(transacaoDTO);
        transacaoService.deletar();
        EstatísticasDTO estatísticasDTO = transacaoService.estatisticas();

        assertEquals(estatísticasDTO.getCount(), 0);
    }

    @Test
    public void testaEstatisticas() throws InterruptedException {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("100.00", formatter.format(offsetDateTime));
        transacaoService.salvar(transacaoDTO);

        Thread.sleep(10);

        OffsetDateTime offsetDateTime2 = OffsetDateTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ISO_DATE_TIME;

        transacaoDTO = new TransacaoDTO("200.00", formatter2.format(offsetDateTime2));
        transacaoService.salvar(transacaoDTO);

        EstatísticasDTO estatísticasDTO = transacaoService.estatisticas();

        assertEquals(estatísticasDTO.getCount(), 2);
        assertEquals(estatísticasDTO.getSum(), 300.0);
        assertEquals(estatísticasDTO.getAverage(), 150.0);
        assertEquals(estatísticasDTO.getMin(), 100.0);
        assertEquals(estatísticasDTO.getMax(), 200.0);
    }

    @Test
    public void testaDataFuturo() {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        OffsetDateTime offsetDateTime2 = offsetDateTime.plusSeconds(1000);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("100.00", formatter.format(offsetDateTime2));

        assertThrows(ValidacaoException.class, () -> transacaoService.salvar(transacaoDTO));
    }

    @Test
    public void testaValorMenorQueZero() {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("-1", formatter.format(offsetDateTime));

        assertThrows(ValidacaoException.class, () -> transacaoService.salvar(transacaoDTO));
    }

}
