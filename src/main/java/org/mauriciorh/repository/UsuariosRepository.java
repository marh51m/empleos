package org.mauriciorh.repository;

import org.mauriciorh.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
