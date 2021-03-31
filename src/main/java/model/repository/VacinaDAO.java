package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.repository.PessoaDAO;
import model.entity.VacinaVO;
import model.entity.FaseVacina;
import model.entity.PessoaVO;

public class VacinaDAO {
	
	/**
	 * Cadastra uma nova vacina na database.
	 * 
	 * @param novaVacina o objeto com os dados a serem persistidos na tabela VACINA
	 * 
	 * @return A vacina com a PK cadastrada no banco.
	 */
	public VacinaVO cadastrarVacina(VacinaVO novaVacina) {
		String sql = " INSERT INTO VACINA ( NOME, PAIS_ORDIGEM, ESTAGIO_PESQUISA, DATA_INICIO_PESQUISA,"
				+ " ID_PESQUISADOR_RESPONSAVEL, FASE, QUANTIDADE_DOSES )"
				+ " VALUES ( ?, ?, ?, ?, ?, ?, ? )";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);) {
			stmt.setString(1, novaVacina.getNome());
			stmt.setString(2, novaVacina.getPaisDeOrigem());
			stmt.setString(3, novaVacina.getEstagioPesquisa());
			stmt.setDate(4, java.sql.Date.valueOf(novaVacina.getDataInicioPesquisa()));
			stmt.setObject(5, novaVacina.getPesquisadorResponsavel().getIdPessoa());
			stmt.setString(6, novaVacina.getFase().toString());
			stmt.setInt(7, novaVacina.getQuantidadeDoses());
			
			stmt.executeUpdate();
			
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novaVacina.setIdVacina(chavesGeradas.getInt(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar vacina: \n " + e.getMessage());
		}

		return novaVacina;
	}
	
	/**
	 * Atualiza uma vacina na database.
	 * 
	 * @param atualizarVacina o objeto com os dados a serem atualizados na tabela VACINA
	 * 
	 * @return se o registro foi ou nao atualizado na tabela
	 */
	public boolean atualizarVacina(VacinaVO atualizarVacina) {
		
		boolean atualizou = false;
		
		String sql = " UPDATE VACINA SET NOME = ?, PAIS_ORDIGEM = ?, ESTAGIO_PESQUISA = ?, DATA_INICIO_PESQUISA = ?,"
				+ " ID_PESQUISADOR_RESPONSAVEL = ?, FASE = ?, QUANTIDADE_DOSES = ?"
				+ " WHERE IDVACINA = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setString(1, atualizarVacina.getNome());
			stmt.setString(2, atualizarVacina.getPaisDeOrigem());
			stmt.setString(3, atualizarVacina.getEstagioPesquisa());
			stmt.setDate(4, java.sql.Date.valueOf(atualizarVacina.getDataInicioPesquisa()));
			stmt.setObject(5, atualizarVacina.getPesquisadorResponsavel().getIdPessoa());
			stmt.setString(6, atualizarVacina.getFase().toString());
			stmt.setInt(7, atualizarVacina.getQuantidadeDoses());
			stmt.setInt(8, atualizarVacina.getIdVacina());
			
			int quantidadeDeLinhasAfetadas = stmt.executeUpdate();
			
			atualizou = quantidadeDeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina: \n " + e.getMessage());
		}

		return atualizou;
	}
	
	/**
	 * Exclui uma vacina na database
	 * 
	 * @param A PK da vacina a ser excluida.
	 * 
	 * @return se o registro foi ou nao excluido na tabela.
	 */
	public boolean excluirVacina(Integer idVacina) {
		
		boolean excluiu = false;
		
		String sql = " DELETE FROM VACINA WHERE IDVACINA = ?";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {

			stmt.setInt(1, idVacina);
			
			excluiu = stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir vacina: \n " + e.getMessage());
		}

		return excluiu;
	}
	
	/**
	 * Busca uma vacina especifica na database
	 * 
	 * @param A PK da vacina a ser buscada.
	 * 
	 * @return A vacina buscada.
	 */
	public VacinaVO consultarVacinaPorId(Integer idVacina) {
		
		VacinaVO vacinaConsultada = null;
		
		String sql = " SELECT * FROM VACINA WHERE IDVACINA = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setInt(1, idVacina);
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			if (resultadoConsulta.next()) {
				
				vacinaConsultada = this.converterDoResultSet(resultadoConsulta);
				}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina: \n " + e.getMessage());
		}

		return vacinaConsultada;
	}

	/**
	 * Busca todas as vacinas cadastradas na Database.
	 * 
	 * @return Retorna todas as vacinas cadastradas na Database.
	 */
	public List<VacinaVO> consultarTodasVacinas() {
		
		List<VacinaVO> todasVacinas = new ArrayList<VacinaVO>();
		
		String sql = " SELECT * FROM VACINA WHERE IDVACINA = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
	
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			while (resultadoConsulta.next()) {
				
				VacinaVO vacina = this.converterDoResultSet(resultadoConsulta);
				
				todasVacinas.add(vacina);
				}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas as vacinas: \n " + e.getMessage());
		}

		return todasVacinas;
	}

	private VacinaVO converterDoResultSet(ResultSet resultadoConsulta) throws SQLException {
		VacinaVO vacinaConsultada = new VacinaVO();
		vacinaConsultada.setIdVacina(resultadoConsulta.getInt("idVacina"));
		vacinaConsultada.setNome(resultadoConsulta.getString("nome"));
		vacinaConsultada.setPaisDeOrigem(resultadoConsulta.getString("PAIS_ORDIGEM"));
		vacinaConsultada.setEstagioPesquisa(resultadoConsulta.getString("ESTAGIO_PESQUISA"));
		vacinaConsultada.setDataInicioPesquisa(resultadoConsulta.getDate("DATA_INICIO_PESQUISA").toLocalDate());
		
		PessoaDAO pDAO = new PessoaDAO();
		PessoaVO responsavel = pDAO.consutarPessoaPorId(resultadoConsulta.getInt("ID_PESQUISADOR_RESPONSAVEL"));
		vacinaConsultada.setPesquisadorResponsavel(responsavel);
		
		vacinaConsultada.setFase(FaseVacina.getFaseVacina(resultadoConsulta.getString("fase")));
		vacinaConsultada.setQuantidadeDoses(resultadoConsulta.getInt("QUANTIDADE_DOSES"));
		
		return vacinaConsultada;
	}
	
}
