package com.weg.biblioteca.repository;

import com.weg.biblioteca.config.Conexao;
import com.weg.biblioteca.model.Nota;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NotaRepository {

    public List<Nota> getAll() {
        String query = """
                SELECT
                    u.id,
                    u.aluno_id,
                    u.aula_id,
                    u.valor
                FROM
                    nota u
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()
        ) {
            List<Nota> notas = new ArrayList<>();

            while (resultSet.next()) {
                Nota nota = new Nota();
                nota.setId(resultSet.getLong("id"));
                nota.setAlunoId(resultSet.getLong("aluno_id"));
                nota.setAulaId(resultSet.getLong("aula_id"));
                nota.setValor(resultSet.getDouble("valor"));
                notas.add(nota);
            }

            return notas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Nota> findById(Long id) {
        String query = """
                SELECT
                    u.id,
                    u.aluno_id,
                    u.aula_id,
                    u.valor
                FROM
                    nota u
                WHERE
                    u.id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setLong(1, id);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Nota nota = new Nota();
                    nota.setId(resultSet.getLong("id"));
                    nota.setAlunoId(resultSet.getLong("aluno_id"));
                    nota.setAulaId(resultSet.getLong("aula_id"));
                    nota.setValor(resultSet.getDouble("valor"));
                    return Optional.of(nota);
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Nota save(Nota nota) {
        String query = """
                INSERT INTO nota (aluno_id, aula_id, valor)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            statement.setLong(1, nota.getAlunoId());
            statement.setLong(2, nota.getAulaId());
            statement.setDouble(3, nota.getValor());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating nota failed, no rows affected.");
            }

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    nota.setId(generatedKeys.getLong(1));
                    return nota;
                } else {
                    throw new RuntimeException("Creating nota failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Nota update(Nota nota) {
        String query = """
                UPDATE nota
                SET aluno_id = ?, aula_id = ?, valor = ?
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, nota.getAlunoId());
            statement.setLong(2, nota.getAulaId());
            statement.setDouble(3, nota.getValor());
            statement.setLong(4, nota.getId());

            statement.executeUpdate();

            return nota;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        String query = """
                DELETE FROM nota
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