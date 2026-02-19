CREATE TABLE livro (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    ano_publicacao INT NOT NULL
);

CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE emprestimo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    livro_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE,
    CONSTRAINT fk_livro FOREIGN KEY (livro_id) REFERENCES livro(id) ON DELETE CASCADE,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);