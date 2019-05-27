package com.global.javaSimulatorSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DBICTManager {

	private static Connection connect = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	//private static String dbConnection	= "jdbc:mysql://103.26.63.246/bankingn_ICTManager";
	//private static String dbConnection 	= "jdbc:mysql://103.26.62.219/bankingn_ICTManager";
	private static String dbConnection 		= "";
	private static String dbName 			= "";
	private static String dbUser 			= "";
	private static String dbPassword 		= "";
	
	private static JsonParser parser 		= new JsonParser();
	private static JsonObject jsonObject 	= null;
	private static JsonArray jsonArray		= null;
	
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } 
	    finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
	public DBICTManager () {
		
		String json 	= "";
		String host_ip	= "", db_user_name	= "", db_user_password	= "", db_name	= "";
		
		try {
			json 				= readUrl("http://api.netcube.com.au/projects/includes/interface.php?action=getICTManagerDBInfo");
			jsonObject 			= (JsonObject) parser.parse(json);
			
			host_ip				= jsonObject.get("host_ip").getAsString();
			db_user_name		= jsonObject.get("db_user_name").getAsString();
			db_user_password	= jsonObject.get("db_user_password").getAsString();
			db_name				= jsonObject.get("db_name").getAsString();
			
			dbConnection 		= "jdbc:mysql://" + host_ip + "/" + db_name;
			dbName 				= db_name;
			dbUser 				= db_user_name;
			dbPassword 			= db_user_password;
		} 
		catch (Exception e) {

		}
	}
	
	public static String getIndexOfAccount(String account)  {
		
		String query = null;
		String retunString = null;
		
		try {
			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
			statement = connect.createStatement();
			query = "select * from " + dbName + ".account where name = '" + account + "'";
			resultSet = statement.executeQuery(query);

  	      	if (resultSet.next()) {
  	      		retunString = resultSet.getString("ID");
  	      	}
  	      	else {
  	      		query = "insert into " + dbName + ".account(name,status)" + " values (?,?)";
  	            // create the mysql insert preparedstatement
  	            PreparedStatement preparedStmt = connect.prepareStatement(query);
  	            preparedStmt.setString (1, account);
  	            preparedStmt.setString (2, "valid");
  	            preparedStmt.execute();
  	            
  	            query = "select * from " + dbName + ".account where name = '" + account + "'";
  	            resultSet = statement.executeQuery(query);
    	      	if (resultSet.next()) {
    	      		retunString = resultSet.getString("ID");
    	      	}
  	      	}
	  	} 
	  	catch (Exception e) {
	  		System.out.println("Connection to Mysql for getIndexOfAccount() is wrong!");
	  		e.printStackTrace();
	  	} 
		
		close();
		return retunString;
	}
	
	public static String insertRecordIgnoreDuplication(String indexOfAccount,String tag,String weekday,String date,String time,String comments)  {
		
		String query = null;
		String retunString = null;
		
		try {
			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
			statement = connect.createStatement();
			query = "insert into " + dbName + ".record(accountID,tag,weekday,date,time,comments)" + " values (?,?,?,?,?,?)";

			// create the mysql insert preparedstatement
  	        PreparedStatement preparedStmt = connect.prepareStatement(query);
  	        preparedStmt.setInt (1, Integer.parseInt(indexOfAccount));
  	        preparedStmt.setString (2, tag);
  	        preparedStmt.setString (3, weekday);
  	        preparedStmt.setString (4, date);
  	        preparedStmt.setString (5, time);
  	        preparedStmt.setString (6, comments);
  	        preparedStmt.execute();
  	        retunString = "inserted";
	  	} 
		catch (MySQLIntegrityConstraintViolationException e) {
	  		retunString = "duplicated";
	  	} 
	  	catch (Exception e) {
	  		System.out.println("Connection to Mysql for insertRecordIgnoreDuplication() is wrong!");
	  		e.printStackTrace();
	  		retunString = "DBError";
	  	} 
		
		close();
		return retunString;
	}
	
	private static void close() {
    	
        try {
        	if (resultSet != null) {
        		resultSet.close();
        		resultSet = null;
        	}

        	if (statement != null) {
        		statement.close();
        		statement = null;
        	}

        	if (connect != null) {
        		connect.close();
        		connect = null;
        	}
        } 
        catch (Exception e) {

        }
    }

}
