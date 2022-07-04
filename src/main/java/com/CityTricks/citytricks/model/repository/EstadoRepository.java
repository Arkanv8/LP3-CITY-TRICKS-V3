package com.CityTricks.citytricks.model.repository;

import com.CityTricks.citytricks.model.entity.Estado;
import com.CityTricks.citytricks.model.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
