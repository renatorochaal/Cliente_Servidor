package com.example.restaurante.DAO;

import com.example.restaurante.Connection.ConnectionFactory;
import com.example.restaurante.Model.Prato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PratoDAO implements IPratoDAO {
    @Override
    public Prato create(Prato prato) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO pratos (nome, preco, serve_quantas_pessoas, ingredientes) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, prato.getNome());
            statement.setDouble(2, prato.getPreco());
            statement.setInt(3, prato.getServeQuantasPessoas());
            statement.setString(4, prato.getIngredientes());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            long idGerado = resultSet.getLong(1);
            prato.setId(Math.toIntExact(idGerado));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir prato no banco de dados", e);
        }
        return prato;
    }

    @Override
    public Prato update(Prato prato) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE pratos SET nome = ?, preco = ?, serve_quantas_pessoas = ?, ingredientes = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, prato.getNome());
            statement.setDouble(2, prato.getPreco());
            statement.setInt(3, prato.getServeQuantasPessoas());
            statement.setString(4, prato.getIngredientes());
            statement.setInt(5, prato.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Erro ao atualizar prato: nenhum registro foi modificado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar prato no banco de dados", e);
        }
        return prato;
    }

    @Override
    public void delete(Prato prato) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM pratos WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, prato.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir prato do banco de dados", e);
        }
    }

    @Override
    public List<Prato> findAll() {
        List<Prato> pratos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM pratos";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Prato prato = new Prato(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("preco"),
                        resultSet.getInt("serve_quantas_pessoas"),
                        resultSet.getString("ingredientes"));
                pratos.add(prato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os pratos no banco de dados", e);
        }
        return pratos;
    }


    @Override
    public Optional<Prato> findById(int id) {
        Optional<Prato> optionalPrato = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM pratos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Prato prato = new Prato(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("preco"),
                        resultSet.getInt("serve_quantas_pessoas"),
                        resultSet.getString("ingredientes")
                );
                optionalPrato = Optional.of(prato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar prato no banco de dados", e);
        }

        return optionalPrato;
    }
}
