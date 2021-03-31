package model.entity;

import java.time.LocalDate;

public class AplicacaoVacinaVO {
	private Integer idAplicacaoVacina;
	private VacinaVO idVacina;
	private int idPessoa;
	private LocalDate dataAplicacao;
	private int nota;

	public AplicacaoVacinaVO(VacinaVO idVacina, int idPessoa, LocalDate dataAplicacao, int nota) {
		super();
		this.idVacina = idVacina;
		this.idPessoa = idPessoa;
		this.dataAplicacao = dataAplicacao;
		this.nota = nota;
	}

	public AplicacaoVacinaVO() {
		super();
	}

	public Integer getIdAplicacaoVacina() {
		return idAplicacaoVacina;
	}

	public void setIdAplicacaoVacina(Integer idAplicacaoVacina) {
		this.idAplicacaoVacina = idAplicacaoVacina;
	}

	public VacinaVO getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(VacinaVO idVacina) {
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

	@Override
	public String toString() {
		return "id Aplicacao Vacina = " + this.idAplicacaoVacina + "\nidVacina = " + this.idVacina.getIdVacina() + "\nidPessoa = "
				+ this.idPessoa + "\ndata Aplicacao=" + this.dataAplicacao + "\nnota = " + this.nota;
	}
	
}
