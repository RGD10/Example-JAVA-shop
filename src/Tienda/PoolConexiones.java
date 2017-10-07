package Tienda;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
/**
*
* @author Ruben
*/
public class PoolConexiones {

	private BasicDataSource pool;
	private static PoolConexiones instancia = null;

	private PoolConexiones ()
	{
		pool = new BasicDataSource();
		pool.setDriverClassName("com.mysql.jdbc.Driver");
		pool.setUsername("root");
		pool.setPassword("");
		pool.setUrl("jdbc:mysql://localhost/test");
		pool.setInitialSize(40);

	}

	public static PoolConexiones getInstancia()
	{
		if (instancia == null)
			instancia = new PoolConexiones();

		return instancia;
	}

	public BasicDataSource getPool() {
		return pool;
	}

}
