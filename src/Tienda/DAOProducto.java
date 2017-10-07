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


public class DAOProducto implements DAOInterface <Producto,String>{


public Producto findById(String id)
{

	Producto p = null;
	try
	{
		BasicDataSource basicDataSource = PoolConexiones.getInstancia().getPool();
		Connection conexion = basicDataSource.getConnection();
		
			
		String query = "select * from producto where id = ?" ;
		
		PreparedStatement s = conexion.prepareStatement(query);
    
		s.setString(1,id);
    
		ResultSet rs = s.executeQuery ();
    
		if (rs.next())
		{
			p = new Producto();
			p.setId(rs.getString(1));
			p.setNombre(rs.getString(2));
			p.setPvp(rs.getFloat(3));
			p.setStock(rs.getInt(4));

		}
        conexion.close();
		
	}
	
	
	catch (Exception ex)
	{
		System.out.println ("Error"+ex.getMessage());
	}


	return p;
}
public int delete(Producto p){
	Connection connection=null;
	int i=-1;
	try{
		PoolConexiones pool = PoolConexiones.getInstancia();
		BasicDataSource datasource = pool.getPool(); 
		connection = datasource.getConnection();
		Statement s = connection.createStatement();

		String query="DELETE FROM CLIENTE WHERE ID = '" +p.getId()+"'";
	

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
public int insert(Producto p){
	Connection connection = null;
	int i=0;
	try
	{

		PoolConexiones pool = PoolConexiones.getInstancia();
		BasicDataSource datasource = pool.getPool(); 
		connection = datasource.getConnection();
		Statement s = connection.createStatement();

		String query = "INSERT INTO PRODUCTO(ID, NOMBRE, STOCK) VALUES ('" + p.getId() + "',"+ "'"+ p.getNombre() + "'," + "'," + p.getStock()+")";
		

		i=s.executeUpdate(query);

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

public Vector<Producto> findAll(){
	
	Connection connection=null;
	Vector <Producto>productos;
	productos=new Vector<Producto>();
	
	try{

		PoolConexiones pool = PoolConexiones.getInstancia();
		BasicDataSource datasource = pool.getPool(); 
		connection = datasource.getConnection();
		Statement s = connection.createStatement();

		String query="SELECT * FROM PRODUCTO";
		
		ResultSet result=s.executeQuery(query);

		Producto p;
		
		while(result.next()){
			p=new Producto();
			p.setId(result.getString(1));
			p.setNombre(result.getString(2));
			p.setPvp(result.getFloat(3));
			p.setStock(result.getInt(4));
			productos.addElement(p);
			
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
	return productos;
}
public int update(Producto p){
	Connection connection=null;
	int i = 0;
	try{
		PoolConexiones pool = PoolConexiones.getInstancia();
		BasicDataSource datasource = pool.getPool(); 
		connection = datasource.getConnection();
		PreparedStatement s = connection.prepareStatement("UPDATE PRODUCTO SET NOMBRE=?, precio = ?, STOCK=? WHERE ID=?");
		
		s.setString(1, p.getNombre());
		s.setFloat(2, p.getPvp());
		s.setInt(3, p.getStock());
		s.setString(4, p.getId());
		
		i=s.executeUpdate();
		
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
