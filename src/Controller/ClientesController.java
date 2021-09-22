package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Database.Connect;
import Models.Clientes;

public class ClientesController {
	
	public ClientesController() {
		super();
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
				
				modelo.addRow(new String[] {
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
