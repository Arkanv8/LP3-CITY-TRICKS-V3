package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.ComentarioTopicoDTO;
import com.CityTricks.citytricks.model.entity.ComentarioTopico;
import com.CityTricks.citytricks.model.entity.Topico;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioTopicoService {

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
}
