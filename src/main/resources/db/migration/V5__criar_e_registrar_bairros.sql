CREATE TABLE TB_BAIRRO 
(
	cod 		BIGINT 		AUTO_INCREMENT,
	nome_bairro VARCHAR(50) NOT NULL,
	cod_cidade 	BIGINT,
	CONSTRAINT pk_tb_bairro PRIMARY KEY(cod),
	CONSTRAINT uq_tb_bairro_nome UNIQUE(nome_bairro),
	CONSTRAINT fk_tb_bairro FOREIGN KEY(cod_cidade) REFERENCES TB_CIDADE(cod)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO TB_BAIRRO (nome_bairro) VALUES ('Pari');
INSERT INTO TB_BAIRRO (nome_bairro) VALUES ('Coimbra');