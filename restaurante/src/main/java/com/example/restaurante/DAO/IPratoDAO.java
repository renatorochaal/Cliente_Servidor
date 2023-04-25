package com.example.restaurante.DAO;

import com.example.restaurante.Model.Prato;

import java.util.List;
import java.util.Optional;

public interface IPratoDAO {
    Prato create(Prato prato);
    Prato update(Prato prato);
    void delete(Prato prato);
    List<Prato> findAll();
    Optional<Prato> findById(int id);
}
