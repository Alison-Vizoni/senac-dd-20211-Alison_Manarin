package view;

import javax.swing.JOptionPane;

import Util.StringUtil;

public class MenuRelatorio {

	private static final int OPCAO_RELATORIO_VACINA = 1;
	private static final int OPCAO_RELATORIO_VOLTAR = 2;

	public void apresentarMenuRelatorio() {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_RELATORIO_VOLTAR) {
			switch (opcao) {
				case OPCAO_RELATORIO_VACINA: {
					TelaConsultarVacinas telaConsultarVacina = new TelaConsultarVacinas();
					telaConsultarVacina.setVisible(true);
					opcao = OPCAO_RELATORIO_VOLTAR;
				break;
				}
				default: {
					JOptionPane.showMessageDialog(null, "Opção inválida");
				break;
				}	
			}
		}
	}

	private int apresentarOpcoesMenu() {
		StringBuilder msg = new StringBuilder();
		msg.append("Opções:\n");
		msg.append(OPCAO_RELATORIO_VACINA + " - Relatório de todas vacinas ativas. \n");
		msg.append(OPCAO_RELATORIO_VOLTAR + " - Voltar\n");
		msg.append("\nDigite a opção: ");
		
		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Menu Relatorio",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = StringUtil.formatarStringParaInteiro(valorInformadoPeloUsuario);
		
		if(opcaoSelecionada == StringUtil.VALOR_INVALIDO) {
			this.apresentarOpcoesMenu();
		}

		return opcaoSelecionada;
	}

}
