package com.msvc.usuario.repository;

import com.msvc.usuario.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuariorepository extends JpaRepository<Usuario,String> {
}
