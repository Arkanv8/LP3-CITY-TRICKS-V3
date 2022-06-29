package com.CityTricks.citytricks.controller;
import com.CityTricks.citytricks.dto.CidadeDTO;
import com.CityTricks.citytricks.dto.UsuarioDTO;
import com.CityTricks.citytricks.model.entity.Cidade;
import com.CityTricks.citytricks.model.entity.Usuario;
import com.CityTricks.citytricks.service.CidadeService;
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
@RequestMapping("/cidade")
public class CidadeController {

    private final CidadeService cidadeService;

    @Autowired
    public CidadeController(CidadeService cidadeService) {

        this.cidadeService = cidadeService;
    }

    @PostMapping(path="/salvar")
    public ResponseEntity<Object> saveCidade(@RequestBody @Valid CidadeDTO cidadeDTO)
    {
        var cidade = new Cidade();
        BeanUtils.copyProperties(cidadeDTO, cidade);
        cidade.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        cidadeService.save(cidadeDTO);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Cidade> cidade = cidadeService.getCidade();
        return ResponseEntity.ok(cidade.stream().map(CidadeDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = cidadeService.getCidadeById(id);
        if (!cidade.isPresent()) {
            return new ResponseEntity("Cidade não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cidade.map(CidadeDTO::create));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = cidadeService.getCidadeById(id);
        if (!cidade.isPresent()) {
            return new ResponseEntity("Cidade não encontrado", HttpStatus.NOT_FOUND);
        }
        cidadeService.excluir(cidade.get());
        return new ResponseEntity(HttpStatus.OK);

    }


}
