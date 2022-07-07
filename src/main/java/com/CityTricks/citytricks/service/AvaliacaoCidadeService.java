package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.AvaliacaoCidadeDTO;
import com.CityTricks.citytricks.model.entity.AvaliacaoCidade;
import com.CityTricks.citytricks.model.entity.Cidade;
import com.CityTricks.citytricks.model.repository.AvaliacaoCidadeRepository;
import com.CityTricks.citytricks.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AvaliacaoCidadeService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    AvaliacaoCidadeRepository avaliacaoCidadeRepository;

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

    public void save(AvaliacaoCidadeDTO avaliacaoCidade) {

        AvaliacaoCidade avaliacaoCidade1 = new AvaliacaoCidade();

        avaliacaoCidade1.setId(avaliacaoCidade.getId());
        avaliacaoCidade1.setUsuario(usuarioRepository.getReferenceById(avaliacaoCidade.getUsuario().getId()));
        avaliacaoCidade1.setNota(avaliacaoCidade.getNota());
        avaliacaoCidade1.setTitulo(avaliacaoCidade.getTitulo());

        avaliacaoCidadeRepository.save(avaliacaoCidade1);
    }
    // GET GERAL
    public List<AvaliacaoCidade> getAvaliacaoCidade() {
        return avaliacaoCidadeRepository.findAll();
    }

    // GET PELO ID
    public Optional<AvaliacaoCidade> getAvaliacaoCidadeById(Long id) {
        return avaliacaoCidadeRepository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public AvaliacaoCidade salvar(AvaliacaoCidade avaliacaoCidade) {
        return avaliacaoCidadeRepository.save(avaliacaoCidade);
    }

    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(AvaliacaoCidade avaliacaoCidade) {
        Objects.requireNonNull(avaliacaoCidade.getId());
        avaliacaoCidadeRepository.delete(avaliacaoCidade);
    }
}
