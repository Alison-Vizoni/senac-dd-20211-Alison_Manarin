CREATE DATABASE DBVACINAVILMAR;

USE DBVACINAVILMAR;

CREATE TABLE PESSOA (
	IDPESSOA INT NOT NULL AUTO_INCREMENT
    , NOME VARCHAR(200) NOT NULL
    , DATA_NASCIMENTO DATE NOT NULL
    , SEXO CHAR(1) NOT NULL
    , CPF VARCHAR(11) NOT NULL
    , TIPO INT NOT NULL
    , CONSTRAINT PK_PESSOA PRIMARY KEY (IDPESSOA)
);

CREATE TABLE VACINA (
	IDVACINA INT NOT NULL AUTO_INCREMENT
    , NOME VARCHAR(200) NOT NULL
    , PAIS_ORDIGEM VARCHAR(100) NOT NULL
    , ESTAGIO_PESQUISA VARCHAR(50) NOT NULL
    , DATA_INICIO_PESQUISA DATE NOT NULL
    , ID_PESQUISADOR_RESPONSAVEL INT NOT NULL
    , FASE INT NOT NULL
    , QUANTIDADE_DOSES INT NOT NULL
    , CONSTRAINT PK_VACINA PRIMARY KEY (IDVACINA)
    , CONSTRAINT FK_VACINA_PESSOA FOREIGN KEY (ID_PESQUISADOR_RESPONSAVEL) REFERENCES PESSOA (IDPESSOA)
);

CREATE TABLE APLICACAO_VACINA (
	ID_APLICACAO_VACINA INT NOT NULL AUTO_INCREMENT
    , IDVACINA INT NOT NULL
    , IDPESSOA INTEGER NULL
    , DATA_APLICACAO INT NOT NULL
    , NOTA INT NOT NULL
    , CONSTRAINT PK_APLICACAO_VACINA PRIMARY KEY (ID_APLICACAO_VACINA)
    , CONSTRAINT FK_APLICACAO_VACINA__VACINA FOREIGN KEY (IDVACINA) REFERENCES VACINA (IDVACINA)
);