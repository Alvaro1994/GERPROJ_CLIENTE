package telas;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dominio.Garcom;
import dominio.Pedido;
import dominio.Produto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.SplitPane;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.PedidoDAO;
import model.ProdutoDAO;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class PedidoClienteController implements Initializable {
	@FXML
	private SplitPane spMenu;
	@FXML
	private AnchorPane apCadMenu;
	@FXML
	private SplitPane spPrincipal;
	@FXML
	private Label lbTitulo;
	@FXML
	private Label labTipo;
	@FXML
	private ComboBox<String> cbTipo;
	@FXML
	private ScrollPane spProdutos;
	@FXML
	private AnchorPane anpDescricao;
	@FXML
	private Label labTituloDescricao;
	@FXML
	private Label labNome;
	@FXML
	private Label labValor;
	@FXML
	private Label labDescricao;
	@FXML
	private Label labQtd;
	@FXML
	private TextField tfNome;
	@FXML
	private TextField tfValor;
	@FXML
	private TextField tfQtd;
	@FXML
	private TextArea teaDescricao;
	@FXML
	private Button btnIncluirItem;
	@FXML
	private AnchorPane apTabela;
	@FXML
	private ScrollPane spTabela;
	@FXML
	private AnchorPane anpTabela;
	@FXML
	private AnchorPane anpItens;
	@FXML
	private TableView<Produto> tvItens;
	@FXML
	private TableColumn<Produto, String> tcNomeItens;
	@FXML
	private TableView<Pedido> tvPedido;
	@FXML
	private TableColumn<Pedido, String> tcStatus;
	@FXML
	private TableColumn<Pedido, String> tcNome;
	@FXML
	private TableColumn<Pedido, Double> tcValor;
	@FXML
	private TableColumn<Pedido, Double> tcSubTotal;
	@FXML
	private TableColumn<Pedido, Integer> tcQuantidade;
	@FXML
	private Label labPedido;
	@FXML
	private Button btnRealizarPedido;
	@FXML
	private Button btnExcluirItem;
	@FXML
	private Button btnCancelarPedido;
	@FXML
	private Button btnConfirmarMesa;
	@FXML
	private HBox hbBotoes;
	@FXML
	private AnchorPane anpPrincipal;
	private int idPedidoAtual;
	private int mesaAtual;
	private PedidoDAO pedidoDAO = new PedidoDAO();
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	private ProdutoDAO pdao = new ProdutoDAO();
	private Produto produtoSelecionado = new Produto();
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	private Garcom garcomAtual = new Garcom();
	private Pedido itemSelecionado;
	private PrintStream mensagem;
	private boolean mesaCorfirmada = false;

	public void cancelarPeido() throws IOException {
		this.anpPrincipal.getChildren().removeAll(this.anpPrincipal.getChildren());
		this.mensagem.println(this.getMesaAtual() - 1 + " cancelarPedido");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/telas/Principal.fxml"));
		Parent root = loader.load();
		PrincipalController ctrl = loader.getController();
		this.anpPrincipal.getChildren().addAll(root);
	}

	public void carregarTabelas() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		tcNomeItens.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcValor.setCellValueFactory(new PropertyValueFactory<>("valorProduto"));
		tcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tcSubTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
	}

	public void adicionarTabelaItens() {

	}

	public void incluirItem() {
		Pedido ped = new Pedido();
		ped.setProduto(produtoSelecionado);
		if (mesaCorfirmada && produtoSelecionado != null) {
			int qtd = Integer.parseInt(tfQtd.getText());
			ped.setQuantidade(qtd);
			ped.setTotal(qtd * produtoSelecionado.getPreco());
			ped.setStatus("Aguardando");
			ped.setNomeProduto(produtoSelecionado.getNome());
			ped.setValorProduto(produtoSelecionado.getPreco());
			pedidos.add(ped);
			tvPedido.getItems().removeAll(pedidos);
			tvPedido.setItems(FXCollections.observableArrayList(pedidos));
			tfNome.clear();
			tfQtd.clear();
			tfValor.clear();
			teaDescricao.clear();
			// idPedido
			pedidoDAO.inseirItens(idPedidoAtual, produtoSelecionado.getIdProduto(), qtd, ped.getTotal());
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção");
			alert.setContentText("Confirme a mesa e/ou selecione um item");
			alert.show();
		}

	}

	public void confirmarMesa() {
		if (btnConfirmarMesa.getText().equals("Alterar Mesa")) {

		} else {

			mesaCorfirmada = true;
			pedidoDAO.criarPedido(this.getMesaAtual(), this.garcomAtual.getIdGarcom());
			this.idPedidoAtual = pedidoDAO.pesquisarPedido(this.getMesaAtual()).getIdPedido();
			labPedido.setText("Pedido atual " + this.idPedidoAtual + " para Mesa " + this.mesaAtual + " Garçom "
					+ garcomAtual.getNome());
			btnConfirmarMesa.setText("Alterar Mesa");
		}
	}

	public void selecionarTipo() {
		tvItens.setItems(FXCollections
				.observableArrayList(pdao.pesquisarProduto("teste", cbTipo.getSelectionModel().getSelectedItem())));
		System.out.println("evento no combobox " + cbTipo.getSelectionModel().getSelectedItem());
	}

	public void realizarPedido() {
		// this.mensagem.println("Pedido "+this.idPedidoAtual+" na mesa
		// "+this.mesaAtual);
		this.mensagem.println(this.mesaAtual);
	}

	public void excluirItem() {
		tvPedido.getItems().removeAll(pedidos);
		pedidos.remove(itemSelecionado);
		tvPedido.setItems(FXCollections.observableArrayList(pedidos));
		// int idItem = pedidoDAO.selecionarIdItem(this.idPedidoAtual,
		// itemSelecionado.getNomeProduto(), itemSelecionado.getQuantidade());
		System.out.println("Pedido " + idPedidoAtual + " idProduto " + itemSelecionado.getProduto().getIdProduto()
				+ " quantidade " + itemSelecionado.getQuantidade());
		pedidoDAO.excluirItem(idPedidoAtual, itemSelecionado.getProduto().getIdProduto(),
				itemSelecionado.getQuantidade());
		System.out.println("Pedido " + idPedidoAtual + " idProduto " + itemSelecionado.getProduto().getIdProduto()
				+ " quantidade " + itemSelecionado.getQuantidade());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarTabelas();
		tvItens.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produto>() {

			@Override
			public void changed(ObservableValue<? extends Produto> arg0, Produto oldValue, Produto newValue) {
				produtoSelecionado = newValue;
				System.out.println("Evento na tabela");
				if (produtoSelecionado != null) {
					tfNome.setText(produtoSelecionado.getNome());
					tfValor.setText("" + produtoSelecionado.getPreco());
					teaDescricao.setText(produtoSelecionado.getDescricao());
					btnIncluirItem.setDisable(false);
				}
			}
		});
		tvPedido.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pedido>() {

			@Override
			public void changed(ObservableValue<? extends Pedido> arg0, Pedido pedidoAntigo, Pedido pedidoNovo) {
				if (pedidoNovo != null) {
					itemSelecionado = pedidoNovo;
				}
			}
		});
		btnIncluirItem.setDisable(true);
		cbTipo.setItems(FXCollections.observableArrayList("Bebida", "Pratos", "Tira Gosto"));
		cbTipo.getSelectionModel().selectFirst();
		cbTipo.setOnMouseClicked(event -> selecionarTipo());
		btnExcluirItem.setOnMouseClicked(event -> excluirItem());
		btnConfirmarMesa.setOnMouseClicked(event -> confirmarMesa());
		btnIncluirItem.setOnMouseClicked(event -> incluirItem());
		btnRealizarPedido.setOnMouseClicked(event -> realizarPedido());

		btnCancelarPedido.setOnMouseClicked(event -> {
			try {
				cancelarPeido();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	public int getMesaAtual() {
		return mesaAtual;
	}

	public void setMesaAtual(int mesaAtual) {
		this.mesaAtual = mesaAtual;
	}

	public Garcom getGarcomAtual() {
		return garcomAtual;
	}

	public void setGarcomAtual(Garcom garcomAtual) {
		this.garcomAtual = garcomAtual;
	}

	public PrintStream getMensagem() {
		return mensagem;
	}

	public void setMensagem(PrintStream mensagem) {
		this.mensagem = mensagem;
	}

}
