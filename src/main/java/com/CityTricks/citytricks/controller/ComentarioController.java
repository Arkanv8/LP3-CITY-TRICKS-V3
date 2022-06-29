package com.CityTricks.citytricks.controller;

import com.CityTricks.citytricks.dto.ComentarioDTO;
import com.CityTricks.citytricks.model.entity.Comentario;
import com.CityTricks.citytricks.service.ComentarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3688)
@RequestMapping("/comentario")
public class ComentarioController{
    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {

        this.comentarioService = comentarioService;
    }

    @PostMapping(path="/salvar")
    public ResponseEntity<Object> saveComentario(@RequestBody @Valid ComentarioDTO comentarioDTO)
    {
        var comentario = new Comentario();
        BeanUtils.copyProperties(comentarioDTO, comentario);
        comentario.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        comentarioService.save(comentarioDTO);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Comentario> comentario = comentarioService.getComentario();
        return ResponseEntity.ok(comentario.stream().map(ComentarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Comentario> comentario = comentarioService.getComentarioById(id);
        if (!comentario.isPresent()) {
            return new ResponseEntity("Comentário não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(comentario.map(ComentarioDTO::create));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Comentario> comentario = comentarioService.getComentarioById(id);
        if (!comentario.isPresent()) {
            return new ResponseEntity("Comentário não encontrado", HttpStatus.NOT_FOUND);
        }
        comentarioService.excluir(comentario.get());
        return new ResponseEntity(HttpStatus.OK);

    }


}
