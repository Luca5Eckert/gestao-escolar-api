package com.weg.biblioteca.repository;

import com.weg.biblioteca.config.Conexao;
import com.weg.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmprestimoRepository {

    public List<Emprestimo> getAll() {
        String query = """
                SELECT
                    e.id,
                    e.livro_id,
                    e.usuario_id,
                    e.data_emprestimo,
                    e.data_devolucao
                FROM
                    emprestimo e
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()
        ) {
            List<Emprestimo> contatoes = new ArrayList<>();

            while (resultSet.next()) {
                Emprestimo emprestimo = new Emprestimo(
                        resultSet.getInt("id"),
                        resultSet.getInt("livro_id"),
                        resultSet.getInt("usuario_id"),
                        resultSet.getDate("data_emprestimo").toLocalDate(),
                        resultSet.getDate("data_devolucao").toLocalDate()
                );
                contatoes.add(emprestimo);
            }

            return contatoes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<Emprestimo> findById(int id) {
        String query = """
                SELECT
                    e.id,
                    e.livro_id,
                    e.usuario_id,
                    e.data_emprestimo,
                    e.data_devolucao
                FROM
                    emprestimo e
                WHERE
                    e.id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, id);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Emprestimo emprestimo = new Emprestimo(
                            resultSet.getInt("id"),
                            resultSet.getInt("livro_id"),
                            resultSet.getInt("usuario_id"),
                            resultSet.getDate("data_emprestimo").toLocalDate(),
                            resultSet.getDate("data_devolucao").toLocalDate()
                    );
                    return Optional.of(emprestimo);
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Emprestimo save(Emprestimo emprestimo) {
        String query = """
                INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao)
                VALUES (?, ?)
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {

            statement.setInt(1, emprestimo.getLivroId());
            statement.setInt(2, emprestimo.getUsuarioId());
            statement.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            statement.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating emprestimo failed, no rows affected.");
            }

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new Emprestimo(
                            id,
                            emprestimo.getLivroId(),
                            emprestimo.getUsuarioId(),
                            emprestimo.getDataEmprestimo(),
                            emprestimo.getDataDevolucao()
                    );
                } else {
                    throw new RuntimeException("Creating emprestimo failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Emprestimo update(Emprestimo emprestimo) {
        String query = """
                UPDATE emprestimo
                SET data_devolucao = ?
                WHERE id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setDate(1, Date.valueOf(emprestimo.getDataDevolucao()));
            statement.setInt(2, emprestimo.getId());

            statement.executeUpdate();

            return emprestimo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        String query = """
                DELETE FROM emprestimo
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

    public boolean existsByLivroIdAndDataDevolucaoIsNull(int i) {
        String query = """
                SELECT COUNT(*)
                FROM emprestimo
                WHERE livro_id = ? AND data_devolucao IS NULL
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, i);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Emprestimo> findByUsuarioId(int usuarioId) {
        String query = """
                SELECT
                    e.id,
                    e.livro_id,
                    e.usuario_id,
                    e.data_emprestimo,
                    e.data_devolucao
                FROM
                    emprestimo e
                WHERE
                    e.usuario_id = ?
                """;

        try (Connection connection = Conexao.toInstance();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, usuarioId);

            try (var resultSet = statement.executeQuery()) {
                List<Emprestimo> emprestimos = new ArrayList<>();

                while (resultSet.next()) {
                    Emprestimo emprestimo = new Emprestimo(
                            resultSet.getInt("id"),
                            resultSet.getInt("livro_id"),
                            resultSet.getInt("usuario_id"),
                            resultSet.getDate("data_emprestimo").toLocalDate(),
                            resultSet.getDate("data_devolucao").toLocalDate()
                    );
                    emprestimos.add(emprestimo);
                }

                return emprestimos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
