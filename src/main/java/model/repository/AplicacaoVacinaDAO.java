package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.AplicacaoVacina;

public class AplicacaoVacinaDAO {
	
	/**
	 * Cadastra uma nova aplicacao de vacina na Database
	 * 
	 * @param Recebe o objeto com os dados a serem persistidos na tabela APLICACAO_VACINA
	 * 
	 * @return A aplicacao vacina cadastrada na Database
	 */
	public AplicacaoVacina cadastrarAplicacaoVacina(AplicacaoVacina novaAplicacaoVacina) {
		
		String sql = "INSERT INTO APLICACAO_VACINA (IDVACINA, DATA_APLICACAO, NOTA) VALUES ( ?, ?, ? )";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setObject(1, novaAplicacaoVacina.getIdVacina());
			stmt.setDate(2, novaAplicacaoVacina.getDataAplicacao());
			stmt.setInt(3, novaAplicacaoVacina.getNota());
			
			stmt.executeUpdate();
			
			ResultSet chavesgeradas = stmt.getGeneratedKeys();
			if (chavesgeradas.next()) {
				novaAplicacaoVacina.setIdAplicacaoVacina(chavesgeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar aplicacao vacina: \n " + e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (cadastro de aplicacao vacina): \n " + e.getMessage());
			}
			
		}
		
		return novaAplicacaoVacina;
	}
	
	/**
	 * Atualiza uma aplicacao de vacina na Database
	 * 
	 * @param Recebe o objeto com os dados as serem atualizados na tabela APLICACAO_VACINA
	 * 
	 * @return Se o registro foi atualizado na tabela ou não
	 */
	public boolean atualizarAplicacaoVacina(AplicacaoVacina atualizarAplicacaoVacina) {
		
		boolean atualizou = false;
	
		String sql = "UPDATE APLICACAO_VACINA SET IDVACINA = ?, DATA_APLICACAO = ?, NOTA = ? WHERE ID_APLICACAO_VACINA = ?";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setObject(1, atualizarAplicacaoVacina.getIdVacina());
			stmt.setDate(2, atualizarAplicacaoVacina.getDataAplicacao());
			stmt.setInt(3, atualizarAplicacaoVacina.getNota());
			stmt.setInt(4, atualizarAplicacaoVacina.getIdAplicacaoVacina());
			
			int quantidadeLinhasAfetadas = stmt.executeUpdate();
			
			atualizou = quantidadeLinhasAfetadas > 0;
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar aplicacao vacina: \n " + e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (atualizar aplicacao vacina): \n " + e.getMessage());
			}
			
		}
		
		return atualizou;
	}
	
	/**
	 * Exclui uma aplicacao de vacina na Database
	 * 
	 * @param Recebe a PK da aplicacao vacina a ser excluida
	 * 
	 * @return  se o registro foi excluido ou nao da tabela
	 */
	public boolean excluirAplicacaoVacina(Integer idAplicacaoVacina) {
		
		boolean excluiu = false;
	
		String sql = "DELETE FROM APLICACAO_VACINA WHERE ID_APLICACAO_VACINA = ?";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			
			stmt.setInt(4, idAplicacaoVacina);
			
			excluiu = stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir aplicacao vacina: \n " + e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (excluir aplicacao vacina): \n " + e.getMessage());
			}
			
		}
		
		return excluiu;
	}
	
	/**
	 * Consulta uma aplicacao de vacina especifica
	 * 
	 * @param Recebe a PK da aplicacao de vacina a ser Consultada
	 * 
	 * @return Se o registro foi consultado ou não
	 */
	public AplicacaoVacina consultarAplicacaoVacinaPorId(Integer idAplicacaoVacina) {
		
		AplicacaoVacina aplicacaoVacinaConsultada = null;
		
		String sql = "SELECT * FROM APLICACAO_VACINA WHERE ID_APLICACAO_VACINA = ?";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setInt(1, idAplicacaoVacina);
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			if (resultadoConsulta.next()) {
				aplicacaoVacinaConsultada = this.converterDoResultSet(resultadoConsulta);
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar aplicacao vacina por idAplicacaoVacina: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar vacina aplicacao por idAplicacaoVacina): \n" + e.getMessage());
			}
		}
		
		return aplicacaoVacinaConsultada;
	}

	/**
	 * Consulta todas as aplicações de vacinas cadastradas na Database
	 * 
	 * @return Retorna todas as aplicações de vacinas existentes
	 */
	public List<AplicacaoVacina> consultarAplicacaoVacina() {
		
		List<AplicacaoVacina> todasAplicacaoVacina = new ArrayList<AplicacaoVacina>();
		
		String sql = "SELECT * FROM APLICACAO_VACINA";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			if (resultadoConsulta.next()) {
				AplicacaoVacina aplicacaoVacina = this.converterDoResultSet(resultadoConsulta);
				
				todasAplicacaoVacina.add(aplicacaoVacina);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas aplicacao vacina: \n" + e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (consultar todas vacina aplicacao ): \n" + e.getMessage());
			}
		}
		
		return todasAplicacaoVacina;
	}
	
	private AplicacaoVacina converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		AplicacaoVacina aplicacaoVacina = new AplicacaoVacina();
		aplicacaoVacina.setIdAplicacaoVacina(resultadoConsulta.getInt("ID_APLICACAO_VACINA"));
		// aplicacaoVacina.setIdVacina(resultadoConsulta.getObject("IDVACINA"));
		aplicacaoVacina.setDataAplicacao(resultadoConsulta.getDate("DATA_APLICACAO"));
		aplicacaoVacina.setNota(resultadoConsulta.getInt("NOTA"));
		return aplicacaoVacina;
	}

}


