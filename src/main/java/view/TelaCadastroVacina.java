package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.ControladoraPessoa;
import Controller.ControladoraVacina;
import model.Enum.EstagioPesquisa;
import model.Enum.FaseVacina;
import model.entity.PessoaVO;
import model.entity.VacinaVO;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class TelaCadastroVacina {

	private JFrame janela;
	private JTextField txtNomeVacina;
	private JTextField txtPaisOrigem;
	private JTextField txtDataInicioPesquisa;
	private JTextField txtNomePesquisador;
	private JTextField txtQuantidadeDoses;
	private JTextField txtCpfPesquisador;
	private JComboBox cbxEstagioPesquisa;
	private JComboBox cbxFaseVacina;
	private JComboBox cbxEstadoVacina;
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroVacina window = new TelaCadastroVacina();
					window.janela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroVacina() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		janela = new JFrame();
		janela.setTitle("Cadastro de Vacina");
		janela.setBounds(100, 100, 609, 457);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.getContentPane().setLayout(null);
		
		JLabel lblPesquisadorResponsavel = new JLabel("Nome do pesquisador respons\u00E1vel");
		lblPesquisadorResponsavel.setBounds(27, 34, 204, 14);
		janela.getContentPane().add(lblPesquisadorResponsavel);
		
		txtNomePesquisador = new JTextField();
		txtNomePesquisador.setColumns(10);
		txtNomePesquisador.setBounds(241, 31, 317, 20);
		janela.getContentPane().add(txtNomePesquisador);
		
		JLabel lblCpfPesquisador = new JLabel("Cpf do pesquisador respons\u00E1vel");
		lblCpfPesquisador.setBounds(29, 69, 202, 14);
		janela.getContentPane().add(lblCpfPesquisador);
		
		txtCpfPesquisador = new JTextField();
		txtCpfPesquisador.setColumns(10);
		txtCpfPesquisador.setBounds(241, 63, 317, 20);
		janela.getContentPane().add(txtCpfPesquisador);
		
		Button btnBuscarPesquisador = new Button("Buscar pesquisador");
		btnBuscarPesquisador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idPesquisador = 0;
				
				if (txtNomePesquisador.getText().isEmpty() || txtCpfPesquisador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "� necessario preencher o Nome e Cpf do pesquisador para verificar a sua exist�ncia no sistema", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
				} else {
					idPesquisador = VerificarPessoa();
				}
				
			}
		});
		btnBuscarPesquisador.setBounds(416, 97, 140, 22);
		janela.getContentPane().add(btnBuscarPesquisador);
		
		JLabel lblNomeVacina = new JLabel("Nome da vacina");
		lblNomeVacina.setBounds(27, 141, 90, 14);
		janela.getContentPane().add(lblNomeVacina);
		
		txtNomeVacina = new JTextField();
		txtNomeVacina.setBounds(127, 138, 429, 20);
		janela.getContentPane().add(txtNomeVacina);
		txtNomeVacina.setColumns(10);
		
		JLabel lblPaisOrigem = new JLabel("Pais de origem");
		lblPaisOrigem.setBounds(27, 179, 90, 14);
		janela.getContentPane().add(lblPaisOrigem);
		
		JLabel lblEstagioPesquisa = new JLabel("Est\u00E1gio da pesquisa");
		lblEstagioPesquisa.setBounds(27, 216, 114, 14);
		janela.getContentPane().add(lblEstagioPesquisa);
		
		JLabel lblDataInicioPesquisa = new JLabel("Data de in\u00EDcio da pesquisa");
		lblDataInicioPesquisa.setBounds(27, 255, 182, 14);
		janela.getContentPane().add(lblDataInicioPesquisa);
		
		JLabel lblFaseVacina = new JLabel("Fase da vacina");
		lblFaseVacina.setBounds(27, 286, 90, 14);
		janela.getContentPane().add(lblFaseVacina);
		
		JLabel lblQuantidadeDoses = new JLabel("Quantidade de doses");
		lblQuantidadeDoses.setBounds(27, 318, 171, 14);
		janela.getContentPane().add(lblQuantidadeDoses);
		
		txtPaisOrigem = new JTextField();
		txtPaisOrigem.setColumns(10);
		txtPaisOrigem.setBounds(127, 176, 429, 20);
		janela.getContentPane().add(txtPaisOrigem);
		
		txtDataInicioPesquisa = new JTextField();
		txtDataInicioPesquisa.setColumns(10);
		txtDataInicioPesquisa.setBounds(219, 252, 337, 20);
		janela.getContentPane().add(txtDataInicioPesquisa);
		
		JLabel lblEstagioVacina = new JLabel("Estado da vacina");
		lblEstagioVacina.setBounds(27, 351, 102, 14);
		janela.getContentPane().add(lblEstagioVacina);
		
		cbxEstagioPesquisa = new JComboBox();
		cbxEstagioPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Inicial", "Testes", "Aplica��o massiva"}));
		cbxEstagioPesquisa.setBounds(159, 213, 397, 20);
		janela.getContentPane().add(cbxEstagioPesquisa);
		
		cbxFaseVacina = new JComboBox();
		cbxFaseVacina.setModel(new DefaultComboBoxModel(new String[] {"Somente pesquisador", "Volunt�rio", "P�blico geral"}));
		cbxFaseVacina.setBounds(159, 283, 397, 20);
		janela.getContentPane().add(cbxFaseVacina);
		
		cbxEstadoVacina = new JComboBox();
		cbxEstadoVacina.setModel(new DefaultComboBoxModel(new String[] {"Ativada", "Desativada"}));
		cbxEstadoVacina.setBounds(159, 348, 397, 20);
		janela.getContentPane().add(cbxEstadoVacina);
		
		Button btnSalvarVacina = new Button("Salvar vacina");
		btnSalvarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNomePesquisador.getText().isEmpty() || txtCpfPesquisador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "� necessario preencher o Nome e Cpf do pesquisador para verificar a sua exist�ncia no sistema", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
				} else {
					VerificarPessoa();
				}
				
				boolean flag = true;
				
				while (flag) {
					if (txtNomeVacina.getText().isEmpty() 
							|| txtPaisOrigem.getText().isEmpty()
							|| txtDataInicioPesquisa.getText().isEmpty()
							|| txtQuantidadeDoses.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "� necessario preencher todos os campos para cadastrar a vacina", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
						flag = true;
					} else {
						flag = false;
						CadastrarVacina();
					}
				}
			}
		});
		btnSalvarVacina.setBounds(433, 386, 125, 22);
		janela.getContentPane().add(btnSalvarVacina);
		
		txtQuantidadeDoses = new JTextField();
		txtQuantidadeDoses.setColumns(10);
		txtQuantidadeDoses.setBounds(208, 315, 348, 20);
		janela.getContentPane().add(txtQuantidadeDoses);
		
	}

	protected int VerificarPessoa() {
		PessoaVO pesquisador = new PessoaVO();
		VacinaVO novaVacina = new VacinaVO();

		pesquisador.setNome(txtNomePesquisador.getText());
		pesquisador.setcpf(txtCpfPesquisador.getText());
		
		ControladoraPessoa controladoraPessoa = new ControladoraPessoa();
		pesquisador = controladoraPessoa.consultarPessoaController(pesquisador);
		
		String resultado = "";
		
		if (pesquisador.getIdPessoa() > 0) {
			novaVacina.setPesquisadorResponsavel(pesquisador.getIdPessoa());
			resultado = "C�digo ID do pesquisador: " + pesquisador.getIdPessoa();
		} else {
			JOptionPane.showMessageDialog(null, "Pesquisador ainda n�o cadastrado! Favor cadastrar!", "MENU VACINA",
					JOptionPane.WARNING_MESSAGE);
			MenuPessoa menuPessoa = new MenuPessoa();
			int idPesquisador = menuPessoa.cadastrarPessoa();
			
			JOptionPane.showMessageDialog(null, "C�digo ID do pesquisador: " + idPesquisador, "MENU VACINA",
					JOptionPane.INFORMATION_MESSAGE);
			
			novaVacina.setPesquisadorResponsavel(idPesquisador);
		}
		JOptionPane.showMessageDialog(null, resultado);
		
		return pesquisador.getIdPessoa();
		
	}

	protected void CadastrarVacina() {
		VacinaVO novaVacina = new VacinaVO();
		
		novaVacina.setNome(txtNomeVacina.getText());
		novaVacina.setPaisDeOrigem(txtPaisOrigem.getText());
		
		if (cbxEstagioPesquisa.getSelectedItem().toString().equals("Inicial")) {
			novaVacina.setEstagioPesquisa(EstagioPesquisa.INICIAL);
		} else if (cbxEstagioPesquisa.getSelectedItem().toString().equals("Testes")) {
			novaVacina.setEstagioPesquisa(EstagioPesquisa.TESTES);
		} else if (cbxEstagioPesquisa.getSelectedItem().toString().equals("Aplica��o massiva")) {
			novaVacina.setEstagioPesquisa(EstagioPesquisa.APLICACAO_MASSIVA);
		}
		
		novaVacina.setDataInicioPesquisa(LocalDate.parse(txtDataInicioPesquisa.getText(), dataFormatter));
		
		if (cbxFaseVacina.getSelectedItem().toString().equals("Somente pesquisador")) {
			novaVacina.setFase(FaseVacina.SOMENTE_PESQUISADOR);
		} else if (cbxFaseVacina.getSelectedItem().toString().equals("Volunt�rio")) {
			novaVacina.setFase(FaseVacina.VOLUNTARIO);
		} else if (cbxFaseVacina.getSelectedItem().toString().equals("P�blico geral")) {
			novaVacina.setFase(FaseVacina.PUBLICO_GERAL);
		}
		
		novaVacina.setQuantidadeDoses(Integer.parseInt(txtQuantidadeDoses.getText()));
		
		if (cbxEstadoVacina.getSelectedItem().toString().equals("Ativada")) {
			novaVacina.setVacinaAtiva(true);
		} else {
			novaVacina.setVacinaAtiva(false);
		}
		
		int idPesquisador = VerificarPessoa();
		novaVacina.setPesquisadorResponsavel(idPesquisador);
		
		ControladoraVacina controladoraVacina = new ControladoraVacina();
		String resultado = controladoraVacina.cadastrarVacinaController(novaVacina);
		JOptionPane.showMessageDialog(null, resultado);
	}
}