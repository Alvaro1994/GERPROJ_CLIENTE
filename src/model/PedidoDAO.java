package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import dominio.Pedido;

public class PedidoDAO {
	private String sql;
	private Connection con;
	private PreparedStatement stm;
	private ResultSet rs;
	ProdutoDAO pdao = new ProdutoDAO();

	public void criarPedido(int mesa, int idGarcom) {
		this.con = FactoryConnection.getConnection();
		this.sql = "INSERT INTO pedito(status,mesa,idGarcom) VALUES (?,?,?)";
		try {
			this.stm = con.prepareStatement(sql);
			stm.setString(1, "aberto");
			stm.setInt(2, mesa);
			stm.setInt(3, idGarcom);

			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void excluirPedido(int mesa) {
		this.con = FactoryConnection.getConnection();
		this.sql = "DELETE FROM pedito WHERE mesa = ? && status = aberto";
		try {
			this.stm = con.prepareStatement(sql);
			stm.setInt(1, mesa);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Pedido pesquisarPedido(int mesa) {
		this.con = FactoryConnection.getConnection();
		this.sql = "SELECT * FROM pedito WHERE mesa = ? AND status = 'aberto'";
		Pedido pedido = new Pedido();
		try {
			this.stm = con.prepareStatement(sql);
			this.stm.setInt(1, mesa);
			this.rs = this.stm.executeQuery();
			while (rs.next()) {
				System.out.println("ID pedido: " + rs.getInt(1));
				pedido.setIdPedido(rs.getInt(1));
			}
			stm.close();
			rs.close();
			con.close();
			return pedido;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedido;
	}

	public ArrayList<Pedido> pesquisarItens(int mesa) {
		this.con = FactoryConnection.getConnection();
		this.sql = "SELECT * FROM pedito WHERE mesa = ? AND status = 'aberto'";
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		Pedido pedido = new Pedido();
		try {
			this.stm = con.prepareStatement(sql);
			this.stm.setInt(1, mesa);
			this.rs = this.stm.executeQuery();
			while (rs.next()) {
				System.out.println("ID pedido: " + rs.getInt(1));
				pedido.setIdPedido(rs.getInt(1));
				pedido.setStatus(rs.getString(2));
				pedido.setMesa(mesa);
				pedido.setIdGarcom(rs.getInt(4));
				pdao.pesquisarProduto(pedido.getProduto().getIdProduto());
			}
			stm.close();
			rs.close();
			con.close();
			return pedidos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedidos;
	}

	public int selecionarIdItem(int idpedido, String nomeProduto, int qtd) {
		this.con = FactoryConnection.getConnection();
		this.sql = "SELECT idItem FROM itempedido WHERE idPedido = ? AND status = 'preparando' AND idpedido = ? AND quantidade ?";
		int id = 0;
		try {
			this.stm = con.prepareStatement(sql);
			this.stm.setInt(1, idpedido);
			this.stm.setString(2, nomeProduto);
			this.stm.setInt(3, qtd);
			this.rs = this.stm.executeQuery();
			while (rs.next()) {
				System.out.println("ID item pedido: " + rs.getInt(1));
				id = rs.getInt(1);
			}
			stm.close();
			rs.close();
			con.close();
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public ArrayList<Pedido> selecionarItens(int idMesa) {
		this.con = FactoryConnection.getConnection();
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
//		SELECT PR.idProduto,PR.nome, PE.status,PE.idPedido, IT.quantidade,IT.subTotal FROM produto AS PR INNER JOIN itempedido 
//		AS IT on PR.idProduto = IT.idProduto INNER JOIN pedito AS PE ON IT.idPedido = PE.idPedido
		this.sql = "SELECT PE.idPedido,PR.nome, PE.status,PE.idPedido, IT.quantidade,IT.subTotal, PR.preco, PE.mesa FROM produto AS PR INNER JOIN itempedido "
				+ "AS IT on PR.idProduto = IT.idProduto INNER JOIN pedito AS PE ON IT.idPedido = PE.idPedido WHERE PE.mesa = ?";
		System.out.println("Id da mesa "+idMesa);
		
		try {
			this.stm = con.prepareStatement(sql);
			this.stm.setInt(1, idMesa);
			this.rs = this.stm.executeQuery();
			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setIdPedido(rs.getInt(1));
				pedido.setNomeProduto(rs.getString(2));
				pedido.setStatus(rs.getString(3));
				pedido.setQuantidade(rs.getInt(5));
				pedido.setTotal(rs.getDouble(6));
				pedido.setValorProduto(rs.getDouble(7));
				pedido.setMesa(rs.getInt(8));
				pedidos.add(pedido);
			}
			stm.close();
			rs.close();
			con.close();
			return pedidos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedidos;
	}
	
	public void alterarStatus(int idPedido) {
		this.con = FactoryConnection.getConnection();
		this.sql = "UPDATE itempedido SET status= 'conluido' WHERE idPedido ?";
		try {
			this.stm = con.prepareStatement(sql);
			this.stm.setInt(1, idPedido);
			stm.executeUpdate();
			stm.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void excluirItem(int idPedido, int idProduto, int quantidade) {
		this.con = FactoryConnection.getConnection();
		this.sql = "DELETE FROM itempedido WHERE idPedido=? AND idProduto=? AND quantidade=?";
		System.out.println("Pedido DAO" + idPedido + " idProduto " + idProduto + " quantidade " + quantidade);
		try {
			this.stm = con.prepareStatement(sql);
			stm.setInt(1, idPedido);
			stm.setInt(2, idProduto);
			stm.setInt(3, quantidade);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inseirItens(int idPedido, int idProduto, int quantidade, double subTotal) {
		this.con = FactoryConnection.getConnection();
		this.sql = "INSERT INTO itempedido(idPedido, idProduto, quantidade, subTotal,status) VALUES (?,?,?,?,?)";
		try {

			this.stm = con.prepareStatement(sql);
			stm.setInt(1, idPedido);
			stm.setInt(2, idProduto);
			stm.setInt(3, quantidade);
			stm.setDouble(4, subTotal);
			stm.setString(5, "Preparando");
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
