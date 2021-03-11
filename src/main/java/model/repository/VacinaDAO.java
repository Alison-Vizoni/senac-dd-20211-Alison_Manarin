package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Vacina;

public class VacinaDAO {
	
	public Vacina cadastrarVacina(Vacina novaVacina) {
		String sql = " INSERT INTO VACINA ( NOME, PAIS_ORDIGEM, ESTAGIO_PESQUISA, DATA_INICIO_PESQUISA,"
				+ " ID_PESQUISADOR_RESPONSAVEL, FASE, QUANTIDADE_DOSES )"
				+ " VALUES ( ?, ?, ?, ?, ?, ?, ? )";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1, novaVacina.getNome());
			stmt.setString(2, novaVacina.getPaisDeOrigem());
			stmt.setString(3, novaVacina.getEstagioPesquisa());
			stmt.setDate(4, novaVacina.getDataInicioPesquisa());
			stmt.setObject(5, novaVacina.getPesquisadorResponsavel());
			stmt.setInt(6, novaVacina.getFase());
			stmt.setInt(7, novaVacina.getQuantidadeDoses());
			
			stmt.executeUpdate();
			
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novaVacina.setIdVacina(chavesGeradas.getInt(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar vacina: \n " + e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (cadastro de pessoa): \n " + e.getMessage());
			}
			
		}

		return novaVacina;
	}
	
	public boolean atualizarVacina(Vacina atualizarVacina) {
		
		boolean atualizou = false;
		
		String sql = " UPDATE VACINA SET NOME = ?, PAIS_ORDIGEM = ?, ESTAGIO_PESQUISA = ?, DATA_INICIO_PESQUISA = ?,"
				+ " ID_PESQUISADOR_RESPONSAVEL = ?, FASE = ?, QUANTIDADE_DOSES = ?)"
				+ " WHERE IDVACINA = ?";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1, atualizarVacina.getNome());
			stmt.setString(2, atualizarVacina.getPaisDeOrigem());
			stmt.setString(3, atualizarVacina.getEstagioPesquisa());
			stmt.setDate(4, atualizarVacina.getDataInicioPesquisa());
			stmt.setObject(5, atualizarVacina.getPesquisadorResponsavel());
			stmt.setInt(6, atualizarVacina.getFase());
			stmt.setInt(7, atualizarVacina.getQuantidadeDoses());
			stmt.setInt(8, atualizarVacina.getIdVacina());
			
			int quantidadeDeLinhasAfetadas = stmt.executeUpdate();
			
			atualizou = quantidadeDeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina: \n " + e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão (atualizar pessoa): \n " + e.getMessage());
			}
			
		}

		return atualizou;
	}
	
}
