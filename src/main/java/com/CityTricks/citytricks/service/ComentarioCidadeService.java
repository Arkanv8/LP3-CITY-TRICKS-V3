package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.api.dto.ComentarioCidadeDTO;
import com.CityTricks.citytricks.model.entity.Cidade;
import com.CityTricks.citytricks.model.entity.ComentarioCidade;
import com.CityTricks.citytricks.model.repository.ComentarioCidadeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ComentarioCidadeService {

    private final ComentarioCidadeRepository comentarioCidadeRepository;

    public List<ComentarioCidade> montaListaComentariosEntidade(List<ComentarioCidadeDTO> listaComentarios, Cidade cidade) {
        if (listaComentarios == null || listaComentarios.isEmpty()) {
            return new ArrayList<ComentarioCidade>();
        }
        List<ComentarioCidade> comentarios = new ArrayList<ComentarioCidade>();
        for (ComentarioCidadeDTO commentDTO : listaComentarios) {
            comentarios.add(preencherComentario(commentDTO, cidade));
        }
        return comentarios;
    }

    public ComentarioCidadeService(ComentarioCidadeRepository comentarioCidadeRepository) {

        this.comentarioCidadeRepository = comentarioCidadeRepository;
    }

    private ComentarioCidade preencherComentario(ComentarioCidadeDTO commentDto, Cidade cidade) {
        ComentarioCidade comentario = new ComentarioCidade();

        comentario.setId(commentDto.getId());
        comentario.setTitulo(commentDto.getTitulo());
        comentario.setInformacao(commentDto.getInformacao());
        comentario.setNota(commentDto.getNota());
        comentario.setCidade(cidade);

        return comentario;
    }

    public void save(ComentarioCidadeDTO comentarioCidade) {

        ComentarioCidade comentarioCidade1 = new ComentarioCidade();

        comentarioCidade1.setId(comentarioCidade.getId());
        comentarioCidade1.setTitulo(comentarioCidade.getTitulo());
        comentarioCidade1.setInformacao(comentarioCidade.getInformacao());
        comentarioCidade1.setNota(comentarioCidade.getNota());
        comentarioCidadeRepository.save(comentarioCidade1);

    }

    // GET GERAL
    public List<ComentarioCidade> getComentarioCidade() {
        return comentarioCidadeRepository.findAll();
    }

    // GET PELO ID
    public Optional<ComentarioCidade> getComentarioCidadeById(Long id) {
        return comentarioCidadeRepository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public ComentarioCidade salvar(ComentarioCidade comentarioCidade) {
        return comentarioCidadeRepository.save(comentarioCidade);
    }

    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(ComentarioCidade comentarioCidade) {
        Objects.requireNonNull(comentarioCidade.getId());
        comentarioCidadeRepository.delete(comentarioCidade);
    }
}
