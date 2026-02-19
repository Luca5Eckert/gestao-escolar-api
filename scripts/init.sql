
CREATE TABLE livro (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    ano_publicacao INT NOT NULL
);

CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE emprestimo (
    id BIGSERIAL PRIMARY KEY,
    livro_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    data_emprestimo DATE NOT NULL DEFAULT CURRENT_DATE,
    data_devolucao DATE,
    CONSTRAINT fk_livro FOREIGN KEY (livro_id) REFERENCES livro(id) ON DELETE CASCADE,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);