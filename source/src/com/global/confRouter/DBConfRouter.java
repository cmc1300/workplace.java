package com.global.confRouter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class DBConfRouter {
	
	//private static String dbConnection = "jdbc:mysql://103.26.62.219/bankingn_netcubeHub";
	private static String dbConnection = "jdbc:mysql://103.26.62.207/bankingn_netcubeHub";
	private static String dbName = "bankingn_netcubeHub";
	private static String dbUser = "bankingn_jerry";
	private static String dbPassword = "u_b2z_y5wHEK";
	
	private static Connection connect = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	public static ArrayList<String> readADSLUserInfoFromDatabse(String emersionId) {
    	
    	ArrayList<String> ret = null;
    	String sql = "";

    	/*		*/
    	try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      sql = "select * from " + dbName + "." + "nc_customerInfo" + "  where emersionId = '" + emersionId + "'";
	  	      resultSet = statement.executeQuery(sql);
	  	      if (resultSet.next()) {
	  	    	  // It is possible to get the columns via name
	  	    	  // also possible to get the columns via the column number
	  	    	  // which starts at 1
	  	    	  // e.g. resultSet.getSTring(2);
	  	    	  String user_id = resultSet.getString("emersionId");
	  	    	  String user_name = resultSet.getString("adsl_nbnUsername");
	  	    	  if ( user_name == null ) {
	  	    		user_name = "none";
	  	    	  }
	  	    	  String password = resultSet.getString("adsl_nbnPassword");
	  	    	  if ( password == null ) {
	  	    		password = "none";
	  	    	  }

	  	    	  String first_name = resultSet.getString("firstName");
	  	    	  String last_name = resultSet.getString("lastName");
	  	    	  String telephone = resultSet.getString("telephone");
	  	    	  String mobile = resultSet.getString("mobile");
	  	    	  
	  	    	  ret = new ArrayList<String>();
	  	    	  ret.add("Success");
	  	    	  ret.add("Waiting");
	  	    	  ret.add(toTitleCase(first_name + " " + last_name));
	  	    	  ret.add(user_name);
	  	    	  ret.add(password);
	  	    	  ret.add("NetCube_" + first_name.toUpperCase().charAt(0) + last_name.toUpperCase().charAt(0));
	  	    	  if ( mobile != null ) {
	  	    		 ret.add(mobile);
	  	    	  }
	  	    	  else {
	  	    		  ret.add(telephone);
	  	    	  }
	  	    	  ret.add("NetCube_" + first_name.toUpperCase().charAt(0) + last_name.toUpperCase().charAt(0));
	  	    	  
	  	    	  String field1 = resultSet.getString("voipUsername"); 
	  	    	  if ( field1 == null ) {
	  	    		field1 = "none";
	  	    	  }
	  	    	  String field2 = resultSet.getString("voipPassword");
	  	    	  if ( field2 == null ) {
	  	    		field2 = "none";
	  	    	  }
	  	    	  String field3 = "billing3.novatel.com.au";
	  	    	  ret.add(field1);
	  	    	  ret.add(field2);
	  	    	  ret.add(field3);
	  	    	  ret.add(user_id);
	  	    	  
	  	    	  String typeOfService = resultSet.getString("typeOfService");
	  	    	  int isNBNService = resultSet.getInt("isNBNService");
	  	    	  int isFibreXService = resultSet.getInt("isFibreXService");
	  	    	  if (typeOfService.equals("1")) {
	  	    		  ret.add(resultSet.getString("typeOfContract"));
	  	    	  }
	  	    	  else if (isNBNService == 1) {
	  	    		  ret.add(resultSet.getString("typeOfNBNService"));
	  	    	  }
	  	    	  else if (isFibreXService == 1) {
	  	    		  ret.add(resultSet.getString("typeOfFibreXService"));
	  	    	  }
	  	    	  
	  	    	  sql = "select *, COUNT(*) AS rowcount from " + dbName + "." + "web_onlineOrderInfo" + "  where customerId = " + emersionId;
		  	      resultSet = statement.executeQuery(sql);
		  	      
		  	      if (resultSet.next()) {
		  	    	  int size = resultSet.getInt("rowcount");
		  	    	  if (size==0) {
		  	    		ret = new ArrayList<String>();
			  	    	ret.add("web_onlineOrderInfo 0");
		  	    	  }
		  	    	  else {
			  	    	  String promotionCode = resultSet.getString("promotionCode"); 
			  	    	  if ( promotionCode.isEmpty() || promotionCode == null ) {
			  	    		promotionCode = "none";
			  	    	  }
			  	    	  String addon = resultSet.getString("addonNote"); 
			  	    	  if ( addon.isEmpty() || addon == null ) {
			  	    		addon = "none";
			  	    	  }
			  	    	  ret.add(size + "");
			  	    	  ret.add(promotionCode);
			  	    	  ret.add(addon);
		  	    	  }
		  	      }
		  	      else {
		  	    	ret = new ArrayList<String>();
		  	    	ret.add("web_onlineOrderInfo 0");
		  	      }
	  	      }
	  	      else {
	  	    	ret = new ArrayList<String>();
	  	    	ret.add("nc_customerInfo 0");
	  	      }
	  	} 
	  	catch (Exception e) {
	  		ret = new ArrayList<String>();
  	    	ret.add("exception");
  	    	StringWriter sw = new StringWriter();
  	    	e.printStackTrace(new PrintWriter(sw));
    		ret.add(sw.toString());
	  	} 
	  	finally {
	  		close();
	  	}
    	return ret;
    }
	
	public static String checkIfThisDateProcessed(String dateString)  {
		
		String query = null;
		String retunString = null;
		
		try {
			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
			statement = connect.createStatement();
			query = "select * from " + dbName + ".record where date = '" + dateString + "'";
			resultSet = statement.executeQuery(query);
			
			int i = 0;
  	      	while (resultSet.next()) {
  	      		i++;
  	      	}
  	      	if (i == 0) {
  	      		retunString = "no record found";
  	      	}
  	      	else {
  	      		retunString = "processed " + i;
  	      	}
	  	} 
	  	catch (Exception e) {
	  		retunString = "DBError";
	  		System.out.println("Connection to Mysql for checkIfThisDateProcessed() is wrong!");
	  		e.printStackTrace();
	  	} 
		
		close();
		return retunString;
	}
	
	public static boolean checkIfThisRecordAlreadyExisting(
			String yearMonDate,
			String listedOneRecordTime,
			int    listedOneRecordDur,
			String listedOneRecordCallingCLI,
			String listedOneRecordCarrier,
			String listedOneRecordLocation, 
			String listedOneRecordCarrier2)  {
		
		String query = null;
		boolean retunboolean = false;
		
		try {
			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
			statement = connect.createStatement();
			query = "select * from " + dbName + ".record where" + 
						" date = '" + yearMonDate + "' AND " +
						" time = '" + listedOneRecordTime + "' AND " +
						" dur = " + listedOneRecordDur + " AND " +
						" callingCLI = '" + listedOneRecordCallingCLI + "' AND " +
						" carrier = '" + listedOneRecordCarrier + "' AND " +
						" location = '" + listedOneRecordLocation + "' AND " +
						" carrier2 = '" + listedOneRecordCarrier2 + "'";
			resultSet = statement.executeQuery(query);

			int i = 0;
  	      	while (resultSet.next()) {
  	      		i++;
  	      	}
  	      	if (i == 0) {
  	      		retunboolean = false;
  	      	}
  	      	else {
  	      		retunboolean = true;
  	      	}
	  	} 
	  	catch (Exception e) {
	  		retunboolean = false;
	  		System.out.println("Connection to Mysql for checkIfThisRecordAlreadyExisting() is wrong!");
	  		e.printStackTrace();
	  	} 
		
		close();
		return retunboolean;
	}
	
	public static String insertRecordIgnoreDuplication(
					String yearMonDate,
					String listedOneRecordTime,
					int    listedOneRecordDur,
					String listedOneRecordCallingCLI,
					String listedOneRecordCarrier,
					String listedOneRecordLocation, 
					String listedOneRecordCarrier2, 
					String listedOneRecord
					)  {
		
		String query = null;
		String retunString = null;
		
		try {
			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
			statement = connect.createStatement();
			query = "insert into " + dbName + ".record(date,time,dur,callingCLI,carrier,location,carrier2,comments)" + " values (?,?,?,?,?,?,?,?)";

			// create the mysql insert preparedstatement
  	        PreparedStatement preparedStmt = connect.prepareStatement(query);
  	        preparedStmt.setString (1, yearMonDate);
  	        preparedStmt.setString (2, listedOneRecordTime);
  	        preparedStmt.setInt (3, listedOneRecordDur);
  	        preparedStmt.setString (4, listedOneRecordCallingCLI);
  	        preparedStmt.setString (5, listedOneRecordCarrier);
  	        preparedStmt.setString (6, listedOneRecordLocation);
  	        preparedStmt.setString (7, listedOneRecordCarrier2);
  	        preparedStmt.setString (8, listedOneRecord);
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
	
    private static String toTitleCase(String givenString) {

    	char[] chars = givenString.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
		    if (!found && Character.isLetter(chars[i])) {
		      chars[i] = Character.toUpperCase(chars[i]);
		      found = true;
		    } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
		      found = false;
		    }
		}
		return String.valueOf(chars);
	}  
    


}
