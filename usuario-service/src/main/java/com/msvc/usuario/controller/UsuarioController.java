package com.msvc.usuario.controller;


import com.msvc.usuario.entity.Usuario;
import com.msvc.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable String id) {
        Usuario usuario = usuarioService.getUsuario(id);
        return ResponseEntity.ok(usuario);
    }
}
