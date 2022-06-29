package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.CidadeDTO;
import com.CityTricks.citytricks.model.entity.Cidade;
import com.CityTricks.citytricks.model.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CidadeService{

    private final CidadeRepository cidadeRepository;

    @Autowired
    private CidadeRepository repository;

    public CidadeService(CidadeRepository cidadeRepository)
    {
        this.cidadeRepository = cidadeRepository;
    }

    public void save(CidadeDTO cidade) {

        Cidade cidade1 = new Cidade();

        cidade1.setId(cidade.getId());
        cidade1.setNome(cidade.getNome());
        cidade1.setLocais(cidade.getLocais());
        cidade1.setTopicos(cidade.getTopicos());
        cidadeRepository.save(cidade1);
    }

    // GET GERAL
    public List<Cidade> getCidade() {
        return repository.findAll();
    }

    // GET PELO ID
    public Optional<Cidade> getCidadeById(Long id) {
        return repository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public Cidade salvar(Cidade cidade) {
        return repository.save(cidade);
    }

    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(Cidade cidade) {
        Objects.requireNonNull(cidade.getId());
        repository.delete(cidade);
    }
}
