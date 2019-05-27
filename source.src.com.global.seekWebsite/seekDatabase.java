package com.global.seekWebsite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class seekDatabase {
	
	//private static String dbConnection = "jdbc:mysql://103.26.63.246/bankingn_netcubeHub";
	//private static String dbConnection = "jdbc:mysql://103.26.62.219/bankingn_netcubeHub";
	private static String dbConnection = "jdbc:mysql://103.26.62.207/bankingn_netcubeHub";
	private static String dbName = "bankingn_netcubeHub";
	private static String dbUser = "bankingn_jerry";
	private static String dbPassword = "u_b2z_y5wHEK";
	
	private static Connection connect = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	
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
	
	
	public static ArrayList<String> getEchosignInfoBasedOnEmail(String onlineOrderTable) {
    	
    	ArrayList<String> row = new ArrayList<String>();
    	String sql = "";
    	
    	/*		*/
    	try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      sql = "select orderNumber from " + dbName + "." + onlineOrderTable + "  where 1 = 1 ORDER BY orderNumber DESC";
	  	      //sql = "select orderNumber from " + dbName + "." + onlineOrderTable + "  where documentKeySyncStatus = 'duplicated' ORDER BY orderNumber DESC";
	  	      resultSet = statement.executeQuery(sql);
		
	  	      while (resultSet.next()) {
	  	    	  row.add(resultSet.getString("orderNumber"));
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
	
	
	public static ArrayList<String> finalDueDiligenceSyncEmersion() {
    	
    	ArrayList<String> row = new ArrayList<String>();
    	String sql = "";
    	
    	/*		*/
    	try {
		      connect = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
		
	  	      // Statements allow to issue SQL queries to the database
	  	      statement = connect.createStatement();
	  	      // Result set get the result of the SQL query
		  	      
	  	      sql = "select emersionId from " + dbName + ".web_due_diligence_info" + "  where syncStatus like 'unique%' AND processStatus is null ORDER BY emersionId DESC";
	  	      //sql = "select orderNumber from " + dbName + "." + onlineOrderTable + "  where documentKeySyncStatus = 'duplicated' ORDER BY orderNumber DESC";
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

}