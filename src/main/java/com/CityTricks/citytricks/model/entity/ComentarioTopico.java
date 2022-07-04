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
@Table(name = "TB_COMENTARIO_TOPICO")
public class ComentarioTopico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = false, length = 250)
    private String titulo;

    @Column(nullable = true, unique = false, length = 250)
    private String informacao;

    @Column(nullable = true, unique = false, length = 250)
    private String nota;

    @ManyToOne
    private Topico topico;


    public void setRegistrationDate(LocalDateTime utc) {
    }
}
