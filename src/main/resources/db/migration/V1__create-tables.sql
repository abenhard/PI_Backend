create table estados(
    id serial not null primary key,
    nome varchar(50) not null
);
create table cidades(
    id serial not null primary key,
    estado_id integer not null,
    nome varchar(100) not null,
    foreign key (estado_id) references estados(id)
);
create table pessoas(
    id serial not null primary key,
    nome varchar(50) not null,
    email varchar(50),
    telefone varchar(20),
    whatsapp varchar(20),
    cpf varchar(20) not null unique
  );
CREATE TABLE enderecos (
                           id serial NOT NULL PRIMARY KEY,
                           pessoa_id integer NOT NULL,
                           complemento varchar(150),
                           bairro varchar(100) NOT NULL,
                           rua varchar(100) NOT NULL,
                           cep varchar(9) NOT NULL,
                           numero varchar(15) NOT NULL,
                           cidade_id integer NOT NULL,
                           FOREIGN KEY (pessoa_id) REFERENCES pessoas(id),
                           FOREIGN KEY (cidade_id) REFERENCES cidades(id)
);
CREATE TABLE cargos(
    id serial not null primary key,
    nome varchar(50) not null
);
CREATE TABLE funcionarios(
    id serial not null primary key,
    pessoa_id integer not null,
    cargo_id integer not null,
    login varchar(60) not null unique,
    senha varchar(60) not null,
    ativo bool not null,
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id),
    FOREIGN KEY (cargo_id) references cargos(id)
);
CREATE table status(
    id serial not null primary key,
    nome varchar(30) not null
);
CREATE TABLE tipos_de_servico(
    id serial not null primary key,
    nome varchar(30) not null,
    descricao varchar(150),
    custo_base decimal
);
CREATE table ordens_de_servico(
    id serial not null primary key,
    pessoa_id integer NOT NULL,
    funcionario_id integer not null,
    tipo_de_servico_id integer not null,
    status_id integer not null,
    descricao_problema varchar(150) not null,
    produto_extra varchar(150),
    custo_total decimal,
    relatorio_tecnico VARCHAR(300),
    data_criacao timestamp not null,
    data_previsao date,
    data_entrega date,
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id),
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id),
    FOREIGN KEY (tipo_de_servico_id) REFERENCES tipos_de_servico(id),
    FOREIGN KEY (status_id) references  status(id)
);
