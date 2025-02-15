create table ministerio (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    data_inauguracao datetime not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    presidente varchar(100) not null,
    vice_presidente varchar(100) not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

create table igreja (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    data_inauguracao datetime not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    ministerio_id bigint not null,
    status_enum enum('MATRIZ', 'CONGREGACAO') not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

alter table igreja add constraint fk_igreja_ministerio foreign key(ministerio_id) references ministerio(id);

create table membro (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    data_nascimento datetime not null,
    igreja_id bigint not null,
    data_batismo datetime not null,
    data_conversao datetime not null,
    rua varchar(200) not null,
    numero varchar(5) not null,
    complemento varchar(100),
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    estado varchar(100) not null,
    cep varchar(10) not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

alter table membro add constraint fk_membro_igreja foreign key(igreja_id) references igreja(id);

create table funcao (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

create table cargo (
    id bigint not null auto_increment,
    funcao_id bigint not null,
    data_posse datetime not null,
    data_desligamento datetime,
    observacao varchar(300),
    membro_id bigint not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

alter table cargo add constraint fk_cargo_membro foreign key(membro_id) references membro(id);
alter table cargo add constraint fk_cargo_funcao foreign key(funcao_id) references funcao(id);

create table contato (
    id bigint not null auto_increment,
    tipo enum('EMAIL', 'TELEFONE') not null,
    valor varchar(50),
    membro_id bigint not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

alter table contato add constraint fk_contato_membro foreign key(membro_id) references membro(id);

create table parente (
    id bigint not null auto_increment,
    membro_id bigint not null,
    nome varchar(100) not null,
    membrezia tinyint not null,
    parentesco enum('PAI', 'MAE', 'IRMAO', 'IRMA', 'PRIMO', 'PRIMA', 'AVO', 'PADRASTO', 'MADRASTA', 'FILHO', 'FILHA', 'OUTROS'),
    celular varchar(12),
    telefone varchar(12),
    email varchar(100),
    data_nascimento datetime not null,
    sexo enum('MASCULINO', 'FEMININO') not null,
    grau_instrucao enum('SEM_INSTRUCAO', 'ENSINO_FUNDAMENTAL_INCOMPLETO', 'ENSINO_FUNDAMENTAL_COMPLETO', 'ENSINO_MEDIO_COMPLETO', 'ENSINO_MEDIO_INCOMPLETO', 'ENSINO_SUPERIOR_COMPLETO', 'ENSINO_SUPERIOR_INCOMPLETO', 'MESTRADO', 'DOUTORADO'),
    profissao varchar(100),
    mora_na_mesma_casa tinyint not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

alter table parente add constraint fk_parente_membro foreign key(membro_id) references membro(id);