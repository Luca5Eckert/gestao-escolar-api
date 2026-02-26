package com.weg.biblioteca.repository;

import com.weg.biblioteca.config.Conexao;
import com.weg.biblioteca.model.Aula;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AulaRepository {

    public List<Aula> getAll() {
        String query = """
                SELECT
                    u.id,
                    u.turma_id,
                    u.data_hora,
                    u.assunto
                FROM
                    aula u
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()
        ) {
            List<Aula> aulas = new ArrayList<>();

            while (resultSet.next()) {
                Aula aula = new Aula(
                        resultSet.getLong("id"),
                        resultSet.getLong("turma_id"),
                        resultSet.getTimestamp("data_hora").toLocalDateTime(),
                        resultSet.getString("assunto")
                );
                aulas.add(aula);
            }

            return aulas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Aula> findById(Long id) {
        String query = """
                SELECT
                    u.id,
                    u.turma_id,
                    u.data_hora,
                    u.assunto
                FROM
                    aula u
                WHERE
                    u.id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setLong(1, id);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Aula aula = new Aula(
                            resultSet.getLong("id"),
                            resultSet.getLong("turma_id"),
                            resultSet.getTimestamp("data_hora").toLocalDateTime(),
                            resultSet.getString("assunto")
                    );
                    return Optional.of(aula);
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aula save(Aula aula) {
        String query = """
                INSERT INTO aula (turma_id, data_hora, assunto)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            statement.setLong(1, aula.getTurmaId());
            statement.setTimestamp(2, Timestamp.valueOf(aula.getDataHora()));
            statement.setString(3, aula.getAssunto());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating aula failed, no rows affected.");
            }

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    return new Aula(id, aula.getTurmaId(), aula.getDataHora(), aula.getAssunto());
                } else {
                    throw new RuntimeException("Creating aula failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aula update(Aula aula) {
        String query = """
                UPDATE aula
                SET turma_id = ?, data_hora = ?, assunto = ?
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, aula.getTurmaId());
            statement.setTimestamp(2, Timestamp.valueOf(aula.getDataHora()));
            statement.setString(3, aula.getAssunto());
            statement.setLong(4, aula.getId());

            statement.executeUpdate();

            return aula;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        String query = """
                DELETE FROM aula
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