package com.weg.biblioteca.repository;

import com.weg.biblioteca.config.Conexao;
import com.weg.biblioteca.model.Aluno;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlunoRepository {

    public List<Aluno> getAll() {
        String query = """
                SELECT
                    u.id,
                    u.nome,
                    u.codigo,
                    u.matricula,
                    u.data_nascimento
                FROM
                    aluno u
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()
        ) {
            List<Aluno> contatoes = new ArrayList<>();

            while (resultSet.next()) {
                Aluno aluno = new Aluno(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("codigo"),
                        resultSet.getString("matricula"),
                        resultSet.getDate("data_nascimento").toLocalDate()
                );
                contatoes.add(aluno);
            }

            return contatoes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Aluno> findById(int id) {
        String query = """
                SELECT
                    u.id,
                    u.nome,
                    u.codigo,
                    u.matricula,
                    u.data_nascimento
                FROM
                    aluno u
                WHERE
                    u.id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, id);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Aluno aluno = new Aluno(
                            resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("codigo"),
                            resultSet.getString("matricula"),
                            resultSet.getDate("data_nascimento").toLocalDate()
                    );
                    return Optional.of(aluno);
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aluno save(Aluno aluno) {
        String query = """
                INSERT INTO aluno (nome, codigo, matricula, data_nascimento)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getEmail());
            statement.setString(3, aluno.getMatricula());
            statement.setDate(4, Date.valueOf(aluno.getDataNascimento()));

            long affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating aluno failed, no rows affected.");
            }

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getInt(1);
                    return new Aluno(id, aluno.getNome(), aluno.getEmail(), aluno.getMatricula(), aluno.getDataNascimento());
                } else {
                    throw new RuntimeException("Creating aluno failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aluno update(Aluno aluno) {
        String query = """
                UPDATE aluno
                SET nome = ?, codigo = ?, matricula = ?, data_nascimento = ?
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getEmail());
            statement.setString(3, aluno.getMatricula());
            statement.setDate(4, Date.valueOf(aluno.getDataNascimento()));
            statement.setLong(5, aluno.getId());

            statement.executeUpdate();

            return aluno;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) {
        String query = """
                DELETE FROM aluno
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