package model.BO;

import java.util.List;

import model.entity.VacinaVO;
import model.repository.VacinaDAO;

public class VacinaBO {

	/**
	 * Verifica a possibilidade de cadastro da vacina no pais de origem
	 * 
	 * @param vacinaVO
	 * 
	 * @return vacina cadastrada OU vacina ja existente
	 */
	public String cadastrarVacinaBO(VacinaVO vacinaVO) {
		String retorno = "";
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		VacinaVO verificar = vacinaDAO.consultarVacinaPorNomeAndPais(vacinaVO);
		
		if (verificar.getPesquisadorResponsavel().getIdPessoa() > 0) {
			if (verificar.getIdVacina() != null && verificar.getIdVacina() != 0) {
				retorno =  "Nome da Vacina j� existente no pais, favor escolher outro nome.";
			} else {
				if (vacinaDAO.cadastrarVacina(vacinaVO) != null) {
					retorno = "Vacina cadastrada com sucesso.";
				} else {
					retorno = "N�o foi possivel cadastrar vacina.";
				}
			}
		} else {
			retorno = "� necessario cadastrar um pesquisador responsavel primeiro";
		}
		
		return retorno;
	}

	public String excluirVacinaBO(VacinaVO vacinaVO) {
		String retorno = "";
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		VacinaVO verificar = vacinaDAO.consultarVacinaPorNomeAndPais(vacinaVO);
		
		if (verificar.getPesquisadorResponsavel() != null) {
			if (verificar.getIdVacina() != null) {
				if (vacinaDAO.excluirVacina(verificar.getIdVacina())) {
					retorno = "Vacina excluida com sucesso.";
				} else {
					retorno = "N�o foi possivel excluir a vacina.";
				}
			} else {
				retorno = "Vacina ainda n�o foi cadastrada no banco.";
			}
		}
		return retorno;
	}

	public List<VacinaVO> consultarTodasVacinas() {
		VacinaDAO vacinaDAO = new VacinaDAO();
		return vacinaDAO.consultarTodasVacinas(true);
	}
}
