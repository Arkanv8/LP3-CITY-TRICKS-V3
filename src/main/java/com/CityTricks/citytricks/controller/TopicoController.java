package com.CityTricks.citytricks.controller;

import com.CityTricks.citytricks.dto.TopicoDTO;
import com.CityTricks.citytricks.exception.RegraNegocioException;
import com.CityTricks.citytricks.model.entity.ComentarioCidade;
import com.CityTricks.citytricks.model.entity.Topico;
import com.CityTricks.citytricks.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3688)
@RequestMapping("/topico")

public class TopicoController {

    private final TopicoService topicoService;

    @Autowired
    public TopicoController(TopicoService topicoService) {

        this.topicoService = topicoService;
    }

    @PostMapping(path="/salvar")
    public ResponseEntity<Object> saveTopico(@RequestBody @Valid TopicoDTO topicoDTO)
    {
        var topico = new Topico();
        BeanUtils.copyProperties(topicoDTO, topico);
        topico.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        topicoService.save(topicoDTO);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Topico> topico = topicoService.getTopico();
        return ResponseEntity.ok(topico.stream().map(TopicoDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Topico> topico = topicoService.getTopicoById(id);
        if (!topico.isPresent()) {
            return new ResponseEntity("Tópico não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(topico.map(TopicoDTO::create));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id) {
        Optional<Topico> topico = topicoService.getTopicoById(id);
        if (!topico.isPresent()) {
            return new ResponseEntity("Tópico não encontrado", HttpStatus.NOT_FOUND);
        }
        topicoService.excluir(topico.get());
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Topico> topico = topicoService.getTopicoById(id);
        if (!topico.isPresent()) {
            return new ResponseEntity("Tópico não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            topicoService.excluir(topico.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
