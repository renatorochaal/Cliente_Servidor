package com.example.nomecadastro.Controller;

import com.example.nomecadastro.DAO.NomeDAO;
import com.example.nomecadastro.Model.Nome;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class NomeController {

    @Autowired
    private NomeDAO nomeDAO;

    @PostMapping("/nomes")
    public Nome adicionarNome(@RequestBody Nome nomes) {
        return nomeDAO.save(nomes);
    }

    @GetMapping("/nomes/{id}")
    public Nome buscarNomePorId(@PathVariable Long id) {
        return nomeDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}


