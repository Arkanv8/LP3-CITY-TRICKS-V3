package com.CityTricks.citytricks.controller;

import com.CityTricks.citytricks.dto.AvaliacaoCidadeDTO;
import com.CityTricks.citytricks.dto.UsuarioDTO;
import com.CityTricks.citytricks.exception.RegraNegocioException;
import com.CityTricks.citytricks.model.entity.AvaliacaoCidade;
import com.CityTricks.citytricks.model.entity.Usuario;
import com.CityTricks.citytricks.service.AvaliacaoCidadeService;
import com.CityTricks.citytricks.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", maxAge = 3688)
@RequestMapping("/avaliacao-cidade")
public class AvaliacaoCidadeController {

    private final AvaliacaoCidadeService avaliacaoCidadeService;

    @Autowired
    public AvaliacaoCidadeController(AvaliacaoCidadeService avaliacaoCidadeService)
    {
        this.avaliacaoCidadeService = avaliacaoCidadeService;
    }

    @PostMapping(path="/salvar")
    public ResponseEntity<Object> saveAvaliacaoCidade(@RequestBody @Valid AvaliacaoCidadeDTO avaliacaoCidadeDTO)
    {
        var avaliacaoCidade = new AvaliacaoCidade();
        BeanUtils.copyProperties(avaliacaoCidadeDTO, avaliacaoCidade);
        avaliacaoCidade.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        avaliacaoCidadeService.save(avaliacaoCidadeDTO);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity get() {
        List<AvaliacaoCidade> avaliacaoCidade = avaliacaoCidadeService.getAvaliacaoCidade();
        return ResponseEntity.ok(avaliacaoCidade.stream().map(AvaliacaoCidadeDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<AvaliacaoCidade> avaliacaoCidade = avaliacaoCidadeService.getAvaliacaoCidadeById(id);
        if (!avaliacaoCidade.isPresent()) {
            return new ResponseEntity("Avaliação não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(avaliacaoCidade.map(AvaliacaoCidadeDTO::create));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<AvaliacaoCidade> avaliacaoCidade = avaliacaoCidadeService.getAvaliacaoCidadeById(id);
        if (!avaliacaoCidade.isPresent()) {
            return new ResponseEntity("Avaliação não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            avaliacaoCidadeService.excluir(avaliacaoCidade.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody @Valid AvaliacaoCidadeDTO avaliacaoCidadeDTO) {
        Optional<AvaliacaoCidade> avaliacaoCidade = avaliacaoCidadeService.getAvaliacaoCidadeById(id);
        if(!avaliacaoCidade.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada!");
        }
        var avaliacaoCidadeEntity = new AvaliacaoCidade();
        BeanUtils.copyProperties(avaliacaoCidadeDTO, avaliacaoCidadeEntity);
        avaliacaoCidadeEntity.setId(avaliacaoCidade.get().getId());
        avaliacaoCidadeEntity.setRegistrationDate(avaliacaoCidade.get().getRegistrationDate());
        avaliacaoCidadeDTO.setId(avaliacaoCidade.get().getId());
        avaliacaoCidadeService.save(avaliacaoCidadeDTO);
        return new ResponseEntity(HttpStatus.OK);
    }





}
