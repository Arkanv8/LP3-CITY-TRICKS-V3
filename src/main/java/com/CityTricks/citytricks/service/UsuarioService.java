package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.api.dto.PontuacaoDTO;
import com.CityTricks.citytricks.api.dto.UsuarioDTO;
import com.CityTricks.citytricks.exception.SenhaInvalidaException;
import com.CityTricks.citytricks.model.entity.Pontuacao;
import com.CityTricks.citytricks.model.entity.Usuario;
import com.CityTricks.citytricks.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario save(UsuarioDTO usuario) {

        Usuario usuario1 = new Usuario();

        usuario1.setId(usuario.getId());
        usuario1.setNome(usuario.getNome());
        usuario1.setLogin(usuario.getLogin());
        usuario1.setSenha(usuario.getSenha());
        usuario1.setAdmin(usuario.isAdmin());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setCidade(usuario.getCidade());
        usuario1.setPontuacoes(preenchePontuacao(usuario.getPontuacoes(), usuario1));
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


    public UserDetails autenticar(Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        System.out.println(usuario.getSenha());
        System.out.println(user.getPassword());
        boolean senhasBatem = (usuario.getSenha().equals(user.getPassword()));

        if (senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String[] roles = usuario.isAdmin()
                ? new String[]{"ADMIN", "USER"}
                : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

}
