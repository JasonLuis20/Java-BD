package br.com.contatos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.contatos.helper.MySqlConnect;

public class Contatos {

	private int id;
	private String nome;
	private String telefone;

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome + " - " + telefone;
	}

	public static boolean inserir(Contatos c){
		Connection con = MySqlConnect.ConectarDb();


		String sql = "insert into contact (name, phone) values(?,?);";

		PreparedStatement parametros;

		try {

			parametros = con.prepareStatement(sql);
			parametros.setString(1,c.getNome());
			parametros.setString(2,c.getTelefone());

			parametros.executeUpdate();
			con.close();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deletar(Contatos c){

		/*variavel de conexão*/
		Connection con = MySqlConnect.ConectarDb();

		String sql = "Delete from contact where id = ?;";
		/*a variavel parametro funciona quando for necessario usar parametros para usar o banco*/
		PreparedStatement parametros;

		try {

			parametros = con.prepareStatement(sql);
			parametros.setInt(1,c.getId());
			parametros.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Editar(Contatos c){

		Connection con = MySqlConnect.ConectarDb();

		String sql = "Update contact set name = ? , phone = ? where id = ?;";
		PreparedStatement parametros;

		try {

			parametros = con.prepareStatement(sql);
			parametros.setString(1,c.getNome());
			parametros.setString(2,c.getTelefone());
			parametros.setInt(3,c.getId());
			parametros.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
