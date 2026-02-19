package com.weg.biblioteca.repository;

import com.weg.biblioteca.config.Conexao;
import com.weg.biblioteca.model.Livro;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LivroRepository {

    public List<Livro> getAll() {
        String query = """
                SELECT
                    l.id,
                    l.titulo,
                    l.autor,
                    l.ano_publicacao
                FROM
                    livro l
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()
        ) {
            List<Livro> contatoes = new ArrayList<>();

            while (resultSet.next()) {
                Livro livro = new Livro(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("autor"),
                        resultSet.getInt("ano_publicacao")
                );
                contatoes.add(livro);
            }

            return contatoes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<Livro> findById(int id) {
        String query = """
                SELECT
                    l.id,
                    l.titulo,
                    l.autor,
                    l.ano_publicacao
                FROM
                    livro l
                WHERE
                    l.id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, id);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Livro livro = new Livro(
                            resultSet.getInt("id"),
                            resultSet.getString("titulo"),
                            resultSet.getString("autor"),
                            resultSet.getInt("ano_publicacao")
                    );
                    return Optional.of(livro);
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Livro save(Livro livro) {
        String query = """
                INSERT INTO livro (titulo, autor, ano_publicacao)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setInt(3, livro.getAnoPublicacao());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating livro failed, no rows affected.");
            }

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new Livro(id, livro.getTitulo(), livro.getTitulo(), livro.getAnoPublicacao());
                } else {
                    throw new RuntimeException("Creating livro failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Livro update(Livro livro) {
        String query = """
                UPDATE livro
                SET titulo = ?, autor = ?, ano_publicacao = ?
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setInt(3, livro.getAnoPublicacao());
            statement.setInt(4, livro.getId());

            statement.executeUpdate();

            return livro;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        String query = """
                DELETE FROM livro
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
