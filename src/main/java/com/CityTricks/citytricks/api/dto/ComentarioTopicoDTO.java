package com.CityTricks.citytricks.api.dto;

import com.CityTricks.citytricks.model.entity.ComentarioTopico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioTopicoDTO {
    private Long id;
    private String titulo;
    private String informacao;
    private String nota;

    public static ComentarioTopicoDTO create(ComentarioTopico comentario) {
        ModelMapper modelMapper = new ModelMapper();
        ComentarioTopicoDTO dto = modelMapper.map(comentario, ComentarioTopicoDTO.class);
        dto.id = comentario.getId();
        dto.titulo = comentario.getTitulo();
        dto.informacao = comentario.getInformacao();
        dto.nota = comentario.getNota();

        return dto;
    }
}
