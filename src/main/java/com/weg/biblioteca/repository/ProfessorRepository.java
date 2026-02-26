package com.weg.biblioteca.repository;

import com.weg.biblioteca.config.Conexao;
import com.weg.biblioteca.model.Professor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorRepository {

    public List<Professor> getAll() {
        String query = """
                SELECT
                    u.id,
                    u.nome,
                    u.email,
                    u.disciplina
                FROM
                    professor u
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()
        ) {
            List<Professor> professores = new ArrayList<>();

            while (resultSet.next()) {
                Professor professor = new Professor(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("email"),
                        resultSet.getString("disciplina")
                );
                professores.add(professor);
            }

            return professores;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Professor> findById(long id) {
        String query = """
                SELECT
                    u.id,
                    u.nome,
                    u.email,
                    u.disciplina
                FROM
                    professor u
                WHERE
                    u.id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setLong(1, id);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Professor professor = new Professor(
                            resultSet.getLong("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("email"),
                            resultSet.getString("disciplina")
                    );
                    return Optional.of(professor);
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Professor save(Professor professor) {
        String query = """
                INSERT INTO professor (nome, email, disciplina)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            statement.setString(1, professor.getNome());
            statement.setString(2, professor.getEmail());
            statement.setString(3, professor.getDisciplina());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating professor failed, no rows affected.");
            }

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    return new Professor(id, professor.getNome(), professor.getEmail(), professor.getDisciplina());
                } else {
                    throw new RuntimeException("Creating professor failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Professor update(Professor professor) {
        String query = """
                UPDATE professor
                SET nome = ?, email = ?, disciplina = ?
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, professor.getNome());
            statement.setString(2, professor.getEmail());
            statement.setString(3, professor.getDisciplina());
            statement.setLong(4, professor.getId());

            statement.executeUpdate();

            return professor;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) {
        String query = """
                DELETE FROM professor
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