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
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private VacinaVO vacinaVO;
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroVacina window = new TelaCadastroVacina(null);
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
	public TelaCadastroVacina(VacinaVO vacinaVO) {
		this.vacinaVO = vacinaVO;
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
		
		JButton btnBuscarPesquisador = new JButton("Buscar pesquisador");
		btnBuscarPesquisador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idPesquisador = 0;
				
				if (txtNomePesquisador.getText().isEmpty() || txtCpfPesquisador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "É necessario preencher o Nome e Cpf do pesquisador para verificar a sua existência no sistema", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
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
		cbxEstagioPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Inicial", "Testes", "Aplicação massiva"}));
		cbxEstagioPesquisa.setBounds(159, 213, 397, 20);
		janela.getContentPane().add(cbxEstagioPesquisa);
		
		cbxFaseVacina = new JComboBox();
		cbxFaseVacina.setModel(new DefaultComboBoxModel(new String[] {"Somente pesquisador", "Voluntário", "Público geral"}));
		cbxFaseVacina.setBounds(159, 283, 397, 20);
		janela.getContentPane().add(cbxFaseVacina);
		
		cbxEstadoVacina = new JComboBox();
		cbxEstadoVacina.setModel(new DefaultComboBoxModel(new String[] {"Ativada", "Desativada"}));
		cbxEstadoVacina.setBounds(159, 348, 397, 20);
		janela.getContentPane().add(cbxEstadoVacina);
		
		JButton btnSalvarVacina = new JButton("Salvar vacina");
		btnSalvarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNomePesquisador.getText().isEmpty() || txtCpfPesquisador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "É necessario preencher o Nome e Cpf do pesquisador para verificar a sua existência no sistema", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
				} else {
					VerificarPessoa();
				}
				
				boolean flag = true;
				
				while (flag) {
					if (txtNomeVacina.getText().isEmpty() 
							|| txtPaisOrigem.getText().isEmpty()
							|| txtDataInicioPesquisa.getText().isEmpty()
							|| txtQuantidadeDoses.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "É necessario preencher todos os campos para cadastrar a vacina", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
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
		
		JButton btnAtualizarVacina = new JButton("Atualizar vacina");
		btnAtualizarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNomePesquisador.getText().isEmpty() || txtCpfPesquisador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "É necessario preencher o Nome e Cpf do pesquisador para verificar a sua existência no sistema", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
				} else {
					VerificarPessoa();
				}
				
				boolean flag = true;
				
				while (flag) {
					if (txtNomeVacina.getText().isEmpty() 
							|| txtPaisOrigem.getText().isEmpty()
							|| txtDataInicioPesquisa.getText().isEmpty()
							|| txtQuantidadeDoses.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "É necessario preencher todos os campos para cadastrar a vacina", "Cadastro de vacina", JOptionPane.WARNING_MESSAGE);
						flag = true;
					} else {
						flag = false;
						CadastrarVacina();
					}
				}
			}
		});
		btnAtualizarVacina.setBounds(433, 386, 125, 22);
		janela.getContentPane().add(btnAtualizarVacina);
		
		txtQuantidadeDoses = new JTextField();
		txtQuantidadeDoses.setColumns(10);
		txtQuantidadeDoses.setBounds(208, 315, 348, 20);
		janela.getContentPane().add(txtQuantidadeDoses);
		
		if (vacinaVO != null) {
			preencerVacinaNaTela(vacinaVO);
			janela.setTitle("Edição de Endereço");
			janela.getContentPane().add(btnAtualizarVacina);
		} else {
			janela.getContentPane().add(btnSalvarVacina);
			janela.setTitle("Cadastro de Vacina");
		}
	}

	private void preencerVacinaNaTela(VacinaVO v) {
		
		PessoaVO pesquisador = RetornarPesquisador(v.getPesquisadorResponsavel());
		
		this.txtNomePesquisador.setText(pesquisador.getNome());
		this.txtCpfPesquisador.setText(pesquisador.getCpf());
		
		this.txtNomeVacina.setText(v.getNome());
		this.txtPaisOrigem.setText(v.getPaisDeOrigem());
		this.cbxEstagioPesquisa.setSelectedItem(v.getEstagioPesquisa());
		
		String mes = String.valueOf(v.getDataInicioPesquisa().getMonthValue());
		
		if(v.getDataInicioPesquisa().getMonthValue() < 10) {
			mes = "0" + v.getDataInicioPesquisa().getMonthValue();
		} else {
			mes = String.valueOf(v.getDataInicioPesquisa().getMonthValue());
		}
		txtDataInicioPesquisa.setText(v.getDataInicioPesquisa().getDayOfMonth() +"/"+ mes +"/"+ v.getDataInicioPesquisa().getYear());
		this.cbxFaseVacina.setSelectedItem(v.getFase());
		this.txtQuantidadeDoses.setText(String.valueOf(v.getQuantidadeDoses()));
		this.cbxEstadoVacina.setSelectedItem(v.isVacinaAtiva());
	}

	private PessoaVO RetornarPesquisador(int pesquisadorResponsavel) {
		ControladoraPessoa controladoraPessoa = new ControladoraPessoa();
		PessoaVO pesquisador = controladoraPessoa.consultarPessoaPorId(pesquisadorResponsavel);
		return pesquisador;
	}

	protected int VerificarPessoa() {
		PessoaVO pesquisador = new PessoaVO();
		VacinaVO novaVacina = new VacinaVO();

		pesquisador.setNome(txtNomePesquisador.getText());
		pesquisador.setcpf(txtCpfPesquisador.getText());
		
		ControladoraPessoa controladoraPessoa = new ControladoraPessoa();
		pesquisador = controladoraPessoa.consultarPessoaController(pesquisador);
		
		String resultado = "Pesquisador Válido";
		
		if (pesquisador.getIdPessoa() > 0) {
			novaVacina.setPesquisadorResponsavel(pesquisador.getIdPessoa());
		} else {
			JOptionPane.showMessageDialog(null, "Pesquisador ainda não cadastrado! Favor cadastrar!", "MENU VACINA",
					JOptionPane.WARNING_MESSAGE);
			MenuPessoa menuPessoa = new MenuPessoa();
			int idPesquisador = menuPessoa.cadastrarPessoa();
			
			JOptionPane.showMessageDialog(null, "Código ID do pesquisador: " + idPesquisador, "MENU VACINA",
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
		} else if (cbxEstagioPesquisa.getSelectedItem().toString().equals("Aplicação massiva")) {
			novaVacina.setEstagioPesquisa(EstagioPesquisa.APLICACAO_MASSIVA);
		}
		
		novaVacina.setDataInicioPesquisa(LocalDate.parse(txtDataInicioPesquisa.getText(), dataFormatter));
		
		if (cbxFaseVacina.getSelectedItem().toString().equals("Somente pesquisador")) {
			novaVacina.setFase(FaseVacina.SOMENTE_PESQUISADOR);
		} else if (cbxFaseVacina.getSelectedItem().toString().equals("Voluntário")) {
			novaVacina.setFase(FaseVacina.VOLUNTARIO);
		} else if (cbxFaseVacina.getSelectedItem().toString().equals("Público geral")) {
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
		String resultado ="";
		
		if (vacinaVO == null) {
			resultado = controladoraVacina.cadastrarVacinaController(novaVacina);
		} else {
			novaVacina.setIdVacina(vacinaVO.getIdVacina());
			resultado = controladoraVacina.atualizarVacina(novaVacina);
		}
		
		JOptionPane.showMessageDialog(null, resultado);
	}

	public JFrame getJanela() {
		return janela;
	}
	
	public VacinaVO getVacinaVO() {
		return vacinaVO;
	}
	
	public void setVacinaVO(VacinaVO vacinaVO) {
		this.vacinaVO = vacinaVO;
	}
}
