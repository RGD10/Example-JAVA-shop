package Tienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Ruben
*/

public interface DAOInterface <T,K> {
	
	public T findById(K key);
	
	
	
	public  List<T> findAll();
	
	
	public int delete(T ov);
	
	
	public int insert(T ov);

	public int update(T ov);
	
}