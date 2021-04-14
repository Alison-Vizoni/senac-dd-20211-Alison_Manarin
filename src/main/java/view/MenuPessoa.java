package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import Controller.ControladoraPessoa;
import Util.StringUtil;
import model.Enum.TipoPessoa;
import model.entity.PessoaVO;

public class MenuPessoa {
	private static final int OPCAO_MENU_CADASTRAR_PESSOA = 1;
	private static final int OPCAO_MENU_VOLTAR = 2;
	
	private static final int OPCAO_PESSOA_FIM = 99;
	private static final int OPCAO_PESQUISADOR = 1;
	private static final int OPCAO_PACIENTE = 2;
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void apresentarMenuPessoa() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_VOLTAR) {
			switch (opcao) {
				case OPCAO_MENU_CADASTRAR_PESSOA : {
					this.cadastrarPessoa();
					opcao = OPCAO_MENU_VOLTAR;
					break;
				}
				default : {
					JOptionPane.showMessageDialog(null, "\nOpção inválida!");
				}
			}
		}
	}

	private int apresentarOpcoesMenu() {
		StringBuilder msg = new StringBuilder();
		msg.append("Opções:\n");
		msg.append(OPCAO_MENU_CADASTRAR_PESSOA + " Cadastrar Pessoa\n");
		msg.append(OPCAO_MENU_VOLTAR + " Voltar\n");
		msg.append("\nDigite a opção: ");
		
		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Menu Vacina",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = StringUtil.formatarStringParaInteiro(valorInformadoPeloUsuario);
		
		if(opcaoSelecionada == StringUtil.VALOR_INVALIDO) {
			this.apresentarOpcoesMenu();
		}
		
		return opcaoSelecionada;
	}

	public int cadastrarPessoa() {
		
		PessoaVO pessoaVO = new PessoaVO();
		ControladoraPessoa pessoaController = new ControladoraPessoa();
		
		String nome = JOptionPane.showInputDialog(null, "Digite o nome da pessoa");
		pessoaVO.setNome(nome);		
		
		LocalDate dataNascimento = (LocalDate.parse(JOptionPane.showInputDialog(null,
				"Digite a data de nascimento  da pessoa"), dataFormatter));
		pessoaVO.setDataNascimento(dataNascimento);
		 
		char sexo = JOptionPane.showInputDialog(null, "Digite o sexo  da pessoa").charAt(0);
		pessoaVO.setSexo(sexo);

		String cpf = JOptionPane.showInputDialog(null, "Digite o cpf  da pessoa");
		pessoaVO.setCpf(cpf); 

		int tipoPessoa = this.apresentarTipoPessoa();
		while (tipoPessoa != OPCAO_PESSOA_FIM) {
			switch(tipoPessoa) {
				case OPCAO_PESQUISADOR : {
					pessoaVO.setTipoPessoa(TipoPessoa.PESQUISADOR);
					tipoPessoa = OPCAO_PESSOA_FIM;
					break;
				}
				case OPCAO_PACIENTE : {
					pessoaVO.setTipoPessoa(TipoPessoa.PACIENTE);
					tipoPessoa = OPCAO_PESSOA_FIM;
					break;
				}
				default : {
					JOptionPane.showMessageDialog(null, "\nOpção inválida!");
					tipoPessoa = this.apresentarTipoPessoa();
				}
			}
		}
		
		int retorno = pessoaController.cadastrarPessoaController(pessoaVO);
		
		if (pessoaVO.getIdPessoa() > 0) {
			JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar a Pessoa");
		}

		return retorno;
	}

	private int apresentarTipoPessoa() {
		StringBuilder msg = new StringBuilder();
		msg.append("\nOpções:\n");
		msg.append(OPCAO_PESQUISADOR + " Pessoa é Pesquisador(a) \n");
		msg.append(OPCAO_PACIENTE + " Pessoa é um paciente \n");
		msg.append("\nDigite a opção: ");
		
		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Tipo Pessoa",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = StringUtil.formatarStringParaInteiro(valorInformadoPeloUsuario);
		
		if(opcaoSelecionada == StringUtil.VALOR_INVALIDO) {
			this.apresentarMenuPessoa();
		}
		
		return opcaoSelecionada;
	}

}
