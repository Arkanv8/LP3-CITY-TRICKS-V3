package com.CityTricks.citytricks.model.repository;

import com.CityTricks.citytricks.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @PersistenceContext
    public EntityManager entityManager = null;

    public static void saveUsuario(Usuario usuario) {

        if (usuario.getId() == null) {
            entityManager.persist(usuario);
        } else {
            entityManager.merge(usuario);
        }
    }

    Optional<Usuario> findById(Long id);
}
