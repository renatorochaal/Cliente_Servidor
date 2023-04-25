package com.example.nomecadastro.DAO;

import com.example.nomecadastro.Model.Nome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NomeDAO extends JpaRepository<Nome, Long> {


}

