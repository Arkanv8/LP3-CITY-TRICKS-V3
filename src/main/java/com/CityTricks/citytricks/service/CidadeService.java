package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.*;
import com.CityTricks.citytricks.model.entity.*;
import com.CityTricks.citytricks.model.repository.CidadeRepository;
import com.CityTricks.citytricks.model.repository.EstadoRepository;
import com.CityTricks.citytricks.model.repository.PaisRepository;
import com.CityTricks.citytricks.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CidadeService{

    private final CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private AvaliacaoCidadeService avaliacaoCidadeService;

    @Autowired
    private ComentarioCidadeService comentarioCidadeService;

    public CidadeService(CidadeRepository cidadeRepository)
    {
        this.cidadeRepository = cidadeRepository;
    }

    public Cidade save(CidadeDTO cidade) {

        Cidade cidade1 = new Cidade();

        cidade1.setId(cidade.getId());
        cidade1.setNome(cidade.getNome());
        cidade1.setLocais(cidade.getLocais());
        Long idEstado = cidade.getEstado().getId();
        cidade1.setEstado(estadoRepository.getReferenceById(idEstado));
        cidade1.setPais(paisRepository.getReferenceById(cidade.getPais().getId()));


        if(cidade1.getListaTopicos() != null){
            cidade1.getListaTopicos().clear();
            cidadeRepository.flush();
        }
        else {
            cidade1.setListaTopicos(new ArrayList<>());
        }

        cidade1.getListaTopicos().addAll(topicoService.montaListaTopicosEntidade(cidade.getListaTopicos(), cidade1));

        if(cidade1.getListaComentarios() != null){
            cidade1.getListaComentarios().clear();
            cidadeRepository.flush();
        }
        else {
            cidade1.setListaComentarios(new ArrayList<>());
        }
        cidade1.getListaComentarios().addAll(comentarioCidadeService.montaListaComentariosEntidade(cidade.getListaComentarios(), cidade1));

        if(cidade1.getListaAvaliacao() != null){
            cidade1.getListaAvaliacao().clear();
            cidadeRepository.flush();
        }
        else {
            cidade1.setListaAvaliacao(new ArrayList<>());
        }

        cidade1.getListaAvaliacao().addAll(avaliacaoCidadeService.montaListaAvaliacaoEntidade(cidade.getListaAvaliacao(), cidade1));

        cidadeRepository.save(cidade1);

        return cidade1;
    }


    // GET GERAL
    public List<Cidade> getCidade() {
        return cidadeRepository.findAll();
    }

    // GET PELO ID
    public Optional<Cidade> getCidadeById(Long id) {
        return cidadeRepository.findById(id);
    }

    // SALVAR (POST)
    @Transactional
    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    // DELETE MAPPING PELO ID
    @Transactional
    public void excluir(Cidade cidade) {
        Objects.requireNonNull(cidade.getId());
        cidadeRepository.delete(cidade);
    }
}
