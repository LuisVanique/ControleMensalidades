CREATE TABLE tb_parametros (
    id BIGSERIAL PRIMARY KEY,
    descricaoParametro VARCHAR(255),
    valorParametro VARCHAR(255),
    ativo VARCHAR(1) DEFAULT 'S'
);