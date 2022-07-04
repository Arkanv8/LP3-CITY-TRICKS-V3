package com.CityTricks.citytricks.dto;

import com.CityTricks.citytricks.model.entity.Avaliacao;
import com.CityTricks.citytricks.model.entity.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO {

    private Long id;

    private String nome;

    public static EstadoDTO create(Estado estado) {
        ModelMapper modelMapper = new ModelMapper();
        EstadoDTO dto = modelMapper.map(estado, EstadoDTO.class);
        dto.id = estado.getId();
        dto.nome = estado.getNome();

        return dto;
    }

}
