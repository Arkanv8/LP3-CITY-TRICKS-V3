package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.api.dto.EstadoDTO;
import com.CityTricks.citytricks.model.entity.Estado;
import com.CityTricks.citytricks.model.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    // GET GERAL
    public List<Estado> getEstado() {
        return repository.findAll();
    }

    // GET PELO ID
    public Optional<Estado> getEstadoById(Long id) {
        return repository.findById(id);
    }

    // SALVAR (POST)
    public Estado save(EstadoDTO estadoDTO) {

        Estado estado1 = new Estado();

        estado1.setId(estado1.getId());
        estado1.setNome(estado1.getNome());
        estado1.setCidade(estado1.getCidade());

        repository.save(estado1);

        return estado1;

    }

    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(Estado estado) {
        Objects.requireNonNull(estado.getId());
        repository.delete(estado);
    }
}
