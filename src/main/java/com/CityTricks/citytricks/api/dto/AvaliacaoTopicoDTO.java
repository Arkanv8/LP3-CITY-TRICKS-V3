package com.CityTricks.citytricks.api.dto;

import com.CityTricks.citytricks.model.entity.AvaliacaoTopico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoTopicoDTO {

    private Long id;
    private UsuarioDTO usuario;
    private String nota;
    private String titulo;

    public static AvaliacaoTopicoDTO create(AvaliacaoTopico avaliacao) {

        ModelMapper modelMapper = new ModelMapper();
        AvaliacaoTopicoDTO dto = modelMapper.map(avaliacao, AvaliacaoTopicoDTO.class);

        dto.id = avaliacao.getId();
        dto.usuario = modelMapper.map(avaliacao.getUsuario(), UsuarioDTO.class);
        dto.nota = avaliacao.getNota();
        dto.titulo = avaliacao.getTitulo();
        //dto.topico = modelMapper.map(avaliacao.getTopico(), TopicoDTO.class);

        return dto;
    }
}
