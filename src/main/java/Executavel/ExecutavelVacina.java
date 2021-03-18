package Executavel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.entity.AplicacaoVacina;
import model.entity.Pessoa;
import model.entity.Vacina;
import model.repository.AplicacaoVacinaDAO;
import model.repository.PessoaDAO;
import model.repository.VacinaDAO;

public class ExecutavelVacina {

	public static void main(String[] args) {
		
		DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		/* Teste com a classe Pessoa
		Pessoa p = new Pessoa("Consegui", LocalDate.parse("23/12/2016", dataFormatter), 'F', "15987456321", 2, null);
		
		p.setIdPessoa(4);
		
		PessoaDAO v = new PessoaDAO();
		
		v.consutarPessoaPorId(4);
		
		System.out.println(v);
		*/
		
		/*
		Pessoa p = new Pessoa();
		p.setIdPessoa(4);
		//Vacina vac = new Vacina( "virus A", "FRA", "meio", LocalDate.parse("20/04/2020", dataFormatter), p, 1, 2);
		
		//Vacina vac = new Vacina();
		//vac.setIdVacina(2);
		
		VacinaDAO vacDao = new VacinaDAO();
		
		vacDao.excluirVacina(4);
		
		System.out.println(vacDao);
		*/
		
		// Vacina vac = new Vacina();
		// vac.setIdVacina(2);
		
		// AplicacaoVacina Api = new AplicacaoVacina(vac, 1, LocalDate.parse("22/06/2019", dataFormatter), 1);
		
		AplicacaoVacinaDAO ApiDAO = new AplicacaoVacinaDAO();
		
		ApiDAO.excluirAplicacaoVacina(5);
		
		System.out.println(ApiDAO);
	}

}
