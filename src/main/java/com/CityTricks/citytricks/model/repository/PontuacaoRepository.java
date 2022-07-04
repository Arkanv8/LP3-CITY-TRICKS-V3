package com.CityTricks.citytricks.model.repository;

import com.CityTricks.citytricks.model.entity.Pontuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long> {

    @PersistenceContext
    public EntityManager entityManager = null;

    public static void saveUsuario(Pontuacao pontuacao) {

        if (pontuacao.getId() == null) {
            entityManager.persist(pontuacao);
        } else {
            entityManager.merge(pontuacao);
        }
    }

    Optional<Pontuacao> findById(Long id);
}
