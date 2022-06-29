package com.CityTricks.citytricks.dto;

import com.CityTricks.citytricks.model.entity.Topico;
import com.CityTricks.citytricks.service.TopicoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicoDTO {

    private Long id;
    private String nome;
    private String local;
    private String cidade;

    public static TopicoDTO create (Topico topico)
    {
        ModelMapper modelMapper = new ModelMapper();
        TopicoDTO dto = modelMapper.map(topico, TopicoDTO.class);
        dto.id = topico.getId();
        dto.nome = topico.getNome();
        dto.local = topico.getLocal();
        dto.cidade = topico.getCidade();

        return dto;

    }
}
