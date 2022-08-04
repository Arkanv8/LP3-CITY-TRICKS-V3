package com.CityTricks.citytricks.controller;

import com.CityTricks.citytricks.dto.CredenciaisDTO;
import com.CityTricks.citytricks.dto.TokenDTO;
import com.CityTricks.citytricks.dto.UsuarioDTO;
import com.CityTricks.citytricks.exception.RegraNegocioException;
import com.CityTricks.citytricks.exception.SenhaInvalidaException;
import com.CityTricks.citytricks.model.entity.Usuario;
import com.CityTricks.citytricks.security.JwtService;
import com.CityTricks.citytricks.service.UsuarioService;
import jakarta.validation.Valid;
import io.swagger.annotations.*;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", maxAge = 3688)
@RequestMapping("/usuario")
@Api("API de Usuários")

public class UsuarioController{

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, JwtService jwtService) {

        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @PostMapping(path="/salvar")
    @ApiOperation("Criar um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário Criado!"),
            @ApiResponse(code = 400, message = "Não encontrado Usuário")
    })
    public ResponseEntity<Object> saveUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO)
    {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        usuarioService.save(usuarioDTO);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation("GERAR TOKEN")
    @ApiResponses({
            @ApiResponse(code = 201, message = "TOKEN GERADO"),
            @ApiResponse(code = 400, message = "TOKEN NÃO GERADO")
    })
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            System.out.println(token);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping()
    @ApiOperation("Lista todos os Usuários")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Todos os Usuarios"),
            @ApiResponse(code = 400, message = "Não encontrado Usuários")
    })
    public ResponseEntity get() {
        List<Usuario> usuario = usuarioService.getUsuario();
        return ResponseEntity.ok(usuario.stream().map(UsuarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Listar apenas um usuário (PELO ID)")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário Encontrado!"),
            @ApiResponse(code = 400, message = "Não encontrado Usuário")
    })
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        if (!usuario.isPresent()) {
            return new ResponseEntity("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usuario.map(UsuarioDTO::create));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Excluir apenas um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário Deletado!"),
            @ApiResponse(code = 400, message = "Não encontrado Usuário")
    })
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        if (!usuario.isPresent()) {
            return new ResponseEntity("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            usuarioService.excluir(usuario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar apenas um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário Alterado!"),
            @ApiResponse(code = 400, message = "Não encontrado Usuário")
    })
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        if(!usuario.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        var usuarioEntity = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuarioEntity);
        usuarioEntity.setId(usuario.get().getId());
        usuarioEntity.setRegistrationDate(usuario.get().getRegistrationDate());
        usuarioDTO.setId(usuario.get().getId());
        usuarioService.save(usuarioDTO);
        return new ResponseEntity(HttpStatus.OK);
    }


}