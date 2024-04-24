-- Estados
INSERT INTO estados (nome)
VALUES ('Acre'),
       ('Alagoas'),
       ('Amapá'),
       ('Amazonas'),
       ('Bahia'),
       ('Ceará'),
       ('Distrito Federal'),
       ('Espírito Santo'),
       ('Goiás'),
       ('Maranhão'),
       ('Mato Grosso'),
       ('Mato Grosso do Sul'),
       ('Minas Gerais'),
       ('Pará'),
       ('Paraíba'),
       ('Paraná'),
       ('Pernambuco'),
       ('Piauí'),
       ('Rio de Janeiro'),
       ('Rio Grande do Norte'),
       ('Rio Grande do Sul'),
       ('Rondônia'),
       ('Roraima'),
       ('Santa Catarina'),
       ('São Paulo'),
       ('Sergipe'),
       ('Tocantins');

-- Cidades
INSERT INTO cidades (estado_id, nome)
VALUES
-- Acre
(1, 'Rio Branco'),
(1, 'Cruzeiro do Sul'),
(1, 'Sena Madureira'),
-- Alagoas
(2, 'Maceió'),
(2, 'Arapiraca'),
(2, 'Palmeira dos Índios'),
-- Amapá
(3, 'Macapá'),
(3, 'Santana'),
(3, 'Laranjal do Jari'),
-- Amazonas
(4, 'Manaus'),
(4, 'Parintins'),
(4, 'Itacoatiara'),
-- Bahia
(5, 'Salvador'),
(5, 'Feira de Santana'),
(5, 'Vitória da Conquista'),
-- Ceará
(6, 'Fortaleza'),
(6, 'Caucaia'),
(6, 'Juazeiro do Norte'),
-- Distrito Federal
(7, 'Brasília'),
(7, 'Ceilândia'),
(7, 'Taguatinga'),
-- Espírito Santo
(8, 'Vitória'),
(8, 'Vila Velha'),
(8, 'Cariacica'),
-- Goiás
(9, 'Goiânia'),
(9, 'Aparecida de Goiânia'),
(9, 'Anápolis'),
-- Maranhão
(10, 'São Luís'),
(10, 'Imperatriz'),
(10, 'Timon'),
-- Mato Grosso
(11, 'Cuiabá'),
(11, 'Várzea Grande'),
(11, 'Rondonópolis'),
-- Mato Grosso do Sul
(12, 'Campo Grande'),
(12, 'Dourados'),
(12, 'Três Lagoas'),
-- Minas Gerais
(13, 'Belo Horizonte'),
(13, 'Contagem'),
(13, 'Uberlândia'),
-- Pará
(14, 'Belém'),
(14, 'Ananindeua'),
(14, 'Santarém'),
-- Paraíba
(15, 'João Pessoa'),
(15, 'Campina Grande'),
(15, 'Santa Rita'),
-- Paraná
(16, 'Curitiba'),
(16, 'Londrina'),
(16, 'Maringá'),
-- Pernambuco
(17, 'Recife'),
(17, 'Jaboatão dos Guararapes'),
(17, 'Olinda'),
-- Piauí
(18, 'Teresina'),
(18, 'Parnaíba'),
(18, 'Picos'),
-- Rio de Janeiro
(19, 'Rio de Janeiro'),
(19, 'São Gonçalo'),
(19, 'Duque de Caxias'),
-- Rio Grande do Norte
(20, 'Natal'),
(20, 'Mossoró'),
(20, 'Parnamirim'),
-- Rio Grande do Sul
(21, 'Porto Alegre'),
(21, 'Caxias do Sul'),
(21, 'Pelotas'),
-- Rondônia
(22, 'Porto Velho'),
(22, 'Ji-Paraná'),
(22, 'Ariquemes'),
-- Roraima
(23, 'Boa Vista'),
(23, 'Caracaraí'),
(23, 'Rorainópolis'),
-- Santa Catarina
(24, 'Florianópolis'),
(24, 'Joinville'),
(24, 'Blumenau'),
-- São Paulo
(25, 'São Paulo'),
(25, 'Guarulhos'),
(25, 'Campinas'),
-- Sergipe
(26, 'Aracaju'),
(26, 'Nossa Senhora do Socorro'),
(26, 'Lagarto'),
-- Tocantins
(27, 'Palmas'),
(27, 'Araguaína'),
(27, 'Gurupi');

-- Pessoas
INSERT INTO pessoas (nome, email, telefone, whatsapp, cpf)
VALUES ('João Silva', 'joao@example.com', '123456789', '123456789', '123.456.789-10'),
       ('Maria Oliveira', 'maria@example.com', '987654321', '987654321', '987.654.321-10'),
       ('Carlos Souza', 'carlos@example.com', '111222333', '111222333', '111.222.333-10'),
       ('Ana Santos', 'ana@example.com', '444555666', '444555666', '444.555.666-10');

-- Endereços
INSERT INTO enderecos (pessoa_id, complemento, bairro, rua, cep, numero, cidade_id)
VALUES (1, 'Apto 101', 'Centro', 'Rua A', '12345-678', '123', 1),
       (2, 'Casa', 'Jardim', 'Rua B', '98765-432', '456', 2),
       (3, 'Sala 10', 'Centro', 'Rua C', '11111-222', '789', 3),
       (4, 'Bloco B', 'Periferia', 'Rua D', '44444-555', '1011', 4);

-- Cargos
INSERT INTO cargos (nome)
VALUES ('ADMIN'),
       ('TECNICO'),
       ('ATENDENTE');

-- Funcionários
INSERT INTO funcionarios (pessoa_id, cargo_id, login, senha, ativo)
VALUES (1, 1, 'admin', '123', true),
       (2, 3, 'maria_oliveira', 'password', true),
       (3, 2, 'carlos_souza', 'secret', true),
       (4, 2, 'ana_santos', 'abcdef', true);

-- Status
INSERT INTO status (nome)
VALUES ('Em Andamento'),
       ('Concluído'),
       ('Cancelado');

-- Tipos de Serviço
INSERT INTO tipos_de_servico (nome, descricao, custo_base)
VALUES ('Manutenção', 'Manutenção preventiva', 100.00),
       ('Instalação', 'Instalação de software', 50.00),
       ('Reparo', 'Reparo de hardware', 80.00);

-- Ordens de Serviço
INSERT INTO ordens_de_servico (pessoa_id, funcionario_id, tipo_de_servico_id, status_id, descricao_problema,
                               produto_extra, custo_total, relatorio_tecnico, data_criacao, data_previsao, data_entrega)
VALUES (1, 3, 1, 1, 'Computador não liga', NULL, 100.00, 'Troca de fonte realizada', '2024-04-22 10:00:00',
        '2024-04-22', '2024-04-22'),
       (2, 4, 2, 1, 'Problema na inicialização do sistema', NULL, 50.00, 'Reinstalação do sistema operacional',
        '2024-04-22 11:00:00', '2024-04-22', '2024-04-22'),
       (3, 3, 3, 2, 'Teclado não funciona', NULL, 80.00, 'Substituição do teclado realizada', '2024-04-22 12:00:00',
        '2024-04-22', '2024-04-23'),
       (4, 4, 1, 3, 'Problema na conexão de rede', 'Cabo de rede', 110.00,
        'Verificação de cabeamento realizada. Cliente desistiu do serviço.', '2024-04-22 13:00:00', '2024-04-22', NULL);
