ALTER TABLE tb_instrutores 
ADD COLUMN nome VARCHAR(255) NOT NULL,
ADD COLUMN telefone VARCHAR(20) UNIQUE;