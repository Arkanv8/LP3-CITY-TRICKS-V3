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
@Table(name = "TB_Cidade")

public class Cidade implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 250)
    private String nome;

    @Column(nullable = false, unique = false, length = 250)
    private String locais;

    @OneToOne
    private Estado estado;

    @OneToOne
    private Pais pais;

    @OneToMany(mappedBy = "cidade", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> listaTopicos;

    @OneToMany(mappedBy = "cidade", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> listaComentarios;

    public void setRegistrationDate(LocalDateTime utc) {
    }
}
