package Controller;

import model.BO.PessoaBO;
import model.entity.PessoaVO;

public class ControladoraPessoa {

	public PessoaVO consultarPessoaController(int idPessoa) {
		PessoaBO pessoaBO = new PessoaBO();
		return pessoaBO.consultarPessoaPorIdPessoa(idPessoa);
	}

	

}
