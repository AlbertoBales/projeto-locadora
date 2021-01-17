package com.alberto.pedro.william.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.alberto.pedro.william.dao.LocacaoDAO;
import com.alberto.pedro.william.entity.Locacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LocacaoListaController implements Initializable {

    @FXML private AnchorPane pnlPrincipal;

    @FXML private SplitPane pnlDivisao;

    @FXML private AnchorPane pnlEsquerda;

    @FXML private TableView<Locacao> tbvLocacao;

    @FXML private TableColumn<Locacao, Long> tbcCodigo;

    @FXML private TableColumn<Locacao, String> tbcCliente;

    @FXML private AnchorPane pnlDireita;

    @FXML private Label lblDetalhes;

    @FXML private GridPane pnlDetalhes;

    @FXML private Label lblCliente;

    @FXML private Label lblClienteValor;

    @FXML private Label lblFilme;

    @FXML private Label lblDataEmprestimo;

    @FXML private Label lblFilmeValor;

    @FXML private Label lblDataEmprestimoValor;

    @FXML private Label lblDataDevolucao;

    @FXML private Label lblDataDevolucaoValor;

    @FXML private Label lblObs;

    @FXML private Label lblObsValor;

    @FXML private ButtonBar barBotoes;

    @FXML private Button btnInclur;

    @FXML private Tooltip tlpIncluir;

    @FXML private Button btnEditar;

    @FXML private Tooltip tlpEditar;
    
    @FXML private Button btnExcluir;

    @FXML private Tooltip tlpExcluir;

    private List<Locacao> listaLocacaos;
	private ObservableList<Locacao> observableListaLocacaos = FXCollections.observableArrayList();
	private LocacaoDAO locacaoDAO;

	public static final String LOCACAO_EDITAR = " - Editar";
	public static final String LOCACAO_INCLUIR = " - Incluir";

	@FXML void onClickBtnEditar(ActionEvent event) {
		Locacao locacao = this.tbvLocacao.getSelectionModel().getSelectedItem();

		if (locacao != null) {
			boolean btnConfirmarClic = this.onShowTelaLocacaoEditar(locacao, LocacaoListaController.LOCACAO_EDITAR);

			if (btnConfirmarClic) {
				this.getLocacaoDAO().update(locacao, null);
				this.carregarTableViewLocacaos();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma Locacao na tabela!");
			alerta.show();
		}

	}
    @FXML
    void onClickBtnExcluir(ActionEvent event) {
		Locacao locacao = this.tbvLocacao.getSelectionModel().getSelectedItem();

		if (locacao != null) {

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Confirma a exclusao da locacao?\n" + locacao.getClientes());

			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == botaoSim) {
				this.getLocacaoDAO().delete(locacao);
				this.carregarTableViewLocacaos();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma locacao na tabela!");
			alerta.show();
		}
	}
    
	@FXML void onClickBtnIncluir(ActionEvent event) {
		Locacao locacao = new Locacao();

		boolean btnConfirmarClic = this.onShowTelaLocacaoEditar(locacao, LocacaoListaController.LOCACAO_INCLUIR);

		if (btnConfirmarClic) {
			this.getLocacaoDAO().save(locacao);
			this.carregarTableViewLocacaos();
		}


	}

	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		this.setLocacaoDAO(new LocacaoDAO());
		this.carregarTableViewLocacaos();
		this.selecionarItemTableViewLocacaos(null);

		this.tbvLocacao.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewLocacaos(newValue));
	}

	public LocacaoDAO getLocacaoDAO() {
		return locacaoDAO;
	}

	public void setLocacaoDAO(LocacaoDAO locacaoDAO) {
		this.locacaoDAO = locacaoDAO;
	}

	public List<Locacao> getListaLocacaos() {
		return listaLocacaos;
	}

	public void setListaLocacaos(List<Locacao> listaLocacaos) {
		this.listaLocacaos = listaLocacaos;
	}

	public ObservableList<Locacao> getObservableListaLocacaos() {
		return observableListaLocacaos;
	}

	public void setObservableListaLocacaos(ObservableList<Locacao> observableListaLocacaos) {
		this.observableListaLocacaos = observableListaLocacaos;
	}

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do cadastro de locacao?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alerta.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alerta.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	public void carregarTableViewLocacaos() {
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tbcCliente.setCellValueFactory(new PropertyValueFactory<>("clientes"));

		this.setListaLocacaos(this.getLocacaoDAO().getAll());
		this.setObservableListaLocacaos(FXCollections.observableArrayList(this.getListaLocacaos()));
		this.tbvLocacao.setItems(this.getObservableListaLocacaos());
	}

	public void selecionarItemTableViewLocacaos(Locacao Locacao) {
		if (Locacao != null) {
			this.lblClienteValor.setText(Locacao.getClientes().getNome());
			this.lblFilmeValor.setText(Locacao.getFilmes().getNome());
			this.lblDataEmprestimoValor.setText(Locacao.getDataEmprestimoFormatado());
			this.lblDataDevolucaoValor.setText(Locacao.getDataDevolucaoFormatado());
			this.lblObsValor.setText(Locacao.getObs());
			
		} else {
			this.lblClienteValor.setText("");
			this.lblFilmeValor.setText("");
			this.lblDataEmprestimoValor.setText("");
			this.lblDataDevolucaoValor.setText("");
			this.lblObsValor.setText("");
		}
	}

	public boolean onShowTelaLocacaoEditar(Locacao locacao, String operacao) {
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alberto/pedro/william/view/LocacaoEdit.fxml"));
			Parent locacaoEditXML = loader.load();

			// criando uma janela nova
			Stage janelaLocacaoEditar = new Stage();
			janelaLocacaoEditar.setTitle("Cadastro de locacao" + operacao);
			janelaLocacaoEditar.initModality(Modality.APPLICATION_MODAL);
			janelaLocacaoEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene locacaoEditLayout = new Scene(locacaoEditXML);
			janelaLocacaoEditar.setScene(locacaoEditLayout);

			// carregando o controller e a scene
			LocacaoEditController locacaoEditController = loader.getController();
			locacaoEditController.setJanelaLocacaoEdit(janelaLocacaoEditar);
			locacaoEditController.populaTela(locacao);

			// mostrando a tela
			janelaLocacaoEditar.showAndWait();

			return locacaoEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
