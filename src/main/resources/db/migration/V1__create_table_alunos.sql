CREATE TABLE tb_alunos (
    id BIGSERIAL PRIMARY KEY,
    telefone VARCHAR(255) NOT NULL,
    ativo VARCHAR(255) NOT NULL,
    logradouro VARCHAR(255),          
    cidade VARCHAR(255),       
    estado VARCHAR(255),       
    cep VARCHAR(255)
);