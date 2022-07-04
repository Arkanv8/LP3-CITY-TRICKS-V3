package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.PontuacaoDTO;
import com.CityTricks.citytricks.dto.UsuarioDTO;
import com.CityTricks.citytricks.model.entity.Pontuacao;
import com.CityTricks.citytricks.model.entity.Usuario;
import com.CityTricks.citytricks.model.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario save(UsuarioDTO usuario) {

        Usuario usuario1 = new Usuario();

        usuario1.setId(usuario.getId());
        usuario1.setNome(usuario.getNome());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setCidade(usuario.getCidade());
        usuario1.setPontuacoes(preenchePontuacao(usuario.getPontuacoes(), usuario1));
        usuario1.setSenha(usuario.getSenha());
        usuario1.setAdmin(usuario.isAdmin());
        usuarioRepository.save(usuario1);

        return usuario1;
    }

    public Pontuacao preenchePontuacao(PontuacaoDTO pontuacaoDTO, Usuario usuario){
        Pontuacao pontuacao = new Pontuacao();

        pontuacao.setPontuacao(pontuacaoDTO.getPontuacao());
        pontuacao.setUsuarios(usuario);

        return pontuacao;
    }


    // GET GERAL
    public List<Usuario> getUsuario() {
        return repository.findAll();
    }

    // GET PELO ID
    public Optional<Usuario> getUsuarioById(Long id) {
        return repository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }


    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(Usuario usuario) {
        Objects.requireNonNull(usuario.getId());
        repository.delete(usuario);
    }

}
