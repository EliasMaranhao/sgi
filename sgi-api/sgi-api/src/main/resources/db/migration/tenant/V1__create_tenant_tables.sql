CREATE TABLE cargo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Usando sintaxe padrão estável para inserts em lote
INSERT INTO cargo (nome) VALUES 
('Pastor presidente'),
('Vice presidente'),
('Pastor'),
('Presbítero'),
('Diácono'),
('Auxiliar'),
('Lider'),
('Músico'),
('Administrador');

CREATE TABLE departamento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO departamento (nome) VALUES 
('Ministério de louvor'), -- Ajustado acento
('Ministério infantil'),
('Ministério de jovens'),
('Ministério de mulheres'),
('Ministério de adolescentes'),
('Escola dominical'); -- Removido o item duplicado

CREATE TABLE lancamento_financeiro (
    id BIGINT NOT NULL AUTO_INCREMENT,
    tipo ENUM('ENTRADA','SAIDA') NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    categoria ENUM('DIZIMO', 'OFERTA', 'MANUTENCAO', 'OUTROS', 'EVENTO', 'CAMPANHA') NOT NULL,
    data_movimento DATE NOT NULL,
    filial_id BIGINT NOT NULL, -- Mantido semanticamente para o Spring filtrar na Toolbar, sem FK física transbanco
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Adicionado índice para acelerar as buscas financeiras por filial que o Angular vai exibir
CREATE INDEX idx_lancamento_filial ON lancamento_financeiro(filial_id);

CREATE TABLE membro (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_batissimo DATE NOT NULL,
    status_membro ENUM('ATIVO', 'INATIVO', 'SUSPENSO', 'EXCLUIDO', 'DESVIADO', 'MUDOU_DE_IGREJA') NOT NULL,
    rua VARCHAR(100) NOT NULL,
    numero INT,
    complemento VARCHAR(100),
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    uf VARCHAR(3) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    pais VARCHAR(50) NOT NULL,
    filial_id BIGINT NOT NULL, -- Mantido logicamente para escopo de dados
    foto_url VARCHAR(255),     -- Ajustado de 100 para 255 prevendo armazenamento em Cloud Storage (S3/GCS)
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Adicionado índice para acelerar a listagem de membros por filial na Toolbar do Angular
CREATE INDEX idx_membro_filial ON membro(filial_id);

CREATE TABLE nomeacao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    membro_id BIGINT NOT NULL,
    cargo_id BIGINT NOT NULL,
    departamento_id BIGINT,
    data_posse DATE NOT NULL,
    data_saida DATE,
    PRIMARY KEY (id),
    CONSTRAINT fk_nomeacao_membro FOREIGN KEY (membro_id) REFERENCES membro (id) ON DELETE CASCADE,
    CONSTRAINT fk_nomeacao_cargo FOREIGN KEY (cargo_id) REFERENCES cargo (id),
    CONSTRAINT fk_nomeacao_departamento FOREIGN KEY (departamento_id) REFERENCES departamento (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;