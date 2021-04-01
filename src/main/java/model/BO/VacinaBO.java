package model.BO;

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
		
		if (vacinaDAO.consultarVacinaPorNomeAndPais(vacinaVO.getNome(), vacinaVO.getPaisDeOrigem()) != null) {
			retorno =  "Nome da Vacina já existente no pais, favor escolher outro nome.";
		} else {
			if (vacinaDAO.cadastrarVacina(vacinaVO) != null) {
				retorno = "Vacina cadastrada com sucesso.";
			} else {
				retorno = "Não foi possivel cadastrar vacina.";
			}
		}
		return retorno;
	}

	public String excluirVacinaBO(VacinaVO vacinaVO) {
		String retorno = "";
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		if (vacinaDAO.consultarVacinaPorNomeAndPais(vacinaVO.getNome(), vacinaVO.getPaisDeOrigem()) != null) {
			if (vacinaDAO.excluirVacina(vacinaVO.getIdVacina())) {
				retorno = "Vacina excluida com sucesso.";
			} else {
				retorno = "Não foi possivel excluir a vacina.";
			}
		} else {
			retorno = "Vacina ainda não foi cadastrada no banco.";
		}
		return retorno;
	}
}
