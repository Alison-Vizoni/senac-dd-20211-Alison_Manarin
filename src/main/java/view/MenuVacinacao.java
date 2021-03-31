package view;

import javax.swing.JOptionPane;

import Util.StringUtil;

public class MenuVacinacao {
	
	private static final int OPCAO_MENU_PESSOA = 1;
	private static final int OPCAO_MENU_VACINA = 2;
	private static final int OPCAO_MENU_APLICACAO_VACINA = 3;
	private static final int OPCAO_MENU_RELATORIO = 4;
	private static final int OPCAO_MENU_SAIR = 5;

	public void apresentarMenu() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_SAIR) {
			switch (opcao) {
				case OPCAO_MENU_PESSOA: {
					MenuPessoa menuPessoa = new MenuPessoa();
					menuPessoa.apresentarMenuPessoa();
					break;
				}
				case OPCAO_MENU_VACINA: {
					MenuVacina menuVacina = new MenuVacina();
					menuVacina.apresentarMenuVacina();
					break;
				}
				case OPCAO_MENU_APLICACAO_VACINA: {
					MenuAplicacaoVacina menuAplicacaoVacina = new MenuAplicacaoVacina();
					menuAplicacaoVacina.apresentarMenuAplicacaoVacina();
					break;
				}
				case OPCAO_MENU_RELATORIO: {
					MenuRelatorio menuRelatorio = new MenuRelatorio();
					menuRelatorio.apresentarMenuRelatorio();
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
		StringBuilder msg = new  StringBuilder();
		msg.append("Opções:\n");
		msg.append(OPCAO_MENU_PESSOA + " - Menu Pessoa \n");
		msg.append(OPCAO_MENU_VACINA + " - Menu Vacina \n");
		msg.append(OPCAO_MENU_APLICACAO_VACINA + " - Menu Aplicação Vacina \n");
		msg.append(OPCAO_MENU_RELATORIO + " - Menu Relatório \n");
		msg.append(OPCAO_MENU_SAIR + " - Sair \n");
		msg.append("\nDigite a opção: ");

		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Menu Principal Vacinação",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = StringUtil.formatarStringParaInteiro(valorInformadoPeloUsuario);
		
		if(opcaoSelecionada == StringUtil.VALOR_INVALIDO) {
			this.apresentarOpcoesMenu();
		}

		return opcaoSelecionada;
	}

}
