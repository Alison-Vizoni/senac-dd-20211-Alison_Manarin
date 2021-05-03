package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import Controller.ControladoraVacina;
import model.entity.VacinaVO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaConsultarVacinas extends JFrame {

	private JPanel contentPane;
	private JTable tblListaVacina;
	private JButton btnExcluirVacina;
	private JButton btnEditarVacina;
	private List<VacinaVO> vacinas;
	private ControladoraVacina controladoraVacina = new ControladoraVacina();
	private String[] nomesColunas = {"Nome da vacina", "Pesquisador responsável", "pais de origem", "estagio da pesquisa", "fase", "Quatidade de doses", "Data de inicio"};

	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JTextField txtNomePesquisador;
	private JTextField txtNomeVacina;
	private DatePickerSettings dateSettings;
	private DatePicker dataTeste;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultarVacinas frame = new TelaConsultarVacinas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaConsultarVacinas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 999, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConsultarVacinas = new JButton("Consultar vacinas");
		btnConsultarVacinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarTabelaVacina();
			}
		});
		btnConsultarVacinas.setBounds(328, 129, 243, 23);
		contentPane.add(btnConsultarVacinas);
		
		btnExcluirVacina = new JButton("Excluir vacina");
		btnExcluirVacina.setEnabled(false);
		btnExcluirVacina.setBounds(113, 392, 205, 23);
		btnExcluirVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indiceSelecionadoNaTablela = tblListaVacina.getSelectedRow();
				VacinaVO vacinaSelecionada = vacinas.get(indiceSelecionadoNaTablela - 1);
				
				String perguntaExclusao = "Deseja excluir a vacina: " + vacinaSelecionada.toString() + "?";
				
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, perguntaExclusao, "Está certo disso?", JOptionPane.YES_NO_OPTION);
				
				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					String mensagem = controladoraVacina.excluirVacinaController(vacinaSelecionada);
					JOptionPane.showMessageDialog(null, mensagem);
					atualizarTabelaVacina();
				}
				
			}
		});
		contentPane.add(btnExcluirVacina);
		
		btnEditarVacina = new JButton("Editar vacina");
		btnEditarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinaVO vacinaSelecionada = vacinas.get(tblListaVacina.getSelectedRow() - 1);
				TelaCadastroVacina cadastroVacina = new TelaCadastroVacina(vacinaSelecionada);
				cadastroVacina.getJanela().setVisible(true);
			}
		});
		btnEditarVacina.setEnabled(false);
		btnEditarVacina.setBounds(576, 392, 205, 23);
		contentPane.add(btnEditarVacina);
		
		tblListaVacina = new JTable();
		this.limparTabelaVacina();
		
		tblListaVacina.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		) {
			@Override
			public boolean isCellEditable(int rowIndex, int collIndex) {
				return false;
			}
		});
		tblListaVacina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int indiceSelecionado = tblListaVacina.getSelectedRow();
				
				if (indiceSelecionado > 0) {
					btnEditarVacina.setEnabled(true);
					btnExcluirVacina.setEnabled(true);
				} else {
					btnEditarVacina.setEnabled(false);
					btnExcluirVacina.setEnabled(false);
				}
			}
		});
		tblListaVacina.setBounds(10, 163, 963, 218);
		contentPane.add(tblListaVacina);
		
		JLabel lblNewLabel = new JLabel("Nome do pesquisador");
		lblNewLabel.setBounds(34, 11, 136, 14);
		contentPane.add(lblNewLabel);
		
		txtNomePesquisador = new JTextField();
		txtNomePesquisador.setBounds(34, 36, 243, 20);
		contentPane.add(txtNomePesquisador);
		txtNomePesquisador.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome da vacina");
		lblNewLabel_1.setBounds(328, 11, 136, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNomeVacina = new JTextField();
		txtNomeVacina.setBounds(328, 36, 243, 20);
		contentPane.add(txtNomeVacina);
		txtNomeVacina.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Data de in\u00EDcio da pesquisa");
		lblNewLabel_2.setBounds(625, 11, 182, 14);
		contentPane.add(lblNewLabel_2);
		
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dataTeste = new DatePicker(dateSettings);
		dataTeste.setBounds(625, 36, 243, 20);
		contentPane.add(dataTeste);
	}

	protected void atualizarTabelaVacina() {
		vacinas = controladoraVacina.consultarVacinaController();
		this.limparTabelaVacina();
		
		DefaultTableModel model = (DefaultTableModel) this.tblListaVacina.getModel();
		
		for(VacinaVO vac: this.vacinas) {
			
			if(vac.isVacinaAtiva()) {
				Object[] novaLinhaTabela = new Object[7];
				
				novaLinhaTabela[0] = vac.getNome();
				novaLinhaTabela[1] = vac.getPesquisadorResponsavel();
				novaLinhaTabela[2] = vac.getPaisDeOrigem();
				novaLinhaTabela[3] = vac.getEstagioPesquisa();
				novaLinhaTabela[4] = vac.getFase();
				novaLinhaTabela[5] = vac.getQuantidadeDoses();
				novaLinhaTabela[6] = vac.getDataInicioPesquisa();
				
				model.addRow(novaLinhaTabela);
			}
		}
	}
	
	private void limparTabelaVacina() {
		tblListaVacina.setModel(new DefaultTableModel(new Object[][] {nomesColunas, }, nomesColunas));
		btnEditarVacina.setEnabled(false);
		btnExcluirVacina.setEnabled(false);		
	}
}
