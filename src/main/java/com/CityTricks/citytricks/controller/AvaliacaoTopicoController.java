package com.CityTricks.citytricks.controller;

import com.CityTricks.citytricks.dto.AvaliacaoTopicoDTO;
import com.CityTricks.citytricks.exception.RegraNegocioException;
import com.CityTricks.citytricks.model.entity.AvaliacaoTopico;
import com.CityTricks.citytricks.service.AvaliacaoTopicoService;
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
@CrossOrigin(origins = ("*"), maxAge = 3688)
@RequestMapping("/avaliacao-topico")
class AvaliacaoTopicoController {

    private final AvaliacaoTopicoService avaliacaoTopicoService;

    @Autowired
    public AvaliacaoTopicoController(AvaliacaoTopicoService avaliacaoTopicoService) {

        this.avaliacaoTopicoService = avaliacaoTopicoService;
    }

    @PostMapping(path="/salvar")
    @ApiOperation("Salvar Avaliacao-Topico")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Salvado!"),
            @ApiResponse(code = 400, message = "Não salvado")
    })
    public ResponseEntity<Object> saveAvaliacaoTopico(@RequestBody @Valid AvaliacaoTopicoDTO avaliacaoTopicoDTO)
    {
        var avaliacaoTopico = new AvaliacaoTopico();
        BeanUtils.copyProperties(avaliacaoTopicoDTO, avaliacaoTopico);
        avaliacaoTopico.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        avaliacaoTopicoService.save(avaliacaoTopicoDTO);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation("Listar todas Avaliacoes-topico")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Listado"),
            @ApiResponse(code = 400, message = "Não encontrado")
    })
    public ResponseEntity get() {
        List<AvaliacaoTopico> avaliacaoTopico = avaliacaoTopicoService.getAvaliacaoTopico();
        return ResponseEntity.ok(avaliacaoTopico.stream().map(AvaliacaoTopicoDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    @ApiOperation("Buscar Avaliacao-topico específico (PELO ID)")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Encontrado"),
            @ApiResponse(code = 400, message = "Não encontrado")
    })
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<AvaliacaoTopico> avaliacaoTopico = avaliacaoTopicoService.getAvaliacaoTopicoById(id);
        if (!avaliacaoTopico.isPresent()) {
            return new ResponseEntity("Avaliação não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(avaliacaoTopico.map(AvaliacaoTopicoDTO::create));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Excluir uma avaliacao-topico")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Excluido"),
            @ApiResponse(code = 400, message = "Não encontrado")
    })
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<AvaliacaoTopico> avaliacaoTopico = avaliacaoTopicoService.getAvaliacaoTopicoById(id);
        if (!avaliacaoTopico.isPresent()) {
            return new ResponseEntity("Avaliação não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            avaliacaoTopicoService.excluir(avaliacaoTopico.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ApiOperation("Ajustar avaliacao-topico")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ajustado"),
            @ApiResponse(code = 400, message = "Não encontrado")
    })
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody @Valid AvaliacaoTopicoDTO avaliacaoTopicoDTO) {
        Optional<AvaliacaoTopico> avaliacaoTopico = avaliacaoTopicoService.getAvaliacaoTopicoById(id);
        if(!avaliacaoTopico.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada!");
        }
        var avaliacaoTopicoEntity = new AvaliacaoTopico();
        BeanUtils.copyProperties(avaliacaoTopicoDTO, avaliacaoTopicoEntity);
        avaliacaoTopicoEntity.setId(avaliacaoTopico.get().getId());
        avaliacaoTopicoEntity.setRegistrationDate(avaliacaoTopico.get().getRegistrationDate());
        avaliacaoTopicoDTO.setId(avaliacaoTopico.get().getId());
        avaliacaoTopicoService.save(avaliacaoTopicoDTO);
        return new ResponseEntity(HttpStatus.OK);
    }















}