package com.global.hubdatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class DBHubdatabase {
	
	private static Connection connect = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	//private static String dbConnection 	= "jdbc:mysql://103.26.63.246/bankingn_netcubeHub";
	//private static String dbConnection 	= "jdbc:mysql://103.26.62.219/bankingn_netcubeHub";
	private static String dbConnection 		= "";
	private static String dbName 			= "";
	private static String dbUser 			= "";
	private static String dbPassword 		= "";
		
	private static JsonParser parser 		= new JsonParser();
	private static JsonObject jsonObject 	= null;
	private static JsonArray jsonArray		= null;
	
	private static String table_em_customerListing	= "em_customerListing";
	//private static String table_em_customerListing	= "em_customerListingTest";
	
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
	
	public DBHubdatabase () {
		
		String json 	= "";
		String host_ip	= "", db_user_name	= "", db_user_password	= "", db_name	= "";
		
		try {
			json 				= readUrl("http://api.netcube.com.au/projects/includes/interface.php?action=getNetCubeHubDBInfo");
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
	
	public static ArrayList<String> getMaximumEmersionId() {
		String sql 				= "";
		ArrayList<String> row	= new ArrayList<String>();
		String status			= "NOK";
		String customerId		= "";
		String customerStatus	= "";
		String customerName		= "";

		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      //sql = "select MAX(customerId) AS maxCustomerId from " + dbName + "." + table_em_customerListing + "  where status = 'Active' AND processStatus = 'new'";
	  	      //sql = "select MAX(customerId) AS maxCustomerId from " + dbName + "." + table_em_customerListing + "  where status = 'Cancelled' AND processStatus = 'new'";
	  	      sql 		= "select MAX(customerId) AS maxCustomerId from " + dbName + "." + table_em_customerListing + "  where processStatus = 'new'";
	  	      resultSet = statement.executeQuery(sql);
		
	  	      if (resultSet.next()) {
	  	    	  customerId	= resultSet.getString("maxCustomerId");
	  	    	  sql 			= "select status, processStatus, customerName from " + dbName + "." + table_em_customerListing + "  where customerId = " + customerId;
		  	      resultSet 	= statement.executeQuery(sql);
			
		  	      if (resultSet.next()) {
		  	    	  status			= "OK";
		  	    	  customerStatus	= resultSet.getString("status");
		  	    	  customerName		= resultSet.getString("customerName");
		  	      }
	  	      }
	  	      else {
	  	    	  status		= "NOK";
	  	      }
	  	} 
	  	catch (Exception e) {
	  			status			= "Exception";
	  			customerId		= (new Date()).toString() + "\n\t" + Arrays.toString(e.getStackTrace());
	  	} 
	  	finally {
	  		close();
	  		row.add(status);
	  		row.add(customerId);
	  		row.add(customerStatus);
	  		row.add(customerName);
	  	}
		return row;
	}
	
	public static String getFailedSynchronizationTasks() {
		String sql 			= "";
		String comments		= "";
		String strReturn	= "";
		int count			= 0;

		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      sql = "select * from " + dbName + "." + table_em_customerListing + "  where status = 'Active' AND processStatus = 'wrong'";
	  	      resultSet = statement.executeQuery(sql);
		
	  	      while (resultSet.next()) {
	  	    	  comments		= resultSet.getString("comments");
	  	    	  strReturn		= strReturn + comments + "\n";
	  	    	  count++;
	  	      }
	  	} 
	  	catch (Exception e) {
	  		strReturn = "Function getFailedSynchronizationTasks() error: " + strReturn;
	  	} 
	  	finally {
	  		close();
	  	}
		return "(" + count + ")\n" + strReturn;
	}
	
	public static String setEmersionProcessStatus(int customerId, String processStatus, String comments) {
		String strReturn = "processed";
		
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		
	  	      String sql = 	"update " + dbName + "." + table_em_customerListing + " " +
	  	    		  		"set processStatus = '" + processStatus + "', comments = '" + comments.replace("'","''") + "', " + 
	  	    		  		"lastSyncTimestamp = NOW() " +
	  	    		  		"where customerId = " + customerId;
	  	      statement.execute(sql);
	  	} 
	  	catch (Exception e) {
	  		strReturn = "\n" + (new Date()).toString();
	  		strReturn = strReturn + "\n" + "    " + Arrays.toString(e.getStackTrace());
	  	} 
	  	finally {
	  		close();
	  	}
		return strReturn;
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
