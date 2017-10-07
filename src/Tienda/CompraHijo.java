package Tienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
/**
*
* @author Ruben
*/



	public class CompraHijo extends DAOCompra {
		public String generarID()
		{
			Connection connection = null;
			String dato = null;
			
			try{
				PoolConexiones pool = PoolConexiones.getInstancia();
				BasicDataSource datasource = pool.getPool(); 

				connection = datasource.getConnection();

				PreparedStatement ss = connection.prepareStatement("SELECT COUNT(*) FROM compra");
				
				ResultSet rs = ss.executeQuery();
				
				if(rs.next()){
					String valor = rs.getString(1);
					int cual = Integer.parseInt(valor);
					cual++;
					dato = String.valueOf(cual);
				}
				
				connection.close();

			} 

			catch (Exception e) {
				e.printStackTrace();
			} 
			return dato;
		}
		public float subtotal(String id)
		{
			
			Connection connection = null;
			float precio = 0;

			try{
				PoolConexiones pool = PoolConexiones.getInstancia();
				BasicDataSource datasource = pool.getPool(); 

				connection = datasource.getConnection();

				PreparedStatement ss = connection.prepareStatement("SELECT precio FROM producto WHERE id = ?");

				ss.setString(1, id);
				ResultSet rs = ss.executeQuery();

				if(rs.next()){
					precio = rs.getFloat(1);
				}
				connection.close();

			} 

			catch (Exception e) {
				e.printStackTrace();
			} 
			return precio;
		}
}
