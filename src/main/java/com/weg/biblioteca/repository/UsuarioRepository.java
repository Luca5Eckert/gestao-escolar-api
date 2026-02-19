package com.weg.biblioteca.repository;

import com.weg.biblioteca.config.Conexao;
import com.weg.biblioteca.model.Usuario;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    public List<Usuario> getAll() {
        String query = """
                SELECT
                    u.id,
                    u.nome,
                    u.usuario
                FROM
                    usuario u
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()
        ) {
            List<Usuario> contatoes = new ArrayList<>();

            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("usuario")
                );
                contatoes.add(usuario);
            }

            return contatoes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<Usuario> findById(int id) {
        String query = """
                SELECT
                    u.id,
                    u.nome,
                    u.usuario
                FROM
                    usuario u
                WHERE
                    u.id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, id);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Usuario usuario = new Usuario(
                            resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("usuario")
                    );
                    return Optional.of(usuario);
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario save(Usuario usuario) {
        String query = """
                INSERT INTO usuario (nome, usuario)
                VALUES (?, ?)
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating usuario failed, no rows affected.");
            }

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new Usuario(id, usuario.getNome(), usuario.getEmail());
                } else {
                    throw new RuntimeException("Creating usuario failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario update(Usuario usuario) {
        String query = """
                UPDATE usuario
                SET nome = ?, usuario = ?
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setInt(3, usuario.getId());

            statement.executeUpdate();

            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        String query = """
                DELETE FROM usuario
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
