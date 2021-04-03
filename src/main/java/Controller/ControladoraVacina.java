package Controller;

import java.util.List;

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

	public List<VacinaVO> consultarVacinaController() {
		VacinaBO vacinaBO = new VacinaBO();
		return vacinaBO.consultarTodasVacinas();
	}

}
