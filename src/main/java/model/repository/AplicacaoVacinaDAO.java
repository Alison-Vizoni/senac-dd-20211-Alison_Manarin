package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Enum.NotaAplicacaoVacina;
import model.entity.AplicacaoVacinaVO;
import model.entity.VacinaVO;
import model.repository.Banco;

/**
 * 
 * @author Alison
 *
 */
public class AplicacaoVacinaDAO {
	
	/**
	 * Cadastra uma nova aplicacao de vacina na Database
	 * 
	 * @param Recebe o objeto com os dados a serem persistidos na tabela APLICACAO_VACINA
	 * 
	 * @return A aplicacao vacina cadastrada na Database
	 */
	public AplicacaoVacinaVO cadastrarAplicacaoVacina(AplicacaoVacinaVO novaAplicacaoVacina) {
		
		String sql = "INSERT INTO APLICACAO_VACINA (IDVACINA, IDPESSOA, DATA_APLICACAO, NOTA) VALUES ( ?, ?, ?, ? )";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);) {
			stmt.setInt(1, novaAplicacaoVacina.getIdVacina().getIdVacina());
			stmt.setInt(2, novaAplicacaoVacina.getIdPessoa());
			stmt.setDate(3, java.sql.Date.valueOf(novaAplicacaoVacina.getDataAplicacao()));
			stmt.setString(4, novaAplicacaoVacina.getNota().toString());
			
			stmt.executeUpdate();
			
			ResultSet chavesgeradas = stmt.getGeneratedKeys();
			if (chavesgeradas.next()) {
				novaAplicacaoVacina.setIdAplicacaoVacina(chavesgeradas.getInt(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar aplicacao vacina: \n " + e.getMessage());
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
	public boolean atualizarAplicacaoVacina(AplicacaoVacinaVO atualizarAplicacaoVacina) {
		
		boolean atualizou = false;
	
		String sql = "UPDATE APLICACAO_VACINA SET IDVACINA = ?, IDPESSOA = ?, DATA_APLICACAO = ?, NOTA = ? WHERE ID_APLICACAO_VACINA = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setInt(1, atualizarAplicacaoVacina.getIdVacina().getIdVacina());
			stmt.setInt(2, atualizarAplicacaoVacina.getIdPessoa());
			stmt.setDate(3, java.sql.Date.valueOf(atualizarAplicacaoVacina.getDataAplicacao()));
			stmt.setString(4, atualizarAplicacaoVacina.getNota().toString());
			stmt.setInt(5, atualizarAplicacaoVacina.getIdAplicacaoVacina());

			
			int quantidadeLinhasAfetadas = stmt.executeUpdate();
			
			atualizou = quantidadeLinhasAfetadas > 0;
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar aplicacao vacina: \n " + e.getMessage());
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
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			
			stmt.setInt(1, idAplicacaoVacina);
			
			excluiu = stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir aplicacao vacina: \n " + e.getMessage());
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
	public AplicacaoVacinaVO consultarAplicacaoVacinaPorId(Integer idAplicacaoVacina) {
		
		AplicacaoVacinaVO aplicacaoVacinaConsultada = null;
		
		String sql = "SELECT * FROM APLICACAO_VACINA WHERE ID_APLICACAO_VACINA = ?";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setInt(1, idAplicacaoVacina);
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			if (resultadoConsulta.next()) {
				aplicacaoVacinaConsultada = this.converterDoResultSet(resultadoConsulta);
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar aplicacao vacina por idAplicacaoVacina: \n" + e.getMessage());
		}
		return aplicacaoVacinaConsultada;
	}

	/**
	 * Consulta todas as aplicações de vacinas cadastradas na Database
	 * 
	 * @return Retorna todas as aplicações de vacinas existentes
	 */
	public List<AplicacaoVacinaVO> consultarAplicacaoVacina() {
		
		List<AplicacaoVacinaVO> todasAplicacaoVacina = new ArrayList<AplicacaoVacinaVO>();
		
		String sql = "SELECT * FROM APLICACAO_VACINA";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			if (resultadoConsulta.next()) {
				AplicacaoVacinaVO aplicacaoVacina = this.converterDoResultSet(resultadoConsulta);
				
				todasAplicacaoVacina.add(aplicacaoVacina);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas aplicacao vacina: \n" + e.getMessage());
		}
		return todasAplicacaoVacina;
	}
	
	public List<AplicacaoVacinaVO> consultarAplicacaoVacinaPorIdPessoa(Integer idPessoa) {
		List<AplicacaoVacinaVO> aplicacoes = new ArrayList<AplicacaoVacinaVO>();
		
		String sql = "SELECT * FROM APLICACAO_VACINA WHERE IDPESSOA = ?";

		try (Connection conexao = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);) {
			stmt.setInt(1, idPessoa);
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			while (resultadoConsulta.next()) {
				AplicacaoVacinaVO aplicacao = this.converterDoResultSet(resultadoConsulta);
				aplicacoes.add(aplicacao);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar aplicacaoVacina por idPessoa: \n " + e.getMessage());
		}
		return aplicacoes;
	}
	
	private AplicacaoVacinaVO converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		AplicacaoVacinaVO aplicacaoVacina = new AplicacaoVacinaVO();
		aplicacaoVacina.setIdAplicacaoVacina(resultadoConsulta.getInt("ID_APLICACAO_VACINA"));
		aplicacaoVacina.setDataAplicacao(resultadoConsulta.getDate("DATA_APLICACAO").toLocalDate());
		aplicacaoVacina.setNota(NotaAplicacaoVacina.getNotaAplicacaoVacina(resultadoConsulta.getString("NOTA")));
		aplicacaoVacina.setIdPessoa(resultadoConsulta.getInt("IDPESSOA"));
		
		VacinaDAO vDao = new VacinaDAO();
		VacinaVO vacinaAplicada = vDao.consultarVacinaPorId(resultadoConsulta.getInt("IDVACINA"));
		aplicacaoVacina.setIdVacina(vacinaAplicada);
		
		return aplicacaoVacina;
	}

}
