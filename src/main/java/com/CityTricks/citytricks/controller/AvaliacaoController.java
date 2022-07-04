package com.CityTricks.citytricks.controller;

import com.CityTricks.citytricks.dto.AvaliacaoDTO;
import com.CityTricks.citytricks.dto.UsuarioDTO;
import com.CityTricks.citytricks.exception.RegraNegocioException;
import com.CityTricks.citytricks.model.entity.Avaliacao;
import com.CityTricks.citytricks.model.entity.Usuario;
import com.CityTricks.citytricks.service.AvaliacaoService;
import com.CityTricks.citytricks.service.UsuarioService;
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
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService) {

        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping(path="/salvar")
    public ResponseEntity<Object> saveAvaliacao(@RequestBody @Valid AvaliacaoDTO avaliacaoDTO)
    {
        var avaliacao = new Avaliacao();
        BeanUtils.copyProperties(avaliacaoDTO, avaliacaoDTO);
        avaliacao.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        avaliacaoService.save(avaliacaoDTO);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Avaliacao> avaliacao = avaliacaoService.getAvaliacao();
        return ResponseEntity.ok(avaliacao.stream().map(AvaliacaoDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoService.getAvaliacaoById(id);
        if (!avaliacao.isPresent()) {
            return new ResponseEntity("Avaliação não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(avaliacao.map(AvaliacaoDTO::create));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoService.getAvaliacaoById(id);
        if (!avaliacao.isPresent()) {
            return new ResponseEntity("Avaliação não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            avaliacaoService.excluir(avaliacao.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody @Valid AvaliacaoDTO avaliacaoDTO) {
        Optional<Avaliacao> avaliacao = avaliacaoService.getAvaliacaoById(id);
        if(!avaliacao.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliacação não encontrada!");
        }
        var avaliacaoEntity = new Avaliacao();
        BeanUtils.copyProperties(avaliacaoDTO, avaliacaoEntity);
        avaliacaoEntity.setId(avaliacao.get().getId());
        avaliacaoEntity.setRegistrationDate(avaliacao.get().getRegistrationDate());
        avaliacaoDTO.setId(avaliacao.get().getId());
        avaliacaoService.save(avaliacaoDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
