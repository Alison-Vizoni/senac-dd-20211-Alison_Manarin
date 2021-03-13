package model.entity;

import java.sql.Date;

public class AplicacaoVacina {
	private int idAplicacaoVacina;
	private Vacina idVacina;
	private Date dataAplicacao;
	private int nota;

	public AplicacaoVacina(int idAplicacaoVacina, Vacina idVacina, Date dataAplicacao, int nota) {
		super();
		this.idAplicacaoVacina = idAplicacaoVacina;
		this.idVacina = idVacina;
		this.dataAplicacao = dataAplicacao;
		this.nota = nota;
	}

	public AplicacaoVacina() {
		super();
	}

	public int getIdAplicacaoVacina() {
		return idAplicacaoVacina;
	}

	public void setIdAplicacaoVacina(int idAplicacaoVacina) {
		this.idAplicacaoVacina = idAplicacaoVacina;
	}

	public Vacina getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Vacina idVacina) {
		this.idVacina = idVacina;
	}
	
	public Date getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(Date dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
}
