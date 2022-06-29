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
@Table(name = "TB_TOPICO")
public class Topico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = false, length = 255)
    private String nome;

    @Column(nullable = false, unique = false, length = 250)
    private String local;

    @Column(nullable = false, unique = false, length = 250)
    private String Cidade;

    public void setRegistrationDate(LocalDateTime utc) {
    }
}