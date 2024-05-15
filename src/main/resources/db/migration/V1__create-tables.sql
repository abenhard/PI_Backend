create table pessoas(
    id serial not null primary key,
    nome varchar(50) not null,
    email varchar(50),
    telefone varchar(20),
    whatsapp varchar(20),
    cpf varchar(20) not null unique,

    complemento varchar(150),
    bairro varchar(100) NOT NULL,
    rua varchar(100) NOT NULL,
    cep varchar(9) NOT NULL,
    numero varchar(15) NOT NULL,
    cidade varchar(100) NOT NULL,
    estado varchar(100) NOT NULL
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
CREATE table ordens_de_servico(
    id serial not null primary key,
    pessoa_id integer NOT NULL,
    funcionario_id integer not null,
    tipo_de_servico varchar(30) not null,
    status varchar(30) not null,
    descricao_problema varchar(150) not null,
    produto_extra varchar(150),
    custo_total decimal,
    relatorio_tecnico VARCHAR(300),
    data_criacao timestamp not null,
    data_previsao date,
    data_entrega date,
    imagem_caminho VARCHAR(100),
    localizacao VARCHAR(100),
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id),
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);
