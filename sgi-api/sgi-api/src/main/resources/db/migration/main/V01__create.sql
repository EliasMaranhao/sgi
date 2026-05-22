create table public.matriz (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    pastor_presidente varchar(100) not null,
    vice_presidente varchar(100) not null,
    denominacao varchar(100) not null,
    schema_name VARCHAR(12) NOT NULL UNIQUE,
    primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table public.filial (
    id bigint not null  auto_increment,
    matriz_id bigint not null,
    nome varchar(100) not null,
    pastor_dirigente varchar(100) not null,
    data_inauguracao date,
    rua varchar(100) not null,
    numero integer,
    complemento varchar(100),
    bairro varchar(50) not null,
    cidade varchar(50) not null,
    uf varchar(3) not null,
    cep varchar(9) not null,
    pais varchar(50) not null,
    instagram varchar(100),
    telefone varchar(12) not null,
    whatsapp varchar(12) not null,
    email varchar(255) not null unique,
    cnpj varchar(14)not null unique,
    primary key(id),
    CONSTRAINT fk_filial_matriz FOREIGN KEY (matriz_id) REFERENCES public.matriz (id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_matriz_schema ON public.matriz(schema_name);

