CREATE TABLE TB_CARGO 
(
	cod 		BIGINT 		AUTO_INCREMENT,
    nome_cargo 	VARCHAR(100) NOT NULL,
    CONSTRAINT pk_tb_cargo 		PRIMARY KEY(cod),
    CONSTRAINT uq_tb_cargo_nome UNIQUE(nome_cargo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO TB_CARGO (nome_cargo) VALUES ('Médico');
INSERT INTO TB_CARGO (nome_cargo) VALUES ('Dentista');
INSERT INTO TB_CARGO (nome_cargo) VALUES ('Secretária');
INSERT INTO TB_CARGO (nome_cargo) VALUES ('Presidente');
INSERT INTO TB_CARGO (nome_cargo) VALUES ('Vice-Presidente');
INSERT INTO TB_CARGO (nome_cargo) VALUES ('Diretor de Finanças');
INSERT INTO TB_CARGO (nome_cargo) VALUES ('Diretor de Imprensa e Propaganda');
INSERT INTO TB_CARGO (nome_cargo) VALUES ('Diretor de Setor Social');
INSERT INTO TB_CARGO (nome_cargo) VALUES ('Coordenador');
INSERT INTO TB_CARGO (nome_cargo) VALUES ('Secretário Geral');
INSERT INTO TB_CARGO (nome_cargo) VALUES ('Conselheiro Fiscal');