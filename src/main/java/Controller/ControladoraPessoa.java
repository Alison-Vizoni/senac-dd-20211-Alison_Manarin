package Controller;

import model.BO.PessoaBO;
import model.entity.PessoaVO;

public class ControladoraPessoa {

	public PessoaVO consultarPessoaController(PessoaVO pesquisadorInformadoPeloUsuario) {
		PessoaBO pessoaBO = new PessoaBO();
		return pessoaBO.consultarPessoaPorNomeAndCpfBO(pesquisadorInformadoPeloUsuario);
	}

	public int cadastrarPessoaController(PessoaVO pessoaVO) {
		PessoaBO pessoaBO = new PessoaBO();
		return pessoaBO.cadastrarPessoaBO(pessoaVO);
	}

	

}
