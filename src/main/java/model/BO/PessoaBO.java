package model.BO;

import model.entity.PessoaVO;
import model.repository.PessoaDAO;

public class PessoaBO {

	public PessoaVO consultarPessoaPorNomeAndCpfBO(PessoaVO pesquisadorInformadoPeloUsuario) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		return pessoaDAO.consultarPessoaPorNomeAndCpf(pesquisadorInformadoPeloUsuario);
	}

	public int cadastrarPessoaBO(PessoaVO pessoaVO) {
		int retorno = 0;
		PessoaDAO pessoaDAO = new PessoaDAO();

		pessoaVO = pessoaDAO.cadastrarPessoa(pessoaVO);
		if (pessoaVO.getIdPessoa() > 0) {
			retorno =  pessoaVO.getIdPessoa();
		} else {
			retorno = 0;
		}

		return  retorno;
	}

}
