package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import dominio.Garcom;

public class GarcomDAO {
	private String sql;
	private Connection con;
	private PreparedStatement stm;
	private ResultSet rs;
	public void inserirGarcom(String nome, String disponibilidade) {
		this.con = FactoryConnection.getConnection();
		double avaliacaoMedia = 0.0;
		this.sql = "INSERT INTO restaurante.garcom(nome,disponibilidade,avaliacaoMedia) VALUES (?,?,?)";
		try {
			this.stm = con.prepareStatement(sql);
			stm.setString(1, nome);
			stm.setString(2, disponibilidade);
			stm.setDouble(3, avaliacaoMedia);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void excluirGarcom(String nome) {
		this.con = FactoryConnection.getConnection();
		this.sql = "DELETE FROM restaurante.garcom WHERE nome =?";
		try {
			this.stm = con.prepareStatement(sql);
			stm.setString(1, nome);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<Garcom> pesquisarGarcom(String nome) {
		this.con = FactoryConnection.getConnection();
		this.sql = "SELECT nome,disponibilidade,avaliacaoMedia,idGarcom FROM restaurante.garcom WHERE nome LIKE ?";
		ArrayList<Garcom> garcoms = new ArrayList<Garcom>();
		try {
			this.stm = con.prepareStatement(sql);
			this.stm.setString(1, nome+"%");
			this.rs = this.stm.executeQuery();
			while (rs.next()) {
				Garcom g = new Garcom();
				g.setNome(rs.getString(1));
				g.setDisponibilidade(rs.getString(2));
				g.setAvaliacaoMedia(rs.getDouble(3));
				g.setIdGarcom(rs.getInt(4));
				garcoms.add(g);
				System.out.println(rs.getString(1));
			}
			stm.close();
			rs.close();
			con.close();
			return garcoms;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return garcoms;
	}
	public void alterarGarcom(Garcom garcom, Garcom novoGarcom) {
		this.con = FactoryConnection.getConnection();
		this.sql = "UPDATE restaurante.garcom SET nome= ? WHERE idGarcom = ? ";
		try {
			this.stm = con.prepareStatement(sql);
			stm.setString(1, novoGarcom.getNome());
			stm.setInt(2, garcom.getIdGarcom());
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
