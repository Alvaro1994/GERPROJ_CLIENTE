package telas;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dominio.Garcom;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.GarcomDAO;

public class PrincipalController implements Initializable {
	@FXML
	private Button btnSelecionar;
	@FXML
	private AnchorPane apPrincipal;
	@FXML
	private ScrollPane srpMesas;
	@FXML
	private GridPane grpMessas;
	@FXML
	private ComboBox<Garcom> cbGarcom;
	private GarcomDAO gDao = new GarcomDAO();
	private ArrayList<Garcom> name = new ArrayList();
	public void mudarStatus(Button btn, PrintStream saida) throws IOException {
		this.apPrincipal.getChildren().removeAll(this.apPrincipal.getChildren());
		// usado paracarregar a interface gráfica e o seu
		// respectivo controle
		FXMLLoader load = new FXMLLoader();
		// recuperando a interface
		load.setLocation(getClass().getResource("/telas/PedidoCliente.fxml"));
		Parent janela = load.load();
		// recuperando o controle.
		PedidoClienteController ctrl = load.getController();
		ctrl.setMesaAtual(Integer.parseInt(btn.getId()));
		ctrl.setGarcomAtual(cbGarcom.getSelectionModel().getSelectedItem());
		saida.println(Integer.parseInt(btn.getId())-1+" confirmar");
		ctrl.setMensagem(saida);
		this.apPrincipal.getChildren().add(janela);

	}

	public void carregarMessas(PrintStream saida) {
		// grpMessas.setGridLinesVisible(true);
		cbGarcom.setItems(FXCollections.observableArrayList(gDao.pesquisarGarcom("")));
		int cont = 1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				VBox vb = new VBox();
				vb.setPrefWidth(100);
				vb.setMaxWidth(100);
				vb.setMaxHeight(120);
				vb.setPrefHeight(120);
				Text txtStatus = new Text(Integer.toString(cont) + " Vazia");
				txtStatus.setTextAlignment(TextAlignment.CENTER);
				Image img = new Image("/imagens/mesa_vazia.png");
				ImageView viewimg = new ImageView(img);
				Button btnMesa = new Button();
				btnMesa.setPrefHeight(100.0);
				btnMesa.setPrefHeight(100.0);
				btnMesa.setMinHeight(100.0);
				btnMesa.setMinWidth(100.0);
				btnMesa.setGraphic(viewimg);
				btnMesa.setId(Integer.toString(cont));
				// olhar aqui
				btnMesa.setOnMouseClicked((event) -> {
					try {
						mudarStatus(btnMesa, saida);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				vb.getChildren().addAll(btnMesa, txtStatus);
				grpMessas.add(vb, j, i);
				cont++;
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			Socket cliente = new Socket("localhost", 12345);
			System.out.println("O cliente se conectou ao servidor!");
			PrintStream saida = new PrintStream(cliente.getOutputStream());
			carregarMessas(saida);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
