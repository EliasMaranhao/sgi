set foreign_key_checks = 0;

delete from ministerio;
delete from funcao;
delete from igreja;
delete from membro;
delete from parente;

set foreign_key_checks = 1;

alter table ministerio auto_increment = 1;
alter table funcao auto_increment = 1;
alter table igreja auto_increment = 1;
alter table membro auto_increment = 1;
alter table parente auto_increment = 1;

insert ignore into ministerio (id, nome, data_inauguracao, data_cadastro, data_atualizacao, presidente, vice_presidente) values (1, 'Assémbleia De Deus Em Bonsucesso', '2025-01-01', utc_timestamp, utc_timestamp, 'Jaime Soares', 'Paulo Alguma Coisa');

insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (1, 'Pastor presidente', utc_timestamp, utc_timestamp);
insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (2, 'Pastor', utc_timestamp, utc_timestamp);
insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (3, 'Presbítero', utc_timestamp, utc_timestamp);
insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (4, 'Diácono', utc_timestamp, utc_timestamp);
insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (5, 'Auxiliar', utc_timestamp, utc_timestamp);
insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (6, 'Líder de jovens', utc_timestamp, utc_timestamp);
insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (7, 'Músico', utc_timestamp, utc_timestamp);
insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (8, 'Secretário', utc_timestamp, utc_timestamp);
insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (9, 'Tesoureiro', utc_timestamp, utc_timestamp);
insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (10, 'Líder de adolescente', utc_timestamp, utc_timestamp);
insert ignore into funcao (id, nome, data_cadastro, data_atualizacao) values (11, 'Administrador', utc_timestamp, utc_timestamp);

insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (1, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Em Bonsucesso', 1, 'MATRIZ');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (2, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Ebenezer', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (3, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Lirios Do Vale', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (4, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Monte das Oliveiras', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (5, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Alvorada', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (6, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Val de Jaboque', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (7, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Engenheiro Pedreira', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (8, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Chapadao', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (9, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Miguel Pereira', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (10, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Filadelfia', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (11, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Monte Moriá', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (12, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Renascer', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (13, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Barra da Tijuca', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (14, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Fonte da Vida', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (15, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Monte Sinai', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (16, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Rosa de Saron', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (17, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Beréia', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (18, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Gileade', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (19, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Nova Canaã', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (20, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Vale da Alvorada', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (21, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Betânia', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (22, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Jacarepaguá', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (23, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Nova Galiléia', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (24, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Betel', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (25, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Maanaim', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (26, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Peniel', 1, 'CONGREGACAO');
insert ignore into igreja (id, data_inauguracao, data_cadastro, data_atualizacao, nome, ministerio_id, status_enum) values (27, '2024-09-22', utc_timestamp, utc_timestamp, 'Assembleía de Deus Cachambi', 1, 'CONGREGACAO');

insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (1, '1976-03-03', 1, 'Elias Maranhão Antonio', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (2, '1952-05-01', 2, 'Carlos Antonio', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (3, '2022-03-05', 2, 'Laudiceia Maranhão', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (4, '2022-03-05', 2, 'Laudecir Maranhão Antonio', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (5, '2022-03-05', 2, 'Eliziel Maranhão Antonio', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (6, '2022-03-05', 2, 'Eliezer Maranhão Antonio', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (7, '2022-03-05', 4, 'Laudiléia Maranhão Antonio', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (8, '2022-03-05', 4, 'Jurassir Silva', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (9, '2022-03-05', 4, 'Milene Barros', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (10, '2022-03-05', 5, 'Augusto Nunes', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (11, '2022-03-05', 5, 'Amanda Fernandez', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (12, '2022-03-05', 5, 'Creuza Silva Borges', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (13, '2022-03-05', 6, 'Jurandir Homato Mendes', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);
insert ignore into membro (id, data_nascimento, igreja_id, nome, data_batismo, data_conversao, numero, cep, bairro, cidade, complemento, estado, rua, data_cadastro, data_atualizacao) values (14, '2022-03-05', 6, 'Jussilene Maria de Jesus', '2019-02-01', '2018-09-23', 1255, '20745100', 'Engenho de Dentro', 'Rio de Janeiro', 'Apto 103 - BL06', 'Rio de Janeiro', 'Borja Reis', utc_timestamp, utc_timestamp);

insert ignore into parente (id, membro_id, nome, membrezia, parentesco, celular, telefone, email, data_nascimento, sexo, grau_instrucao, profissao, mora_na_mesma_casa, data_cadastro, data_atualizacao) values (1, 1, 'Carlos Antonio', 1, 'PAI', '21943230989', '', 'email@.com', '2024-07-12', 'MASCULINO', 'DOUTORADO', 'Eletricista', 1, utc_timestamp, utc_timestamp);