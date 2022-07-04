package com.CityTricks.citytricks.dto;

import com.CityTricks.citytricks.model.entity.Avaliacao;
import com.CityTricks.citytricks.model.entity.Cidade;
import com.CityTricks.citytricks.model.entity.Topico;
import com.CityTricks.citytricks.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {

    private Long id;
    private Usuario usuario;
    private String nota;
    private String titulo;
    private Cidade cidade;
    private Topico topico;

    public static AvaliacaoDTO create(Avaliacao avaliacao) {
        ModelMapper modelMapper = new ModelMapper();
        AvaliacaoDTO dto = modelMapper.map(avaliacao, AvaliacaoDTO.class);
        dto.id = avaliacao.getId();
        dto.usuario = avaliacao.getUsuario();
        dto.nota = avaliacao.getNota();
        dto.cidade = avaliacao.getCidade();
        dto.topico = avaliacao.getTopico();

        return dto;
    }
}
