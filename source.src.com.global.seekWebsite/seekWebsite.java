/*
 * This Java simulator was developed by Guoping Xu under below requirement:
 * All candidates should be selected for market purpose
 * https://talent.seek.com.au
 * careers@webnova.com.au	Welcome9876
 */

package com.global.seekWebsite;

import java.awt.*;   
import java.awt.event.*; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Set;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class seekWebsite extends Frame implements ActionListener {

	public WebDriver driver = null;
	
	public int waitTime = 30;

	public String seekWebsiteTransaction					= "https://talent.seek.com.au/";
	public String echosignSyncTransaction					= "http://api.netcube.com.au/projects/netcube/echosign/retrieveEchosignDocumentKeys.php";
	public String getEchosignInfoBasedOnEmail				= "http://api.netcube.com.au/projects/netcube/wamp/getEchosignInfoBasedOnEmail.php";
	public String finalDueDiligenceSyncEmersion				= "http://api.netcube.com.au/projects/netcube/wamp/finalDueDiligenceSyncEmersion.php";
	public String getEchosignContractBasedOnEmersion		= "http://api.netcube.com.au/projects/netcube/echosign/getEchosignContractBasedOnEmersion.php";
	public String downloadEchosignContractBasedOnEmersion	= "http://api.netcube.com.au/projects/netcube/echosign/downloadEchosignContractBasedOnEmersion.php";
	
	public String LoginUsername = "careers@webnova.com.au";
	public String LoginPassword = "Welcome9876";
	
	public static int internalMin = 4;
	public static int internalMax = 5;
	public static int internalBetweenLoop = 30;	
	public static int internalBetweenExtLoop = 300;	
	
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private Label lblCount;    // Declare component Label
	private TextField tfCount; // Declare component TextField
	private Button btnCount;   // Declare component Button
	private int outputLineMAXCount = 8000;    	
	private JTextArea logOutput = null;
	private JScrollPane scroller = null;
	 
	   /** Constructor to setup GUI components and event handling */
	   public seekWebsite () {
	      setLayout(new FlowLayout());
	         // "super" Frame sets its layout to FlowLayout, which arranges the components
	         //  from left-to-right, and flow to next row from top-to-bottom.
	      
	      JPanel rootPanel = new JPanel();
	      rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
	      add(rootPanel);
	      
	      JPanel  panel1 = new JPanel(new FlowLayout()); 
	      rootPanel.add(panel1);  
	 
	      lblCount = new Label("Summary infomation");  // construct Label
	      panel1.add(lblCount);                    // "super" Frame adds Label
	      tfCount = new TextField("0", 20); // construct TextField
	      tfCount.setEditable(false);       // set to read-only
	      panel1.add(tfCount);                     // "super" Frame adds tfCount
	      
	     
	      JPanel panel2 = new JPanel();
	      panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	      rootPanel.add(panel2);
	 
	      logOutput = new JTextArea(30, 80);
	      logOutput.setEditable(false);
	      scroller = new JScrollPane(logOutput);
          scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
          scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	      DefaultCaret caret = (DefaultCaret)logOutput.getCaret();
	      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	      panel2.add(scroller); 
	      
	      btnCount = new Button("Exit");   // construct Button
	      btnCount.setFont(new Font("Sans Serif", Font.BOLD, 20));
	      panel2.add(btnCount);                    // "super" Frame adds Button

	      btnCount.addActionListener(this);
	         // Clicking Button source fires ActionEvent
	         // btnCount registers this instance as ActionEvent listener
	 
	      setTitle("Once-off Website Scanning");  
	      //setSize(800, 600);        
	      setBackground( Color.GRAY );
	      
	      GraphicsConfiguration gc = getGraphicsConfiguration();
	      Rectangle bounds = gc.getBounds();
	      /*
	      setLocation((int) ((bounds.width / 2) - (size.getWidth() / 2)),
                  (int) ((bounds.height / 2) - (size.getHeight() / 2))); */
	      setLocation((int) ((bounds.width) - 930),0);
	      pack(); 
	      setVisible(true);         // "super" Frame shows
	   }
	   
	   /** ActionEvent handler - Called back upon button-click. */
	   @Override
	   public void actionPerformed(ActionEvent evt) {
		   System.exit(0);
	   }
	
	public WebElement waitForLocator(final By by) {
		boolean flag = new WebDriverWait(driver, waitTime).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				//wlog("======!"+d.findElement(by).isDisplayed()+"!=======");
				return d.findElement(by).isDisplayed();
			}
		});
		//wlog("======555"+flag+"!=======");
		WebElement element = null;
		if(flag){
			element = driver.findElement(by);
		}
		return element;
	}
	
	
	public void selectOption(String id, String text) {       
        Select select = new Select(this.waitForLocator(By.id(id)));
        select.selectByVisibleText(text);        
    }
	
	
	public void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} 
		catch (InterruptedException e) {			
			wlog("Function sleep() error: " + Arrays.toString(e.getStackTrace()));
		}
	}
        
    private int randomWithRange(int min, int max) {
       int range = (max - min) + 1;     
       return (int)(Math.random() * range) + min;
    }
        
    
    private void wlog(String log) {
    	
        // Display the counter value on the TextField tfCount
        // tfCount.setText(logOutput.getLineCount() + ""); // convert int to String
        
        if (logOutput.getLineCount() > outputLineMAXCount) {
        	logOutput.setText(log);
        }
        else {
        	logOutput.append(log + "\n");
        	JScrollBar vertical = scroller.getVerticalScrollBar();
        	vertical.setValue( vertical.getMaximum());
        }
    }
    
    
    /*	
	private void seekWebsiteTransaction() {
    	WebElement WebComponent = null;	
    	String candidateInfo = null;
    	String name = "",job = "",tel = "", email = "";
    	String[] candidateInfoArray = null;
    	int loopIntCount = 0, loopExtCount = 0, loopExtStop = 0, tryCount = 0;
    	Boolean isPresent = true;
    	Actions action = null;
    	FileOutputStream writer = null;
    	OutputStreamWriter bufferedWriter = null;
    		
    	try {
    		this.driver = new FirefoxDriver();
    		this.sleep(randomWithRange(internalMin, internalMax));
        	this.driver.navigate().to(seekWebsiteTransaction);
    		this.sleep(randomWithRange(internalMin, internalMax));
    			
    		//driver.manage().window().maximize();
    		this.waitForLocator(By.name("Username")).sendKeys(LoginUsername);
    		this.waitForLocator(By.name("Password")).sendKeys(LoginPassword);
    		this.sleep(1);
    		this.waitForLocator(By.name("Submit")).click();	
    		this.sleep(randomWithRange(internalMin, internalMax));
    		
    		this.waitForLocator(By.cssSelector("#mainNavMenu > li:nth-child(3) > a")).click();
    		this.sleep(internalBetweenLoop);
    		wlog("=============================================================================================================");
    		writer = new FileOutputStream("seekWebsite-3.txt");
    		bufferedWriter = new OutputStreamWriter(writer);
    		
    		// FileOutputStream("seekWebsite.txt");
    		// loopExtCount = 100;
    		// loopStopAt = 200;
    		
    		loopExtCount = 200;			// 0  100  200  262
    		loopExtStop  = 262;
    		if (loopExtCount != 0) {
    			this.waitForLocator(By.cssSelector("#mainFrame > div.l-new-content > form > div:nth-child(4) > div > div.pagination-area_center > input")).clear();
    			this.sleep(1);
    			this.waitForLocator(By.cssSelector("#mainFrame > div.l-new-content > form > div:nth-child(4) > div > div.pagination-area_center > input")).sendKeys("" + loopExtCount);
    			this.sleep(1);
    			this.waitForLocator(By.cssSelector(".pagination-area_next-page > div:nth-child(2) > i:nth-child(1)")).click();
    			this.sleep(internalBetweenLoop);
    		}
    		

    		while (loopExtCount++ < loopExtStop) {
    			
    			loopIntCount = 1;
    			while (loopIntCount <= 20) {
        			
    				try {
    					
	    				if (tryCount == 3) {
	    					loopIntCount ++;
	    					tryCount = 0;
	    				}
	    	    		
	        			isPresent = driver.findElements(By.cssSelector("#mainFrame > div.l-new-content > form > div.l-inner-row > div > div:nth-child(5) > div:nth-child(" + loopIntCount + ") > div:nth-child(1)")).size() > 0;
	    				if (!isPresent) {
	    					break;
	    				}
	    				
	    	    		WebComponent = this.driver.findElement(By.cssSelector("#mainFrame > div.l-new-content > form > div.l-inner-row > div > div:nth-child(5) > div:nth-child(" + loopIntCount + ") > div:nth-child(1)"));
	    	    		candidateInfo = WebComponent.getText().trim();
	    	    		candidateInfoArray = candidateInfo.split("\n");
	    	    		//wlog("candidateInfo-1: " + candidateInfo);
	    	    		name = candidateInfoArray[0];
	    	    		//job	 = candidateInfoArray[3];
	    	    		WebComponent = this.driver.findElement(By.partialLinkText(name));
	    	    		action = new Actions(driver);
	    	    		action.moveToElement(WebComponent).perform();
	    	    		this.sleep(1);
	    	    		
	    	    		WebComponent = this.driver.findElement(By.cssSelector("div.table:nth-child(" + loopIntCount + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)"));
	    	    		candidateInfo = WebComponent.getText().trim();
	    	    		candidateInfoArray = candidateInfo.split("\n");
	    	    		//wlog("candidateInfo-2: " + candidateInfo);
	    	    		tel   = candidateInfoArray[0];
	    	    		email = candidateInfoArray[1];
	    	    		
	    	    		//wlog(name + "," + tel + "," + email);
	    	    		bufferedWriter.write(name + "," + tel + "," + email + "\r\n");
	    	    		tfCount.setText("" + ((loopExtCount-1)*20 + loopIntCount) + "/" + loopExtCount);
	    	    		tryCount = 0;
	    	    		loopIntCount++;
	    	    		this.sleep(1);
    				}
    				catch (Exception e) {
    		    		wlog("Function seekWebsiteTransaction() internal error: " + name + " " + Arrays.toString(e.getStackTrace()));
    		    		tryCount ++;
    		    		this.sleep(randomWithRange(internalMin, internalMax));
    		    	} 
        		}
    			
    			wlog("Page " + loopExtCount + " has been processed successfully");
    			if (loopExtCount!= 262) {
	    			this.waitForLocator(By.cssSelector(".pagination-area_next-page > div:nth-child(2) > i:nth-child(1)")).click();
	    			this.sleep(internalBetweenLoop);
    			}
    		}
    		
    		wlog("=============================================================================================================");
    		bufferedWriter.close();
        	this.sleep(randomWithRange(internalMin, internalMax));
    		this.driver.close();
    		driver = null;
    	} 
    	catch (Exception e1) {
    		wlog("Function seekWebsiteTransaction() external error: " + Arrays.toString(e1.getStackTrace()));
    		this.sleep(randomWithRange(internalMin, internalMax));
    		if (driver != null) {
    			this.driver.close();
    			driver = null;
    		}
    	} 
    }
    */
   
    
	/*	
    private void echosignSyncTransaction() {
    	WebElement WebComponent = null;	
    	int yearLoopCount = 0, weekLoopCount = 0, waitLoopCount = 0, succLoopCount = 0;
    	Boolean isPresent = false;
    	String responseText = "";
    	
    	try {
    		wlog((new Date()).toString());
    		wlog("==============================================================================================================");
    		
    			
    		yearLoopCount = 2014;
    		for (weekLoopCount = 40; weekLoopCount <= 52; weekLoopCount++) {
    			this.sleep(randomWithRange(internalMin, internalMax));
        		this.driver = new FirefoxDriver();
        		this.sleep(randomWithRange(internalMin, internalMax));
    			this.driver.navigate().to(echosignSyncTransaction + "?year=" + yearLoopCount + "&week=" + weekLoopCount);
    			this.sleep(randomWithRange(internalMin, internalMax));
    			
    			waitLoopCount = 0;
        		isPresent = driver.findElements(By.name("result")).size() > 0;
    			while (!isPresent && waitLoopCount < 100) {
    				isPresent = driver.findElements(By.name("result")).size() > 0;
    				waitLoopCount ++;
    				this.sleep(1);
    			}
    			
    			if (isPresent) {
    				succLoopCount ++;
    				WebComponent = this.driver.findElement(By.name("result"));
    				responseText = WebComponent.getText().trim();
    			}
    			else {
    				WebComponent = this.driver.findElement(By.cssSelector("body"));
    				responseText = WebComponent.getText().trim();
    			}
    			
    			wlog((new Date()).toString().substring(4, 19) + "  " + responseText);
    			tfCount.setText("" + succLoopCount + " weeks processed");
    			this.sleep(randomWithRange(internalMin, internalMax));
    			this.driver.close(); 	
    			driver = null;
    		}
    		wlog("==============================================================================================================");
    		
    		yearLoopCount = 2015;
    		for (weekLoopCount = 0; weekLoopCount <= 48; weekLoopCount++) {
    			this.sleep(randomWithRange(internalMin, internalMax));
        		this.driver = new FirefoxDriver();
        		this.sleep(randomWithRange(internalMin, internalMax));
    			this.driver.navigate().to(echosignSyncTransaction + "?year=" + yearLoopCount + "&week=" + weekLoopCount);
    			this.sleep(randomWithRange(internalMin, internalMax));
    			
    			waitLoopCount = 0;
        		isPresent = driver.findElements(By.name("result")).size() > 0;
    			while (!isPresent && waitLoopCount < 100) {
    				isPresent = driver.findElements(By.name("result")).size() > 0;
    				waitLoopCount ++;
    				this.sleep(1);
    			}
    			
    			if (isPresent) {
    				succLoopCount ++;
    				WebComponent = this.driver.findElement(By.name("result"));
    				responseText = WebComponent.getText().trim();
    			}
    			else {
    				WebComponent = this.driver.findElement(By.cssSelector("body"));
    				responseText = WebComponent.getText().trim();
    			}
    			
    			wlog((new Date()).toString().substring(4, 19) + "  " + responseText);
    			tfCount.setText("" + succLoopCount + " weeks processed");
    			this.sleep(randomWithRange(internalMin, internalMax));
    			this.driver.close(); 	
    			driver = null;
    		}
    		wlog("==============================================================================================================");
    	} 
    	catch (Exception e1) {
    		wlog("Function echosignSyncTransaction() external error: " + Arrays.toString(e1.getStackTrace()));
    		this.sleep(randomWithRange(internalMin, internalMax));
    		if (driver != null) {
    			this.driver.close();
    			driver = null;
    		}
    	} 
    }
    */
    	
    
    /*		*/
    private void getEchosignInfoBasedOnEmail() {
    	WebElement WebComponent = null;	
    	String onlineOrderTable = "", orderNumber = "";
    	ArrayList<String> objArrayList = null;
    	int objArrayListSize = 0;
    	int orderLoopCount = 0, waitLoopCount = 0, succLoopCount = 0;
    	int countRecordAll = 0;
    	Boolean isPresent = false;
    	String responseText = "";

    	for (int i = 0; i < 2; i++) {
    		
	    	try {
	    		if (i == 0) {
	    			onlineOrderTable	= "web_cp_nbn_onlineOrderInfo";
	    		}
	    		else if (i == 1) {
	    			onlineOrderTable	= "web_cp_adsl_onlineOrderInfo";
	    		}
	    		wlog(onlineOrderTable + ": " + (new Date()).toString());
	    		wlog("==============================================================================================================");
	    		
	    		objArrayList 		= seekDatabase.getEchosignInfoBasedOnEmail(onlineOrderTable);
	    		objArrayListSize 	= objArrayList.size();
	    		countRecordAll		= countRecordAll + objArrayListSize;
	    		wlog("Table " + onlineOrderTable + " size: " + objArrayListSize);
	    		for (orderLoopCount = 0; orderLoopCount < objArrayListSize; orderLoopCount++) {
	    			orderNumber = objArrayList.get(orderLoopCount);
	    			
	    			try {
		    			this.sleep(randomWithRange(internalMin, internalMax));
		        		this.driver = new FirefoxDriver();
		        		this.sleep(randomWithRange(internalMin, internalMax));
		    			this.driver.navigate().to(getEchosignInfoBasedOnEmail + "?onlineOrderTable=" + onlineOrderTable + "&orderNumber=" + orderNumber);
		    			this.sleep(randomWithRange(internalMin, internalMax));
		    			
		    			waitLoopCount = 0;
		        		isPresent = driver.findElements(By.name("result")).size() > 0;
		    			while (!isPresent && waitLoopCount < 100) {
		    				isPresent = driver.findElements(By.name("result")).size() > 0;
		    				waitLoopCount ++;
		    				this.sleep(1);
		    			}
		    			
		    			if (isPresent) {
		    				succLoopCount ++;
		    				WebComponent = this.driver.findElement(By.name("result"));
		    				responseText = WebComponent.getText().trim();
		    			}
		    			else {
		    				WebComponent = this.driver.findElement(By.cssSelector("body"));
		    				responseText = WebComponent.getText().trim();
		    			}
		    			
		    			wlog((new Date()).toString().substring(4, 19) + "  " + responseText);
		    			tfCount.setText("" + succLoopCount + "/" + countRecordAll + " orders processed");
		    			this.sleep(randomWithRange(internalMin, internalMax));
		    			this.driver.close(); 	
		    			driver = null;
	    			}
	    			catch (Exception e) {
	    	    		wlog("Function getEchosignInfoBasedOnEmail() internal error: " + Arrays.toString(e.getStackTrace()));
	    	    		this.sleep(randomWithRange(internalMin, internalMax));
	    	    		orderLoopCount = orderLoopCount -1;
	    	    		if (driver != null) {
	    	    			this.driver.close();
	    	    			driver = null;
	    	    		}
	    	    	} 
	    		}
	    		
	    		wlog("==============================================================================================================");
	    	} 
	    	catch (Exception e1) {
	    		wlog("Function getEchosignInfoBasedOnEmail() external error: " + Arrays.toString(e1.getStackTrace()));
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		if (driver != null) {
	    			this.driver.close();
	    			driver = null;
	    		}
	    	} 
    	}
    }
    
    
    /*	*/
    private void finalDueDiligenceSyncEmersion() {
    	WebElement WebComponent = null;	
    	String onlineOrderTable = "", emersionId = "";
    	ArrayList<String> objArrayList = null;
    	int objArrayListSize = 0;
    	int orderLoopCount = 0, waitLoopCount = 0, succLoopCount = 0;
    	Boolean isPresent = false;
    	String responseText = "";

    	for (int i = 0; i < 1; i++) {
    		
	    	try {
	    		onlineOrderTable	= "web_due_diligence_info";
	    		wlog(onlineOrderTable + ": " + (new Date()).toString());
	    		wlog("==============================================================================================================");
	    		
	    		objArrayList 		= seekDatabase.finalDueDiligenceSyncEmersion();
	    		objArrayListSize 	= objArrayList.size();
	    		wlog("Table " + onlineOrderTable + " size: " + objArrayListSize);
	    		for (orderLoopCount = 0; orderLoopCount < objArrayListSize; orderLoopCount++) {
	    			emersionId = objArrayList.get(orderLoopCount);
	    			
	    			try {
		    			this.sleep(randomWithRange(internalMin, internalMax));
		        		this.driver = new FirefoxDriver();
		        		this.sleep(randomWithRange(internalMin, internalMax));
		    			this.driver.navigate().to(finalDueDiligenceSyncEmersion + "?emersionId=" + emersionId);
		    			this.sleep(randomWithRange(internalMin, internalMax));
		    			
		    			waitLoopCount = 0;
		        		isPresent = driver.findElements(By.name("result")).size() > 0;
		    			while (!isPresent && waitLoopCount < 100) {
		    				isPresent = driver.findElements(By.name("result")).size() > 0;
		    				waitLoopCount ++;
		    				this.sleep(1);
		    			}
		    			
		    			if (isPresent) {
		    				succLoopCount ++;
		    				WebComponent = this.driver.findElement(By.name("result"));
		    				responseText = WebComponent.getText().trim();
		    			}
		    			else {
		    				WebComponent = this.driver.findElement(By.cssSelector("body"));
		    				responseText = WebComponent.getText().trim();
		    			}
		    			
		    			wlog(emersionId + ": " + (new Date()).toString().substring(4, 19) + "  " + responseText);
		    			tfCount.setText("" + succLoopCount + "/" + objArrayListSize + " orders processed");
		    			this.sleep(randomWithRange(internalMin, internalMax));
		    			this.sleep(randomWithRange(internalMin, internalMax));
		    			this.driver.close(); 	
		    			driver = null;
	    			}
	    			catch (Exception e) {
	    	    		wlog("Function finalDueDiligenceSyncEmersion() internal error: " + Arrays.toString(e.getStackTrace()));
	    	    		this.sleep(randomWithRange(internalMin, internalMax));
	    	    		orderLoopCount = orderLoopCount -1;
	    	    		if (driver != null) {
	    	    			this.driver.close();
	    	    			driver = null;
	    	    		}
	    	    	} 
	    		}
	    		
	    		wlog("==============================================================================================================");
	    	} 
	    	catch (Exception e1) {
	    		wlog("Function finalDueDiligenceSyncEmersion() external error: " + Arrays.toString(e1.getStackTrace()));
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		if (driver != null) {
	    			this.driver.close();
	    			driver = null;
	    		}
	    	} 
    	}
    }
    
    
    /*	*/
    private void getEchosignContractBasedOnEmersion() {
    	WebElement WebComponent = null;	
    	String onlineOrderTable = "", emersionId = "";
    	ArrayList<String> objArrayList = null;
    	int objArrayListSize = 0;
    	int orderLoopCount = 0, waitLoopCount = 0, succLoopCount = 0;
    	Boolean isPresent = false;
    	String responseText = "";

    	try {
    		onlineOrderTable	= "web_due_diligence_info";
    		wlog(onlineOrderTable + ": " + (new Date()).toString());
    		wlog("==============================================================================================================");
    		
    		
    		this.sleep(randomWithRange(internalMin, internalMax));
    		this.driver = new FirefoxDriver();
    		this.sleep(randomWithRange(internalMin, internalMax));
			this.driver.navigate().to("http://api.netcube.com.au/projects/netcube/wamp/sync_latest_table_em_customerListing.php");
			this.sleep(randomWithRange(internalMin, internalMax));
			
			waitLoopCount = 0;
    		isPresent = driver.findElements(By.name("result")).size() > 0;
			while (!isPresent && waitLoopCount < 180) {
				isPresent = driver.findElements(By.name("result")).size() > 0;
				waitLoopCount ++;
				this.sleep(1);
			}
			
			if (isPresent) {
				WebComponent = this.driver.findElement(By.name("result"));
				responseText = WebComponent.getText().trim();
			}
			else {
				WebComponent = this.driver.findElement(By.cssSelector("body"));
				responseText = WebComponent.getText().trim();
			}
			if (driver != null) {
    			this.driver.close();
    			driver = null;
    		}
			wlog((new Date()).toString().substring(4, 19) + ":  " + responseText);
			
			
    		objArrayList 		= seekDatabase.getEchosignContractBasedOnEmersion();
    		objArrayListSize 	= objArrayList.size();
    		wlog("Table " + onlineOrderTable + " size: " + objArrayListSize);
    		for (orderLoopCount = 0; orderLoopCount < objArrayListSize; orderLoopCount++) {
    			emersionId = objArrayList.get(orderLoopCount);
    			
    			try {
	    			this.sleep(randomWithRange(internalMin, internalMax));
	        		this.driver = new FirefoxDriver();
	        		this.sleep(randomWithRange(internalMin, internalMax));
	    			this.driver.navigate().to(getEchosignContractBasedOnEmersion + "?emersionId=" + emersionId);
	    			this.sleep(randomWithRange(internalMin, internalMax));
	    			
	    			waitLoopCount = 0;
	        		isPresent = driver.findElements(By.name("result")).size() > 0;
	    			while (!isPresent && waitLoopCount < 100) {
	    				isPresent = driver.findElements(By.name("result")).size() > 0;
	    				waitLoopCount ++;
	    				this.sleep(1);
	    			}
	    			
	    			if (isPresent) {
	    				succLoopCount ++;
	    				WebComponent = this.driver.findElement(By.name("result"));
	    				responseText = WebComponent.getText().trim();
	    			}
	    			else {
	    				WebComponent = this.driver.findElement(By.cssSelector("body"));
	    				responseText = WebComponent.getText().trim();
	    			}
	    			
	    			wlog(emersionId + ": " + (new Date()).toString().substring(4, 19) + "  " + responseText);
	    			tfCount.setText("" + succLoopCount + "/" + objArrayListSize + " orders processed");
	    			this.sleep(randomWithRange(internalMin, internalMax));
	    			this.driver.close(); 	
	    			driver = null;
    			}
    			catch (Exception e) {
    	    		wlog("Function getEchosignContractBasedOnEmersion() internal error: " + Arrays.toString(e.getStackTrace()));
    	    		this.sleep(randomWithRange(internalMin, internalMax));
    	    		orderLoopCount = orderLoopCount -1;
    	    		if (driver != null) {
    	    			this.driver.close();
    	    			driver = null;
    	    		}
    	    	} 
    		}
    		
    		wlog("==============================================================================================================");
    	} 
    	catch (Exception e1) {
    		wlog("Function getEchosignContractBasedOnEmersion() external error: " + Arrays.toString(e1.getStackTrace()));
    		this.sleep(randomWithRange(internalMin, internalMax));
    		if (driver != null) {
    			this.driver.close();
    			driver = null;
    		}
    	} 
    }
    
    
    /*	*/
    private void downloadEchosignContractBasedOnEmersion() {
    	WebElement WebComponent = null;	
    	String onlineOrderTable = "", emersionId = "";
    	ArrayList<String> objArrayList = null;
    	int objArrayListSize = 0;
    	int orderLoopCount = 0, waitLoopCount = 0, succLoopCount = 0;
    	Boolean isPresent = false;
    	String responseText = "";

    	for (int i = 0; i < 1; i++) {
    		
	    	try {
	    		onlineOrderTable	= "web_due_diligence_info";
	    		wlog(onlineOrderTable + ": " + (new Date()).toString());
	    		wlog("==============================================================================================================");
	    		
	    		objArrayList 		= seekDatabase.downloadEchosignContractBasedOnEmersion();
	    		objArrayListSize 	= objArrayList.size();
	    		wlog("Table " + onlineOrderTable + " size: " + objArrayListSize);
	    		for (orderLoopCount = 0; orderLoopCount < objArrayListSize; orderLoopCount++) {
	    			emersionId = objArrayList.get(orderLoopCount);
	    			
	    			try {
		    			this.sleep(randomWithRange(internalMin, internalMax));
		        		this.driver = new FirefoxDriver();
		        		this.sleep(randomWithRange(internalMin, internalMax));
		    			this.driver.navigate().to(downloadEchosignContractBasedOnEmersion + "?emersionId=" + emersionId);
		    			this.sleep(randomWithRange(internalMin, internalMax));
		    			
		    			waitLoopCount = 0;
		        		isPresent = driver.findElements(By.name("result")).size() > 0;
		    			while (!isPresent && waitLoopCount < 100) {
		    				isPresent = driver.findElements(By.name("result")).size() > 0;
		    				waitLoopCount ++;
		    				this.sleep(1);
		    			}
		    			
		    			if (isPresent) {
		    				succLoopCount ++;
		    				WebComponent = this.driver.findElement(By.name("result"));
		    				responseText = WebComponent.getText().trim();
		    			}
		    			else {
		    				WebComponent = this.driver.findElement(By.cssSelector("body"));
		    				responseText = WebComponent.getText().trim();
		    			}
		    			
		    			wlog(emersionId + ": " + (new Date()).toString().substring(4, 19) + "  " + responseText);
		    			tfCount.setText("" + succLoopCount + "/" + objArrayListSize + " orders processed");
		    			this.sleep(randomWithRange(internalMin, internalMax));
		    			this.driver.close(); 	
		    			driver = null;
	    			}
	    			catch (Exception e) {
	    	    		wlog("Function downloadEchosignContractBasedOnEmersion() internal error: " + Arrays.toString(e.getStackTrace()));
	    	    		this.sleep(randomWithRange(internalMin, internalMax));
	    	    		orderLoopCount = orderLoopCount -1;
	    	    		if (driver != null) {
	    	    			this.driver.close();
	    	    			driver = null;
	    	    		}
	    	    	} 
	    		}
	    		
	    		wlog("==============================================================================================================");
	    	} 
	    	catch (Exception e1) {
	    		wlog("Function downloadEchosignContractBasedOnEmersion() external error: " + Arrays.toString(e1.getStackTrace()));
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		if (driver != null) {
	    			this.driver.close();
	    			driver = null;
	    		}
	    	} 
    	}
    }
    
    
	public static void main(String[] args) {
		 
		seekWebsite obj = new seekWebsite();
	    	
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			
	    	//obj.seekWebsiteTransaction();
	    	//obj.echosignSyncTransaction();
	    	//obj.getEchosignInfoBasedOnEmail();
	    	//obj.finalDueDiligenceSyncEmersion();
	    	
			while ( true ) {
				/*		*/
				obj.getEchosignContractBasedOnEmersion();
				obj.sleep(internalBetweenExtLoop);
				obj.downloadEchosignContractBasedOnEmersion();
				obj.sleep(internalBetweenExtLoop);
			}
		} 
	    catch (Exception e) {
			// TODO Auto-generated catch block
	    	obj.wlog("Function main() error: " + Arrays.toString(e.getStackTrace()));
		}    	
	}

}
