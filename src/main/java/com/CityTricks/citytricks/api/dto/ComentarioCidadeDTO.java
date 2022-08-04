package com.CityTricks.citytricks.api.dto;

import com.CityTricks.citytricks.model.entity.ComentarioCidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioCidadeDTO {

    private Long id;
    private String titulo;
    private String informacao;
    private String nota;

    public static ComentarioCidadeDTO create(ComentarioCidade comentario) {
        ModelMapper modelMapper = new ModelMapper();
        ComentarioCidadeDTO dto = modelMapper.map(comentario, ComentarioCidadeDTO.class);
        dto.id = comentario.getId();
        dto.titulo = comentario.getTitulo();
        dto.informacao = comentario.getInformacao();
        dto.nota = comentario.getNota();

        return dto;
    }
}
