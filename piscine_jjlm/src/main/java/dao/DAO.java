package dao;

import java.sql.SQLException;

import java.util.List;



public interface DAO<T> {

	T read(int id) throws SQLException;
	
	List<T> getAll() throws SQLException;
	
	int create(T t) throws SQLException;
		
	
	int update(T t) throws SQLException;
	
	
	int delete(T t) throws SQLException;
	
	
	
}
