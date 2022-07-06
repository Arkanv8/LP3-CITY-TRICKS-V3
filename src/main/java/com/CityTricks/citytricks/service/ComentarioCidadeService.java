package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.ComentarioCidadeDTO;
import com.CityTricks.citytricks.model.entity.Cidade;
import com.CityTricks.citytricks.model.entity.Comentario;
import com.CityTricks.citytricks.model.entity.ComentarioCidade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioCidadeService {

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

    private ComentarioCidade preencherComentario(ComentarioCidadeDTO commentDto, Cidade cidade) {
        ComentarioCidade comentario = new ComentarioCidade();

        comentario.setId(commentDto.getId());
        comentario.setTitulo(commentDto.getTitulo());
        comentario.setInformacao(commentDto.getInformacao());
        comentario.setNota(commentDto.getNota());
        comentario.setCidade(cidade);

        return comentario;
    }
}
