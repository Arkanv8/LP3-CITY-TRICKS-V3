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
@Table(name = "TB_AVALIACAO")
public class Avaliacao implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 250)
    private String usuario;

    @Column(nullable = true, unique = false, length = 250)
    private String nota;

    @Column(nullable = false, unique = false, length = 250)
    private String titulo;

    @Column(nullable = false, unique = false, length = 250)
    private String cidade;

    @Column(nullable = false, unique = false, length = 250)
    private String topico;

    public void setRegistrationDate(LocalDateTime utc) {
    }
}
