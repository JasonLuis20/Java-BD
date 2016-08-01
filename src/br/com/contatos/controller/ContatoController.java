package br.com.contatos.controller;

import java.awt.List;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.spi.DirStateFactory.Result;

import br.com.contatos.helper.MySqlConnect;
import br.com.contatos.model.Contatos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

public class ContatoController implements Initializable{

	@FXML TextField txtTelefone;
	@FXML TextField txtNome;
	@FXML Button btnInserir;
	@FXML Button btnEditar;
	@FXML Button btnDeletar;
	@FXML ListView <Contatos> lstContatos;

	private void preencherLista(){

		lstContatos.getSelectionModel().clearSelection();
		lstContatos.getItems().clear();

		Connection con = MySqlConnect.ConectarDb();

		String sql = "SELECT * FROM  contact;";
		try {
			ResultSet rs =	con.createStatement().executeQuery(sql);

			while(rs.next()){
				Contatos c = new Contatos();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("name"));
				c.setTelefone(rs.getString("phone"));
				lstContatos.getItems().add(c);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}



	@FXML public void InserirContatos() {

		//Connection con = MySqlConnect.ConectarDb();


		//String sql = "insert into contact (name, phone) values(?,?);";

		//PreparedStatement parametros;

		Contatos novo = new Contatos();
		novo.setNome(txtNome.getText());
		novo.setTelefone(txtTelefone.getText());
		boolean sucesso = Contatos.inserir(novo);


		/*try {

			parametros = con.prepareStatement(sql);
			parametros.setString(1,txtNome.getText());
			parametros.setString(2,txtTelefone.getText());

			parametros.executeUpdate();

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		preencherLista();

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//metodo para preencher a lista
		preencherLista();
		/*quando der um clicl na lista vai aparecer na caixa de texto os valores selecionados.*/
		lstContatos.getSelectionModel().selectedItemProperty().addListener(x ->{
			Contatos contatos = lstContatos.getSelectionModel().getSelectedItem();
			if(contatos !=null){
				txtTelefone.setText(contatos.getTelefone());
				txtNome.setText(contatos.getNome());
			}
			});


	}
	@FXML public void EdiatarContatos() {
		Contatos contatos = lstContatos.getSelectionModel().getSelectedItem();
		contatos.setNome(txtNome.getText());
		contatos.setTelefone(txtTelefone.getText());
		boolean sucesso = Contatos.Editar(contatos);

		if(sucesso){
			System.out.print("funcionou");
		}else{
			System.out.print("nada");
		}
		
		txtTelefone.setText("");
		txtNome.setText("");
		preencherLista();

	}
	@FXML public void DeletarContatos() {

		Contatos contatos = lstContatos.getSelectionModel().getSelectedItem();



		boolean sucesso = Contatos.deletar(contatos);

		if(sucesso){
			System.out.print("funccionou");
		}else{
			System.out.print("nada");
		}
		/*variavel de conexão*/
		//Connection con = MySqlConnect.ConectarDb();

		//String sql = "Delete from contact where id= ?;";
		/*a variavel parametro funciona quando for necessario usar parametros para usar o banco*/
		/*PreparedStatement parametros;

		try {

			parametros = con.prepareStatement(sql);
			parametros.setInt(1,contatos.getId());
			parametros.executeUpdate();

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtTelefone.setText("");
		txtNome.setText("");*/
		preencherLista();
	}

}
