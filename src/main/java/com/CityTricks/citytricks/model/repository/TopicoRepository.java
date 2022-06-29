package com.CityTricks.citytricks.model.repository;

import com.CityTricks.citytricks.model.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.CityTricks.citytricks.model.repository.UsuarioRepository.entityManager;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    public static void saveTopico(Topico topico) {

        if (topico.getId() == null) {
            entityManager.persist(topico);
        } else {
            entityManager.merge(topico);
        }
    }
}
