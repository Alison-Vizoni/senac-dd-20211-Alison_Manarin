package model.entity;

import java.time.LocalDate;

public class AplicacaoVacina {
	private Vacina idVacina;
	private LocalDate dataAplicacao;
	private int nota;

	public AplicacaoVacina(Vacina idVacina, LocalDate dataAplicacao, int nota) {
		super();

		this.idVacina = idVacina;
		this.dataAplicacao = dataAplicacao;
		this.nota = nota;
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
