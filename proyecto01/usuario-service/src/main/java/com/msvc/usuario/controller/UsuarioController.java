package com.msvc.usuario.controller;


import com.msvc.usuario.entities.Usuario;
import com.msvc.usuario.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuarioRequest) {
        Usuario usuario = usuarioService.saveUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> listUsuario = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(listUsuario);
    }

    int cantidadReintentos=1;

    @GetMapping
    @RequestMapping("/{id}")
    //@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
    @Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<Usuario> findById(@PathVariable String id) {
        log.info("Listar un solo usuario");
        log.info("Cantidad de reintentos: {}", cantidadReintentos);
        cantidadReintentos++;
        Usuario usuario = usuarioService.getUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    public ResponseEntity<Usuario> ratingHotelFallBack(String usuarioId,Exception exception){
        log.info("El respaldo se ejecuta porque el servicio esta inactivo : ",exception.getMessage());
        Usuario usuario = Usuario.builder()
                .email("root1@gmail.com")
                .nombre("root")
                .informacion("usuario SOS")
                .usuarioId("1234").build();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
