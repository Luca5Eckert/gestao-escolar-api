package com.weg.biblioteca.repository;

import com.weg.biblioteca.config.Conexao;
import com.weg.biblioteca.model.Turma;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TurmaRepository {

    public List<Turma> getAll() {
        String query = """
                SELECT
                    u.id,
                    u.nome,
                    u.curso_id,
                    u.professor_id
                FROM
                    turma u
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()
        ) {
            List<Turma> turmas = new ArrayList<>();

            while (resultSet.next()) {
                Turma turma = new Turma(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        resultSet.getLong("curso_id"),
                        resultSet.getLong("professor_id")
                );
                turmas.add(turma);
            }

            return turmas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Turma> findById(Long id) {
        String query = """
                SELECT
                    u.id,
                    u.nome,
                    u.curso_id,
                    u.professor_id
                FROM
                    turma u
                WHERE
                    u.id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setLong(1, id);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Turma turma = new Turma(
                            resultSet.getLong("id"),
                            resultSet.getString("nome"),
                            resultSet.getLong("curso_id"),
                            resultSet.getLong("professor_id")
                    );
                    return Optional.of(turma);
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Turma save(Turma turma) {
        String query = """
                INSERT INTO turma (nome, curso_id, professor_id)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            statement.setString(1, turma.getNome());
            statement.setLong(2, turma.getCursoId());
            statement.setLong(3, turma.getProfessorId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating turma failed, no rows affected.");
            }

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    return new Turma(id, turma.getNome(), turma.getCursoId(), turma.getProfessorId());
                } else {
                    throw new RuntimeException("Creating turma failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Turma update(Turma turma) {
        String query = """
                UPDATE turma
                SET nome = ?, curso_id = ?, professor_id = ?
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, turma.getNome());
            statement.setLong(2, turma.getCursoId());
            statement.setLong(3, turma.getProfessorId());
            statement.setLong(4, turma.getId());

            statement.executeUpdate();

            return turma;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        String query = """
                DELETE FROM turma
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