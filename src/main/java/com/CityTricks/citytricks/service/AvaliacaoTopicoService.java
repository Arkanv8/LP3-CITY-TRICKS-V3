package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.api.dto.AvaliacaoTopicoDTO;
import com.CityTricks.citytricks.model.entity.AvaliacaoTopico;
import com.CityTricks.citytricks.model.entity.Topico;
import com.CityTricks.citytricks.model.repository.AvaliacaoTopicoRepository;
import com.CityTricks.citytricks.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AvaliacaoTopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    AvaliacaoTopicoRepository avaliacaoTopicoRepository;

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
        avaliacao.setUsuario(usuarioRepository.getById(avaliacaoDTO.getUsuario().getId()));
        avaliacao.setTopico(topico);
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setTitulo(avaliacaoDTO.getTitulo());

        return avaliacao;
    }

    public void save(AvaliacaoTopicoDTO avaliacaoTopico) {

        AvaliacaoTopico avaliacaoTopico1 = new AvaliacaoTopico();

        avaliacaoTopico1.setId(avaliacaoTopico.getId());
        avaliacaoTopico1.setUsuario(usuarioRepository.getById(avaliacaoTopico.getUsuario().getId()));
        avaliacaoTopico1.setNota(avaliacaoTopico.getNota());
        avaliacaoTopico1.setTitulo(avaliacaoTopico.getTitulo());

        avaliacaoTopicoRepository.save(avaliacaoTopico1);
    }

    // GET GERAL
    public List<AvaliacaoTopico> getAvaliacaoTopico() {
        return avaliacaoTopicoRepository.findAll();
    }

    // GET PELO ID
    public Optional<AvaliacaoTopico> getAvaliacaoTopicoById(Long id) {
        return avaliacaoTopicoRepository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public AvaliacaoTopico salvar(AvaliacaoTopico avaliacaoTopico) {
        return avaliacaoTopicoRepository.save(avaliacaoTopico);
    }

    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(AvaliacaoTopico avaliacaoTopico) {
        Objects.requireNonNull(avaliacaoTopico.getId());
        avaliacaoTopicoRepository.delete(avaliacaoTopico);
    }
}
