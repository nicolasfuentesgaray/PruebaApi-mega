package com.api.prueba.repositories;

import com.api.prueba.entities.Compra;
import com.api.prueba.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findCompraByUsuario(Usuario usuario);
}