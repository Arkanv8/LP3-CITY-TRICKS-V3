package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.TopicoDTO;
import com.CityTricks.citytricks.model.entity.Topico;
import com.CityTricks.citytricks.model.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    @Autowired
    private TopicoRepository repository;

    public TopicoService(TopicoRepository topicoRepository) {

        this.topicoRepository = topicoRepository;
    }

    public void save(TopicoDTO topico) {

        Topico topico1 = new Topico();

        topico1.setId(topico.getId());
        topico1.setNome(topico.getNome());
        topico1.setCidade(topico.getCidade());
        topico1.setLocal(topico.getLocal());
        topicoRepository.save(topico1);

    }

    // GET GERAL
    public List<Topico> getTopico() {
        return repository.findAll();
    }

    // GET PELO ID
    public Optional<Topico> getTopicoById(Long id) {
        return repository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public Topico salvar(Topico topico) {
        return repository.save(topico);
    }

    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(Topico topico) {
        Objects.requireNonNull(topico.getId());
        repository.delete(topico);
    }
}
