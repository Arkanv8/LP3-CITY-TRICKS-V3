package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.AvaliacaoDTO;
import com.CityTricks.citytricks.model.entity.Avaliacao;
import com.CityTricks.citytricks.model.entity.Usuario;
import com.CityTricks.citytricks.model.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoRepository repository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository)
    {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public void save(AvaliacaoDTO avaliacao) {

        Avaliacao avaliacao1 = new Avaliacao();

        avaliacao1.setId(avaliacao.getId());
        avaliacao1.setUsuario(avaliacao.getUsuario());
        avaliacao1.setNota(avaliacao.getNota());
        avaliacao1.setCidade(avaliacao.getCidade());
        avaliacao1.setTitulo(avaliacao.getTitulo());
        avaliacao1.setTopico(avaliacao.getTopico());

        avaliacaoRepository.save(avaliacao1);
    }

    // GET GERAL
    public List<Avaliacao> getAvaliacao() {
        return repository.findAll();
    }

    // GET PELO ID
    public Optional<Avaliacao> getAvaliacaoById(Long id) {
        return repository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public Avaliacao salvar(Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }

    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(Avaliacao avaliacao) {
        Objects.requireNonNull(avaliacao.getId());
        repository.delete(avaliacao);
    }
}
