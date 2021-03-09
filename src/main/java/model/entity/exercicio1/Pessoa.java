package model.entity.exercicio1;

import java.time.LocalDate;
import java.util.List;

public class Pessoa {
	private int idPessoa;
	private String nome;
	private LocalDate dataNascimento;
	private char sexo;
	private String CPF;
	private int tipo;
	private List<AplicacaoVacina> vacinacoes;
	
	public Pessoa(int idPessoa, String nome, LocalDate dataNascimento, char sexo, String cPF, int tipo,
			List<AplicacaoVacina> vacinacoes) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		CPF = cPF;
		this.tipo = tipo;
		this.vacinacoes = vacinacoes;
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
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
