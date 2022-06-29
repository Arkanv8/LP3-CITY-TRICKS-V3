package com.CityTricks.citytricks.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 250)
    private String email;

    @Column(nullable = false, unique = false, length = 250)
    private String senha;

    @Column(nullable = false, unique = false, length = 250)
    private String nome;

    @Column(nullable = false, unique = false, length = 250)
    private String cidade;

    @Column(nullable = true, unique = false, length = 1)
    private boolean admin;


    public void setRegistrationDate(LocalDateTime utc) {
    }
}
