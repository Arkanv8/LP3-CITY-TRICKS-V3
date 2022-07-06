package com.CityTricks.citytricks.model.repository;

import com.CityTricks.citytricks.model.entity.ComentarioCidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public interface ComentarioCidadeRepository extends JpaRepository<ComentarioCidade, Long>{
}
