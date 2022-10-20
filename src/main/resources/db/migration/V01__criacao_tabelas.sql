CREATE TABLE categoria(
	idCategoria serial PRIMARY KEY,
	nomeCategoria varchar(30) NOT NULL UNIQUE,
	descricaoCategoria varchar(200)
);
	
CREATE TABLE produto(
	idProduto serial PRIMARY KEY,
	nomeProduto varchar(30) NOT NULL UNIQUE,
	descricaoProduto varchar(200),
	quantidadeEstoqueProduto int,
	dataCadastroProduto date,
	valorUnitarioProduto REAL NOT NULL,
	imagemProduto bytea,
	id_categoria int REFERENCES categoria(idCategoria)
);

CREATE TABLE endereco(
	idEndereco serial PRIMARY KEY,
	cepEndereco varchar(8) NOT NULL,
	ruaEndereco varchar(80) NOT NULL,
	bairroEndereco varchar(50) NOT NULL,
	cidadeEndereco varchar(80) NOT NULL,
	numeroEndereco varchar(20) NOT NULL,
	complementoEndereco varchar(80),
	ufEndereco varchar(2) NOT NULL
);

CREATE TABLE cliente(
	idCliente serial PRIMARY KEY,
	nomeCompletoCliente varchar(50) NOT NULL,
	emailCliente varchar(80) NOT NULL unique,
	cpfCliente varchar(11) NOT NULL unique,
	telefoneCliente varchar(40) NOT NULL,
	dataNascimentoCliente date,
	id_endereco int REFERENCES endereco(idEndereco) NOT NULL
);
	
CREATE TABLE pedido(
	idPedido serial PRIMARY KEY,
	dataPedido date NOT NULL,
	dataEntregaPedido date,
	dataEnvioPedido date,
	status varchar(1) NOT NULL,
	valorTotalPedido REAL NOT NULL,
	id_cliente int REFERENCES cliente(idCliente)
);	
	
	CREATE TABLE itemPedido(
	idItemPedido serial PRIMARY KEY,
	quantidadeItemPedido int NOT NULL,
	precoVendaItemPedido REAL NOT NULL, 
	percentualDescontoItemPedido REAL NOT NULL,
	valorBrutoItemPedido REAL NOT NULL,	
	valorLiquidoItemPedido REAL NOT NULL,	
	id_produto int REFERENCES produto(idProduto) NOT NULL,
	id_pedido int REFERENCES pedido(idPedido)NOT NULL
);
	
	
	
	
	
	