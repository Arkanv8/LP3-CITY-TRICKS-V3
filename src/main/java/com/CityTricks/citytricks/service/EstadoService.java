package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.ComentarioDTO;
import com.CityTricks.citytricks.model.entity.Comentario;
import com.CityTricks.citytricks.model.entity.Estado;
import com.CityTricks.citytricks.model.repository.ComentarioRepository;
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


}
