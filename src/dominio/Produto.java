package dominio;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto {
	private IntegerProperty idProduto;
	private StringProperty nome;
	private StringProperty tipo;
	private StringProperty descricao;
	private DoubleProperty preco;
	
	public Produto() {
		idProduto = new SimpleIntegerProperty();
		nome = new SimpleStringProperty();
		tipo = new SimpleStringProperty();
		descricao = new SimpleStringProperty();
		preco = new SimpleDoubleProperty();
	}
	
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	

	public final java.lang.String getNome() {
		return this.nomeProperty().get();
	}
	

	public final void setNome(final java.lang.String nome) {
		this.nomeProperty().set(nome);
	}
	

	public final StringProperty tipoProperty() {
		return this.tipo;
	}
	

	public final java.lang.String getTipo() {
		return this.tipoProperty().get();
	}
	

	public final void setTipo(final java.lang.String tipo) {
		this.tipoProperty().set(tipo);
	}
	

	public final StringProperty descricaoProperty() {
		return this.descricao;
	}
	

	public final java.lang.String getDescricao() {
		return this.descricaoProperty().get();
	}
	

	public final void setDescricao(final java.lang.String descricao) {
		this.descricaoProperty().set(descricao);
	}
	

	public final DoubleProperty precoProperty() {
		return this.preco;
	}
	

	public final double getPreco() {
		return this.precoProperty().get();
	}
	

	public final void setPreco(final double preco) {
		this.precoProperty().set(preco);
	}


	public final IntegerProperty idProdutoProperty() {
		return this.idProduto;
	}
	


	public final int getIdProduto() {
		return this.idProdutoProperty().get();
	}
	


	public final void setIdProduto(final int idProduto) {
		this.idProdutoProperty().set(idProduto);
	}
	
	
	
	
}
