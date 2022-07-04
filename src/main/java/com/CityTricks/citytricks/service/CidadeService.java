package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.CidadeDTO;
import com.CityTricks.citytricks.dto.ComentarioDTO;
import com.CityTricks.citytricks.dto.ComentarioTopicoDTO;
import com.CityTricks.citytricks.dto.TopicoDTO;
import com.CityTricks.citytricks.model.entity.Cidade;
import com.CityTricks.citytricks.model.entity.Comentario;
import com.CityTricks.citytricks.model.entity.ComentarioTopico;
import com.CityTricks.citytricks.model.entity.Topico;
import com.CityTricks.citytricks.model.repository.CidadeRepository;
import com.CityTricks.citytricks.model.repository.EstadoRepository;
import com.CityTricks.citytricks.model.repository.PaisRepository;
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
    private CidadeRepository repository;

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

        if(cidade1.getListaTopicos() == null) {
            cidade1.setListaTopicos(new ArrayList<>());
        }

        if(cidade1.getListaTopicos() != null){
            cidade1.getListaTopicos().clear();
            cidadeRepository.flush();
        }
        else {
            cidade1.setListaTopicos(new ArrayList<>());
        }

        cidade1.getListaTopicos().addAll(montaListaTopicosEntidade(cidade.getListaTopicos(), cidade1));

        if(cidade1.getListaComentarios() != null){
            cidade1.getListaComentarios().clear();
            cidadeRepository.flush();
        }
        else {
            cidade1.setListaComentarios(new ArrayList<>());
        }
        cidade1.getListaComentarios().addAll(montaListaComentariosEntidade(cidade.getListaComentarios(), cidade1));

        cidadeRepository.save(cidade1);

        return cidade1;
    }

    private List<Topico> montaListaTopicosEntidade(List<TopicoDTO> listaTopicos, Cidade cidade) {
        if (listaTopicos == null || listaTopicos.isEmpty()) {
            return new ArrayList<Topico>();
        }
        List<Topico> topicos = new ArrayList<Topico>();
        for (TopicoDTO topicoDTO : listaTopicos) {
            topicos.add(preencherTopico(topicoDTO, cidade));
        }
        return topicos;
    }

    private Topico preencherTopico(TopicoDTO topicoDto, Cidade cidade) {
        Topico topico = new Topico();

        topico.setId(topicoDto.getId());
        topico.setNome(topicoDto.getNome());
        topico.setCidade(cidade);
        topico.setLocal(topicoDto.getLocal());

        if(topico.getListaComentarios() == null) {
            topico.setListaComentarios(new ArrayList<>());
        }

        topico.getListaComentarios().clear();
        cidadeRepository.flush();
        topico.getListaComentarios().addAll(montaListaComentariosTopicoEntidade(topicoDto.getListaComentarios(), topico));

        return topico;
    }

    private List<ComentarioTopico> montaListaComentariosTopicoEntidade(List<ComentarioTopicoDTO> listaComentarios, Topico topico) {
        if (listaComentarios == null || listaComentarios.isEmpty()) {
            return new ArrayList<ComentarioTopico>();
        }
        List<ComentarioTopico> comentarios = new ArrayList<ComentarioTopico>();
        for (ComentarioTopicoDTO commentDTO : listaComentarios) {
            comentarios.add(preencherComentarioTopico(commentDTO, topico));
        }
        return comentarios;
    }

    private ComentarioTopico preencherComentarioTopico(ComentarioTopicoDTO commentDto, Topico topico) {
        ComentarioTopico comentario = new ComentarioTopico();

        comentario.setId(commentDto.getId());
        comentario.setTitulo(commentDto.getTitulo());
        comentario.setInformacao(commentDto.getInformacao());
        comentario.setNota(commentDto.getNota());
        comentario.setTopico(topico);

        return comentario;
    }

    private List<Comentario> montaListaComentariosEntidade(List<ComentarioDTO> listaComentarios, Cidade cidade) {
        if (listaComentarios == null || listaComentarios.isEmpty()) {
            return new ArrayList<Comentario>();
        }
        List<Comentario> comentarios = new ArrayList<Comentario>();
        for (ComentarioDTO commentDTO : listaComentarios) {
            comentarios.add(preencherComentario(commentDTO, cidade));
        }
        return comentarios;
    }

    private Comentario preencherComentario(ComentarioDTO commentDto, Cidade cidade) {
        Comentario comentario = new Comentario();

        comentario.setId(commentDto.getId());
        comentario.setTitulo(commentDto.getTitulo());
        comentario.setInformacao(commentDto.getInformacao());
        comentario.setNota(commentDto.getNota());
        comentario.setCidade(cidade);

        return comentario;
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
