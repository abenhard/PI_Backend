INSERT INTO estados (nome) VALUES ('AC'), ('AL'),('AP'),('AM'),('BA'), ('CE'),
                           ('DF'), ('ES'),('GO'),('MA'),('MT'),('MS'),
                           ('MG'),('PA'),('PB'),('PR'),('PE'),('PI'),
                           ('RJ'),('RN'), ('RS'), ('RO'),('RR'),('SC'),
                           ('SP'),('SE'),('TO');

-- Inserir cidades para o estado do Rio Grande do Sul (RS)
INSERT INTO cidades (iduf, nome) VALUES
                                     (21, 'Porto Alegre'),
                                     (21, 'Caxias do Sul'),
                                     (21, 'Pelotas'),
                                     (21, 'Canoas'),
                                     (21, 'Santa Maria'),
                                     (21, 'Gravataí'),
                                     (21, 'Novo Hamburgo'),
                                     (21, 'São Leopoldo'),
                                     (21, 'Rio Grande'),
                                     (21, 'Viamão');

-- Inserir cidades para o estado de Santa Catarina (SC)
INSERT INTO cidades (iduf, nome) VALUES
                                     (23, 'Florianópolis'),
                                     (23, 'Joinville'),
                                     (23, 'Blumenau'),
                                     (23, 'São José'),
                                     (23, 'Chapecó');

-- Inserir cidades para o estado de São Paulo (SP)
INSERT INTO cidades (iduf, nome) VALUES
                                     (24, 'São Paulo'),
                                     (24, 'Guarulhos'),
                                     (24, 'Campinas'),
                                     (24, 'São Bernardo do Campo'),
                                     (24, 'Santo André');

-- Inserir cidades para o estado de Minas Gerais (MG)
INSERT INTO cidades (iduf, nome) VALUES
                                     (13, 'Belo Horizonte'),
                                     (13, 'Uberlândia'),
                                     (13, 'Contagem'),
                                     (13, 'Juiz de Fora'),
                                     (13, 'Betim');



INSERT INTO marcas (nome) VALUES
                              ('Nestle'), ('Apple'), ('Microsoft'), ('Samsung'), ('Toyota'),
                              ('Coca-Cola'), ('Amazon'), ('Google'), ('Sony'), ('Nike'),
                              ('Adidas'), ('BMW'), ('Mercedes-Benz'), ('Pepsi'), ('Ford'),
                              ('General Electric'), ('IBM'), ('McDonald''s'), ('Walmart'), ('Honda'),
                              ('Louis Vuitton'), ('Intel'), ('Nissan'), ('L''Oréal'), ('Audi'),
                              ('HP'), ('Canon'), ('Dell'), ('Panasonic');
INSERT INTO categorias (nome) VALUES
                                  ('Limpeza'), ('Eletrônicos'), ('Alimentos'), ('Roupas'), ('Calçados'),
                                  ('Automóveis'), ('Beleza'), ('Saúde'), ('Esportes'), ('Jogos'),
                                  ('Casa e Jardim'), ('Animais de Estimação'), ('Móveis'), ('Livros'), ('Filmes e Séries'),
                                  ('Música'), ('Arte e Artesanato'), ('Viagens'), ('Tecnologia'), ('Jóias'),
                                  ('Brinquedos'), ('Instrumentos Musicais'), ('Fotografia'), ('Eletrodomésticos'), ('Fitness'),
                                  ('Crianças e Bebês'), ('Papelaria'), ('Ferramentas'), ('Moda'), ('Acessórios');

INSERT INTO produtos (nome, descricao, precobase, idmarca, disponibilidade) VALUES
            ('Nestle Chocolate', 'Barra de chocolate delicioso', 9.99, 1,'disponivel'),
            ('Samsung 4K Smart TV', 'Televisão inteligente com resolução 4K', 3499.99, 4,'disponivel'),
            ('Nike Air Max', 'Tênis esportivo para corrida', 299.99, 9,'disponivel'),
            ('Amazon Echo Dot', 'Assistente de voz inteligente com Alexa', 199.99, 7,'disponivel'),
            ('Coca-Cola Zero', 'Refrigerante sem açúcar', 5.49, 6,'disponivel'),
            ('Adidas Originals Hoodie', 'Moletom com capuz para estilo casual', 79.99, 10,'disponivel'),
            ('Toyota Camry', 'Carro sedan eficiente em combustível', 89999.99, 5,'disponivel'),
            ('Samsung Galaxy Buds Pro', 'Fones de ouvido sem fio com cancelamento de ruído', 249.99, 4,'disponivel'),
            ('L''Oréal Paris Shampoo', 'Shampoo para cabelos saudáveis', 14.99, 23,'disponivel'),
            ('Microsoft Surface Laptop', 'Laptop fino e leve para produtividade', 12999.99, 3,'disponivel'),
            ('McDonald''s Big Mac Combo', 'Combo de hambúrguer, batata frita e refrigerante', 9.99, 18,'disponivel'),
            ('Sony PlayStation 5', 'Console de videogame de última geração', 499.99, 8,'disponivel'),
            ('Honda Civic', 'Carro compacto e econômico', 21999.99, 20,'disponivel'),
            ('Apple AirPods Pro', 'Fones de ouvido sem fio com cancelamento de ruído', 249.99, 2,'disponivel'),
            ('Nestle Nescau', 'Chocolate em pó para bebidas quentes', 7.99, 1,'disponivel'),
            ('Samsung Galaxy Tab S7', 'Tablet Android com tela Super AMOLED', 649.99, 4,'disponivel'),
            ('Amazon Kindle Paperwhite', 'Leitor de e-books à prova d''água', 129.99, 7,'disponivel'),
            ('Nike Dri-FIT Camiseta', 'Camiseta esportiva para corrida', 34.99, 9,'disponivel'),
            ('Coca-Cola Classic', 'Refrigerante clássico com gás', 5.49, 6,'disponivel'),
            ('Apple Watch Series 7', 'Relógio inteligente com monitoramento de saúde', 399.99, 2,'disponivel');


INSERT INTO produto_categoria (idproduto, idcategoria) VALUES
                                                           (1, 3), -- Nestle Chocolate - Alimentos
                                                           (2, 2), -- Samsung 4K Smart TV - Eletrônicos
                                                           (3, 4), -- Nike Air Max - Calçados
                                                           (3, 9), -- Nike Air Max - Esportes
                                                           (4, 2), -- Amazon Echo Dot - Eletrônicos
                                                           (4, 20), -- Amazon Echo Dot - Tecnologia
                                                           (5, 3), -- Coca-Cola Zero - Alimentos
                                                           (5, 6), -- Coca-Cola Zero - Bebidas
                                                           (6, 5), -- Adidas Originals Hoodie - Roupas
                                                           (7, 6), -- Toyota Camry - Automóveis
                                                           (8, 2), -- Samsung Galaxy Buds Pro - Eletrônicos
                                                           (8, 20), -- Samsung Galaxy Buds Pro - Tecnologia
                                                           (9, 7), -- L'Oréal Paris Shampoo - Beleza
                                                           (10, 2), -- Microsoft Surface Laptop - Eletrônicos
                                                           (10, 20), -- Microsoft Surface Laptop - Tecnologia
                                                           (11, 3), -- McDonald's Big Mac Combo - Alimentos
                                                           (11, 6), -- McDonald's Big Mac Combo - Bebidas
                                                           (12, 2), -- Sony PlayStation 5 - Eletrônicos
                                                           (12, 10), -- Sony PlayStation 5 - Jogos
                                                           (13, 6), -- Honda Civic - Automóveis
                                                           (14, 2), -- Apple AirPods Pro - Eletrônicos
                                                           (14, 20), -- Apple AirPods Pro - Tecnologia
                                                           (15, 3), -- Nestle Nescau - Alimentos
                                                           (15, 6), -- Nestle Nescau - Bebidas
                                                           (16, 2), -- Samsung Galaxy Tab S7 - Eletrônicos
                                                           (16, 20), -- Samsung Galaxy Tab S7 - Tecnologia
                                                           (17, 2), -- Amazon Kindle Paperwhite - Eletrônicos
                                                           (17, 15), -- Amazon Kindle Paperwhite - Livros
                                                           (18, 5), -- Nike Dri-FIT Camiseta - Roupas
                                                           (18, 9), -- Nike Dri-FIT Camiseta - Esportes
                                                           (19, 3), -- Coca-Cola Classic - Alimentos
                                                           (19, 6), -- Coca-Cola Classic - Bebidas
                                                           (20, 2), -- Apple Watch Series 7 - Eletrônicos
                                                           (20, 8); -- Apple Watch Series 7 - Saúde
