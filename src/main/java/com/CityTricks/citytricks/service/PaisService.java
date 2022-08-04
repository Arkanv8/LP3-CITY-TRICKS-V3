package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.api.dto.PaisDTO;
import com.CityTricks.citytricks.model.entity.Pais;
import com.CityTricks.citytricks.model.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PaisService {

    private final PaisRepository paisRepository;

    @Autowired
    private PaisRepository repository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public Pais save(PaisDTO pais) {

        Pais pais1 = new Pais();

        pais1.setId(pais.getId());
        pais1.setNome(pais.getNome());
        pais1.setCidade(pais.getCidade());
        paisRepository.save(pais1);

        return pais1;
    }

    // GET GERAL
    public List<Pais> getPais() {
        return repository.findAll();
    }

    // GET PELO ID
    public Optional<Pais> getPaisById(Long id) {
        return repository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public Pais salvar(Pais pais) {
        return repository.save(pais);
    }


    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(Pais pais) {
        Objects.requireNonNull(pais.getId());
        repository.delete(pais);
    }

}
