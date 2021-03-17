package model.entity;

import java.time.LocalDate;

public class AplicacaoVacina {
	private Vacina idVacina;
	private int idPessoa;
	private LocalDate dataAplicacao;
	private int nota;

	public AplicacaoVacina(Vacina idVacina, int idPessoa, LocalDate dataAplicacao, int nota) {
		super();
		this.idVacina = idVacina;
		this.idPessoa = idPessoa;
		this.dataAplicacao = dataAplicacao;
		this.nota = nota;
	}

	public AplicacaoVacina() {
		super();
	}

	public Vacina getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Vacina idVacina) {
		this.idVacina = idVacina;
	}
	
	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
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
