create table matriz (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    pastor_presidente varchar(100) not null,
    vice_presidente varchar(100) not null,
    denominacao varchar(100) not null,
    primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

insert into matriz (id, nome, pastor_presidente, vice_presidente, denominacao)
values (default, 'Igreja Assembléia de Deus em Bonsucesso', 'Jaime Soares', 'Paulo Sergio', 'Assembléia de Deus');

create table filial (
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
    CONSTRAINT fk_filial_matriz FOREIGN KEY (matriz_id) REFERENCES matriz (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

insert into filial (id, matriz_id, nome, pastor_dirigente, data_inauguracao, rua, numero, complemento, bairro, cidade, uf, cep, pais, instagram, telefone, whatsapp, email, cnpj)
values (default, 1, 'Igreja Assembléia de Deus Ebenézer', 'Paulo Cezar', '1967-03-09', 'Gil Gaffre', 64, '', 'Higienópolis', 'Rio de Janeiro', 'RJ', '20763209', 'Brasil', '@adebenezer', '21934987654', '21994320918', 'adebenezer@email.com', '87623976450174');

create table cargo (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

insert into cargo (id, nome) values (default, 'Pastor presidente');
insert into cargo (id, nome) values (default, 'Vice presidente');
insert into cargo (id, nome) values (default, 'Pastor');
insert into cargo (id, nome) values (default, 'Presbítero');
insert into cargo (id, nome) values (default, 'Diácono');
insert into cargo (id, nome) values (default, 'Auxiliar');
insert into cargo (id, nome) values (default, 'Lider');
insert into cargo (id, nome) values (default, 'Músico');
insert into cargo (id, nome) values (default, 'Administrador');

create table departamento (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

insert into departamento (id, nome) values (default, 'Ministerio de louvor');
insert into departamento (id, nome) values (default, 'Ministerio infantil');
insert into departamento (id, nome) values (default, 'Ministerio de jovens');
insert into departamento (id, nome) values (default, 'Ministerio de mulheres');
insert into departamento (id, nome) values (default, 'Ministerio de adolescente');
insert into departamento (id, nome) values (default, 'Ministerio de louvor');
insert into departamento (id, nome) values (default, 'Escola dominical');

create table lancamento_financeiro (
    id bigint not null auto_increment,
    tipo enum('ENTRADA','SAIDA'),
    valor decimal(10,2),
    categoria enum('DIZIMO', 'OFERTA', 'MANUTENCAO', 'OUTROS', 'EVENTO', 'CAMPANHA'),
    data_movimento date not null,
    filial_id bigint not null,
    primary key(id),
    CONSTRAINT fk_lancamento_financeiro_filial FOREIGN KEY (filial_id) REFERENCES filial (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table membro (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(11) not null unique,
    data_batismo date not null,
    status_membro enum('ATIVO', 'INATIVO', 'SUSPENSO', 'EXCLUIDO', 'DESVIADO', 'MUDOU_DE_IGREJA'),
    rua varchar(100) not null,
    numero integer,
    complemento varchar(100),
    bairro varchar(50) not null,
    cidade varchar(50) not null,
    uf varchar(3) not null,
    cep varchar(9) not null,
    pais varchar(50) not null,
    filial_id bigint not null,
    foto_url varchar(100),
    primary key(id),
    CONSTRAINT fk_membro_filial FOREIGN KEY (filial_id) REFERENCES filial (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table nomeacao (
    id bigint not null auto_increment,
    membro_id bigint not null,
    cargo_id bigint not null,
    departamento_id bigint,
    data_posse date not null,
    data_saida date,
    primary key(id),
    CONSTRAINT fk_nomeacao_membro FOREIGN KEY (membro_id) REFERENCES membro (id),
    CONSTRAINT fk_nomeacao_cargo FOREIGN KEY (cargo_id) REFERENCES cargo (id),
    CONSTRAINT fk_nomeacao_departamento FOREIGN KEY (departamento_id) REFERENCES departamento (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table usuario (
    id bigint not null auto_increment,
    usuario varchar(20) not null unique,
    senha varchar(100) not null,
    role_usuario enum('ADMIN', 'SECRETARIO', 'PASTOR'),
    filial_id bigint not null,
    primary key(id),
    CONSTRAINT fk_usuarios_filial FOREIGN KEY (filial_id) REFERENCES filial (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO usuario (id, usuario, senha, role_usuario, filial_id) VALUES (default, 'admin@sistema.com', '$2a$12$JiCkPqnI3OFMD56xG6SeZ.Viv0/TIrBW6hgQ0L5Qvj0rmZfO3pW1q', 'ADMIN', 1);