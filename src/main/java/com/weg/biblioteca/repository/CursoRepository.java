package com.weg.biblioteca.repository;

import com.weg.biblioteca.config.Conexao;
import com.weg.biblioteca.model.Curso;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepository {

    public List<Curso> getAll() {
        String query = """
                SELECT
                    u.id,
                    u.nome,
                    u.codigo
                FROM
                    curso u
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()
        ) {
            List<Curso> cursos = new ArrayList<>();

            while (resultSet.next()) {
                Curso curso = new Curso(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("codigo")
                );
                cursos.add(curso);
            }

            return cursos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Curso> findById(Long id) {
        String query = """
                SELECT
                    u.id,
                    u.nome,
                    u.codigo
                FROM
                    curso u
                WHERE
                    u.id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setLong(1, id);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Curso curso = new Curso(
                            resultSet.getLong("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("codigo")
                    );
                    return Optional.of(curso);
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Curso save(Curso curso) {
        String query = """
                INSERT INTO curso (nome, codigo)
                VALUES (?, ?)
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            statement.setString(1, curso.getNome());
            statement.setString(2, curso.getCodigo());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating curso failed, no rows affected.");
            }

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    return new Curso(id, curso.getNome(), curso.getCodigo());
                } else {
                    throw new RuntimeException("Creating curso failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Curso update(Curso curso) {
        String query = """
                UPDATE curso
                SET nome = ?, codigo = ?
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, curso.getNome());
            statement.setString(2, curso.getCodigo());
            statement.setLong(3, curso.getId());

            statement.executeUpdate();

            return curso;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        String query = """
                DELETE FROM curso
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}