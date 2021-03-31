package view;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.ControladoraVacina;
import Util.StringUtil;
import model.entity.FaseVacina;
import model.entity.PessoaVO;
import model.entity.VacinaVO;
import model.repository.PessoaDAO;

public class MenuVacina {
	
	private static final int OPCAO_MENU_CADASTRAR_VACINA = 1;
	private static final int OPCAO_MENU_CONSULTAR_VACINA = 2;
	private static final int OPCAO_MENU_EXCLUIR_VACINA = 3;
	private static final int OPCAO_MENU_VACINA_VOLTAR = 4;
	
	private static final int OPCAO_VACINA_SOMENTE_PESQUISADOR = 1;
	private static final int OPCAO_VACINA_VOLUNTARIOS = 2;
	private static final int OPCAO_VACINA_PUBLICO_GERAL = 3;
	private static final int OPCAO_VACINA_FIM = 99;

	public void apresentarMenuVacina() {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_VACINA_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_VACINA: {
					this.cadastrarVacina();
					break;
				}
				case OPCAO_MENU_CONSULTAR_VACINA: {
					this.consultarTodasVacina();
					break;
				}
				case OPCAO_MENU_EXCLUIR_VACINA: {
					this.excluirVacina();
					break;
				}
				default: {
					System.out.println("\nOpção inválida!");
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
		
	}

	private int apresentarOpcoesMenu() {
		StringBuilder msg = new StringBuilder();
		msg.append("\nOpções:");
		msg.append(OPCAO_MENU_CADASTRAR_VACINA + " - Cadastrar Vacina");
		msg.append(OPCAO_MENU_CONSULTAR_VACINA + " - Consultar Vacina");
		msg.append(OPCAO_MENU_EXCLUIR_VACINA + " - Excluir Vacina");
		msg.append(OPCAO_MENU_VACINA_VOLTAR + " - Voltar");
		msg.append("\nDigite a opção: ");
		
		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Menu Vacina",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = StringUtil.formatarStringParaInteiro(valorInformadoPeloUsuario);
		
		if(opcaoSelecionada == StringUtil.VALOR_INVALIDO) {
			this.apresentarOpcoesMenu();
		}
		
		return opcaoSelecionada;
	}
	
	private void cadastrarVacina() {
		VacinaVO vacinaVO = new VacinaVO();
		
		String nomeInformadoPeloUsuario = JOptionPane.showInputDialog(null, "Digite o Nome");
		vacinaVO.setNome(nomeInformadoPeloUsuario);
		
		String paisInformadoPeloUsuario = JOptionPane.showInputDialog(null, "Digite o Pais de Origem");
		vacinaVO.setPaisDeOrigem(paisInformadoPeloUsuario);
		
		String estagioInformadoPeloUsuario = JOptionPane.showInputDialog(null, "Digite o Estagio da Pesquisa");
		vacinaVO.setEstagioPesquisa(estagioInformadoPeloUsuario);
		
		LocalDate dataInformadoPeloUsuario = LocalDate.parse(JOptionPane.showInputDialog(null, "Digite o Nome"));
		vacinaVO.setDataInicioPesquisa(dataInformadoPeloUsuario);
		
		PessoaVO pesquisadorInformadoPeloUsuario = new PessoaVO();
		pesquisadorInformadoPeloUsuario.setIdPessoa(1);
//		String pesquisadorInformadoPeloUsuario = JOptionPane.showInputDialog(null, "Digite o Nome");
		vacinaVO.setPesquisadorResponsavel(pesquisadorInformadoPeloUsuario);
		
		int dosesInformadoPeloUsuario = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o Nome"));
		vacinaVO.setQuantidadeDoses(dosesInformadoPeloUsuario);
		
		int opcao = this.apresentarOpcoesFaseVacina();
		while(opcao != OPCAO_VACINA_FIM) {
			switch(opcao) {
				case OPCAO_VACINA_SOMENTE_PESQUISADOR: {
					opcao = OPCAO_VACINA_FIM;
					vacinaVO.setFase(FaseVacina.SOMENTE_PESQUISADOR);
					break;
				}
				case OPCAO_VACINA_VOLUNTARIOS: {
					opcao = OPCAO_VACINA_FIM;
					vacinaVO.setFase(FaseVacina.VOLUNTARIO);
					break;
				}
				case OPCAO_VACINA_PUBLICO_GERAL: {
					opcao = OPCAO_VACINA_FIM;
					vacinaVO.setFase(FaseVacina.PUBLICO_GERAL);
					break;
				}
				default: {
					System.out.println("\nOpção inválida!");
					opcao = this.apresentarOpcoesFaseVacina();
				}
			}
		}
		
		ControladoraVacina controladoraVacina = new ControladoraVacina();
		String resultado = controladoraVacina.cadastrarVacinaController(vacinaVO);
		System.out.println(resultado);
	}
	
	private void consultarTodasVacina() {
		// TODO Auto-generated method stub
		
	}

	private void excluirVacina() {
		// TODO Auto-generated method stub
		
	}

	private int apresentarOpcoesFaseVacina() {
		StringBuilder msg = new StringBuilder();
		msg.append("\nOpções:");
		msg.append(OPCAO_VACINA_SOMENTE_PESQUISADOR + " - Somete pesquisador");
		msg.append(OPCAO_VACINA_VOLUNTARIOS + " - Voluntarios");
		msg.append(OPCAO_VACINA_PUBLICO_GERAL + " - Publico geral");
		msg.append("\nDigite a opção: ");

		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Menu Vacina",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = StringUtil.formatarStringParaInteiro(valorInformadoPeloUsuario);
		
		if(opcaoSelecionada == StringUtil.VALOR_INVALIDO) {
			this.apresentarOpcoesMenu();
		}
		
		return opcaoSelecionada;
	}

}
