package Tienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;


/**
*
* @author Ruben
*/


public class DAOUsuario implements DAOInterface <Usuario,String> {
	
	
	public Usuario findById(String login)
	{
	
		Usuario Cliente = null;
		try
		{
			BasicDataSource basicDataSource = PoolConexiones.getInstancia().getPool();
			Connection conexion = basicDataSource.getConnection();
			
				
			String query = "select * from CLIENTE where login = ?" ;
			
			PreparedStatement s = conexion.prepareStatement(query);
        
			s.setString(1,login);
        
			ResultSet rs = s.executeQuery ();
        
			
			// Se recorre el ResultSet, mostrando por pantalla los resultados.
			if (rs.next())
			{
				Cliente = new Usuario();
				Cliente.setLogin(rs.getString(1));
				Cliente.setPswd(rs.getString(2));
				Cliente.setSaldo(rs.getFloat(3));

			}
			// Se libera la conexion
	        conexion.close();
			
		}
		
		
    	catch (Exception ex)
    	{
    		System.out.println ("Error"+ex.getMessage());
    	}


		return Cliente;
	}
	
	public Vector <Usuario> findAll()
	{
		Connection connection = null;
		Vector<Usuario>clientes = new Vector<Usuario>();

		try{
			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 

			connection = datasource.getConnection();

			PreparedStatement s = connection.prepareStatement("SELECT * FROM cliente");

			ResultSet rs = s.executeQuery();

			Usuario c;
			while (rs.next()){
				c = new Usuario();
				c.setLogin(rs.getString(1));
				c.setPswd(rs.getString(2));
				c.setSaldo(rs.getFloat(3));
				clientes.add(c);
			}
			

			connection.close();

		} 

		catch (Exception e) {
			e.printStackTrace();
		} 

		return clientes;
	}
	
	public int delete(Usuario c)
	{
		int result = 0;
		Connection connection = null;

		try{
			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 

			connection = datasource.getConnection();

			String query = "DELETE FROM CLIENTE WHERE login = ?";
			PreparedStatement s = connection.prepareStatement("DELETE FROM cliente WHERE login = ?");

			s.setString(1, c.getLogin());

			System.out.println (query);

			result = s.executeUpdate();

			if (result > 0)
				System.out.println("eliminado correctamente");
			else
				System.err.println("cliente no eliminado");

			connection.close();

		} 

		catch (Exception e) {
			e.printStackTrace();
		} 

		return result;
	}
	
	public int insert(Usuario c)
	{
		Connection connection = null;
		int result = 0;
		
		try{
			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 

			connection = datasource.getConnection();

			//String query = "INSERT INTO cliente VALUES(?,?,?)";
			PreparedStatement s = connection.prepareStatement("INSERT INTO cliente VALUES (?,?,?)");	  
			s.setString(1, c.getLogin());
			s.setString(2, c.getPswd());
			s.setFloat(3, c.getSaldo());

			//System.out.println (query);

			result = s.executeUpdate();

			connection.close();

		} 

		catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	public int update(Usuario c)
	{
		Connection connection = null;
		int result = 0;
		
		try{
			PoolConexiones pool = PoolConexiones.getInstancia();
			BasicDataSource datasource = pool.getPool(); 

			connection = datasource.getConnection();

			PreparedStatement s = connection.prepareStatement("UPDATE cliente set saldo = ?, pasword = ? WHERE login = ?");

			s.setFloat(1, c.getSaldo());
			s.setString(2, c.getPswd());
			s.setString(3, c.getLogin());

			result = s.executeUpdate();

			connection.close();

		} 

		catch (Exception e) {
			e.printStackTrace();
		} 

		return result;
	}
}
