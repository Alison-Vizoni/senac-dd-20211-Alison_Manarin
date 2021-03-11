package model.entity;

import java.sql.Date;
import java.util.List;

public class Pessoa {
	private int idPessoa;
	private String nome;
	private Date dataNascimento;
	private char sexo;
	private String cpf;
	private int tipo;
	private List<AplicacaoVacina> vacinacoes;
	
	public Pessoa(String nome, Date dataNascimento, char sexo, String cpf, int tipo,
			List<AplicacaoVacina> vacinacoes) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipo = tipo;
		this.vacinacoes = vacinacoes;
	}

	public Pessoa() {
		super();
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getcpf() {
		return cpf;
	}

	public void setcpf(String cpf) {
		this.cpf = cpf;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public List<AplicacaoVacina> getVacinacoes() {
		return vacinacoes;
	}

	public void setVacinacoes(List<AplicacaoVacina> vacinacoes) {
		this.vacinacoes = vacinacoes;
	}
	
}
