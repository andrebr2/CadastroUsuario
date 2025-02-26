create table tb_ddd (codigo_area integer not null check ((codigo_area<=99) and (codigo_area>=10)), primary key (codigo_area));
create table tb_email (id bigint generated by default as identity, usuario_id bigint not null, endereco_email varchar(255) not null, primary key (id));
create table tb_endereco (id bigint generated by default as identity, cep varchar(255) not null, primary key (id));
create table tb_telefone (codigo_area integer not null, id bigint generated by default as identity, usuario_id bigint not null, nro_telefone varchar(255) not null, primary key (id));
create table tb_usuario (endereco_id bigint, id bigint generated by default as identity, nro_endereco varchar(20), complemento varchar(100), nome varchar(100) not null, sobrenome varchar(100) not null, foto_perfil varchar(255), genero varchar(255) check (genero in ('MASCULINO','FEMININO','NAO_BINARIO','PREFIRO_NAO_INFORMAR','OUTRO')), primary key (id));
alter table if exists tb_email add constraint FKktmsgbw4kt4sga8h1repnq8lv foreign key (usuario_id) references tb_usuario;
alter table if exists tb_telefone add constraint FKisp03pxpfk4rwhulj4jat2ri7 foreign key (codigo_area) references tb_ddd;
alter table if exists tb_telefone add constraint FKlf26qdq78s365njl6nfj1ncn2 foreign key (usuario_id) references tb_usuario;
alter table if exists tb_usuario add constraint FK54a7ohfvgkof6wn17lnfmrgc7 foreign key (endereco_id) references tb_endereco;
INSERT INTO tb_endereco(cep) VALUES('48918900');
INSERT INTO tb_endereco(cep) VALUES('39270030');
INSERT INTO tb_endereco(cep) VALUES('65091078');
INSERT INTO tb_endereco(cep) VALUES('68460178');
INSERT INTO tb_endereco(cep) VALUES('77001902');
INSERT INTO tb_endereco(cep) VALUES('68030041');
INSERT INTO tb_endereco(cep) VALUES('73850000');
INSERT INTO tb_endereco(cep) VALUES('86807315');
INSERT INTO tb_endereco(cep) VALUES('49790000');
INSERT INTO tb_endereco(cep) VALUES('96400011');
INSERT INTO tb_endereco(cep) VALUES('16201544');
INSERT INTO tb_endereco(cep) VALUES('98783320');
INSERT INTO tb_endereco(cep) VALUES('87280990');
INSERT INTO tb_endereco(cep) VALUES('89205301');
INSERT INTO tb_endereco(cep) VALUES('12870959');
INSERT INTO tb_endereco(cep) VALUES('29194436');
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Simão', 'Benites', 'MASCULINO', NULL, '504', 'CASA', 1);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Avelino', 'Câmara', 'MASCULINO', NULL, '1032', 'APTO 305', 2);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Cícero', 'Neves', 'MASCULINO', NULL, '106', 'CASA DOS FUNDOS', 3);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Elano', 'Montenegro', 'MASCULINO', NULL, '2097', 'CASA', 4);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Fabio', 'Campos', 'MASCULINO', NULL, '901', 'APTO 31', 5);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Raul', 'Casanova', 'MASCULINO', NULL, '308B', 'BLOCO C', 6);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Ana', 'Palhares', 'FEMININO', NULL, '1606', 'CASA', 7);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Manuela', 'Quintana', 'FEMININO', NULL, '23', 'CASA', 8);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Fabiana', 'Duarte', 'FEMININO', NULL, '605', 'CASA', 9);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Sandra', 'Peres', 'FEMININO', NULL, '1810', 'APTO 104', 10);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Mateus', 'Santana', 'MASCULINO', NULL, '208', 'CASA', 11);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Alessandra', 'Abreu', 'FEMININO', NULL, '1048', 'APTO 901 BLOCO B', 12);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Renata', 'Godói', 'FEMININO', NULL, '592', 'CASA', 13);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Aldo', 'Meireles', 'MASCULINO', NULL, '368', 'CASA', 14);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Adônis', 'Oliveira', 'MASCULINO', NULL, '1407', 'APTO 101', 15);
INSERT INTO tb_usuario(nome, sobrenome, genero, foto_perfil, nro_endereco, complemento, endereco_id) VALUES('Maira', 'Sobrinho', 'FEMININO', NULL, '777', 'CASA', 16);
INSERT INTO tb_ddd(codigo_area) VALUES(74);
INSERT INTO tb_ddd(codigo_area) VALUES(38);
INSERT INTO tb_ddd(codigo_area) VALUES(98);
INSERT INTO tb_ddd(codigo_area) VALUES(94);
INSERT INTO tb_ddd(codigo_area) VALUES(63);
INSERT INTO tb_ddd(codigo_area) VALUES(93);
INSERT INTO tb_ddd(codigo_area) VALUES(61);
INSERT INTO tb_ddd(codigo_area) VALUES(43);
INSERT INTO tb_ddd(codigo_area) VALUES(79);
INSERT INTO tb_ddd(codigo_area) VALUES(53);
INSERT INTO tb_ddd(codigo_area) VALUES(18);
INSERT INTO tb_ddd(codigo_area) VALUES(54);
INSERT INTO tb_ddd(codigo_area) VALUES(44);
INSERT INTO tb_ddd(codigo_area) VALUES(47);
INSERT INTO tb_ddd(codigo_area) VALUES(12);
INSERT INTO tb_ddd(codigo_area) VALUES(28);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('98090-9076', 74, 1);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('96165-1312', 38, 2);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('98954-0849', 98, 3);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('96041-0111', 94, 4);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('96256-1630', 63, 5);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('98030-5230', 93, 6);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('96060-4189', 61, 7);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('98140-4998', 43, 8);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('97894-8201', 79, 9);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('96320-4784', 53, 10);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('96468-6616', 18, 11);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('99885-3837', 54, 12);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('98841-9811', 44, 13);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('99356-1007', 47, 14);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('97047-7545', 12, 15);
INSERT INTO tb_telefone(nro_telefone, codigo_area, usuario_id) VALUES('96183-9932', 28, 16);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('benitesim@yahoo.com', 1);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('lino28cam@outlook.com', 2);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('cineves@gmail.com', 3);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('elmontenegro@gmail.com', 4);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('fabcampos@icloud.com', 5);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('rnova@gmail.com', 6);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('apalhares@yahoo.com', 7);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('manuquintana@icloud.com', 8);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('fabiduarte@outlook.com', 9);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('saperes@gmail.com', 10);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('mateus23santana@gmail.com', 11);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('ale_abreu@yahoo.com', 12);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('renatagodoi@gmail.com', 13);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('almeireles@outlook.com', 14);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('adonis_oli@gmail.com', 15);
INSERT INTO tb_email(endereco_email, usuario_id) VALUES('masob@yahoo.com', 16);
