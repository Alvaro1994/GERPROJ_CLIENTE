package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Produto;

public class ProdutoDAO {
	private String sql;
	private Connection con;
	private PreparedStatement stm;
	private ResultSet rs;
	public void inserirProduto(String nome, String tipo, String descricao, double preco) {
		this.con = FactoryConnection.getConnection();
		this.sql = "INSERT INTO restaurante.produto(nome,tipo,descricao,preco) VALUES (?,?,?,?)";
		try {
			this.stm = con.prepareStatement(sql);
			stm.setString(1, nome);
			stm.setString(2, tipo);
			stm.setString(3, descricao);
			stm.setDouble(4, preco);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void excluirProduto(String nome) {
		this.con = FactoryConnection.getConnection();
		this.sql = "DELETE FROM restaurante.produto WHERE nome =?";
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
	public ArrayList<Produto> pesquisarProduto(String nome) {
		this.con = FactoryConnection.getConnection();
		this.sql = "SELECT nome,descricao,tipo,preco,idProduto FROM restaurante.produto WHERE nome LIKE ?";
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		try {
			this.stm = con.prepareStatement(sql);
			this.stm.setString(1, nome+"%");
			this.rs = this.stm.executeQuery();
			while(rs.next()){
				Produto p = new Produto();
				p.setNome(rs.getString(1));
				p.setDescricao(rs.getString(2));
				p.setTipo(rs.getString(3));
				p.setPreco(rs.getDouble(4));
				p.setIdProduto(rs.getInt(5));
				produtos.add(p);
			}
			stm.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produtos;
	}
	public ArrayList<Produto> pesquisarProduto(String nome,String tipo) {
		this.con = FactoryConnection.getConnection();
		this.sql = "SELECT nome,descricao,tipo,preco,idProduto FROM restaurante.produto WHERE tipo = ?";
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		try {
			this.stm = con.prepareStatement(sql);
			this.stm.setString(1, tipo);
			this.rs = this.stm.executeQuery();
			while(rs.next()){
				Produto p = new Produto();
				p.setNome(rs.getString(1));
				p.setDescricao(rs.getString(2));
				p.setTipo(rs.getString(3));
				p.setPreco(rs.getDouble(4));
				p.setIdProduto(rs.getInt(5));
				System.out.println("Buscou p nome: "+p.getNome()+" p descrição "+p.getDescricao());
				produtos.add(p);
			}
			stm.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produtos;
	}
	public Produto pesquisarProduto(int idProduto) {
		this.con = FactoryConnection.getConnection();
		this.sql = "SELECT nome,descricao,tipo,preco,idProduto FROM restaurante.produto WHERE idProduto = ?";
		Produto p = new Produto();
		try {
			this.stm = con.prepareStatement(sql);
			this.stm.setInt(1, idProduto);
			this.rs = this.stm.executeQuery();
			while(rs.next()){
				
				p.setNome(rs.getString(1));
				p.setDescricao(rs.getString(2));
				p.setTipo(rs.getString(3));
				p.setPreco(rs.getDouble(4));
				p.setIdProduto(rs.getInt(5));
				System.out.println("Buscou p nome: "+p.getNome()+" p descrição "+p.getDescricao());
			}
			stm.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	public void alterarProduto(Produto produtoAntigo, Produto produtoNovo) {
		this.con = FactoryConnection.getConnection();
		this.sql = "UPDATE restaurante.produto SET nome= ?, descricao= ?, tipo= ?, preco= ? WHERE idProduto= ? ";
		try {
			this.stm = con.prepareStatement(sql);
			stm.setString(1, produtoNovo.getNome());
			stm.setString(2, produtoNovo.getDescricao());
			stm.setString(3, produtoNovo.getTipo());
			stm.setDouble(4, produtoNovo.getPreco());
			stm.setInt(5, produtoAntigo.getIdProduto());
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
