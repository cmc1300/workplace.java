package com.global.netcubeBackend;

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
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class DBNetcubeBackend {
	
	private static Connection connect = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement PStmt = null;
	
	private static int LEVEL_MONTHLY_UPDATE	= 1;
	private static int LEVEL_DAILY_UPDATE 	= 2;
	private static int LEVEL_HOURLY_UPDATE 	= 3;
	
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
	
	public DBNetcubeBackend () {
		
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
	
	
	public static void resetWebsiteSyncEmersionIdList() {
		
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      String sql = "update " + dbName + "." + "em_customerListing" + " set status = 'Cancelled' where 1 = 1";
	  	      statement.execute(sql);
	  	} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
	}
	
	
	public static String websiteSyncEmersionIdList(	String customerId, String newStatus, 
													String customerName, String accountEmail, 
													String accountCreated) {
		String strReturn 	= "";
		String sql 			= "";
		String conCustomer	= "";
		
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      sql = "select * from " + dbName + "." + "em_customerListing" + "  where customerId = " + customerId;
	  	      resultSet = statement.executeQuery(sql);
	  	      
	  	      if (customerName.indexOf("'") == -1) {
	  	    	  conCustomer = customerName;
	  	      }
	  	      else {
	  	    	  conCustomer = customerName.replaceAll("'", "''");
	    	  }
		
	  	      if (resultSet.next()) {
	  	    	  String processStatus	= resultSet.getString("processStatus");
	  	    	  String status 		= resultSet.getString("status");
	  	    	  /*
	  	    	  if (status.equalsIgnoreCase("Cancelled") && processStatus.equalsIgnoreCase("processed")) {
	  	    		  strReturn			= "CustomerId - " + customerId + " has already been cancelled and processed!";
	  	    	  }
	  	    	  else if (!status.equalsIgnoreCase("Cancelled") || !processStatus.equalsIgnoreCase("processed")) {*/
	  	    	  if (true) {
		  	    	  sql = 	"update " + dbName + "." + "em_customerListing" + " set status = '" + newStatus + "'" + 
		  	    			  	", customerEmail = '" + accountEmail + "', customerCreatedDate = '" + accountCreated + "'" +
		  	    			  	", processStatus = 'new', customerName = '" + conCustomer + "'" +
		  	    			  	"  where customerId = " + customerId;
		  	    	  statement.execute(sql);
		  	    	  strReturn			= "CustomerId - " + customerId + " has been updated!";
	  	    	  }
	  	    	  
	  	    	  if (customerId != "394423") {
	  	    		  strReturn			= "";
	  	    	  }
	  	      }
	  	      else {
	  	    	  sql = "INSERT INTO " + dbName + "." + "em_customerListing (`customerId`, `customerName`, `customerEmail`, `customerCreatedDate`, `status`) " + 
	  	    			"VALUES (" + customerId + ", '" + conCustomer + "', '" + accountEmail + "', '" + accountCreated + "', '" + newStatus + "')";
	  	    	  statement.execute(sql);
	  	    	  strReturn				= "New customerId - " + customerId + " has been created!";
	  	      }
	  	} 
	  	catch (Exception e) {
	  		strReturn = "customerId: " + customerId + " " + (new Date()).toString();
	  		strReturn = strReturn + "\n\t" + "    " + sql;
	  		strReturn = strReturn + "\n\t" + "    " + Arrays.toString(e.getStackTrace());
	  	} 
	  	finally {
	  		close();
	  	}
		return strReturn;
	}
	
	
	public static String websiteSyncEmersionIdListTUpdateRelatedTables(	String emersionId, String newStatus, 
																		String customerName, String accountPhone, 
																		String accountMobile, String accountEmail, 
																		String accountCreated) {
		String strReturn 				= "";
		String sql 						= "";
		String customer_name			= customerName.trim().toLowerCase();
		String firstName 				= "";
		String lastName 				= "";
		String telephone				= accountPhone.trim();
		String mobile					= accountMobile.trim();
		String nc_customerInfo_table	= "nc_customerInfoSync";
		
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      
	  	      sql = "select * from " + dbName + "." + nc_customerInfo_table + "  where emersionId = " + emersionId;
	  	      resultSet = statement.executeQuery(sql);
	  	      
	  	      if (resultSet.next()) {
	  	    	  sql =		"update " + dbName + "." + nc_customerInfo_table + " set emersionStatus = '" + newStatus + "' " + 
	  	    			  	"where emersionId = " + emersionId;
	  	    	  statement.execute(sql);
	  	      }
	  	      else {
	  	    	  sql =	  "INSERT INTO " + dbName + "." + nc_customerInfo_table + " (" +
	  	    			  "`emersionId`, `emersionStatus`, `firstName`, `middleName`, `lastName`, " + 
	  	    			  "`dateOfBirth`, `telephone`, `mobile`, `email`, `address`, `contractStartDate`" + ") " +  
	  	    			  "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	  	    	  PreparedStatement preparedStmt = connect.prepareStatement(sql);
	  	    	  
	  	    	  preparedStmt.setInt	(1, Integer.parseInt(emersionId));
	  	    	  
	  	    	  preparedStmt.setString(2, newStatus);
	  	    	  
	  	    	  if (customer_name.isEmpty()) {
	  	    		  firstName 			= "";
	  	    		  lastName 				= "";
	  	    	  }
	  	    	  else if (customer_name.endsWith("pte ltd")) {
	  	    		  firstName 			= customer_name.replaceAll("pte ltd", "");
	  	    		  firstName				= firstName.trim();
	  	    		  lastName 				= "pte ltd";
	  	    	  }
	  	    	  else if (customer_name.endsWith("pty ltd")) {
	  	    		  firstName 			= customer_name.replaceAll("pty ltd", "");
	  	    		  firstName				= firstName.trim();
	  	    		  lastName 				= "pty ltd";
	  	    	  }
	  	    	  else {
		  	    	  String[] name_array	= customer_name.split(" ");
		  	    	  if (name_array.length == 1) {
		  	    		  firstName 		= name_array[0].trim();
		  	    		  lastName 			= "";
		  	    	  }
		  	    	  else if (name_array.length == 2) {
		  	    		  firstName 		= name_array[0].trim();
		  	    		  lastName 			= name_array[1].trim();
		  	    	  }
		  	    	  else {
		  	    		  for (int i=0; i < name_array.length-1; i++) {
		  	    			firstName		= firstName + " " + name_array[i].trim();
		  	    		  }
		  	    		  firstName			= firstName.trim();
		  	    		  lastName 			= name_array[name_array.length-1].trim();
		  	    	  }
	  	    	  }
	  	    	  preparedStmt.setString(3, firstName);
	  	    	  preparedStmt.setString(4, "");
	  	    	  preparedStmt.setString(5, lastName);
	  	    	  
	  	    	  preparedStmt.setString(6, "0001-01-01");
	  	    	  
	  	    	  if (telephone.equalsIgnoreCase(mobile) && mobile.startsWith("04")) {
		  	    	  preparedStmt.setString(7, "");
		  	    	  preparedStmt.setString(8, mobile);
	  	    	  }
	  	    	  else if (telephone.equalsIgnoreCase(mobile) && !mobile.startsWith("04")) {
		  	    	  preparedStmt.setString(7, telephone);
		  	    	  preparedStmt.setString(8, "");
	  	    	  }
	  	    	  else {
	  	    		  preparedStmt.setString(7, telephone);
		  	    	  preparedStmt.setString(8, mobile);
	  	    	  }
	  	    	  
	  	    	  preparedStmt.setString(9, accountEmail.toLowerCase());
	  	    	  
	  	    	  preparedStmt.setString(10, "");
	  	    	  
	  	    	  SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy");
	  	    	  Date contractStartDate = formatter.parse(accountCreated);
	  	    	  formatter = new SimpleDateFormat("yyyy-MM-dd");
	  	    	  preparedStmt.setString(11, formatter.format(contractStartDate));
	  	    	  
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
	
	
	public static String websiteSyncEmersionIdListTStatusStatistics() {
		String sql 				= "";
		String customerId		= "";
		String strReturn		= "";
		String[] state_array 	= { 
										"Active", 
										"Cancelled", 
										"Preactive", 
										"Inactive", 
										"Suspended", 
										"Restricted by Parent", 
										"System Suspended", 
										"Suspended - Billing and Ordering"
									};

		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();

	  	      for (String status  : state_array) {
	  	    	  int count					= 0;
	  	    	  int countInOneLine		= 1;
	  	    	  String loopString 		= "";
	  	    	  ArrayList<String> row		= new ArrayList<String>();
	  	    	  
		  	      sql = "select customerId from " + dbName + "." + "em_customerListing  where status = '" + status + "'";
		  	      resultSet = statement.executeQuery(sql);
			
		  	      while (resultSet.next()) {
		  	    	  customerId	= resultSet.getString("customerId");
		  	    	  if (countInOneLine == 10) {
		  	    		  loopString		= loopString + customerId + "\n\t";
		  	    		  countInOneLine 	= 1;
		  	    	  }
		  	    	  else {
		  	    		  loopString		= loopString + customerId + "\t";
		  	    		  countInOneLine ++;
		  	    	  }
		  	    	  row.add(customerId);
		  	    	  count++;
		  	      }
		  	      
		  	      if (status.equalsIgnoreCase("Cancelled")) {
			  	      for (String temp : row) {
			  	    	  sql = "update " + dbName + "." + "web_onlineOrderInfo set status = 'cancelled' where customerId = " + temp;
				  	      statement.execute(sql);
			  	      }
		  	      }
		  	    
		  	      strReturn = 	strReturn + "\n" + "(" + status + " - " + count + ")\n\t" + "There are " + count + " customers so far";
		  	      if (!loopString.isEmpty() && !status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Cancelled")) { 
		  	    	  strReturn = strReturn + "\n\t" + loopString;
		  	      }
	  	      }
	  	} 
	  	catch (Exception e) {
	  		strReturn = "Function syncEmersionCancelledCustomers() error: " + strReturn + "\n\t" + Arrays.toString(e.getStackTrace());;
	  	} 
	  	finally {
	  		close();
	  	}

		return strReturn;
	}
	
	
	public static ArrayList<String> websiteSyncEmersionPayment() {
    	
    	ArrayList<String> row = new ArrayList<String>();
    	
    	/*		*/
    	try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      String sql = "select * from " + dbName + "." + "em_invoicePayment" + "  where cardType != 'Direct Debit' AND processedStatus = 'new' ORDER BY id ASC";
	  	      resultSet = statement.executeQuery(sql);
		
	  	      if (resultSet.next()) {
	  	    	  // It is possible to get the columns via name
	  	    	  // also possible to get the columns via the column number
	  	    	  // which starts at 1
	  	    	  // e.g. resultSet.getSTring(2);
	  	    	  String customerId 	= resultSet.getInt("customerId") + "";
	  	    	  String paymentAmount 	= resultSet.getString("paymentAmount");
	  	    	  String paymentTransId	= resultSet.getString("paymentTransId");
	  	    	  String cardType 		= resultSet.getString("cardType");
	  	    	  String cardName 		= resultSet.getString("cardName");
	  	    	  String cardNumber 	= resultSet.getString("cardNumber");
	  	    	  String cardExpiry 	= resultSet.getString("cardExpiry");
	  	    	  String cardCVV 		= resultSet.getString("cardCVV");
	  	    	  String autoPayment 	= resultSet.getInt("autoPayment") + "";
	  	    	  String customerName	= resultSet.getString("customerName");
	  	    	  String paymentUnit	= resultSet.getString("paymentUnit");
	  	    	  Timestamp paymentTime	= resultSet.getTimestamp("paymentTime");
	  	    	  String id 			= resultSet.getInt("id") + "";
	  	    	  
	  	    	  row.add(customerId);
	  	    	  row.add(paymentAmount);
	  	    	  row.add(paymentTransId);
	  	    	  row.add(cardType);
	  	    	  row.add(cardName);
	  	    	  row.add(cardNumber);
	  	    	  row.add(cardExpiry);
	  	    	  row.add(cardCVV);
	  	    	  row.add(autoPayment);
	  	    	  row.add(customerName);
	  	    	  row.add(paymentUnit);
	  	    	  row.add(paymentTime.toString());
	  	    	  row.add(id);
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
	
	
	public static void updateWebsiteSyncEmersionPayment(String id) {
		
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      String sql = "update " + dbName + "." + "em_invoicePayment" + " set processedStatus = 'processed', processedTime = NOW() where id = " + id;
	  	      statement.execute(sql);
	  	} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
	}
	
	public static String websiteSyncEmersionIdListV2GetLatestRecord(String table) {
		String strReturn 	= "";
		String sql 			= "";
		
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	     if (table == "em_packagePlan") {
		  	     sql = "select packagePlanId from " + dbName + "." + "em_packagePlan" + "  where 1 = 1 ORDER BY packagePlanId DESC";
		  	     resultSet = statement.executeQuery(sql);
		  	     if (resultSet.next()) {
		  	    	strReturn = resultSet.getString("packagePlanId");
		  	     }
		  	     else {
		  	    	strReturn = "";
		  	     }
	  	     }
	  	     else if (table == "em_servicePlan") {
		  	     sql = "select servicePlanId from " + dbName + "." + "em_servicePlan" + "  where 1 = 1 ORDER BY servicePlanId DESC";
		  	     resultSet = statement.executeQuery(sql);
		  	     if (resultSet.next()) {
		  	    	strReturn = resultSet.getString("servicePlanId");
		  	     }
		  	     else {
		  	    	strReturn = "";
		  	     }
	  	     }
	  	     
	  	     
	  	} 
	  	catch (Exception e) {
	  		strReturn = "";
	  	} 
	  	finally {
	  		close();
	  	}
		return strReturn;
	}
	
	public static void websiteSyncEmersionIdListV2InsertNewPackage (String id, String internalName, String externalName, 
			String periodLength, String periodsInAdvance, String accessFee, 
			String taxType, String buyOrSell, String status, 
			String servicePlanCount, String saleableInPortal, String createdDate, String saleableFrom) {
		
		String sql = "";
		
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      sql = "INSERT INTO em_packagePlan "
	  	      		+ "(packagePlanId, packagePlanIntName, packagePlanExtName, "
	  	      		+ " periodLength, periodsInAdvance, accessFee, "
	  	      		+ "taxType, buyOrSell, status, "
	  	      		+ "servicePlanCount, saleableInPortal, createdDate, saleableFrom) "
	  	      		+ "VALUES (" + id + ", '" + internalName + "', '" + externalName 
	  	      		+ "', '" + periodLength + "', " + periodsInAdvance + ", " + accessFee 
	  	      		+ ", '" + taxType + "', '" + buyOrSell + "', '" + status 
	  	      		+ "', " + servicePlanCount + ", '" + saleableInPortal + "', '" + createdDate + "', '" + saleableFrom + "')";
	  	      //System.out.println("System.out.: " + sql);
	  	      statement.execute(sql);
	  	} 
		catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException eMySQLIntegrityConstraintViolationException) {
	  	} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
	}
	
	public static void websiteSyncEmersionIdListV2InsertNewService (String id, String internalName, String externalName, 
			String serviceType, String serviceTypeCategory, String periodLength,
			String accessFee, String taxType, String buyOrSell, 
			String status, String createdDate, String saleableFrom) {
		
		String sql = "", serviceTypeId = "";
		
		try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
	  	      
	  	      sql = "select serviceTypeId from " + dbName + "." + "em_serviceType" + "  where serviceTypeName = '" + serviceType + "'";
	  	      resultSet = statement.executeQuery(sql);
	  	      if (resultSet.next()) {
	  	    	  serviceTypeId = resultSet.getString("serviceTypeId");
	  	      }
	  	      else {
	  	    	  serviceTypeId = "0";
	  	      }
	  	      
	  	      sql = "INSERT INTO em_servicePlan "
	  	      		+ "(servicePlanId, servicePlanIntName, servicePlanExtName, "
	  	      		+ " serviceTypeId, periodLength, accessFee, "
	  	      		+ "taxType, buyOrSell, status, "
	  	      		+ "createdDate, saleableFrom) "
	  	      		+ "VALUES (" + id + ", '" + internalName + "', '" + externalName 
	  	      		+ "', " + serviceTypeId + ", '" + periodLength + "', " + accessFee 
	  	      		+ ", '" + taxType + "', '" + buyOrSell + "', '" + status 
	  	      		+ "', '" + createdDate + "', '" + saleableFrom + "')";
	  	      statement.execute(sql);
	  	} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
	}

	public static void init_javaHooplaMonitorUpdate(Map<String, HooplaAgent> list_of_agents) {
		String sql = "";
		String agent_id;
		String agent_email;
		String hoopla_name;
		
		try {
			connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
			
		    // Statements allow to issue SQL queries to the database
		    statement = connect.createStatement();
		    // Result set get the result of the SQL query
		      
		    sql = "select * from " + dbName + "." + "web_hoopla_agents" + "  where status = 'valid'";
		    resultSet = statement.executeQuery(sql);
		    while (resultSet.next()) {
		    	agent_id = resultSet.getString("agent_id");
		    	agent_email = resultSet.getString("agent_email");
		    	hoopla_name = resultSet.getString("hoopla_name");
		    	list_of_agents.put(agent_email, new HooplaAgent(agent_id, agent_email, hoopla_name));
		    }
		} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
	}
	
	public static String javaHooplaMonitorUpdate(int level, String agent_email, HooplaAgent hooplaAgent, String previous_month, String current_year_month, String current_year_month_date) {
		String sql = "";
		String agent_id 		= hooplaAgent.get_agent_id();
		String hoopla_name 		= "";
		int current_24_months 	= 0;
		int current_12_months 	= 0;
		int current_0_months	= 0;
		int current_6_months 	= 0;
		float mtd_revenue 		= 0;

		/*		*/
		try {			
		    hooplaAgent.set_previous_month_revenue("0.00");
		    hooplaAgent.set_mtd_revenue("0.00");
		    
			connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		    // Statements allow to issue SQL queries to the database
		    statement = connect.createStatement();
		    // Result set get the result of the SQL query
		      
		    /*
		    if (true) {
		    	sql =	"select count(*) as previous_month_sales, sum(firstMonthFee) previous_month_revenue from " + dbName + "." + "web_onlineOrderInfo " + 
			    		"where dealer = '" + agent_id + "' " + 
			    		"and orderNumber like '" + previous_month + "%' "; 
			    resultSet = statement.executeQuery(sql);
			    while (resultSet.next()) {
			    	hooplaAgent.set_previous_month_sales(resultSet.getString("previous_month_sales"));
			    	if (resultSet.getString("previous_month_revenue") != null) {
			    		hooplaAgent.set_previous_month_revenue(resultSet.getString("previous_month_revenue"));
			    	}
			    }
		    }*/
		    
		    if (true) {
		    	sql =	"select orderNumber, serviceTerm, firstMonthFee, monthlyFee from " + dbName + "." + "web_onlineOrderInfo " + 
			    		"where dealer = '" + agent_id + "' " + 
			    		"and orderNumber like '" + previous_month + "%' "; 
			    resultSet = statement.executeQuery(sql);
			    while (resultSet.next()) {
			    	if (resultSet.getInt("serviceTerm") == 24) {
			    		current_24_months ++;
			    		mtd_revenue = mtd_revenue + resultSet.getFloat("monthlyFee") * 24;
			    	}
			    	else if (resultSet.getInt("serviceTerm") == 12) {
			    		current_12_months ++;
			    		mtd_revenue = mtd_revenue + resultSet.getFloat("monthlyFee") * 12;
			    	}
			    	else if (resultSet.getInt("serviceTerm") == 0) {
			    		current_0_months ++;
			    		mtd_revenue = mtd_revenue + resultSet.getFloat("monthlyFee") * 1;
			    	}
			    	else if (resultSet.getInt("serviceTerm") == 6) {
			    		current_6_months ++;
			    		mtd_revenue = mtd_revenue + resultSet.getFloat("monthlyFee") * 6;
			    	}
			    }
			    
			    hooplaAgent.set_previous_month_sales((current_24_months + current_12_months + current_0_months + current_6_months) + "");
			    if (Math.abs(mtd_revenue) > 0.01) {
			    	hooplaAgent.set_previous_month_revenue(String.format("%.02f", mtd_revenue) + "");
			    }
			    
			    current_24_months 	= 0;
				current_12_months 	= 0;
				current_0_months	= 0;
				current_6_months 	= 0;
				mtd_revenue 		= 0;
		    }
		    
		    sql =	"select orderNumber, serviceTerm, firstMonthFee, monthlyFee from " + dbName + "." + "web_onlineOrderInfo " + 
		    		"where dealer = '" + agent_id + "' " + 
		    		"and orderNumber like '" + current_year_month + "%' "; // + "and serviceTerm = 24";
		    resultSet = statement.executeQuery(sql);
		    while (resultSet.next()) {
		    	if (resultSet.getInt("serviceTerm") == 24) {
		    		current_24_months ++;
		    		mtd_revenue = mtd_revenue + resultSet.getFloat("monthlyFee") * 24;
		    	}
		    	else if (resultSet.getInt("serviceTerm") == 12) {
		    		current_12_months ++;
		    		mtd_revenue = mtd_revenue + resultSet.getFloat("monthlyFee") * 12;
		    	}
		    	else if (resultSet.getInt("serviceTerm") == 0) {
		    		current_0_months ++;
		    		mtd_revenue = mtd_revenue + resultSet.getFloat("monthlyFee") * 1;
		    	}
		    	else if (resultSet.getInt("serviceTerm") == 6) {
		    		current_6_months ++;
		    		mtd_revenue = mtd_revenue + resultSet.getFloat("monthlyFee") * 6;
		    	}
		    }
		    hooplaAgent.set_current_24_months(current_24_months + "");
		    hooplaAgent.set_current_12_months(current_12_months + "");
		    hooplaAgent.set_current_0_months(current_0_months + "");
		    hooplaAgent.set_current_6_months(current_6_months + "");
		    hooplaAgent.set_mtd_sales((current_24_months + current_12_months + current_0_months + current_6_months) + "");
		    if (Math.abs(mtd_revenue) > 0.01) {
		    	hooplaAgent.set_mtd_revenue(String.format("%.02f", mtd_revenue) + "");
		    }
		    
		    sql =	"select count(*) as daily_sales_count from " + dbName + "." + "web_onlineOrderInfo " + 
			   		"where dealer = '" + agent_id + "' " + 
			   		"and orderNumber like '" + current_year_month_date + "%' "; 
		    resultSet = statement.executeQuery(sql);
		    while (resultSet.next()) {
		    	hooplaAgent.set_daily_sales_count(resultSet.getString("daily_sales_count"));
		    }
		} 
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	  	finally {
	  		close();
	  	}
		
		return hooplaAgent.toString();
	}
}
