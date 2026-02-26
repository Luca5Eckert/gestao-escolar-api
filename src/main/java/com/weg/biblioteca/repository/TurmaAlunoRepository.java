package com.weg.biblioteca.repository;

import com.weg.biblioteca.config.Conexao;
import com.weg.biblioteca.model.TurmaAluno;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TurmaAlunoRepository {

    public List<TurmaAluno> getAll() {
        String query = """
                SELECT
                    u.turma_id,
                    u.aluno_id
                FROM
                    turma_aluno u
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()
        ) {
            List<TurmaAluno> lista = new ArrayList<>();

            while (resultSet.next()) {
                TurmaAluno turmaAluno = new TurmaAluno(
                        resultSet.getLong("turma_id"),
                        resultSet.getLong("aluno_id")
                );
                lista.add(turmaAluno);
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TurmaAluno> findByTurmaId(long turmaId) {
        String query = """
                SELECT
                    u.turma_id,
                    u.aluno_id
                FROM
                    turma_aluno u
                WHERE
                    u.turma_id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, turmaId);
            try (var resultSet = statement.executeQuery()) {
                List<TurmaAluno> lista = new ArrayList<>();
                while (resultSet.next()) {
                    lista.add(new TurmaAluno(
                            resultSet.getLong("turma_id"),
                            resultSet.getLong("aluno_id")
                    ));
                }
                return lista;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TurmaAluno save(TurmaAluno turmaAluno) {
        String query = """
                INSERT INTO turma_aluno (turma_id, aluno_id)
                VALUES (?, ?)
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, turmaAluno.getTurmaId());
            statement.setLong(2, turmaAluno.getAlunoId());

            statement.executeUpdate();
            return turmaAluno;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long turmaId, long alunoId) {
        String query = """
                DELETE FROM turma_aluno
                WHERE turma_id = ? AND aluno_id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, turmaId);
            statement.setLong(2, alunoId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(long turmaId, long alunoId, TurmaAluno novo) {
        String query = """
                UPDATE turma_aluno
                SET turma_id = ?, aluno_id = ?
                WHERE turma_id = ? AND aluno_id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, novo.getTurmaId());
            statement.setLong(2, novo.getAlunoId());
            statement.setLong(3, turmaId);
            statement.setLong(4, alunoId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<TurmaAluno> findByTurmaIdAndAlunoId(long turmaId, long alunoId) {
        String query = """
                SELECT
                    u.turma_id,
                    u.aluno_id
                FROM
                    turma_aluno u
                WHERE
                    u.turma_id = ? AND u.aluno_id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, turmaId);
            statement.setLong(2, alunoId);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    TurmaAluno turmaAluno = new TurmaAluno(
                            resultSet.getLong("turma_id"),
                            resultSet.getLong("aluno_id")
                    );
                    return Optional.of(turmaAluno);
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}