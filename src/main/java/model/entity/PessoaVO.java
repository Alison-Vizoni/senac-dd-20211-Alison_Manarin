package model.entity;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 * @author Alison
 *
 */
public class PessoaVO {
	private int idPessoa;
	private String nome;
	private LocalDate dataNascimento;
	private char sexo;
	private String cpf;
	private int tipo;
	private List<AplicacaoVacinaVO> vacinacoes;
	
	public PessoaVO(String nome, LocalDate dataNascimento, char sexo, String cpf, int tipo,
			List<AplicacaoVacinaVO> vacinacoes) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipo = tipo;
		this.vacinacoes = vacinacoes;
	}
	
	public PessoaVO() {
		super();
	}

	@Override
	public String toString() {
		
		String textoVacina = "";
		
		if (this.getVacinacoes() == null || this.getVacinacoes().isEmpty()) {
			textoVacina = "Sem aplicção vacina";
		} else {
			textoVacina = vacinacoes.toString();
		}

		return "ID PESSOA " + this.getIdPessoa() + "\nNome = " + this.getNome() + "\ndata Nascimento = " + this.getDataNascimento() + "\nsexo = "
				+ this.getSexo() + "\ncpf = " + this.getcpf() + "\ntipo = " + this.getTipo() + "\nvacinacoes = " + textoVacina;
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

	public List<AplicacaoVacinaVO> getVacinacoes() {
		return vacinacoes;
	}

	public void setVacinacoes(List<AplicacaoVacinaVO> vacinacoes) {
		this.vacinacoes = vacinacoes;
	}
	
}
