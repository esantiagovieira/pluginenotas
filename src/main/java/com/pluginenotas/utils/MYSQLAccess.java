package com.pluginenotas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MYSQLAccess {
	private Connection connect=null;
	private Statement statement =null;
	private ResultSet resultSet = null;
	
	public ResultSet readDatabase(Integer id) {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/db_uc_tantrix?"
                            + "user=unosol&password=#prudencia5!");
		statement = connect.createStatement();
		
		resultSet = statement.executeQuery("Select * from cd_empresa where cod_empresa = " + id);
		resultSet.next();
		}
		catch (Exception e) {
			throw new RuntimeException("Não foi possível obter informações da empresa");
		}
		return resultSet;
	}
	
	public ResultSet readDatabase(Integer id, String host, String dbName, String user, String pass, String port) {
		String url = "jdbc:mysql://" + host + "/" + dbName;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url,user,pass);
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM cd_empresa WHERE cod_empresa = " + id);
			resultSet.next();
		}
		catch(Exception e) {
			throw new RuntimeException("Não foi possível obter informações da empresa");
		}
		return resultSet;
	}

}
