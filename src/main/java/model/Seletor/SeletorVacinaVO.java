package model.Seletor;

import java.time.LocalDate;

public class SeletorVacinaVO {
	
	private int idVacina;
	private String nomePais;
	private String nomeVacina;
	private LocalDate dataInicioPesquisa;
	
	private int limite;
	private int pagina;
	
	public SeletorVacinaVO() {
		this.limite = 0;
		this.pagina = -1;
	}
	
	/**
	 * Verifica se este seletor tem algum campo preenchido
	 *
	 * @return verdadeiro se existe algum campo de filtro preenchido
	 */
	public boolean temFiltro() {
		boolean retorno = false;
		
		if (this.idVacina > 0) {
			retorno = true;
		}
		
		if ((this.nomePais != null) && (this.nomePais.trim().length() > 0)) {
			retorno = true;
		}
		
		if ((this.nomeVacina != null) && (this.nomeVacina.trim().length() > 0)) {
			retorno = true;
		}
		
		if (this.dataInicioPesquisa != null) {
			retorno = true;
		}
		
		return retorno;
	}
	
	/**
	 * Verifica se os campos de paginacao estao preenchidos
	 *
	 * @return verdadeiro se os campos limite e pagina estao preenchidos
	 */
	public boolean temPaginacao() {
		return ((this.limite > 0) && (this.pagina > -1));
	}
	
	/**
	 * Calcula deslocamento (offset) a partir da pagina e do limite
	 *
	 * @return offset
	 */
	public int getOffset() {
		return (this.limite * (this.pagina - 1));
	}

	public int getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(int idVacina) {
		this.idVacina = idVacina;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

}
