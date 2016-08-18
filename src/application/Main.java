package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import telas.PrincipalController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/telas/Principal.fxml"));
			Parent root = loader.load();
			PrincipalController ctrl = loader.getController();
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image("/imagens/cliente-icone.png"));
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			//Titulo da Janela
			primaryStage.setTitle("Cliente");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
