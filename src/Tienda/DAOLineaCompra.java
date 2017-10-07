package Tienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
*
* @author Ruben
*/




public class DAOLineaCompra implements DAOInterface<LineaCompra,String>{

	public LineaCompra findById(String id)
	{

		LineaCompra l = null;
		try
		{
			BasicDataSource basicDataSource = PoolConexiones.getInstancia().getPool();
			Connection conexion = basicDataSource.getConnection();
			
				
			String query = "select * from lineacompra where login = ?" ;
			
			PreparedStatement s = conexion.prepareStatement(query);
	    
			s.setString(1,id);
	    
			ResultSet rs = s.executeQuery ();
	    
			if (rs.next())
			{
				l = new LineaCompra();
				l.setId(rs.getString(1));
				l.setIdcompra(rs.getString(2));
				l.setCantidad(rs.getInt(3));
				l.setSubtotal(rs.getFloat(4));

			}
	        conexion.close();
			
		}catch (Exception ex)
		{
			System.out.println ("Error"+ex.getMessage());
		}


		return l;
}
	public int delete(LineaCompra l){
		Connection connection=null;
		int i=-1;
		try{
			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 
			connection = datasource.getConnection();
			Statement s = connection.createStatement();

			String query="DELETE FROM lineacompra WHERE ID = '" +l.getId()+"'";


			i=s.executeUpdate(query);
			
		
		}catch(Exception e){
			System.out.println("error");
		}
		finally {
			if (connection != null) {

				try {
					connection.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return i;
	}
	public int insert(LineaCompra l){
		Connection connection = null;
		int i=0;
		try
		{

			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 
			connection = datasource.getConnection();
			PreparedStatement s = connection.prepareStatement("INSERT INTO lineacompra VALUES (?,?,?,?)");

			s.setString(1, l.getId());
			s.setString(2, l.getIdcompra());
			s.setInt(3, l.getCantidad());
			s.setFloat(4, l.getSubtotal());
			
			i=s.executeUpdate();

		}  
		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (connection != null) {

				try {
					connection.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return i;
	}
	
	public Vector<LineaCompra> findAll(){
		
		Connection connection=null;
		Vector <LineaCompra>compras;
		compras=new Vector<LineaCompra>();
		
		try{

			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 
			connection = datasource.getConnection();
			Statement s = connection.createStatement();

			String query="SELECT * FROM lineacompra";
			
			ResultSet result=s.executeQuery(query);

			LineaCompra l;
			
			while(result.next()){
				
				l=new LineaCompra();
				l.setId(result.getString(1));
				l.setIdcompra(result.getString(2));
				l.setCantidad(result.getInt(3));
				l.setSubtotal(result.getInt(4));
				compras.addElement(l);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (connection != null) {

				try {
					connection.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return compras;
	}
	public int update(LineaCompra l){
		Connection connection=null;
		int i = 0;
		try{
			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 
			connection = datasource.getConnection();
			Statement s = connection.createStatement();
			
			String query="UPDATE lineacompra SET NOMBRE=?,STOCK=?"+"WHERE ID=?";
			i=s.executeUpdate(query);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (connection != null) {

				try {
					connection.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return i;
	}
	
	
}
