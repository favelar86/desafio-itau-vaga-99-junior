package com.itau.desafioprogramacao.controller;

import com.itau.desafioprogramacao.dto.EstatísticasDTO;
import com.itau.desafioprogramacao.dto.TransacaoDTO;
import com.itau.desafioprogramacao.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transacao", produces = {"application/json"})
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Operation(summary = "Cria uma transação", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Serviço indisponível"),
    })
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid TransacaoDTO transacaoDTO) {
        transacaoService.salvar(transacaoDTO);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "deleta todas as transações", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Serviço indisponível"),
    })
    @DeleteMapping
    public ResponseEntity<Void> deletar() {
        transacaoService.deletar();
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Mostra as estatísticas das transações cadastradas", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Serviço indisponível"),
    })
    @GetMapping
    public ResponseEntity<EstatísticasDTO> findAll() {

        return ResponseEntity.ok(transacaoService.estatisticas());
    }
}
