package model.BO;

import model.entity.PessoaVO;
import model.repository.PessoaDAO;

public class PessoaBO {

	public PessoaVO consultarPessoaPorIdPessoa(int idPessoa) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		return pessoaDAO.consutarPessoaPorId(idPessoa, false);
	}

}
