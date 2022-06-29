package com.CityTricks.citytricks.model.repository;

import com.CityTricks.citytricks.model.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

    @PersistenceContext
    public EntityManager entityManager = null;

    public static void saveUsuario(Avaliacao avaliacao) {

        if (avaliacao.getId() == null) {
            entityManager.persist(avaliacao);
        } else {
            entityManager.merge(avaliacao);
        }
    }
    Optional<Avaliacao> findById(Long id);
}

