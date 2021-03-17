package Executavel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.entity.Pessoa;
import model.entity.Vacina;
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
		
		Pessoa p = new Pessoa();
		p.setIdPessoa(5);
		// Vacina vac = new Vacina( "virus D", "não", "fim", LocalDate.parse("17/03/2021", dataFormatter), p, 2, 2);
		Vacina vac = new Vacina();
		
		VacinaDAO vacDao = new VacinaDAO();
		
		vacDao.cadastrarVacina(vac);
		
		System.out.println(vac);
	}

}
