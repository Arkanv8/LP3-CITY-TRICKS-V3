package com.CityTricks.citytricks.model.repository;

import com.CityTricks.citytricks.model.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    @PersistenceContext
    public EntityManager entityManager = null;

    public static void saveComentario(Comentario comentario) {

        if (comentario.getId() == null) {
            entityManager.persist(comentario);
        } else {
            entityManager.merge(comentario);
        }
    }
    Optional<Comentario> findById(Long id);
}
