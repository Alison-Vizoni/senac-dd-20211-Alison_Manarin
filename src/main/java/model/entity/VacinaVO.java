package model.entity;

import java.time.LocalDate;

import model.Enum.EstagioPesquisa;
import model.Enum.FaseVacina;

public class VacinaVO {	
	private Integer idVacina;
	private String nome;
	private String paisDeOrigem;
	private EstagioPesquisa estagioPesquisa;
	private LocalDate dataInicioPesquisa;
	private int pesquisadorResponsavel;
	private FaseVacina fase;
	private int quantidadeDoses;
	private boolean vacinaAtiva;

	public VacinaVO(Integer idVacina, String nome, String paisDeOrigem, EstagioPesquisa estagioPesquisa,
			LocalDate dataInicioPesquisa, int pesquisadorResponsavel, FaseVacina fase, int quantidadeDoses,
			boolean vacinaAtiva) {
		super();
		this.idVacina = idVacina;
		this.nome = nome;
		this.paisDeOrigem = paisDeOrigem;
		this.estagioPesquisa = estagioPesquisa;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.fase = fase;
		this.quantidadeDoses = quantidadeDoses;
		this.vacinaAtiva = vacinaAtiva;
	}

	public VacinaVO() {
		super();
	}

	@Override
	public String toString() {
		return " idVacina = " + this.idVacina + "\nnome = " + this.nome + "\npais De Origem = " + this.paisDeOrigem
				+ "\nestagio Pesquisa = " + this.estagioPesquisa + "\ndata Inicio Pesquisa = " + this.dataInicioPesquisa
				+ "\npesquisador Responsavel = " + this.pesquisadorResponsavel + "\nfase = " + this.fase + "\nquantidade Doses = "
				+ this.quantidadeDoses + "\nvacina ativa = " + this.vacinaAtiva;
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

	public EstagioPesquisa getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(EstagioPesquisa estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public int getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(int pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public boolean isVacinaAtiva() {
		return vacinaAtiva;
	}

	public void setVacinaAtiva(boolean vacinaAtiva) {
		this.vacinaAtiva = vacinaAtiva;
	}

	public FaseVacina getFase() {
		return fase;
	}

	public void setFase(FaseVacina fase) {
		this.fase = fase;
	}

	public int getQuantidadeDoses() {
		return quantidadeDoses;
	}

	public void setQuantidadeDoses(int quantidadeDoses) {
		this.quantidadeDoses = quantidadeDoses;
	}
	
}
