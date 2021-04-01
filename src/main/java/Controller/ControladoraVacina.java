package Controller;

import model.BO.VacinaBO;
import model.entity.VacinaVO;

public class ControladoraVacina {

	public String cadastrarVacinaController(VacinaVO vacinaVO) {
		VacinaBO vacinaBO = new VacinaBO();
		return vacinaBO.cadastrarVacinaBO(vacinaVO);
	}

	public String excluirVacinaController(VacinaVO vacinaVO) {
		VacinaBO vacinaBO = new VacinaBO();
		return vacinaBO.excluirVacinaBO(vacinaVO);
	}

}
