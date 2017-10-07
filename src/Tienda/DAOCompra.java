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



public class DAOCompra implements DAOInterface<Compra,String> {
	
	public Compra findById(String id)
	{

		Compra c = null;
		try
		{
			BasicDataSource basicDataSource = PoolConexiones.getInstancia().getPool();
			Connection conexion = basicDataSource.getConnection();
			
				
			String query = "select * from compra where login = ?" ;
			
			PreparedStatement s = conexion.prepareStatement(query);
	    
			s.setString(1,id);
	    
			ResultSet rs = s.executeQuery ();
	    
			if (rs.next())
			{
				c = new Compra();
				c.setId(rs.getString(1));
				c.setLogin(rs.getString(2));
				c.setFecha(rs.getDate(3));

			}
	        conexion.close();
			
		}catch (Exception ex)
		{
			System.out.println ("Error"+ex.getMessage());
		}


		return c;
}
	public int delete(Compra c){
		Connection connection=null;
		int i=-1;
		try{
			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 
			connection = datasource.getConnection();
			Statement s = connection.createStatement();

			String query="DELETE FROM compra WHERE ID = '" +c.getId()+"'";


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
	public int insert(Compra c){
		Connection connection = null;
		int i=0;
		try
		{

			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 
			connection = datasource.getConnection();
			PreparedStatement s = connection.prepareStatement("INSERT INTO compra VALUES (?,?,?)");

			s.setString(1, c.getId());
			s.setString(2, c.getLogin());
			s.setDate(3, c.getFecha());

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
	
	public Vector<Compra> findAll(){
		
		Connection connection=null;
		Vector <Compra>compras;
		compras=new Vector<Compra>();
		
		try{

			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 
			connection = datasource.getConnection();
			Statement s = connection.createStatement();

			String query="SELECT * FROM compra";
			
			ResultSet result=s.executeQuery(query);

			Compra c;
			
			while(result.next()){
				
				c=new Compra();
				c.setId(result.getString(1));
				c.setLogin(result.getString(2));
				c.setFecha(result.getDate(3));
				compras.addElement(c);
				
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
	public int update(Compra c){
		Connection connection=null;
		int i = 0;
		try{
			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 
			connection = datasource.getConnection();
			Statement s = connection.createStatement();
			
			String query="UPDATE compra SET NOMBRE=?,STOCK=?"+"WHERE ID=?";
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
