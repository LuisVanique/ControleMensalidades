CREATE TABLE tb_mensalidades (
    id BIGSERIAL PRIMARY KEY,
    valor DECIMAL(10, 2) NOT NULL,
    data_vencimento DATE NOT NULL,
    aluno_id BIGINT NOT NULL, 
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES tb_alunos(id) ON DELETE CASCADE
);