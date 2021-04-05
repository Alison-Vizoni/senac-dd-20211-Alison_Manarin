package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Controller.ControladoraPessoa;
import Controller.ControladoraVacina;
import Util.StringUtil;
import jdk.nashorn.internal.scripts.JO;
import model.Enum.EstagioPesquisa;
import model.Enum.FaseVacina;
import model.entity.PessoaVO;
import model.entity.VacinaVO;
import model.repository.PessoaDAO;

public class MenuVacina { 
	
	private static final int OPCAO_MENU_CADASTRAR_VACINA = 1;
	private static final int OPCAO_MENU_CONSULTAR_TODAS_VACINAS = 2;
	private static final int OPCAO_MENU_EXCLUIR_VACINA = 3;
	private static final int OPCAO_MENU_VACINA_VOLTAR = 4;
	
	private static final int OPCAO_VACINA_SOMENTE_PESQUISADOR = 1;
	private static final int OPCAO_VACINA_VOLUNTARIOS = 2;
	private static final int OPCAO_VACINA_PUBLICO_GERAL = 3;
	private static final int OPCAO_VACINA_FIM = 99;
	
	private static final int OPCAO_ESTAGIO_INICIAL = 1;
	private static final int OPCAO_ESTAGIO_TESTES = 2;
	private static final int OPCAO_ESTAGIO_APLICACAO_MASSIVA = 3;
	private static final int OPCAO_ESTAGIO_FIM = 99;

	public void apresentarMenuVacina() {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_VACINA_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_VACINA: {
					this.cadastrarVacina();
					break;
				}
				case OPCAO_MENU_CONSULTAR_TODAS_VACINAS: {
					this.consultarTodasVacina();
					break;
				}
				case OPCAO_MENU_EXCLUIR_VACINA: {
					this.excluirVacina();
					break;
				}
				default: {
					JOptionPane.showMessageDialog(null, "\nOpção inválida!");
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
	}

	private int apresentarOpcoesMenu() {
		StringBuilder msg = new StringBuilder();
		msg.append("Opções:\n");
		msg.append(OPCAO_MENU_CADASTRAR_VACINA + " - Cadastrar Vacina\n");
		msg.append(OPCAO_MENU_CONSULTAR_TODAS_VACINAS + " - Consultar todas as Vacinas\n");
		msg.append(OPCAO_MENU_EXCLUIR_VACINA + " - Excluir Vacina\n");
		msg.append(OPCAO_MENU_VACINA_VOLTAR + " - Voltar\n");
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
		PessoaVO pessoaVO = new PessoaVO();
		ControladoraPessoa controladoraPessa = new ControladoraPessoa();
		
		String nomeInformadoPeloUsuario = JOptionPane.showInputDialog(null, "Digite o Nome");
		vacinaVO.setNome(nomeInformadoPeloUsuario);
		
		String paisInformadoPeloUsuario = JOptionPane.showInputDialog(null, "Digite o Pais de Origem");
		vacinaVO.setPaisDeOrigem(paisInformadoPeloUsuario);
		
		int estagio = this.apresentarOpcoesEstagio();
		while(estagio != OPCAO_ESTAGIO_FIM) {
			switch(estagio) {
				case OPCAO_ESTAGIO_INICIAL: {
					estagio = OPCAO_ESTAGIO_FIM;
					vacinaVO.setEstagioPesquisa(EstagioPesquisa.INICIAL);
					break;
				}
				case OPCAO_ESTAGIO_TESTES: {
					estagio = OPCAO_ESTAGIO_FIM;
					vacinaVO.setEstagioPesquisa(EstagioPesquisa.TESTES);
					break;
				}
				case OPCAO_ESTAGIO_APLICACAO_MASSIVA: {
					estagio = OPCAO_ESTAGIO_FIM;
					vacinaVO.setEstagioPesquisa(EstagioPesquisa.APLICACAO_MASSIVA);
					break;
				}
				default: {
					JOptionPane.showMessageDialog(null, "\nOpção inválida!");
					estagio = this.apresentarOpcoesEstagio();
				}
			}
		}
		
		LocalDate dataInformadoPeloUsuario = LocalDate.parse(JOptionPane.showInputDialog(null, "Digite a Data de Inicio da Pesquisa"));
		vacinaVO.setDataInicioPesquisa(dataInformadoPeloUsuario);
		
		int dosesInformadoPeloUsuario = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade de doses"));
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
					JOptionPane.showMessageDialog(null, "\nOpção inválida!");
					opcao = this.apresentarOpcoesFaseVacina();
				}
			}
		}
		
		PessoaVO pesquisadorInformadoPeloUsuario = new PessoaVO();
		
		int idPesquisador = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do responsavel pela vacina"));
		
		ControladoraPessoa controladoraPessoa = new ControladoraPessoa();
		pesquisadorInformadoPeloUsuario = controladoraPessoa.consultarPessoaController(idPesquisador);
		
		String resultado = "";
		
		if (pesquisadorInformadoPeloUsuario.getIdPessoa() >= 0) {
			vacinaVO.setPesquisadorResponsavel(pesquisadorInformadoPeloUsuario);
			
			ControladoraVacina controladoraVacina = new ControladoraVacina();
			resultado = controladoraVacina.cadastrarVacinaController(vacinaVO);
		} else {
			resultado = "Pesquisador inexistente";
		}
		JOptionPane.showMessageDialog(null, resultado);
	}

	private void consultarTodasVacina() {
		
		ControladoraVacina controladoraVacina = new ControladoraVacina();
		List<VacinaVO> todasVacinas = controladoraVacina.consultarVacinaController();
		String listaVacina = "";
		
		for (VacinaVO vacinaVO : todasVacinas) {
			listaVacina += vacinaVO + "\n";
		}
		
		JOptionPane.showMessageDialog(null, listaVacina, "Vacinas", JOptionPane.INFORMATION_MESSAGE, null);
	}

	private void excluirVacina() {
		VacinaVO vacinaVO = new VacinaVO();
		
		String nome = JOptionPane.showInputDialog(null, "Dgite o nome da vacina a ser excluida");
		vacinaVO.setNome(nome);
		
		String paisOrigem = JOptionPane.showInputDialog(null, "Digite o nome do pais de origem da vacina a ser excluida");
		vacinaVO.setPaisDeOrigem(paisOrigem);
		
		ControladoraVacina controladoraVacina = new ControladoraVacina();
		String resultado = controladoraVacina.excluirVacinaController(vacinaVO);
		JOptionPane.showMessageDialog(null, resultado);
	}
	
	private int apresentarOpcoesEstagio() {
		StringBuilder msg = new StringBuilder();
		msg.append("\nOpções:\n");
		msg.append(OPCAO_ESTAGIO_INICIAL + " - INICIAL\n");
		msg.append(OPCAO_ESTAGIO_TESTES + " - TESTES\n");
		msg.append(OPCAO_ESTAGIO_APLICACAO_MASSIVA + " - APLICACAO_MASSIVA\n");
		msg.append("\nDigite a opção:");
		
		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Estagio Pesquisa",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = StringUtil.formatarStringParaInteiro(valorInformadoPeloUsuario);
		
		if(opcaoSelecionada == StringUtil.VALOR_INVALIDO) {
			this.apresentarOpcoesMenu();
		}
		
		return opcaoSelecionada;
	}

	private int apresentarOpcoesFaseVacina() {
		StringBuilder msg = new StringBuilder();
		msg.append("Opções:\n");
		msg.append(OPCAO_VACINA_SOMENTE_PESQUISADOR + " - Somete pesquisador\n");
		msg.append(OPCAO_VACINA_VOLUNTARIOS + " - Voluntarios\n");
		msg.append(OPCAO_VACINA_PUBLICO_GERAL + " - Publico geral\n");
		msg.append("\nDigite a opção:");

		String valorInformadoPeloUsuario = JOptionPane.showInputDialog(null, msg, "Fase vacina",
				JOptionPane.INFORMATION_MESSAGE);

		int opcaoSelecionada = StringUtil.formatarStringParaInteiro(valorInformadoPeloUsuario);
		
		if(opcaoSelecionada == StringUtil.VALOR_INVALIDO) {
			this.apresentarOpcoesMenu();
		}
		
		return opcaoSelecionada;
	}

}
