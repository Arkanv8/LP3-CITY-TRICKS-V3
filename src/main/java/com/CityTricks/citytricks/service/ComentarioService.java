package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.ComentarioDTO;
import com.CityTricks.citytricks.model.entity.Comentario;
import com.CityTricks.citytricks.model.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    @Autowired
    private ComentarioRepository repository;

    public ComentarioService(ComentarioRepository comentarioRepository)
    {
        this.comentarioRepository = comentarioRepository;
    }
    @Transactional
    public void save(ComentarioDTO comentario) {

        Comentario comentario1 = new Comentario();

        comentario1.setId(comentario.getId());
        comentario1.setTitulo(comentario.getTitulo());
        comentario1.setInformacao(comentario.getInformacao());
        comentario1.setNota(comentario.getNota());
        comentarioRepository.save(comentario1);
    }

    // GET GERAL
    public List<Comentario> getComentario() {
        return repository.findAll();
    }

    // GET PELO ID
    public Optional<Comentario> getComentarioById(Long id) {
        return repository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public Comentario salvar(Comentario comentario) {
        return repository.save(comentario);
    }

    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(Comentario comentario) {
        Objects.requireNonNull(comentario.getId());
        repository.delete(comentario);
    }

}
