package com.CityTricks.citytricks.api.dto;

import com.CityTricks.citytricks.model.entity.AvaliacaoTopico;
import com.CityTricks.citytricks.model.entity.ComentarioTopico;
import com.CityTricks.citytricks.model.entity.Topico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicoDTO {

    private Long id;
    private String nome;
    private String local;

    private List<ComentarioTopicoDTO> listaComentarios;

    private List<AvaliacaoTopicoDTO> listaAvaliacao;

    public static TopicoDTO create(Topico topico)
    {
        ModelMapper modelMapper = new ModelMapper();
        TopicoDTO dto = new TopicoDTO();
        dto.id = topico.getId();
        dto.nome = topico.getNome();
        dto.local = topico.getLocal();

        if(topico.getListaComentarios() != null) {
            dto.listaComentarios = new ArrayList<>();
            for (ComentarioTopico comentario : topico.getListaComentarios()) {
                dto.listaComentarios.add(modelMapper.map(comentario, ComentarioTopicoDTO.class));
            }
        }

        if(topico.getListaAvaliacao() != null) {
            dto.listaAvaliacao = new ArrayList<>();
            for (AvaliacaoTopico avaliacao : topico.getListaAvaliacao()) {
                dto.listaAvaliacao.add(modelMapper.map(avaliacao, AvaliacaoTopicoDTO.class));
            }
        }

        return dto;

    }
}
