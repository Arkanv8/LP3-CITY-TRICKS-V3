package com.CityTricks.citytricks.dto;

import com.CityTricks.citytricks.model.entity.Comentario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {

    private Long id;
    private String titulo;
    private String informacao;
    private String nota;

    public static ComentarioDTO create(Comentario comentario) {
        ModelMapper modelMapper = new ModelMapper();
        ComentarioDTO dto = modelMapper.map(comentario, ComentarioDTO.class);
        dto.id = comentario.getId();
        dto.titulo = comentario.getTitulo();
        dto.informacao = comentario.getInformacao();
        dto.nota = comentario.getNota();

        return dto;
    }
}
