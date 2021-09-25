package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import Database.Connect;

public class ClientesController {
	
	public static int clienteId = 0;
	
	public ClientesController() {
		super();
	}
	
public boolean editarCliente(String data, int tamanho) {
	
		String dado[] = new String[tamanho];
		
		for(int i = 0; i < dado.length; i++) {
			dado[i] = data;
		}
		
		return true;
		
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		PreparedStatement preparedStmt = null;
//
//		try {
//			conn = Connect.getConnection();
//			st = conn.createStatement();
//			var query = "INSERT INTO clientes(nome, endereco, bairro, cidade, estado, cep, telefone) values(?, ?, ?, ?, ?, ?, ?)";
//			preparedStmt = conn.prepareStatement(query);
//			
//			preparedStmt.setString(1, (String) data.get(0));
//			preparedStmt.setString(2, (String) data.get(1));
//			preparedStmt.setString(3, (String) data.get(2));
//			preparedStmt.setString(4, (String) data.get(3));
//			preparedStmt.setString(5, (String) data.get(4));
//			preparedStmt.setString(6, (String) data.get(5)); // trim remove espaços da string
//			preparedStmt.setString(7, (String) data.get(6));
//			
//			preparedStmt.execute();
//			
//			return true;
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//			
//			return false;
//		}finally{
//			Connect.closeResultSet(rs);
//			Connect.closeStatement(st);
////			Connect.closeConnection();
//		}
		
	}
	
	
	public boolean cadastrarCliente(List data) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStmt = null;

		try {
			conn = Connect.getConnection();
			st = conn.createStatement();
			var query = "INSERT INTO clientes(nome, endereco, bairro, cidade, estado, cep, telefone) values(?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = conn.prepareStatement(query);
			
			preparedStmt.setString(1, (String) data.get(0));
			preparedStmt.setString(2, (String) data.get(1));
			preparedStmt.setString(3, (String) data.get(2));
			preparedStmt.setString(4, (String) data.get(3));
			preparedStmt.setString(5, (String) data.get(4));
			preparedStmt.setString(6, (String) data.get(5)); // trim remove espaços da string
			preparedStmt.setString(7, (String) data.get(6));
			
			preparedStmt.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}finally{
			Connect.closeResultSet(rs);
			Connect.closeStatement(st);
//			Connect.closeConnection();
		}
		
	}

	public DefaultTableModel loadClientes(DefaultTableModel modelo) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			
			conn = Connect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT  * FROM clientes");
			while(rs.next()) {
				
				modelo.addRow(new Object[] {
						String.valueOf(rs.getInt("id")),
						rs.getString("nome"),
						rs.getString("endereco"),
						rs.getString("bairro"),
						rs.getString("cidade"),
						rs.getString("estado"),
						rs.getString("cep"),
						rs.getString("telefone"),
						rs.getString("detalhes")
					});
			
			}

			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			Connect.closeResultSet(rs);
			Connect.closeStatement(st);
//			Connect.closeConnection();
		}
		
		return modelo;
	}

}
