package com.CityTricks.citytricks.controller;

import com.CityTricks.citytricks.dto.ComentarioTopicoDTO;
import com.CityTricks.citytricks.dto.UsuarioDTO;
import com.CityTricks.citytricks.exception.RegraNegocioException;
import com.CityTricks.citytricks.model.entity.ComentarioTopico;
import com.CityTricks.citytricks.model.entity.Usuario;
import com.CityTricks.citytricks.service.ComentarioTopicoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation("Adicionar um Comentario-Topico")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Comentario-Topico adicionado!"),
            @ApiResponse(code = 400, message = "Erro ao adicionar Comentario-Topico")
    })
    public ResponseEntity<Object> saveComentarioTopico(@RequestBody @Valid ComentarioTopicoDTO comentarioTopicoDTO)
    {
        var comentarioTopico = new ComentarioTopico();
        BeanUtils.copyProperties(comentarioTopicoDTO, comentarioTopico);
        comentarioTopico.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        comentarioTopicoService.save(comentarioTopicoDTO);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation("Listar todos os Comentarios-Topico")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Listados!"),
            @ApiResponse(code = 400, message = "Erro ao listar")
    })
    public ResponseEntity get() {
        List<ComentarioTopico> comentarioTopico = comentarioTopicoService.getComentarioTopico();
        return ResponseEntity.ok(comentarioTopico.stream().map(ComentarioTopicoDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    @ApiOperation("Listar um Comentario-Topico específico (PELO ID)")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Comentario-Topico encontrado!"),
            @ApiResponse(code = 400, message = "Erro ao buscar Comentario-Topico")
    })
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ComentarioTopico> comentarioTopico = comentarioTopicoService.getComentarioTopicoById(id);
        if (!comentarioTopico.isPresent()) {
            return new ResponseEntity("Comentário Tópico não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(comentarioTopico.map(ComentarioTopicoDTO::create));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Excluir um Comentario-Topico")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Comentario-Topico excluído"),
            @ApiResponse(code = 400, message = "Erro ao excluir Comentario-Topico")
    })
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
    @ApiOperation("Editar um Comentario-Topico")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Comentario-Topico alterado!"),
            @ApiResponse(code = 400, message = "Erro ao editar Comentario-Topico")
    })
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
