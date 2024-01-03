package com.itau.desafioprogramacao.controller;

import com.itau.desafioprogramacao.dto.EstatísticasDTO;
import com.itau.desafioprogramacao.dto.TransacaoDTO;
import com.itau.desafioprogramacao.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid TransacaoDTO transacaoDTO) {
        transacaoService.salvar(transacaoDTO);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar() {
        transacaoService.deletar();
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    public ResponseEntity<EstatísticasDTO> findAll() {
        return ResponseEntity.ok(transacaoService.estatisticas());
    }
}
