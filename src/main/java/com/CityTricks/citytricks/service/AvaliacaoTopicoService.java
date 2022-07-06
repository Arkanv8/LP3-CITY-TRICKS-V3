package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.AvaliacaoTopicoDTO;
import com.CityTricks.citytricks.model.entity.AvaliacaoTopico;
import com.CityTricks.citytricks.model.entity.Topico;
import com.CityTricks.citytricks.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvaliacaoTopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<AvaliacaoTopico> montaListaAvaliacaoEntidade(List<AvaliacaoTopicoDTO> listaAvaliacao, Topico topico) {
        if (listaAvaliacao == null || listaAvaliacao.isEmpty()) {
            return new ArrayList<AvaliacaoTopico>();
        }
        List<AvaliacaoTopico> avaliacao = new ArrayList<AvaliacaoTopico>();
        for (AvaliacaoTopicoDTO avaliacaoDTO : listaAvaliacao) {
            avaliacao.add(preencherAvaliacao(avaliacaoDTO, topico));
        }
        return avaliacao;
    }

    private AvaliacaoTopico preencherAvaliacao(AvaliacaoTopicoDTO avaliacaoDTO, Topico topico) {
        AvaliacaoTopico avaliacao = new AvaliacaoTopico();

        avaliacao.setId(avaliacaoDTO.getId());
        avaliacao.setUsuario(usuarioRepository.getReferenceById(avaliacaoDTO.getUsuario().getId()));
        avaliacao.setTopico(topico);
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setTitulo(avaliacaoDTO.getTitulo());

        return avaliacao;
    }
}
