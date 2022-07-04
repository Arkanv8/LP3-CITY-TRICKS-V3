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
@Table(name = "TB_AVALIACAO")
public class Avaliacao implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Usuario usuario;

    private String nota;

    private String titulo;

    @OneToOne
    private Topico topico;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public com.CityTricks.citytricks.model.entity.Cidade getCidade() {
        return cidade;
    }

    public void setCidade(com.CityTricks.citytricks.model.entity.Cidade cidade) {
        this.cidade = cidade;
    }


    public void setRegistrationDate(LocalDateTime utc) {
    }

    public LocalDateTime getRegistrationDate() {
        return null;
    }
}
