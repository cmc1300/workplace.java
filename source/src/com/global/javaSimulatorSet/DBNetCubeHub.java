package com.global.javaSimulatorSet;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.parser.JavadocParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class DBNetCubeHub {
	
	private static Connection connect = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement PStmt = null;
	
	//private static String dbConnection 	= "jdbc:mysql://103.26.63.246/bankingn_netcubeHub";
	//private static String dbConnection 	= "jdbc:mysql://103.26.62.219/bankingn_netcubeHub";
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
	
	public DBNetCubeHub () {
		
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
	
	public static ArrayList<String> readTaskParametersFromDatabse(String type) {
    	
    	ArrayList<String> row = new ArrayList<String>();
    	
    	/*		*/
    	try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      String sql = "select * from " + dbName + "." + "web_JavaScheduledPlan" + "  where status = 'valid' AND planName = '" + type + "'";
	  	      resultSet = statement.executeQuery(sql);
		
	  	      if (resultSet.next()) {
	  	    	  // It is possible to get the columns via name
	  	    	  // also possible to get the columns via the column number
	  	    	  // which starts at 1
	  	    	  // e.g. resultSet.getSTring(2);
	  	    	  String planName = resultSet.getString("planName");
	  	    	  String status = resultSet.getString("status");
	  	    	  String planIntervalInSeconds = resultSet.getInt("planIntervalInSeconds") + "";
	  	    	  Timestamp planLastExecutedTime = resultSet.getTimestamp("planLastExecutedTime");
	  	    	  row.add(planName);
	  	    	  row.add(status);
	  	    	  row.add(planIntervalInSeconds);
	  	    	  row.add(planLastExecutedTime.toString());
	  	      }
	  	      else {
	  	    	  row = null;
	  	      }
	  	} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
    	return row;
    }
	
	public static ArrayList<String> getTableFieldFromDatabse(String type, String tableField) {
    	
    	ArrayList<String> row = new ArrayList<String>();
    	
    	/*		*/
    	try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      String sql = "select " + tableField + " from " + dbName + "." + "web_JavaScheduledPlan" + "  where status = 'valid' AND planName = '" + type + "'";
	  	      resultSet = statement.executeQuery(sql);
		
	  	      if (resultSet.next()) {
	  	    	  if (tableField == "planLastEmailSentTime") {
	  	    		  row.add(tableField);
		  	    	  Timestamp planLastEmailSentTime = resultSet.getTimestamp(tableField);
		  	    	  if (planLastEmailSentTime != null) {
		  	    		  row.add(planLastEmailSentTime.toString());
		  	    	  }
		  	    	  else {
		  	    		row = null;
		  	    	  }
	  	    	  }
	  	      }
	  	      else {
	  	    	  row = null;
	  	      }
	  	} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
    	return row;
    }
	
	public static void setTableFieldFromDatabse(String type, String tableField, java.util.Date date) {
    	
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
		      java.sql.Timestamp sqlDatetime = new java.sql.Timestamp(date.getTime());
		      PStmt = connect.prepareStatement("update " + dbName + "." + "web_JavaScheduledPlan" + " set " + tableField + " = ? where status = 'valid' AND planName = '" + type + "'");
		      PStmt.setTimestamp(1, sqlDatetime);
		      PStmt.execute();
	  	} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
    }

	
	public static void resetLastExecutedTime(String type, java.util.Date date) {
		
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
		      /*		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
	  	      String sql = "update " + dbName + "." + "web_JavaScheduledPlan" + " set planLastExecutedTime = NOW() where status = 'valid' AND planName = '" + type + "'";
	  	      statement.execute(sql);
	  	      */
		      java.sql.Timestamp sqlDatetime = new java.sql.Timestamp(date.getTime());
		      PStmt = connect.prepareStatement("update " + dbName + "." + "web_JavaScheduledPlan" + " set planLastExecutedTime = ? where status = 'valid' AND planName = '" + type + "'");
		      PStmt.setTimestamp(1, sqlDatetime);
		      PStmt.execute();
	  	} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
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
	
	
	public static ArrayList<String> getEchosignContractBasedOnEmersion() {
    	
    	ArrayList<String> row = new ArrayList<String>();
    	String sql = "";
    	
    	/*		*/
    	try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
	  	      
	  	      /*		*/
	  	      sql = "select emersionId from " + dbName + ".web_due_diligence_info " + 
	  	      		" where source LIKE  'web_onlineOrderInfo' " + 
	  	    		" AND  (documentStatus LIKE  'OUT_FOR_SIGNATURE' OR documentStatus LIKE  'OUT_FOR_APPROVAL') " +
	  	    		" AND  (syncStatus NOT LIKE  'ok' OR syncStatus IS NULL) " +
	  	    		" AND  (documentKeySync IS NULL OR documentKeySync =  '') ORDER BY emersionId DESC";
	  	      resultSet = statement.executeQuery(sql);
	  	      while (resultSet.next()) {
	  	    	  row.add(resultSet.getString("emersionId"));
	  	      }
		  	      
	  	      //sql = "select emersionId from " + dbName + ".web_due_diligence_info" + "  where source = '' ORDER BY emersionId DESC";
	  	      //sql = "select emersionId from " + dbName + ".web_due_diligence_info" + "  where source = 'emersion' and emersionStatus = 'Active' and syncStatus = 'nok' ORDER BY emersionId DESC";
	  	      //sql = "select emersionId from " + dbName + ".web_due_diligence_info" + "  where source = 'emersion' and syncStatus = 'nok' ORDER BY emersionId DESC";
	  	      //sql = "select emersionId from " + dbName + ".web_due_diligence_info" + "  where source != 'emersion' and documentStatus = '' and processStatus = 'NOK' ORDER BY emersionId DESC";
	  	      /*			*/
	  	      sql =	"select emersionId from " + dbName + ".web_due_diligence_info " + 
	  	    		  " where (documentStatus = '' or documentStatus is null) " + 
	  	    		  " and (syncStatus != 'ok' or syncStatus IS NULL) ORDER BY emersionId DESC";
	  	      resultSet = statement.executeQuery(sql);
	  	      while (resultSet.next()) {
	  	    	  row.add(resultSet.getString("emersionId"));
	  	      }
	  	      
	  	      /*
	  	      sql = "select emersionId from " + dbName + ".web_due_diligence_info" + "  where source != 'emersion' and documentStatus = '' and processStatus = 'NOK' ORDER BY emersionId DESC";
	  	      resultSet = statement.executeQuery(sql);
		
	  	      while (resultSet.next()) {
	  	    	  row.add(resultSet.getString("emersionId"));
	  	      }*/
	  	} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
    	return row;
    }
	
	public static ArrayList<String> downloadEchosignContractBasedOnEmersion() {
    	
    	ArrayList<String> row = new ArrayList<String>();
    	String sql = "";
    	
    	/*		*/
    	try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      //sql = "select emersionId from " + dbName + ".web_due_diligence_info" + "  where source = 'emersion' AND syncStatus = 'ok'  AND processStatus = 'NOK' ORDER BY emersionId ASC";
	  	      sql = "select emersionId from " + dbName + ".web_due_diligence_info" + "  where (documentStatus != '' or syncStatus = 'ok') AND (processStatus = 'NOK' or processStatus IS NULL) ORDER BY emersionId DESC";
	  	      resultSet = statement.executeQuery(sql);
		
	  	      while (resultSet.next()) {
	  	    	  row.add(resultSet.getString("emersionId"));
	  	      }
	  	} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
    	return row;
    }
	
	
	public static String websiteSyncEmersionIdListTUpdateRelatedTables(	
			String emersionId, 		String newStatus, 
			String customerName, 	String accountPhone, 
			String accountMobile, 	String accountEmail, 
			String accountCreated, 	String activeService, 
			String activePurchase, 	String accountBalance) {
		
		String strReturn 				= "";
		String sql 						= "";
		String customer_name			= customerName.trim().toLowerCase();
		String firstName 				= "";
		String lastName 				= "";
		String telephone				= accountPhone.trim();
		String mobile					= accountMobile.trim();
		String nc_customerInfo_table	= "blis_customerListing";
		
		try {
			connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
			
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			
			sql = "select * from " + dbName + "." + nc_customerInfo_table + "  where customerId = " + emersionId;
			resultSet = statement.executeQuery(sql);
			
			if (resultSet.next()) {
				sql =		"update " + dbName + "." + nc_customerInfo_table + " set status = '" + newStatus + "', " + 
							"activeService = " + activeService + ", " + 
							"activePurchase = " + activePurchase + ", " + 
							"accountBalance = '" + accountBalance + "', " + 
							"lastSyncTimestamp = NOW() " + 
							"where customerId = " + emersionId;
				statement.execute(sql);
			}
			else {
				sql =		"INSERT INTO " + dbName + "." + nc_customerInfo_table + " (" +
							"`customerId`, `customerName`, `status`, `phone`, " + 
							"`mobile`, `email`, `startDate`, `activeService`, " + 
							"`activePurchase`, `accountBalance`, `lastSyncTimestamp`) " +  
							"VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStmt = connect.prepareStatement(sql);
				
				preparedStmt.setInt	(1, Integer.parseInt(emersionId));
				
				preparedStmt.setString(2, customer_name);
				
				preparedStmt.setString(3, newStatus);

				if (telephone.equalsIgnoreCase(mobile) && mobile.startsWith("04")) {
					preparedStmt.setString(4, "");
					preparedStmt.setString(5, mobile);
				}
				else if (telephone.equalsIgnoreCase(mobile) && !mobile.startsWith("04")) {
					preparedStmt.setString(4, telephone);
					preparedStmt.setString(5, "");
				}
				else {
					preparedStmt.setString(4, telephone);
					preparedStmt.setString(5, mobile);
				}
				
				preparedStmt.setString(6, accountEmail.toLowerCase());
				
				preparedStmt.setString(7, accountCreated);
				
				preparedStmt.setInt(8, Integer.parseInt(activeService));
				
				preparedStmt.setInt(9, Integer.parseInt(activePurchase));
				
				preparedStmt.setString(10, accountBalance);
				
				preparedStmt.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
				
				preparedStmt.execute();
			}
		}
		catch (Exception e) {
			strReturn = "emersionId: " + emersionId + " " + (new Date()).toString();
			strReturn = strReturn + "\n\t" + "    " + sql;
			strReturn = strReturn + "\n\t" + "    " + Arrays.toString(e.getStackTrace());
		} 
		finally {
			close();
		}
		return strReturn;
	}
	
	
	public static ArrayList<String> getMaximumBlisstelEmersionId() {
		String sql 				= "";
		ArrayList<String> row	= new ArrayList<String>();
		String status			= "NOK";
		String customerId		= "";
		String customerStatus	= "";

		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      sql 		= 	"select MAX(customerId) AS maxCustomerId from " + dbName + "." + "blis_customerListing" + 
	  	    		  		"  where uploadReference is null";
	  	      resultSet = statement.executeQuery(sql);
		
	  	      if (resultSet.next()) {
	  	    	  customerId			= resultSet.getString("maxCustomerId");
	  	    	  
	  	    	  sql 					= 	"select status from " + dbName + "." + "blis_customerListing" + 
	  	    			  					"  where customerId = " + customerId;
		  	      resultSet 			= statement.executeQuery(sql);
		  	      if (resultSet.next()) {
		  	    	  status			= "OK";
		  	    	  customerStatus	= resultSet.getString("status");
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
	  	}
		return row;
	}
	
	public static ArrayList<String> getBlisstelAAPTCustomerServiceRef() {
		String sql 					= "";
		ArrayList<String> row		= new ArrayList<String>();
		String status				= "OK";
		String customer_service_ref	= "";
		String service_number		= "";

		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      sql 		= 	"select service_number, customer_service_ref from " + dbName + "." + "blis_aapt_service_internet" + 
	  	    		  		"  where length(customer_service_ref) = 9 and emersionId is null LIMIT 1";
	  	      resultSet 				= statement.executeQuery(sql);
		
	  	      if (resultSet.next()) {
	  	    	customer_service_ref	= resultSet.getString("customer_service_ref");
	  	    	service_number			= resultSet.getString("service_number");
	  	      }
	  	      else {
	  	    	  status				= "NOK";
	  	      }
	  	} 
	  	catch (Exception e) {
	  			status					= "Exception";
	  			customer_service_ref	= (new Date()).toString() + "\n\t" + Arrays.toString(e.getStackTrace());
	  	} 
	  	finally {
	  		close();
	  		row.add(status);
	  		row.add(customer_service_ref);
	  		row.add(service_number);
	  	}
		return row;
	}
	
	
	public static String websiteSyncEmersionIdUpdate_blis_aapt_service_internet (	
			String service_number, 			String customer_service_ref,
			String customerId, 				String customerStatus, 
			String balance, 				String export_Reference) {
		
		String strReturn 				= "";
		String sql 						= "";
		
		try {
			connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
			
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			
			sql =		"update " + dbName + "." + "blis_aapt_service_internet" + " set emersionId = " + customerId + ", " + 
						"emersionStatus = '" + customerStatus + "', " + 
						"emersionUploadReference = '" + export_Reference + "', " +
						"emersionAccountBalance = '" + balance + "' " +
						"where service_number = " + service_number;
			statement.execute(sql);
		}
		catch (Exception e) {
			strReturn = "emersionId: " + customerId + " " + (new Date()).toString();
			strReturn = strReturn + "\n\t" + "    " + sql;
			strReturn = strReturn + "\n\t" + "    " + Arrays.toString(e.getStackTrace());
		} 
		finally {
			close();
		}
		return strReturn;
	}
	
	
	public static String setWebsiteSyncEmersionIdListTUploadReference(String customerId, String upload_Reference) {
		String strReturn = "processed";
		
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		
	  	      String sql = 	"update " + dbName + "." + "blis_customerListing" + " " +
	  	    		  		"set uploadReference = '" + upload_Reference + "'" + 
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
	
}
