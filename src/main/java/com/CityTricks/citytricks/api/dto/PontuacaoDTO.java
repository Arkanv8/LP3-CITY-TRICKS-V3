package com.CityTricks.citytricks.api.dto;

import com.CityTricks.citytricks.model.entity.Pontuacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PontuacaoDTO {

    private Long id;
    private Long pontuacao;

    public static PontuacaoDTO create(Pontuacao pontuacoes) {
        ModelMapper modelMapper = new ModelMapper();
        PontuacaoDTO dto = new PontuacaoDTO();
        dto.id = pontuacoes.getId();
        dto.pontuacao = pontuacoes.getPontuacao();
        return dto;
    }
}
