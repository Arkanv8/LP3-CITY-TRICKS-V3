package com.CityTricks.citytricks.dto;

import com.CityTricks.citytricks.model.entity.Cidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO {

    private Long id;
    private String nome;
    private String locais;
    private String topicos;

    public static CidadeDTO create(Cidade cidade) {
        ModelMapper modelMapper = new ModelMapper();
        CidadeDTO dto = modelMapper.map(cidade, CidadeDTO.class);
        dto.id = cidade.getId();
        dto.nome = cidade.getNome();
        dto.locais = cidade.getLocais();
        dto.topicos = cidade.getTopicos();

        return dto;
    }
}
