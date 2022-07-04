package com.CityTricks.citytricks.dto;

import com.CityTricks.citytricks.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO {

    private Long id;
    private String nome;
    private String locais;
    private List<TopicoDTO> listaTopicos;

    private Estado estado;

    private Pais pais;

    private List<ComentarioDTO> listaComentarios;

    public static CidadeDTO create(Cidade cidade) {
        ModelMapper modelMapper = new ModelMapper();
        CidadeDTO dto = modelMapper.map(cidade, CidadeDTO.class);
        dto.id = cidade.getId();
        dto.nome = cidade.getNome();
        dto.locais = cidade.getLocais();
        dto.estado = cidade.getEstado();
        dto.pais = cidade.getPais();

        if(cidade.getListaTopicos() != null) {
            dto.listaTopicos = new ArrayList<>();
            for (Topico topico : cidade.getListaTopicos()) {
                dto.listaTopicos.add(modelMapper.map(topico, TopicoDTO.class));
            }
        }

        if(cidade.getListaComentarios() != null) {
            dto.listaComentarios = new ArrayList<>();
            for (Comentario comment : cidade.getListaComentarios()) {
                dto.listaComentarios.add(modelMapper.map(comment, ComentarioDTO.class));
            }
        }

        return dto;
    }
}
