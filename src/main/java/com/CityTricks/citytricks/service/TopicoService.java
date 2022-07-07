package com.CityTricks.citytricks.service;

import com.CityTricks.citytricks.dto.TopicoDTO;
import com.CityTricks.citytricks.model.entity.Cidade;
import com.CityTricks.citytricks.model.entity.Topico;
import com.CityTricks.citytricks.model.repository.CidadeRepository;
import com.CityTricks.citytricks.model.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private AvaliacaoTopicoService avaliacaoTopicoService;

    @Autowired
    private ComentarioTopicoService comentarioTopicoService;

    public List<Topico> montaListaTopicosEntidade(List<TopicoDTO> listaTopicos, Cidade cidade) {
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
        topico.getListaComentarios().addAll(comentarioTopicoService.montaListaComentariosTopicoEntidade(topicoDto.getListaComentarios(), topico));

        if(topico.getListaAvaliacao() != null){
            topico.getListaAvaliacao().clear();
            cidadeRepository.flush();
        }
        else {
            topico.setListaAvaliacao(new ArrayList<>());
        }

        topico.getListaAvaliacao().addAll(avaliacaoTopicoService.montaListaAvaliacaoEntidade(topicoDto.getListaAvaliacao(), topico));

        return topico;
    }


    public TopicoService(TopicoRepository topicoRepository) {

        this.topicoRepository = topicoRepository;
    }

    public void save(TopicoDTO topico) {

        Topico topico1 = preencherTopico(topico, null);
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
