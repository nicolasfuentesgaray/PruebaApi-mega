package com.api.prueba.repositories;

import com.api.prueba.entities.Rol;
import com.api.prueba.entities.RolEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByNombre(RolEnum nombre);
}
