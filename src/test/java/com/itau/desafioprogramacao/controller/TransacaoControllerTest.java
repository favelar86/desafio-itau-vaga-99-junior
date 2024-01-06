package com.itau.desafioprogramacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.desafioprogramacao.dto.TransacaoDTO;
import com.itau.desafioprogramacao.model.Transacao;
import com.itau.desafioprogramacao.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class TransacaoControllerTest {

    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private TransacaoService transacaoService;

    private MockMvc mockMvc;

    TransacaoDTO transacaoDTO;

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("100.00", formatter.format(offsetDateTime));
    }

    @Test
    public void salvarTest() throws Exception {

        assertEquals(HttpStatus.CREATED, transacaoController.salvar(transacaoDTO).getStatusCode());
    }

    @Test
    public void deletarTest() throws Exception {

        transacaoController.salvar(transacaoDTO);

        assertEquals(HttpStatus.OK, transacaoController.deletar().getStatusCode());
    }

    @Test
    public void findAllTest() throws Exception {

        transacaoController.salvar(transacaoDTO);

        assertEquals(HttpStatus.OK, transacaoController.findAll().getStatusCode());
    }

}
