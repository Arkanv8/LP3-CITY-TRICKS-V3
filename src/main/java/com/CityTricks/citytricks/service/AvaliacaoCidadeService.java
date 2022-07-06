package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.AvaliacaoCidadeDTO;
import com.CityTricks.citytricks.model.entity.AvaliacaoCidade;
import com.CityTricks.citytricks.model.entity.Cidade;
import com.CityTricks.citytricks.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvaliacaoCidadeService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<AvaliacaoCidade> montaListaAvaliacaoEntidade(List<AvaliacaoCidadeDTO> listaAvaliacao, Cidade cidade) {
        if (listaAvaliacao == null || listaAvaliacao.isEmpty()) {
            return new ArrayList<AvaliacaoCidade>();
        }
        List<AvaliacaoCidade> avaliacao = new ArrayList<AvaliacaoCidade>();
        for (AvaliacaoCidadeDTO avaliacaoDTO : listaAvaliacao) {
            avaliacao.add(preencherAvaliacao(avaliacaoDTO, cidade));
        }
        return avaliacao;
    }

    private AvaliacaoCidade preencherAvaliacao(AvaliacaoCidadeDTO avaliacaoDTO, Cidade cidade) {
        AvaliacaoCidade avaliacao = new AvaliacaoCidade();

        avaliacao.setId(avaliacaoDTO.getId());
        avaliacao.setUsuario(usuarioRepository.getReferenceById(avaliacaoDTO.getUsuario().getId()));
        avaliacao.setCidade(cidade);
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setTitulo(avaliacaoDTO.getTitulo());

        return avaliacao;
    }

}
