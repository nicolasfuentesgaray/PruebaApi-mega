package com.api.prueba.controllers;

import com.api.prueba.entities.Producto;
import com.api.prueba.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/producto")
@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/lista")
    public List<Producto> lista() {

         return  productoRepository.findAll();
    }

    @GetMapping("/lista/{id}")
    public Optional<Producto> obtenerProductoPorId(@PathVariable int id) {

       return  productoRepository.findById(id);

    }


}
