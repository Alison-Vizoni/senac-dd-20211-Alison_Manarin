package model.entity;

import java.sql.Date;

public class Vacina {	
	private Integer idVacina;
	private String nome;
	private String paisDeOrigem;
	private String estagioPesquisa;
	private Date dataInicioPesquisa;
	private Pessoa pesquisadorResponsavel;
	private int fase;
	private int quantidadeDoses;

	public Vacina(Integer idVacina, String nome, String paisDeOrigem, String estagioPesquisa,
			Date dataInicioPesquisa, Pessoa pesquisadorResponsavel, int fase, int quantidadeDoses) {
		super();
		this.idVacina = idVacina;
		this.nome = nome;
		this.paisDeOrigem = paisDeOrigem;
		this.estagioPesquisa = estagioPesquisa;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.fase = fase;
		this.quantidadeDoses = quantidadeDoses;
	}

	public Integer getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Integer idVacina) {
		this.idVacina = idVacina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPaisDeOrigem() {
		return paisDeOrigem;
	}

	public void setPaisDeOrigem(String paisDeOrigem) {
		this.paisDeOrigem = paisDeOrigem;
	}

	public String getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(String estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public Date getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(Date dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public int getQuantidadeDoses() {
		return quantidadeDoses;
	}

	public void setQuantidadeDoses(int quantidadeDoses) {
		this.quantidadeDoses = quantidadeDoses;
	}
	
	
}
