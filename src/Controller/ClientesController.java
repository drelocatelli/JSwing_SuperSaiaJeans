package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.Connect;

import Models.Clientes;

public class ClientesController {
	
	public ClientesController() {
		super();
	}

	public List<Clientes> loadClientes() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Clientes> clienteList = new ArrayList<>();
		
		try {
			
			conn = Connect.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT  * FROM clientes");
			while(rs.next()) {
				Clientes cliente = new Clientes();
				
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setBairro(rs.getString("bairro"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setEstado(rs.getString("estado"));
				cliente.setCep(rs.getInt("cep"));
				cliente.setTelefone(rs.getInt("telefone"));
				cliente.setDetalhes(rs.getString("detalhes"));

				clienteList.add(cliente);
			
			}

			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			Connect.closeResultSet(rs);
			Connect.closeStatement(st);
//			Connect.closeConnection();
		}
		
		return clienteList;
	}

}
