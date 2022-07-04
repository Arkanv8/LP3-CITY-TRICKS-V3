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
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;

    private String nome;

    private String cidade;

    private boolean admin;

    @OneToOne(cascade = CascadeType.ALL)
    private Pontuacao pontuacoes;

    public void setRegistrationDate(LocalDateTime utc) {
    }

    public LocalDateTime getRegistrationDate() {

        return null;
    }
}
