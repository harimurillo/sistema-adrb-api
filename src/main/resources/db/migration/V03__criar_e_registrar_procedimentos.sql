CREATE TABLE TB_PROCEDIMENTO
(
	cod 		INT 		AUTO_INCREMENT,
    nome_proced VARCHAR(50) NOT NULL,
    CONSTRAINT pk_tb_procedimento PRIMARY KEY(cod),
    CONSTRAINT uq_tb_procedimento UNIQUE(nome_proced)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO TB_PROCEDIMENTO (nome_proced) VALUES ('Limpeza Dental');
INSERT INTO TB_PROCEDIMENTO (nome_proced) VALUES ('Canal');
INSERT INTO TB_PROCEDIMENTO (nome_proced) VALUES ('Clínica');