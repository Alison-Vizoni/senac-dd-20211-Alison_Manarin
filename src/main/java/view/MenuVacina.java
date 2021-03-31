package view;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import Controller.ControladoraVacina;
import model.entity.FaseVacina;
import model.entity.VacinaVO;

public class MenuVacina {
	
	private static final int OPCAO_MENU_CADASTRAR_VACINA = 1;
	private static final int OPCAO_MENU_CONSULTAR_VACINA = 2;
	private static final int OPCAO_MENU_ATUALIZAR_VACINA = 3;
	private static final int OPCAO_MENU_EXCLUIR_VACINA = 4;
	private static final int OPCAO_MENU_VACINA_VOLTAR = 5;
	
	private static final int OPCAO_MENU_CONSULTAR_TODAS_VACINAS = 1;
	private static final int OPCAO_MENU_CONSULTAR_UMA_VACINA = 2;
	private static final int OPCAO_MENU_CONSULTAR_VACINAS_VOLTAR = 3;
	
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
					this.consultarVeiculo();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_VACINA: {
					this.atualizarVeiculo();
					break;
				}
				case OPCAO_MENU_EXCLUIR_VACINA: {
					this.excluirVeiculo();
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
		String mensagem = "\nOpções:";
		mensagem += OPCAO_MENU_CADASTRAR_VACINA + " - Cadastrar Vacina";
		mensagem += OPCAO_MENU_CONSULTAR_VACINA + " - Consultar Vacina";
		mensagem += OPCAO_MENU_ATUALIZAR_VACINA + " - Atualizar Vacina";
		mensagem += OPCAO_MENU_EXCLUIR_VACINA + " - Excluir Vacina";
		mensagem += OPCAO_MENU_VACINA_VOLTAR + " - Voltar";
		mensagem += "\nDigite a opção: ";
		
		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, mensagem, "Menu Vacina",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = 0;
		try {
			opcaoSelecionada = Integer.parseInt(valorInformadoPeloUsuario);
		} catch (NumberFormatException excecao) {
			JOptionPane.showMessageDialog(null, "Informe um valor numérico");
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
		
		String pesquisadorInformadoPeloUsuario = JOptionPane.showInputDialog(null, "Digite o Nome");
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

	private int apresentarOpcoesFaseVacina() {
		String mensagem = "\nOpções:";
		mensagem += OPCAO_VACINA_SOMENTE_PESQUISADOR + " - Somete pesquisador";
		mensagem += OPCAO_VACINA_VOLUNTARIOS + " - Voluntarios";
		mensagem += OPCAO_VACINA_PUBLICO_GERAL + " - Publico geral";
		mensagem += "\nDigite a opção: ";

		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, mensagem, "Menu Vacina",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = 0;
		try {
			opcaoSelecionada = Integer.parseInt(valorInformadoPeloUsuario);
		} catch (NumberFormatException excecao) {
			JOptionPane.showMessageDialog(null, "Informe um valor numérico");
			this.apresentarOpcoesMenu();
		}
		
		return opcaoSelecionada;
	}

}
