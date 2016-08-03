package telas;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;



public class PrincipalController implements Initializable{
	@FXML
	private Button btnEnviar;
	@FXML
	private TextField tfMsg;
	@FXML
	private Button btnSelecionar;
	@FXML
	private AnchorPane apPrincipal;
	@FXML
	private ScrollPane srpMesas;
	@FXML
	private GridPane grpMessas;
	public void enviar(PrintStream msg) {
		msg.println(tfMsg.getText());
		tfMsg.clear();
	}
	public void mudarStatus(Button btn,PrintStream saida) {
		saida.println(btn.getId());

	}
	public void carregarMessas(PrintStream saida) {
		//grpMessas.setGridLinesVisible(true);
		int cont = 1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				VBox vb = new VBox();
				vb.setPrefWidth(100);
				vb.setMaxWidth(100);
				vb.setMaxHeight(120);
				vb.setPrefHeight(120);
				Text txtStatus = new Text(Integer.toString(cont)+" Vazia");
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
				//olhar aqui
				btnMesa.setOnMouseClicked((event)->mudarStatus(btnMesa,saida));
				vb.getChildren().addAll(btnMesa,txtStatus);
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
			btnEnviar.setOnMouseClicked(event -> enviar(saida));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
