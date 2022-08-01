package com.CityTricks.citytricks.controller;

import com.CityTricks.citytricks.dto.ComentarioCidadeDTO;
import com.CityTricks.citytricks.exception.RegraNegocioException;
import com.CityTricks.citytricks.model.entity.ComentarioCidade;
import com.CityTricks.citytricks.service.ComentarioCidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", maxAge = 3688)
@RequestMapping("/comentario")
public class ComentarioCidadeController {

    private final ComentarioCidadeService comentarioCidadeService;

    @Autowired
    public ComentarioCidadeController(ComentarioCidadeService comentarioCidadeService) {
        this.comentarioCidadeService = comentarioCidadeService;

    }

    @PostMapping(path="/salvar")
    public ResponseEntity<Object> saveComentario(@RequestBody @Valid ComentarioCidadeDTO comentarioCidadeDTO)
    {
        var comentarioCidade = new ComentarioCidade();
        BeanUtils.copyProperties(comentarioCidadeDTO, comentarioCidade);
        comentarioCidade.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        comentarioCidadeService.save(comentarioCidadeDTO);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity get() {
        List<ComentarioCidade> comentarioCidade = comentarioCidadeService.getComentarioCidade();
        return ResponseEntity.ok(comentarioCidade.stream().map(ComentarioCidadeDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ComentarioCidade> comentarioCidade = comentarioCidadeService.getComentarioCidadeById(id);
        if (!comentarioCidade.isPresent()) {
            return new ResponseEntity("Comentario não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(comentarioCidade.map(ComentarioCidadeDTO::create));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ComentarioCidade> comentarioCidade = comentarioCidadeService.getComentarioCidadeById(id);
        if (!comentarioCidade.isPresent()) {
            return new ResponseEntity("Comentario não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            comentarioCidadeService.excluir(comentarioCidade.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody @Valid ComentarioCidadeDTO comentarioCidadeDTO) {
        Optional<ComentarioCidade> comentarioCidade = comentarioCidadeService.getComentarioCidadeById(id);
        if(!comentarioCidade.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario não encontrado!");
        }
        var comentarioCidadeEntity = new ComentarioCidade();
        BeanUtils.copyProperties(comentarioCidadeDTO, comentarioCidadeEntity);
        comentarioCidadeEntity.setId(comentarioCidade.get().getId());
        comentarioCidadeEntity.setRegistrationDate(comentarioCidade.get().getRegistrationDate());
        comentarioCidadeDTO.setId(comentarioCidade.get().getId());
        comentarioCidadeService.save(comentarioCidadeDTO);
        return new ResponseEntity(HttpStatus.OK);
    }



}
