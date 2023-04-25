package com.example.restaurante.DAO;

import com.example.restaurante.Model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioDAO {

    Usuario create(Usuario usuario);

    Usuario update(Usuario usuario);

    void delete(Usuario usuario);

    List<Usuario> findAll();

    Optional<Usuario> findByUsername(String username);
}
