package com.CityTricks.citytricks.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_topico")
public class Topico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    private String local;

    @ManyToOne
    private Cidade cidade;

    @OneToMany(mappedBy = "topico", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ComentarioTopico> listaComentarios;

    @OneToMany(mappedBy = "topico", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AvaliacaoTopico> listaAvaliacao;

    public void setRegistrationDate(LocalDateTime utc) {
    }
}