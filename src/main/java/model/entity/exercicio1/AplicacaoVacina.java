package model.entity.exercicio1;

import java.time.LocalDate;

public class AplicacaoVacina {
	private Pessoa idpessoa;
	private Vacina idVacina;
	private LocalDate dataAplicacao;
	private int nota;

	public AplicacaoVacina(Pessoa idpessoa, Vacina idVacina, LocalDate dataAplicacao, int nota) {
		super();
		this.idpessoa = idpessoa;
		this.idVacina = idVacina;
		this.dataAplicacao = dataAplicacao;
		this.nota = nota;
	}

	public Pessoa getIdpessoa() {
		return idpessoa;
	}

	public void setIdpessoa(Pessoa idpessoa) {
		this.idpessoa = idpessoa;
	}

	public Vacina getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Vacina idVacina) {
		this.idVacina = idVacina;
	}
	
	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
}
