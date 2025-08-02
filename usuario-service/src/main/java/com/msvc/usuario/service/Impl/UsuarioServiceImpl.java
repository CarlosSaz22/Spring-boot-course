package com.msvc.usuario.service.Impl;

import com.msvc.usuario.entity.Usuario;
import com.msvc.usuario.exceptions.ResourceNotFoundException;
import com.msvc.usuario.repository.Usuariorepository;
import com.msvc.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private Usuariorepository usuariorepository;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String randomUsuario = UUID.randomUUID().toString();
        usuario.setUsuarioId(randomUsuario);
        return usuariorepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuariorepository.findAll();
    }

    @Override
    public Usuario getUsuario(String usuarioId) {
        return usuariorepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID :" + usuarioId));
    }
}
