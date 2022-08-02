package com.CityTricks.citytricks.dto;

import com.CityTricks.citytricks.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String email;

    private String login;
    private String senha;
    private String nome;
    private String cidade;
    private PontuacaoDTO pontuacoes;
    private boolean admin;

    public static UsuarioDTO create(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);
        dto.id = usuario.getId();
        dto.email = usuario.getEmail();
        dto.senha = usuario.getSenha();
        dto.nome = usuario.getNome();
        dto.login = usuario.getLogin();
        if(usuario.getPontuacoes() != null)
        {
            dto.pontuacoes = modelMapper.map(usuario.getPontuacoes(), PontuacaoDTO.class);
        }
        dto.admin = usuario.isAdmin();

        return modelMapper.map(usuario, UsuarioDTO.class);
    }

}
