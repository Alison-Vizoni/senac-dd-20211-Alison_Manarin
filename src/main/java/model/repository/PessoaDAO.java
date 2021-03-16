package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.repository.Banco;
import model.entity.Pessoa;

public class PessoaDAO {
	
	/**
	 * Cadastra uma nova pessoa na Database
	 * 
	 * @param novaPessoa o objeto com os dados a serem persistidos na tabela PESSOA
	 * 
	 * @return pessoa cadastrada com a PK
	 */
	public Pessoa cadastrarPessoa(Pessoa novaPessoa) {
		String sql = "INSERT INTO PESSOA ( NOME, DATA_NASCIMENTO, SEXO, CPF, TIPO ) VALUES ( ?, ?, ?, ?, ? )";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setString(1, novaPessoa.getNome());
			stmt.setDate(2, novaPessoa.getDataNascimento());
			stmt.setString(3, String.valueOf(novaPessoa.getSexo()));
			stmt.setString(4, novaPessoa.getcpf());
			stmt.setInt(5, novaPessoa.getTipo());
			
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
	public boolean atualizarPessoa(Pessoa atualizarPessoa) {
		
		boolean atualizou = false;
		
		String sql = "UPDATE PESSOA SET NOME = ?, DATA_NASCIMENTO = ?, SEXO = ?, CPF = ?, TIPO = ? "
				+ "	WHERE IDPESSOA = ? ";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setString(1, atualizarPessoa.getNome());
			stmt.setDate(2, atualizarPessoa.getDataNascimento());
			stmt.setString(3, String.valueOf(atualizarPessoa.getSexo()));
			stmt.setString(4, atualizarPessoa.getcpf());
			stmt.setInt(5, atualizarPessoa.getTipo());
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
	public Pessoa consutarPessoaPorId(Integer idPessoa) {
		
		Pessoa pessoaConsultada = null;
		
		String sql = "SELECT * FROM PESSOA WHERE IDPESSOA = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setInt(1, idPessoa);
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			if (resultadoConsulta.next()) {
				
				pessoaConsultada = this.converterDoResultSet(resultadoConsulta);
				pessoaConsultada.setIdPessoa(resultadoConsulta.getInt("IdPessoa"));
				pessoaConsultada.setNome(resultadoConsulta.getString("nome"));
				pessoaConsultada.setDataNascimento(resultadoConsulta.getDate("DATA_NASCIMENTO"));
				pessoaConsultada.setSexo(resultadoConsulta.getString("sexo").charAt(0));
				pessoaConsultada.setcpf(resultadoConsulta.getString("cpf"));
				pessoaConsultada.setTipo(resultadoConsulta.getInt("tipo"));
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
	public List<Pessoa> consutarTodasPessoas() {
		
		List<Pessoa> todasPessoas = new ArrayList<Pessoa>();
		
		String sql = "SELECT * FROM PESSOA";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
	
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			while (resultadoConsulta.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(resultadoConsulta.getInt("IdPessoa"));
				pessoa.setNome(resultadoConsulta.getString("nome"));
				pessoa.setDataNascimento(resultadoConsulta.getDate("DATA_NASCIMENTO"));
				pessoa.setSexo(resultadoConsulta.getString("sexo").charAt(0));
				pessoa.setcpf(resultadoConsulta.getString("cpf"));
				pessoa.setTipo(resultadoConsulta.getInt("tipo"));
				
				todasPessoas.add(pessoa);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas as pessoas: \n" + e.getMessage());
		}
		
		return todasPessoas;
	}
	
	private Pessoa converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		Pessoa pessoaConsultada = new Pessoa();
		pessoaConsultada.setIdPessoa(resultadoConsulta.getInt("IdPessoa"));
		pessoaConsultada.setNome(resultadoConsulta.getString("nome"));
		pessoaConsultada.setDataNascimento(resultadoConsulta.getDate("DATA_NASCIMENTO"));
		pessoaConsultada.setSexo(resultadoConsulta.getString("sexo").charAt(0));
		pessoaConsultada.setcpf(resultadoConsulta.getString("cpf"));
		pessoaConsultada.setTipo(resultadoConsulta.getInt("tipo"));
		return null;
	}
}
