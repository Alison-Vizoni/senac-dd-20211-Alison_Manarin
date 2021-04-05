package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Enum.TipoPessoa;
import model.entity.AplicacaoVacinaVO;
import model.entity.PessoaVO;
import model.repository.AplicacaoVacinaDAO;
import model.repository.Banco;

/**
 * 
 * @author Alison
 *
 */
public class PessoaDAO {
	
	
	/**
	 * Cadastra uma nova pessoa na Database
	 * 
	 * @param novaPessoa o objeto com os dados a serem persistidos na tabela PESSOA
	 * 
	 * @return pessoa cadastrada com a PK
	 */
	public PessoaVO cadastrarPessoa(PessoaVO novaPessoa) {
		String sql = "INSERT INTO PESSOA ( NOME, DATA_NASCIMENTO, SEXO, CPF, TIPO ) VALUES ( ?, ?, ?, ?, ? )";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);) {
			stmt.setString(1, novaPessoa.getNome());
			stmt.setDate(2, java.sql.Date.valueOf( novaPessoa.getDataNascimento()));
			stmt.setString(3, String.valueOf(novaPessoa.getSexo()));
			stmt.setString(4, novaPessoa.getcpf());
			stmt.setString(5, novaPessoa.getTipoPessoa().toString());
			
			stmt.executeUpdate();
			
			ResultSet chavesgeradas = stmt.getGeneratedKeys();
			if (chavesgeradas.next()) {
				novaPessoa.setIdPessoa(chavesgeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar pessoa: \n " + e.getMessage());
		}
		
		return novaPessoa;
	}
	
	/**
	 * Atualiza uma pessoa na Database
	 * 
	 * @param Recebe o objeto com os dados a serem atualizados na tabela PESSOA
	 * 
	 * @return Se o registro foi realizado ou nao na tabela PESSOA
	 */
	public boolean atualizarPessoa(PessoaVO atualizarPessoa) {
		
		boolean atualizou = false;
		
		String sql = "UPDATE PESSOA SET NOME = ?, DATA_NASCIMENTO = ?, SEXO = ?, CPF = ?, TIPO = ? "
				+ "	WHERE IDPESSOA = ? ";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setString(1, atualizarPessoa.getNome());
			stmt.setDate(2, java.sql.Date.valueOf(atualizarPessoa.getDataNascimento()));
			stmt.setString(3, String.valueOf(atualizarPessoa.getSexo()));
			stmt.setString(4, atualizarPessoa.getcpf());
			stmt.setString(5, atualizarPessoa.getTipoPessoa().toString());
			stmt.setInt(6, atualizarPessoa.getIdPessoa());
			
			int quantidadeLinhasAfetadas = stmt.executeUpdate();
			
			atualizou = quantidadeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar pessoa: \n " + e.getMessage());
		}
		
		return atualizou;
	}
	
	/**
	 * Exclui uma pessoa na Database
	 * 
	 * @param A PK da pessoa a ser excluida
	 * 
	 * @return informa se a pessoa foi excluida ou não
	 */
	public boolean excluirPessoa(Integer idPessoa) {
		
		boolean excluiu = false;
		
		String sql = "DELETE FROM PESSOA WHERE IDPESSOA = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setInt(1, idPessoa);

			excluiu = stmt.executeUpdate() > 0;
			
			System.out.println("Pessoa " + idPessoa + " excluida com sucesso");
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pessoa: \n " + e.getMessage());
		}
		
		return excluiu;
	}
	
	/**
	 * Consulta uma pessoa especifica na Database]
	 * 
	 * @param A PK da pessoa a ser consultada
	 * 
	 * @return A pessoa buscada na Database
	 */
	public PessoaVO consultarPessoaPorId(Integer idPessoa) {
		
		PessoaVO pessoaConsultada = new PessoaVO();
		
		String sql = "SELECT * FROM PESSOA WHERE IDPESSOA = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setInt(1, idPessoa);
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			if (resultadoConsulta.next()) {
				pessoaConsultada = this.converterDoResultSet(resultadoConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por idPessoa: \n" + e.getMessage());
		}
		
		return pessoaConsultada;
	}
	
	/**
	 * Consulta todas as pessoas cadastradas na Database
	 * 
	 * @return Todos os clientes registrados na Database
	 */
	public List<PessoaVO> consultarTodasPessoas() {
		
		List<PessoaVO> todasPessoas = new ArrayList<PessoaVO>();
		
		String sql = "SELECT * FROM PESSOA";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
	
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			while (resultadoConsulta.next()) {
				PessoaVO pessoa = this.converterDoResultSet(resultadoConsulta);

				todasPessoas.add(pessoa);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas as pessoas: \n" + e.getMessage());
		}
		
		return todasPessoas;
	}
	
	private PessoaVO converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		PessoaVO pessoaConsultada = new PessoaVO();
		pessoaConsultada.setIdPessoa(resultadoConsulta.getInt("IdPessoa"));
		pessoaConsultada.setNome(resultadoConsulta.getString("nome"));
		pessoaConsultada.setDataNascimento(resultadoConsulta.getDate("DATA_NASCIMENTO").toLocalDate());
		pessoaConsultada.setSexo(resultadoConsulta.getString("sexo").charAt(0));
		pessoaConsultada.setcpf(resultadoConsulta.getString("cpf"));
		pessoaConsultada.setTipoPessoa(TipoPessoa.getTipoPessoa(resultadoConsulta.getString("tipo")));
		
		AplicacaoVacinaDAO aplicacaoDAO = new AplicacaoVacinaDAO();
		List<AplicacaoVacinaVO> aplicacoes = aplicacaoDAO.consultarAplicacaoVacinaPorIdPessoa(pessoaConsultada.getIdPessoa());
		pessoaConsultada.setVacinacoes(aplicacoes);
		
		return pessoaConsultada;
	}

	public PessoaVO consultarPessoaPorNomeAndCpf(PessoaVO pesquisadorInformadoPeloUsuario) {
		
		String sql = " SELECT * FROM PESSOA WHERE NOME = ? AND CPF = ? ";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setString(1, pesquisadorInformadoPeloUsuario.getNome());
			stmt.setString(2, pesquisadorInformadoPeloUsuario.getcpf());
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			if (resultadoConsulta.next()) {
				pesquisadorInformadoPeloUsuario = this.converterDoResultSet(resultadoConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por idPessoa: \n" + e.getMessage());
		}
		
		return pesquisadorInformadoPeloUsuario;
	}
}
