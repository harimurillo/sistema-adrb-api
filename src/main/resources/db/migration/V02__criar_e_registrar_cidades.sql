CREATE TABLE TB_CIDADE
(
	cod 		BIGINT 		AUTO_INCREMENT,
    nome_cidade VARCHAR(100) NOT NULL,
    CONSTRAINT pk_tb_cidade 		PRIMARY KEY(cod),
    CONSTRAINT uq_tb_cidade_nome 	UNIQUE(nome_cidade)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO TB_CIDADE (nome_cidade) VALUES ('São Paulo');
INSERT INTO TB_CIDADE (nome_cidade) VALUES ('Guarulhos');
INSERT INTO TB_CIDADE (nome_cidade) VALUES ('Osasco');
INSERT INTO TB_CIDADE (nome_cidade) VALUES ('São Bernardo do Campo');
INSERT INTO TB_CIDADE (nome_cidade) VALUES ('São Mateus');