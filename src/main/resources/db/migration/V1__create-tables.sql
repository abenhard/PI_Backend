create table estados(
    id serial not null primary key,
    nome varchar(50) not null
);
create table cidades(
    id serial not null primary key,
    id_estado integer not null,
    nome varchar(100) not null,
    foreign key (id_estado) references estados(id)
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
    complemento varchar(150),
    bairro varchar(100) NOT NULL,
    rua varchar(100)NOT NULL,
    cep varchar(9) NOT NULL,
    numero varchar(15) NOT NULL,
    cidade_id integer NOT NULL,
    FOREIGN KEY (cidade_id) REFERENCES cidades(id)
);
CREATE TABLE pessoas_enderecos(
    pessoa_id integer NOT NULL,
    endereco_id integer NOT NULL,
    FOREIGN KEY(pessoa_id) references pessoas(id),
    FOREIGN KEY(endereco_id) references enderecos(id)
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
    data_de_entrada date not null,
    data_de_saida date,
    salario decimal not null,
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
    descricao varchar(150) not null,
    cpu varchar(50),
    placa_de_video varchar(50),
    placa_mae varchar(50),
    memoria_ram varchar(50),
    produto_extra varchar(150),
    custo_total decimal,
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id),
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id),
    FOREIGN KEY (tipo_de_servico_id) REFERENCES tipos_de_servico(id),
    FOREIGN KEY (status_id) references  status(id)
);
