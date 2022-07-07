package com.CityTricks.citytricks.model.repository;

import com.CityTricks.citytricks.model.entity.AvaliacaoCidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public interface AvaliacaoCidadeRepository extends JpaRepository<AvaliacaoCidade, Long>{


}
