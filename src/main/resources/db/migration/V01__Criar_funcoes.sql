create table funcao(
        id bigint(20) primary key auto_increment,
        nome varchar(100) not null
)engine=InnoDB default charset=utf8;

insert into funcao (id, nome) values (1, "Pastor Presidente");
insert into funcao (id, nome) values (2, "Pastor");
insert into funcao (id, nome) values (3, "Presbítero");
insert into funcao (id, nome) values (4, "Diácono");
insert into funcao (id, nome) values (5, "Auxiliar");
insert into funcao (id, nome) values (6, "Líder Juventude");