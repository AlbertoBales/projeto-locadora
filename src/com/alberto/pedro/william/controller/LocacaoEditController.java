package com.alberto.pedro.william.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.alberto.pedro.william.entity.Cliente;
import com.alberto.pedro.william.entity.Filme;
import com.alberto.pedro.william.entity.Locacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LocacaoEditController implements Initializable {

	@FXML private AnchorPane pnlPrincipal;

	@FXML private GridPane pnlDados;

	@FXML private Label lblDataEmprestimo;

	@FXML private DatePicker dtpEmprestimo;

	@FXML private Label lblClientes;

	@FXML private ComboBox<Cliente> cbxClientes;

	@FXML private Label lblFilmes;

	@FXML private ComboBox<Filme> cbxFilmes;

	@FXML private Label lblDataDevolucao;

	@FXML private DatePicker dtpDevolucao;

	@FXML private Label lblObs;

	@FXML private TextField txtObs;

	@FXML private HBox pnlBotoes;

	@FXML private Button btnOk;

	@FXML private Button btnCancelar;

	private Locacao locacao;

	private boolean okClick = false;

	private ClienteListaController clienteListaController;
	private FilmeListaController filmeListaController;

	private Stage janelaLocacaoEdit;

	@FXML void onClickBtnCancelar(ActionEvent event) {
		
		this.getJanelaLocacaoEdit().close();
	}

	@FXML void onClickBtnOK(ActionEvent event) {
		
		if (validarCampos()) {
			this.locacao.setClientes(this.cbxClientes.getSelectionModel().getSelectedItem());
			this.locacao.setFilmes(this.cbxFilmes.getSelectionModel().getSelectedItem());
			this.locacao.setData_emprestimo(Date.valueOf(this.dtpEmprestimo.getValue()));
			this.locacao.setData_devolucao(Date.valueOf(this.dtpDevolucao.getValue()));
			this.locacao.setObs(this.txtObs.getText());

			this.okClick = true;
			this.getJanelaLocacaoEdit().close();
		}
	}

	@Override public void initialize(URL location, ResourceBundle resources) {
		
		this.clienteListaController = new ClienteListaController();
		this.filmeListaController = new FilmeListaController();

		this.carregarComboBoxClientes();
		this.carregarComboBoxFilmes();
	}

	public Stage getJanelaLocacaoEdit() {
		return janelaLocacaoEdit;
	}

	public void setJanelaLocacaoEdit(Stage janelaLocacaoEdit) {
		this.janelaLocacaoEdit = janelaLocacaoEdit;
	}

	public void populaTela(Locacao locacao) {
		this.locacao = locacao;

		if (this.locacao.getClientes() != null) {
			this.cbxClientes.setValue(this.locacao.getClientes());
		}

		if (this.locacao.getFilmes() != null) {
			this.cbxFilmes.setValue(this.locacao.getFilmes());
		}
		if (this.locacao.getData_emprestimo() != null) {
			this.dtpEmprestimo.setValue(this.locacao.getData_emprestimo().toLocalDate());
		}
		if (this.locacao.getData_devolucao() != null) {
			this.dtpDevolucao.setValue(this.locacao.getData_devolucao().toLocalDate());
		}
		if (this.locacao.getObs() != null) {
			this.txtObs.setText(this.locacao.getObs());
		}

	}

	public boolean isOkClick() {

		return okClick;
	}

	private boolean validarCampos() {
		String mensagemErros = new String();
		if (this.cbxClientes.getSelectionModel().getSelectedItem() == null) {
			mensagemErros += "Cliente!\n";
		}
		
		if (this.cbxFilmes.getSelectionModel().getSelectedItem() == null) {
			mensagemErros += "Filme!\n";
		}
		
		if (this.dtpEmprestimo.getValue() == null) {
			mensagemErros += "Data de Empréstimo!\n";
		}
		
		if (this.dtpDevolucao.getValue() == null) {
			mensagemErros += "Data de Devolução!\n";
		}
		
		
		if (this.txtObs.getText() == null || this.txtObs.getText().trim().length() == 0) {
			
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.initOwner(this.janelaLocacaoEdit);
			alerta.setTitle("Dados invalidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informacoes:");
			alerta.setContentText(mensagemErros);
			alerta.showAndWait();

			return false;
		}
	}



	public void carregarComboBoxClientes() {
		ObservableList<Cliente> observableListaCliente = FXCollections.observableArrayList(this.clienteListaController.retornaListagemCliente());

		this.cbxClientes.setItems(observableListaCliente);
	}

	public void carregarComboBoxFilmes() {
		ObservableList<Filme> observableListaFilme = FXCollections.observableArrayList(this.filmeListaController.retornaListagemFilme());

		this.cbxFilmes.setItems(observableListaFilme);
	}
}
