create database lojadb;
use lojadb;

create table usuarios (
	USUARIO_ID int auto_increment primary key,
	NOME varchar(40) not null,
	CPF varchar(11) not null,
	EMAIL varchar(40) not null,
	SENHA varchar(40) not null,
	TELEFONE char(12) not null);

create table enderecos (
	ENDERECO_ID int auto_increment primary key,
	USUARIO_ID int not null,
	CEP varchar(8) not null,
	RUA varchar(100) not null,
	NUMERO int not null,
	COMPLEMENTO varchar(100),
	REFERENCIA varchar(100),
	foreign key (USUARIO_ID) references usuarios(USUARIO_ID));

create table tamanhos (
	TAMANHO_ID int auto_increment primary key,
	TAMANHO varchar(2) not null);

create table tecidos (
	TECIDO_ID int auto_increment primary key,
	TECIDO varchar(40) not null);

create table sexos (
	SEXO_ID int auto_increment primary key,
	SEXO varchar(10) not null);

create table roupas (
	ROUPA_ID int auto_increment primary key,
	TAMANHO_ID int not null,
	TECIDO_ID int not null,
	SEXO_ID int not null,
	QUANTIDADE int not null,
	MARCA varchar(20) not null,
	MODELO varchar(40) not null,
	COR varchar(20) not null,
	DESCRICAO varchar(100) not null,
	VALOR decimal(6,2) not null,
	foreign key(TAMANHO_ID) references tamanhos(TAMANHO_ID),
	foreign key(TECIDO_ID) references tecidos(TECIDO_ID),
	foreign key(SEXO_ID) references sexos(SEXO_ID));

create table pagamentos (
	PAGAMENTO_ID int auto_increment primary key,
	PAGAMENTO varchar(10) not null);

create table compras (
	COMPRA_ID int auto_increment primary key,
	USUARIO_ID int not null,
	ROUPA_ID int not null,
	PAGAMENTO_ID int not null,
	QUANTIDADE int not null,
	foreign key(USUARIO_ID) references usuarios(USUARIO_ID),
	foreign key(ROUPA_ID) references roupas(ROUPA_ID),
	foreign key(PAGAMENTO_ID) references pagamentos(PAGAMENTO_ID));

create table recibos (
	RECIBO_ID int auto_increment primary key,
	COMPRA_ID int,
	DATA date,
	foreign key(COMPRA_ID) references compras(COMPRA_ID));

insert into usuarios (NOME, CPF, EMAIL, SENHA, TELEFONE) values
('ADMIN', '00000000000', 'admin@admin.com', 'admin', '00000000000'),
('GABRIEL', '78912345610', 'gabriel@gmail.com', 'spfc', '11976887687'),
('KARINE', '85589345610', 'karine@gmail.com', 'nulo', '11996317687'),
('RODRIGO', '87742345610', 'rodrigo@gmail.com', 'flamengo', '11924117687');

insert into enderecos (USUARIO_ID, CEP, RUA, NUMERO, COMPLEMENTO, REFERENCIA) values
(1, '00000000', 'Rua Fatec Zona Leste', 10000, 'Portao marrom', 'Proximo ao terminal AE Carvalho'),
(2, '96968370', 'Rua Morumbi', 20, 'Muitos titulos', 'Fica no Morumbi'),
(3, '08948678', 'Rua Grande Craque Neto', 30, 'Portao do curinthia', 'Proximo daquele lugar'),
(4, '59355472', 'Rua Maracana', 40, 'Eh o Flamengo', 'La no RJ');

insert into tamanhos (TAMANHO) values 
('PP'), ('P'), ('M'), ('G'), ('GG');

insert into tecidos (TECIDO) values 
('ALGODAO_ORGANICO'), ('CANHAMO'), ('FIBRA_DE_BANANEIRA'), ('FIBRA_DE_LARANJA'), ('FIBRA_DE_SOJA'), ('LENPUR'), ('LINHO'), ('LIOCEL'), ('MODAL'), ('PINATEX'), ('POLIAMIDA_BIODEGRADAVEL'), ('QMILK');

insert into sexos (SEXO) values 
('Masculino'), ('Feminino'), ('Unissex');

insert into roupas (TAMANHO_ID, TECIDO_ID, SEXO_ID, QUANTIDADE, MARCA, MODELO, COR, DESCRICAO, VALOR) values
(1, 1, 1, 60, 'Nike', 'Calca ecologica', 'Azul', 'Uma bela calca. Ecologica e agradavel de usar!', 99);

insert into pagamentos (PAGAMENTO) values
('Boleto'), ('Credito'), ('Debito');

insert into compras (USUARIO_ID, ROUPA_ID, PAGAMENTO_ID, QUANTIDADE) values
(1, 1, 1, 30);

insert into recibos(COMPRA_ID, DATA) values
(1, '2020-11-19');

select * from usuarios;
select * from enderecos;
select * from tamanhos;
select * from tecidos;
select * from sexos;
select * from roupas;
select * from pagamentos;
select * from compras;
select * from recibos;