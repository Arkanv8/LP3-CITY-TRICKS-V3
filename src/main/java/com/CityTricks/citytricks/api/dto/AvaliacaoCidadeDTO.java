package com.CityTricks.citytricks.api.dto;

import com.CityTricks.citytricks.model.entity.AvaliacaoCidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoCidadeDTO {

    private Long id;
    private UsuarioDTO usuario;
    private String nota;
    private String titulo;

    public static AvaliacaoCidadeDTO create(AvaliacaoCidade avaliacao) {

        ModelMapper modelMapper = new ModelMapper();
        AvaliacaoCidadeDTO dto = modelMapper.map(avaliacao, AvaliacaoCidadeDTO.class);

        dto.id = avaliacao.getId();
        dto.usuario = modelMapper.map(avaliacao.getUsuario(), UsuarioDTO.class);
        dto.nota = avaliacao.getNota();
        dto.titulo = avaliacao.getTitulo();
        //dto.cidade = modelMapper.map(avaliacao.getCidade(), CidadeDTO.class);

        return dto;
    }
}
