package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Enum.EstagioPesquisa;
import model.Enum.FaseVacina;
import model.Seletor.SeletorVacinaVO;
import model.entity.VacinaVO;

public class VacinaDAO {
	
	/**
	 * Cadastra uma nova vacina na database.
	 * 
	 * @param novaVacina o objeto com os dados a serem persistidos na tabela VACINA
	 * 
	 * @return A vacina com a PK cadastrada no banco.
	 */
	public VacinaVO cadastrarVacina(VacinaVO novaVacina) {
		String sql = " INSERT INTO VACINA ( NOME, PAIS_ORIGEM, ESTAGIO_PESQUISA, DATA_INICIO_PESQUISA,"
				+ " ID_PESQUISADOR_RESPONSAVEL, FASE, QUANTIDADE_DOSES, ATIVA )"
				+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);) {
			stmt.setString(1, novaVacina.getNome().toUpperCase());
			stmt.setString(2, novaVacina.getPaisDeOrigem().toUpperCase());
			stmt.setString(3, novaVacina.getEstagioPesquisa().toString());
			stmt.setDate(4, java.sql.Date.valueOf(novaVacina.getDataInicioPesquisa()));
			stmt.setObject(5, novaVacina.getPesquisadorResponsavel());
			stmt.setString(6, novaVacina.getFase().toString());
			stmt.setInt(7, novaVacina.getQuantidadeDoses());
			stmt.setBoolean(8, novaVacina.isVacinaAtiva());
			
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
	public boolean atualizarVacinaDAO(VacinaVO atualizarVacina) {
		
		boolean atualizou = false;
		
		String sql = " UPDATE VACINA SET NOME = ?, PAIS_ORIGEM = ?, ESTAGIO_PESQUISA = ?, DATA_INICIO_PESQUISA = ?,"
				+ " ID_PESQUISADOR_RESPONSAVEL = ?, FASE = ?, QUANTIDADE_DOSES = ?, ATIVA = ?"
				+ " WHERE IDVACINA = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setString(1, atualizarVacina.getNome());
			stmt.setString(2, atualizarVacina.getPaisDeOrigem());
			stmt.setString(3, atualizarVacina.getEstagioPesquisa().toString());
			stmt.setDate(4, java.sql.Date.valueOf(atualizarVacina.getDataInicioPesquisa()));
			stmt.setObject(5, atualizarVacina.getPesquisadorResponsavel());
			stmt.setString(6, atualizarVacina.getFase().toString());
			stmt.setInt(7, atualizarVacina.getQuantidadeDoses());
			stmt.setInt(8, atualizarVacina.getIdVacina());
			stmt.setBoolean(9, atualizarVacina.isVacinaAtiva());
			
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
			System.out.println("Erro ao consultar vacina por IDVACINA: \n " + e.getMessage());
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
		
		String sql = " SELECT * FROM VACINA";
		
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
		vacinaConsultada.setPaisDeOrigem(resultadoConsulta.getString("PAIS_ORIGEM"));
		vacinaConsultada.setEstagioPesquisa(EstagioPesquisa.getEstagioPesquisa(resultadoConsulta.getString("ESTAGIO_PESQUISA")));
		vacinaConsultada.setDataInicioPesquisa(resultadoConsulta.getDate("DATA_INICIO_PESQUISA").toLocalDate());
		vacinaConsultada.setPesquisadorResponsavel(resultadoConsulta.getInt("ID_PESQUISADOR_RESPONSAVEL"));
		vacinaConsultada.setFase(FaseVacina.getFaseVacina(resultadoConsulta.getString("fase")));
		vacinaConsultada.setQuantidadeDoses(resultadoConsulta.getInt("QUANTIDADE_DOSES"));
		vacinaConsultada.setVacinaAtiva(resultadoConsulta.getBoolean("ATIVA"));
		
		return vacinaConsultada;
	}
	
	/**
	 * Consulta a existencia de vacinas na tabela VACINA, filtrando pelo nome AND pais_origem
	 * 
	 * @param vacinaVO
	 * 
	 * @return 
	 */
	public VacinaVO consultarVacinaPorNomeAndPais(VacinaVO vacinaVO) {
		
		String sql = " SELECT * FROM VACINA WHERE UPPER(NOME) = ? AND UPPER(PAIS_ORIGEM) = ? ";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setString(1, vacinaVO.getNome());
			stmt.setString(2, vacinaVO.getPaisDeOrigem());
			
			ResultSet resultadoConsulta = stmt.executeQuery();

			if (resultadoConsulta.next()) {
				vacinaVO = this.converterDoResultSet(resultadoConsulta);
				}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas as vacinas: \n " + e.getMessage());
		}
		return vacinaVO;
	}

	public boolean desativarVacina(Integer idVacina) {
		boolean desativarVacina = false;
		
		String sql = " UPDATE VACINA SET ATIVA = ?"
				+ " WHERE IDVACINA = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setBoolean(1, false);
			stmt.setInt(2, idVacina);
			
			int quantidadeDeLinhasAfetadas = stmt.executeUpdate();
			
			desativarVacina = quantidadeDeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina: \n " + e.getMessage());
		}

		return desativarVacina;
	}
	
	public List<VacinaVO> consultarTodasVacinasComSeletor(SeletorVacinaVO seletor) {
		
		String sql = " SELECT * FROM VACINA v";
		
		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}
		
		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		
		List<VacinaVO> todasVacinas = new ArrayList<>();
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
	
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			while (resultadoConsulta.next()) {
				
				VacinaVO vacina = this.converterDoResultSet(resultadoConsulta);
				
				todasVacinas.add(vacina);
				}
		} catch (SQLException e) {
			System.err.println("Erro ao consultar todas as vacinas: \n " + e.getMessage());
		}

		return todasVacinas;
	}

	private String criarFiltros(SeletorVacinaVO seletor, String sql) {
		sql += " WHERE ";
		boolean primeiro = true;
		
		if (seletor.getIdVacina() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.IDVACINA " + seletor.getIdVacina();
			primeiro = false;
		}
		
		if ((seletor.getNomePais() != null) && (seletor.getNomePais().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.PAIS_ORIGEM LIKE '%" + seletor.getNomePais() + "%'";
			primeiro = false;
		}
		
		if ((seletor.getNomeVacina() != null) && (seletor.getNomeVacina().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.NOME LIKE '%" + seletor.getNomeVacina() + "%'";
			primeiro = false;
		}
		
		if (seletor.getDataInicioPesquisa() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.DATA_INICIO_PESQUISA >= '" + seletor.getDataInicioPesquisa() + "'";
			primeiro = false;
		}
		return sql;
	}
}
