package dominio;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Garcom {
	/*
	 * Ao invés de int, String, double, 
	 * usem respectivamente IntegerProperty
	 * doubleProperty e StringProperty. 
	 * Quando forem instanciar usem new Simple[tipoescolhido]Property
	 * */
	
	IntegerProperty idGarcom;
	StringProperty nome;
	StringProperty disponibilidade;
	DoubleProperty avaliacaoMedia;
	IntegerProperty qtdAtendimentos;
	
	public Garcom() {
		idGarcom = new SimpleIntegerProperty();
		nome = new SimpleStringProperty();
		disponibilidade = new SimpleStringProperty();
		avaliacaoMedia = new SimpleDoubleProperty();
		qtdAtendimentos = new SimpleIntegerProperty();
	}
	
	public void alterarDisponibilidade() {
		if (disponibilidade.equals("Disponível")) {
			setDisponibilidade("Indisponível");
		}else{
			setDisponibilidade("Disponível");
		}
	}
	public final IntegerProperty idGarcomProperty() {
		return this.idGarcom;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nome: "+this.getNome()+" Disponibilidade: "+this.getDisponibilidade()+"id: "+this.getIdGarcom()+" Avaliação Média: "+this.getAvaliacaoMedia();
	}
	
	public final int getIdGarcom() {
		return this.idGarcomProperty().get();
	}
	

	public final void setIdGarcom(final int idGarcom) {
		this.idGarcomProperty().set(idGarcom);
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
	

	public final StringProperty disponibilidadeProperty() {
		return this.disponibilidade;
	}
	

	public final java.lang.String getDisponibilidade() {
		return this.disponibilidadeProperty().get();
	}
	

	public final void setDisponibilidade(final java.lang.String disponibilidade) {
		this.disponibilidadeProperty().set(disponibilidade);
	}
	

	public final DoubleProperty avaliacaoMediaProperty() {
		return this.avaliacaoMedia;
	}
	

	public final double getAvaliacaoMedia() {
		return this.avaliacaoMediaProperty().get();
	}
	

	public final void setAvaliacaoMedia(final double avaliacaoMedia) {
		this.avaliacaoMediaProperty().set(avaliacaoMedia);
	}
	

	public final IntegerProperty qtdAtendimentosProperty() {
		return this.qtdAtendimentos;
	}
	

	public final int getQtdAtendimentos() {
		return this.qtdAtendimentosProperty().get();
	}
	

	public final void setQtdAtendimentos(final int qtdAtendimentos) {
		this.qtdAtendimentosProperty().set(qtdAtendimentos);
	}
	
	
	
}
