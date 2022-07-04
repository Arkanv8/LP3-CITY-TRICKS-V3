package com.CityTricks.citytricks.model.repository;

import com.CityTricks.citytricks.model.entity.Estado;
import com.CityTricks.citytricks.model.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
}
