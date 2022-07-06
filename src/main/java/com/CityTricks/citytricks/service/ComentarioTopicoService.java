package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.ComentarioTopicoDTO;
import com.CityTricks.citytricks.model.entity.ComentarioTopico;
import com.CityTricks.citytricks.model.entity.Topico;
import com.CityTricks.citytricks.model.repository.ComentarioTopicoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ComentarioTopicoService {

    private final ComentarioTopicoRepository comentarioTopicoRepository;

    public List<ComentarioTopico> montaListaComentariosTopicoEntidade(List<ComentarioTopicoDTO> listaComentarios, Topico topico) {
        if (listaComentarios == null || listaComentarios.isEmpty()) {
            return new ArrayList<ComentarioTopico>();
        }
        List<ComentarioTopico> comentarios = new ArrayList<ComentarioTopico>();
        for (ComentarioTopicoDTO commentDTO : listaComentarios) {
            comentarios.add(preencherComentarioTopico(commentDTO, topico));
        }
        return comentarios;
    }

    private ComentarioTopico preencherComentarioTopico(ComentarioTopicoDTO commentDto, Topico topico) {
        ComentarioTopico comentario = new ComentarioTopico();

        comentario.setId(commentDto.getId());
        comentario.setTitulo(commentDto.getTitulo());
        comentario.setInformacao(commentDto.getInformacao());
        comentario.setNota(commentDto.getNota());
        comentario.setTopico(topico);

        return comentario;
    }

    public ComentarioTopicoService(ComentarioTopicoRepository comentarioTopicoRepository) {

        this.comentarioTopicoRepository = comentarioTopicoRepository;
    }


    public void save(ComentarioTopicoDTO comentarioTopico) {

        ComentarioTopico comentarioTopico1 = new ComentarioTopico();

        comentarioTopico1.setId(comentarioTopico.getId());
        comentarioTopico1.setTitulo(comentarioTopico.getTitulo());
        comentarioTopico1.setInformacao(comentarioTopico.getInformacao());
        comentarioTopico1.setNota(comentarioTopico.getNota());
        comentarioTopicoRepository.save(comentarioTopico1);

    }

    // GET GERAL
    public List<ComentarioTopico> getComentarioTopico() {
        return comentarioTopicoRepository.findAll();
    }

    // GET PELO ID
    public Optional<ComentarioTopico> getComentarioTopicoById(Long id) {
        return comentarioTopicoRepository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public ComentarioTopico salvar(ComentarioTopico comentarioTopico) {
        return comentarioTopicoRepository.save(comentarioTopico);
    }

    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(ComentarioTopico comentarioTopico) {
        Objects.requireNonNull(comentarioTopico.getId());
        comentarioTopicoRepository.delete(comentarioTopico);
    }
}
