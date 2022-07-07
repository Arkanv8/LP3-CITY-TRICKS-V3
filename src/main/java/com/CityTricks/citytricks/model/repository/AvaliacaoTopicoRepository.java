package com.CityTricks.citytricks.model.repository;

import com.CityTricks.citytricks.model.entity.AvaliacaoCidade;
import com.CityTricks.citytricks.model.entity.AvaliacaoTopico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public interface AvaliacaoTopicoRepository extends JpaRepository<AvaliacaoTopico, Long>{


}
