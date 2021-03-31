package view;

import javax.swing.JOptionPane;

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
					System.out.println("\nOp��o inv�lida!");
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
		
	}
	
	private int apresentarOpcoesMenu() {
		String mensagem = "Op��es:\n";
		mensagem += OPCAO_MENU_PESSOA + " - Menu Pessoa \n";
		mensagem += OPCAO_MENU_VACINA + " - Menu Vacina \n";
		mensagem += OPCAO_MENU_APLICACAO_VACINA + " - Menu Aplica��o Vacina \n";
		mensagem += OPCAO_MENU_RELATORIO + " - Menu Relat�rio \n";
		mensagem += OPCAO_MENU_SAIR + " - Sair \n";
		mensagem += "\nDigite a op��o: ";

		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, mensagem, "Menu Principal Vacina��o",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = 0;
		try {
			opcaoSelecionada = Integer.parseInt(valorInformadoPeloUsuario);
		} catch (NumberFormatException excecao) {
			JOptionPane.showMessageDialog(null, "Informe um valor num�rico");
			this.apresentarOpcoesMenu();
		}

		return opcaoSelecionada;
	}

}
