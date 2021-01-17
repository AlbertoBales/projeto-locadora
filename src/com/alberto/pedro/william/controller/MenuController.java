
package com.alberto.pedro.william.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;



import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuController implements Initializable {

	@FXML private VBox pnlPrincipal;

	@FXML private MenuBar barMenu;

	@FXML private Menu mnuCadastro;

	@FXML private MenuItem mnoClientes;

	@FXML private MenuItem mnoFilmes;

	@FXML private MenuItem mnoLocacoes;

	@FXML private SeparatorMenuItem sepCadastro;

	@FXML private MenuItem mnoSair;

	@FXML private Menu mnuAjuda;

	@FXML private MenuItem mnoSobre;

	@FXML private AnchorPane pnlMeio;

	@FXML private ImageView img;

	@FXML private HBox pnlStatuBar;

	@FXML private Label lblData;

	@FXML private Separator sepData;

	@FXML private Label lblHora;

	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML void onClickMnoClientes(ActionEvent event) {
		
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alberto/pedro/william/view/ClienteLista.fxml"));
			Parent clienteListaXML = loader.load();

			// carregando o controller e a scene
			ClienteListaController clienteListaController = loader.getController();
			Scene clienteListaLayout = new Scene(clienteListaXML);

			this.getStage().setScene(clienteListaLayout);
			this.getStage().setTitle("Cadastro de clientes");

			// atribuindo evento para fechar janela
			this.getStage().setOnCloseRequest(e -> {
				if (clienteListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});

			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	@FXML void onClickMnoLocacoes(ActionEvent event) {
		
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alberto/pedro/william/view/LocacaoLista.fxml"));
			Parent locacaoListaXML = loader.load();

			// carregando o controller e a scene
			LocacaoListaController locacaoListaController = loader.getController();
			Scene locacaoListaLayout = new Scene(locacaoListaXML);

			this.getStage().setScene(locacaoListaLayout);
			this.getStage().setTitle("Cadastro de locaçao");

			// atribuindo evento para fechar janela
			this.getStage().setOnCloseRequest(e -> {
				if (locacaoListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});

			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@FXML void onClickMnoFilmes(ActionEvent event) {
		
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alberto/pedro/william/view/FilmeLista.fxml"));
			Parent FilmeListaXML = loader.load();

			// carregando o controller e a scene
			FilmeListaController filmeController = loader.getController();
			Scene filmeListaLayout = new Scene(FilmeListaXML);

			this.getStage().setScene(filmeListaLayout);
			this.getStage().setTitle("Cadastro de filmes");

			// atribuindo evento para fechar janela
			this.getStage().setOnCloseRequest(e -> {
				if (filmeController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});

			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML void onClickMnoSair(ActionEvent event) {
		
		if (this.onCloseQuery()) {
			System.exit(0);
		} else {
			event.consume();
		}
	}

	@FXML void onClickMnoSobre(ActionEvent event) {
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Sobre");
		alerta.setHeaderText(
				"\nSistema desenvolvido por: Alberto, Pedro e William.\nDesenvolvido com JavaFX versão 1.8.");
		alerta.showAndWait();
	}

	@Override public void initialize(URL location, ResourceBundle resources) {
		
		this.configuraBarraStatus();
		this.configuraStage();
	}

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do Menu Locadora?");
		ButtonType botaoNao = ButtonType.NO;
		ButtonType botaoSim = ButtonType.YES;
		alerta.getButtonTypes().setAll(botaoSim, botaoNao);
		Optional<ButtonType> resultado = alerta.showAndWait();
		return resultado.get() == botaoSim ? true : false;
	}

	public void configuraBarraStatus() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.lblData.setText("Data: " + LocalDateTime.now().format(dataFormatada));

		Timeline relogio = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			DateTimeFormatter horaFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");
			this.lblHora.setText("Hora: " + LocalDateTime.now().format(horaFormatada));
		}), new KeyFrame(Duration.seconds(1)));
		relogio.setCycleCount(Animation.INDEFINITE);
		relogio.play();
	}

	// configura tela
	public void configuraStage() {
		this.setStage(new Stage());
		this.getStage().initModality(Modality.APPLICATION_MODAL);
		this.getStage().resizableProperty().setValue(Boolean.FALSE);
	}
}
