package com.CityTricks.citytricks.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Column(nullable = false, unique = false, length = 250)
    private String topicos;

    public void setRegistrationDate(LocalDateTime utc) {
    }
}
