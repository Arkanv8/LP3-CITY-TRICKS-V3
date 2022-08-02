package com.CityTricks.citytricks.controller;

import com.CityTricks.citytricks.dto.ComentarioTopicoDTO;
import com.CityTricks.citytricks.dto.UsuarioDTO;
import com.CityTricks.citytricks.exception.RegraNegocioException;
import com.CityTricks.citytricks.model.entity.ComentarioTopico;
import com.CityTricks.citytricks.model.entity.Usuario;
import com.CityTricks.citytricks.service.ComentarioTopicoService;
import jakarta.validation.Valid;
import lombok.var;
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
@RequestMapping("/comentario-topico")
public class ComentarioTopicoController {


    private final ComentarioTopicoService comentarioTopicoService;

    @Autowired
    public ComentarioTopicoController(ComentarioTopicoService comentarioTopicoService) {
        this.comentarioTopicoService = comentarioTopicoService;
    }

    @PostMapping(path="/salvar")
    public ResponseEntity<Object> saveComentarioTopico(@RequestBody @Valid ComentarioTopicoDTO comentarioTopicoDTO)
    {
        var comentarioTopico = new ComentarioTopico();
        BeanUtils.copyProperties(comentarioTopicoDTO, comentarioTopico);
        comentarioTopico.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        comentarioTopicoService.save(comentarioTopicoDTO);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity get() {
        List<ComentarioTopico> comentarioTopico = comentarioTopicoService.getComentarioTopico();
        return ResponseEntity.ok(comentarioTopico.stream().map(ComentarioTopicoDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ComentarioTopico> comentarioTopico = comentarioTopicoService.getComentarioTopicoById(id);
        if (!comentarioTopico.isPresent()) {
            return new ResponseEntity("Comentário Tópico não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(comentarioTopico.map(ComentarioTopicoDTO::create));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ComentarioTopico> comentarioTopico = comentarioTopicoService.getComentarioTopicoById(id);
        if (!comentarioTopico.isPresent()) {
            return new ResponseEntity("Comentário Tópico não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            comentarioTopicoService.excluir(comentarioTopico.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody @Valid ComentarioTopicoDTO comentarioTopicoDTO) {
        Optional<ComentarioTopico> comentarioTopico = comentarioTopicoService.getComentarioTopicoById(id);
        if(!comentarioTopico.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário Tópico não encontrado!");
        }
        var comentarioTopicoEntity = new ComentarioTopico();
        BeanUtils.copyProperties(comentarioTopicoDTO, comentarioTopicoEntity);
        comentarioTopicoEntity.setId(comentarioTopico.get().getId());
        comentarioTopicoEntity.setRegistrationDate(comentarioTopico.get().getRegistrationDate());
        comentarioTopicoDTO.setId(comentarioTopico.get().getId());
        comentarioTopicoService.save(comentarioTopicoDTO);
        return new ResponseEntity(HttpStatus.OK);
    }




}
