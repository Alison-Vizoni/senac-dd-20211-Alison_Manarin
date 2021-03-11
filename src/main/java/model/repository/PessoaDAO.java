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
	
	public Pessoa cadastrar(Pessoa novaPessoa) {
		String sql = "INSERT INTO PESSOA ( NOME, DATA_NASCIMENTO, SEXO, CPF, TIPO ) VALUES ( ?, ?, ?, ?, ? )";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
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
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conex�o (cadastro de pessoa): \n " + e.getMessage());
			}
			
		}
		
		return novaPessoa;
	}
	
	public boolean atualizar(Pessoa atualizarPessoa) {
		
		boolean atualizou = false;
		
		String sql = "UPDATE PESSOA SET NOME = ?, DATA_NASCIMENTO = ?, SEXO = ?, CPF = ?, TIPO = ? "
				+ "	WHERE IDPESSOA = ? ";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
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
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conex�o (atualizar pessoa): \n " + e.getMessage());
			}
			
		}
		
		return atualizou;
	}
	
	public boolean excluir(Integer idPessoa) {
		
		boolean excluiu = false;
		
		String sql = "DELETE FROM PESSOA WHERE IDPESSOA = ?";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setInt(1, idPessoa);

			excluiu = stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pessoa: \n " + e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conex�o (excluir pessoa): \n " + e.getMessage());
			}
			
		}
		
		return excluiu;
	}
	
	public Pessoa consutarPorId(Integer idPessoa) {
		
		Pessoa pessoaConsultada = null;
		
		String sql = "SELECT * FROM PESSOA WHERE IDPESSOA = ?";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setInt(1, idPessoa);
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			if (resultadoConsulta.next()) {
				pessoaConsultada = new Pessoa();
				pessoaConsultada.setIdPessoa(resultadoConsulta.getInt("IdPessoa"));
				pessoaConsultada.setNome(resultadoConsulta.getString("nome"));
				pessoaConsultada.setDataNascimento(resultadoConsulta.getDate("dataNascimento"));
				pessoaConsultada.setSexo(resultadoConsulta.getString("sexo").charAt(0));
				pessoaConsultada.setcpf(resultadoConsulta.getString("cpf"));
				pessoaConsultada.setTipo(resultadoConsulta.getInt("tipo"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por idPessoa: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conex�o (consultar pessoa por idPessoa): \n" + e.getMessage());

			}
		}
		
		return pessoaConsultada;
	}
	
	public List<Pessoa> consutarTodasPessoas() {
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		String sql = "SELECT * FROM PESSOA";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
	
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			while (resultadoConsulta.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(resultadoConsulta.getInt("IdPessoa"));
				pessoa.setNome(resultadoConsulta.getString("nome"));
				pessoa.setDataNascimento(resultadoConsulta.getDate("dataNascimento"));
				pessoa.setSexo(resultadoConsulta.getString("sexo").charAt(0));
				pessoa.setcpf(resultadoConsulta.getString("cpf"));
				pessoa.setTipo(resultadoConsulta.getInt("tipo"));
				
				pessoas.add(pessoa);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas as pessoas: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conex�o (consultar todas as pessoas): \n" + e.getMessage());

			}
		}
		
		return pessoas;
	}
}
