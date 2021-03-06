DROP DATABASE IF EXISTS DBVACINAVILMAR;

CREATE DATABASE DBVACINAVILMAR;

USE DBVACINAVILMAR;

CREATE TABLE PESSOA (
	IDPESSOA INT NOT NULL AUTO_INCREMENT
    , NOME VARCHAR(200) NOT NULL
    , DATA_NASCIMENTO DATE NULL
    , SEXO CHAR(1) NOT NULL
    , CPF VARCHAR(11) NOT NULL UNIQUE
    , TIPO ENUM('PESQUISADOR', 'PACIENTE') NOT NULL
    , CONSTRAINT PK_PESSOA PRIMARY KEY (IDPESSOA)
);

CREATE TABLE VACINA (
	IDVACINA INT NOT NULL AUTO_INCREMENT
    , NOME VARCHAR(200) NOT NULL
    , PAIS_ORIGEM VARCHAR(100) NOT NULL
    , ESTAGIO_PESQUISA ENUM('INICIAL', 'TESTES', 'APLICACAO_MASSIVA') NOT NULL
    , DATA_INICIO_PESQUISA DATE NOT NULL
    , ID_PESQUISADOR_RESPONSAVEL INT NOT NULL
    , FASE ENUM('SOMENTE_PESQUISADOR', 'VOLUNTARIO', 'PUBLICO_GERAL') NOT NULL
    , QUANTIDADE_DOSES INT NOT NULL
    , ATIVA BOOLEAN NOT NULL
    , CONSTRAINT PK_VACINA PRIMARY KEY (IDVACINA)
    , CONSTRAINT FK_VACINA_PESSOA FOREIGN KEY (ID_PESQUISADOR_RESPONSAVEL) REFERENCES PESSOA (IDPESSOA)
);

CREATE TABLE APLICACAO_VACINA (
	ID_APLICACAO_VACINA INT NOT NULL auto_increment
    , IDVACINA INT NOT NULL
    , IDPESSOA INT NOT NULL
    , DATA_APLICACAO DATE NOT NULL
    , NOTA ENUM('PESSIMO', 'RUIM', 'REGULAR', 'BOM', 'OTIMO') NOT NULL
    , CONSTRAINT PK_APLICACAO_VACINA_PESSOA PRIMARY KEY (ID_APLICACAO_VACINA)
    , CONSTRAINT FK_APLICACAO_VACINA__VACINA FOREIGN KEY (IDVACINA) REFERENCES VACINA (IDVACINA)
    , CONSTRAINT FK_APLICACAO_PESSOA FOREIGN KEY (IDPESSOA) REFERENCES PESSOA (IDPESSOA)
);

-- PESSOA
INSERT INTO PESSOA (NOME, DATA_NASCIMENTO, SEXO, CPF, TIPO) 
	VALUES ("FABIO", '1989-02-15', 'M', "12345678656", "PESQUISADOR"),
		("LUCIO", '1980-01-17', 'M', "13465789878", "PESQUISADOR"),
        ("SABRINA", '1981-03-13', 'F', "91548765231", "PACIENTE"),
        ("ALEX", '1981-04-14', 'M', "52416325487", "PACIENTE"),
        ("GABI", '1982-05-16', 'F', "65412365487", "PACIENTE"),
        ("BRUNO", '1983-06-18', 'M', "23012456054", "PACIENTE"),
        ("LEILA", '1984-07-19', 'F', "02587413698", "PACIENTE"),
        ("JUNIOR", '1985-08-20', 'M', "13256487954", "PACIENTE"),
        ("LEITAO", '1986-09-21', 'M', "03154987645", "PACIENTE"),
        ("FEIJO", '1989-10-22', 'M', "30619491547", "PACIENTE"),
        ("JUCREUZA", '1987-11-23', 'F', "30302001548", "PACIENTE"),
        ("SUELEM", '1988-12-24', 'F', "30300055448", "PACIENTE"),
        ("MARIANE", '1990-01-25', 'F', "61548784516", "PACIENTE"),
        ("JUKAO", '1991-02-26', 'M', "08170526954", "PACIENTE"),
        ("CORONA VIRUS", '1992-03-27', 'M', "30625184965", "PACIENTE"),
        ("MARIA", '1980-05-20', 'F', "12457898653", "PACIENTE");

-- VACINA
INSERT INTO VACINA (NOME, PAIS_ORIGEM, ESTAGIO_PESQUISA, DATA_INICIO_PESQUISA, ID_PESQUISADOR_RESPONSAVEL, FASE, QUANTIDADE_DOSES, ATIVA)
	VALUES (upper("vac 1"), upper("bra"), "INICIAL", '2020-04-15', 1, "SOMENTE_PESQUISADOR", 2, true),
			("VAC 2", "ARG", "TESTES", '2020-10-05', 2, "VOLUNTARIO", 2, true),
            (upper("vac 3"), upper("URSS"), "APLICACAO_MASSIVA", '2020-07-20', 1, "SOMENTE_PESQUISADOR", 3, true),
			("VAC 4", "FRA", "TESTES", '2020-03-23', 2, "PUBLICO_GERAL", 2, true);
    

-- APLICACAO VACINA
INSERT INTO APLICACAO_VACINA (IDVACINA, IDPESSOA, DATA_APLICACAO, NOTA)
	VALUES (1, 1, '2020-10-20', "BOM"),
		(2, 2, '2020-06-10', "RUIM"),
        (3, 3, '2020-10-20', "REGULAR"),
		(4, 4, '2020-06-10', "OTIMO"),
        (1, 5, '2020-10-20', "REGULAR"),
		(2, 6, '2020-06-10', "OTIMO"),
        (3, 7, '2020-10-20', "RUIM"),
		(4, 8, '2020-06-10', "PESSIMO"),
        (1, 9, '2020-10-20', "BOM"),
		(2, 10, '2020-06-10', "OTIMO"),
        (3, 11, '2020-10-20', "RUIM"),
		(4, 12, '2020-06-10', "PESSIMO"),
        (1, 13, '2020-10-20', "BOM"),
		(2, 14, '2020-06-10', "OTIMO"),
        (3, 15, '2020-10-20', "REGULAR"),
		(4, 16, '2020-06-10', "OTIMO"),
        (1, 2, '2020-10-20', "RUIM"),
		(2, 3, '2020-06-10', "PESSIMO"),
        (3, 4, '2020-10-20', "BOM"),
		(4, 5, '2020-06-10', "REGULAR"),
        (4, 6, '2020-10-20', "BOM"),
		(3, 7, '2020-06-10', "OTIMO"),
        (4, 8, '2020-10-20', "RUIM"),
		(2, 9, '2020-06-10', "PESSIMO");

select * from pessoa;

select * from vacina;

SELECT * FROM APLICACAO_VACINA;

SELECT * FROM VACINA WHERE UPPER(NOME) = "VAC 1" AND UPPER(pais_origem) = "BRA";

UPDATE VACINA SET ATIVA = TRUE WHERE IDVACINA = 2;