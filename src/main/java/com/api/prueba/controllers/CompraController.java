package com.api.prueba.controllers;

import com.api.prueba.entities.Compra;
import com.api.prueba.entities.Producto;
import com.api.prueba.entities.Usuario;
import com.api.prueba.repositories.CompraRepository;
import com.api.prueba.repositories.ProductoRepository;
import com.api.prueba.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/compra")
@RestController
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/listar")
    public List<Map.Entry<Long, Producto>> listar(@RequestParam("id_usuario") int id_usuario) {

        Optional<Usuario> usuario = usuarioRepository.findById(id_usuario);

        return compraRepository.findCompraByUsuario(usuario.get()).stream()
                .map(Compra -> Map.entry(Compra.getId(), Compra.getProducto()))
                .collect(Collectors.toList());
    }

    @GetMapping("/actualizar")
    public ResponseEntity<Map<String, Object>> actualizar(@RequestParam("id_compra") Long id_compra,@RequestParam("id_producto") int id_producto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Producto> producto = productoRepository.findById(id_producto);
            Optional<Compra> Optionalcompra = compraRepository.findById(id_compra);


            if (Optionalcompra.isPresent() && producto.isPresent()) {
               Compra compra= Optionalcompra.get();
                compra.setProducto(producto.get());

                Compra savedCompra = compraRepository.save(compra);

                if (savedCompra != null && savedCompra.getId() != null) {
                    response.put("success", true);
                    return ResponseEntity.ok(response);
                } else {
                    response.put("success", false);
                    response.put("message", "Error al actualizar la compra.");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            } else {
                response.put("success", false);
                response.put("message", "compra o producto no encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Ocurrió un error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/eliminar")
    public ResponseEntity<Map<String, Object>> eliminar(@RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();

        if (compraRepository.existsById(id)) {
            compraRepository.deleteById(id);
            if (!compraRepository.existsById(id)) {
                response.put("success", true);
                response.put("message", "Producto eliminado correctamente.");
            } else {
                response.put("success", false);
                response.put("message", "No se pudo eliminar el producto.");
            }
        } else {
            response.put("success", false);
            response.put("message", "Producto no encontrado.");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/grabar")
    public ResponseEntity<Map<String, Object>> grabar(@RequestParam("id_usuario") int id_usuario, @RequestParam("id_producto") int id_producto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id_usuario);
            Optional<Producto> producto = productoRepository.findById(id_producto);

            if (usuario.isPresent() && producto.isPresent()) {
                Compra compra = new Compra();
                compra.setUsuario(usuario.get());
                compra.setProducto(producto.get());

                Compra savedCompra = compraRepository.save(compra);

                if (savedCompra != null && savedCompra.getId() != null) {
                    response.put("success", true);
                    return ResponseEntity.ok(response);
                } else {
                    response.put("success", false);
                    response.put("message", "Error al guardar la compra.");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            } else {
                response.put("success", false);
                response.put("message", "Usuario o producto no encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Ocurrió un error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
