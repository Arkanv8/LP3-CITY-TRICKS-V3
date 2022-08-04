package com.CityTricks.citytricks.api.dto;

import com.CityTricks.citytricks.model.entity.Cidade;
import com.CityTricks.citytricks.model.entity.Pais;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaisDTO {

    private Long id;

    private String nome;
    private Cidade cidade;

    public static PaisDTO create(Pais pais) {
        ModelMapper modelMapper = new ModelMapper();
        PaisDTO dto = modelMapper.map(pais, PaisDTO.class);
        dto.id = pais.getId();
        dto.nome = pais.getNome();
        dto.cidade = pais.getCidade();

        return dto;
    }

}
