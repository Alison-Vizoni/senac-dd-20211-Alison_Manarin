package model.entity;

import java.time.LocalDate;

public class VacinaVO {	
	private Integer idVacina;
	private String nome;
	private String paisDeOrigem;
	private String estagioPesquisa;
	private LocalDate dataInicioPesquisa;
	private PessoaVO pesquisadorResponsavel;
	private FaseVacina fase;
	private int quantidadeDoses;
	
	public VacinaVO(Integer idVacina, String nome, String paisDeOrigem, String estagioPesquisa,
			LocalDate dataInicioPesquisa, PessoaVO pesquisadorResponsavel, FaseVacina fase, int quantidadeDoses) {
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

	public VacinaVO() {
		super();
	}

	@Override
	public String toString() {
		return " idVacina = " + this.idVacina + "\nnome = " + this.nome + "\npais De Origem = " + this.paisDeOrigem
				+ "\nestagio Pesquisa = " + this.estagioPesquisa + "\ndata Inicio Pesquisa = " + this.dataInicioPesquisa
				+ "\npesquisador Responsavel = " + this.pesquisadorResponsavel.getIdPessoa() + "\nfase = " + this.fase + "\nquantidade Doses = "
				+ this.quantidadeDoses;
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

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public PessoaVO getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(PessoaVO pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
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
