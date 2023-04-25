package com.example.restaurante.DAO;

import com.example.restaurante.Connection.ConnectionFactory;
import com.example.restaurante.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAO implements IUsuarioDAO {
    @Override
    public Usuario create(Usuario usuario) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO usuarios (nome, cpf, matricula, cargo, area, usuario, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getCpf());
            statement.setString(3, usuario.getMatricula());
            statement.setString(4, usuario.getCargo());
            statement.setString(5, usuario.getArea());
            statement.setString(6, usuario.getUsuario());
            statement.setString(7, usuario.getSenha());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                usuario.setId(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário no banco de dados", e);
        }
        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE usuarios SET usuario = ?, senha = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            statement.setLong(3, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário no banco de dados", e);
        }

        return usuario;
    }

    @Override
    public void delete(Usuario usuario) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM usuarios WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, usuario.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir usuário no banco de dados", e);
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM usuarios";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getString("usuario"),
                        resultSet.getString("senha"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("cargo"),
                        resultSet.getString("matricula"),
                        resultSet.getString("area")
                );

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuários no banco de dados", e);
        }

        return usuarios;
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        Optional<Usuario> optionalUsuario = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM usuarios WHERE usuario = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getString("usuario"),
                        resultSet.getString("senha"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("cargo"),
                        resultSet.getString("matricula"),
                        resultSet.getString("area")
                );

                optionalUsuario = Optional.of(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário no banco de dados", e);
        }

        return optionalUsuario;
    }


    public boolean validarLogin(String matricula, String senha) {
        String sql = "SELECT * FROM Usuarios WHERE matricula = ? AND senha = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao validar login", e);
        }
    }

    /*public boolean validarLogin(String matricula, String senha) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM usuarios WHERE matricula = ? AND senha = ?")) {
            stmt.setString(1, matricula);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // retorna true se houver um resultado na consulta, ou seja, se a matrícula e senha estiverem corretas
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao validar login", e);
        }
    }

     */



}
