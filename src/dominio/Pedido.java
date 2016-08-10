package dominio;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pedido {
	Produto produto;
	IntegerProperty mesa;
	StringProperty nomeProduto;
	DoubleProperty valorProduto;
	IntegerProperty quantidade;
	DoubleProperty total;
	StringProperty status;
	IntegerProperty idPedido;
	IntegerProperty idGarcom;
	public Pedido() {
		this.idGarcom = new SimpleIntegerProperty();
		this.idPedido = new SimpleIntegerProperty();
		this.mesa = new SimpleIntegerProperty();
		this.nomeProduto = new SimpleStringProperty();
		this.valorProduto = new SimpleDoubleProperty();
		this.produto = new Produto();
		this.quantidade = new SimpleIntegerProperty();
		this.total = new SimpleDoubleProperty();
		this.status = new SimpleStringProperty();
	}
	
	
	public final IntegerProperty quantidadeProperty() {
		return this.quantidade;
	}
	
	public final int getQuantidade() {
		return this.quantidadeProperty().get();
	}
	
	public final void setQuantidade(final int quantidade) {
		this.quantidadeProperty().set(quantidade);
	}
	
	public final StringProperty statusProperty() {
		return this.status;
	}
	
	public final java.lang.String getStatus() {
		return this.statusProperty().get();
	}
	
	public final void setStatus(final java.lang.String status) {
		this.statusProperty().set(status);
	}
	public final DoubleProperty totalProperty() {
		return this.total;
	}
	
	public final double getTotal() {
		return this.totalProperty().get();
	}
	
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public final void setTotal(final double total) {
		this.totalProperty().set(total);
	}


	public final StringProperty nomeProdutoProperty() {
		return this.nomeProduto;
	}
	


	public final java.lang.String getNomeProduto() {
		return this.nomeProdutoProperty().get();
	}
	


	public final void setNomeProduto(final java.lang.String nomeProduto) {
		this.nomeProdutoProperty().set(nomeProduto);
	}
	


	public final DoubleProperty valorProdutoProperty() {
		return this.valorProduto;
	}
	


	public final double getValorProduto() {
		return this.valorProdutoProperty().get();
	}
	


	public final void setValorProduto(final double valorProduto) {
		this.valorProdutoProperty().set(valorProduto);
	}


	public final IntegerProperty mesaProperty() {
		return this.mesa;
	}
	


	public final int getMesa() {
		return this.mesaProperty().get();
	}
	


	public final void setMesa(final int mesa) {
		this.mesaProperty().set(mesa);
	}
	


	public final IntegerProperty idPedidoProperty() {
		return this.idPedido;
	}
	


	public final int getIdPedido() {
		return this.idPedidoProperty().get();
	}
	


	public final void setIdPedido(final int idPedido) {
		this.idPedidoProperty().set(idPedido);
	}


	public final IntegerProperty idGarcomProperty() {
		return this.idGarcom;
	}
	


	public final int getIdGarcom() {
		return this.idGarcomProperty().get();
	}
	


	public final void setIdGarcom(final int idGarcom) {
		this.idGarcomProperty().set(idGarcom);
	}
	
	
	
	
	
	
	
}
