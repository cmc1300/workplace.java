package com.global.confRouter;

import java.awt.*;   
import java.awt.event.*; 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
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

public class confRouter extends JFrame implements ActionListener {

	public WebDriver driver;
	
	public int waitTime = 30;

	public String LoginUsername = "admin";
	public String LoginPassword = "admin";
	public String ipAddress 	= "192.168.1.1";
	
	public static int internalMin = 2;
	public static int internalMax = 2;
	public DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private JFrame frame;
	private JPanel rootPanel;
	private DefaultTableModel model;
	private JTextField adslTextField;
	private int iTableRowCount = 0;
	private Label lblCount;    // Declare component Label
	private int countSuccess = 0, countAll = 0;  
	private int tableSelectedRow = 0;  
	private JLabel lbCPInfoRead;
	private JLabel lbReachable;
	private TextField tfCount; // Declare component TextField
	private Button btnAddRouter;   // Declare component Button
	private Button btnConfigAddOn, btnConfigNoAddOn;   // Declare component Button
	private Button btnExit;   // Declare component Button
	private int outputLineMAXCount 	= 5000;    	
	private JTextArea logOutput 	= null;
	private	JTable table 			= null;
	private JScrollPane scroller1	= null;
	private JScrollPane scroller2	= null;
	private String 	colStatus = "", colEmersionId = "", colUserName = "", colServiceType = "", 
					colPromotion = "", colAddOn = "", colRouterType = "", 
					colADSLName = "", colADSLPassword = "", colVoipName = "", colVoipPassword = "", 
					colWifiSSID = "", colWifiKey = "", colProfile = "", colBillingServer = "";
	
	
	private String premium_TPLink = "TD-VG3631";
	private String standard_TPLink = "TD-W8960N";
	 
	   /** Constructor to setup GUI components and event handling */
	   public confRouter () {
		  frame = new JFrame("Router Automated Configuration");
		  frame.getContentPane().setLayout(new FlowLayout());
	         // "super" Frame sets its layout to FlowLayout, which arranges the components
	         //  from left-to-right, and flow to next row from top-to-bottom.
	      
	      rootPanel = (JPanel) frame.getContentPane();
	      rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
	      add(rootPanel);
	      
	      JPanel  panel1 = new JPanel(new FlowLayout()); 
	      rootPanel.add(panel1);  
	 
	      lblCount = new Label("Successfully configured ADSL routers");  // construct Label
	      panel1.add(lblCount);                    // "super" Frame adds Label
	      tfCount = new TextField("0/0", 10); // construct TextField
	      tfCount.setEditable(false);       // set to read-only
	      panel1.add(tfCount);                     // "super" Frame adds tfCount
	      
	     
	      JPanel panel2 = new JPanel();
	      panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	      rootPanel.add(panel2);
	      
	      String columnNames[] = { 	"Status", "Emersion ID", "Customer", "Service Type", 
	    		  					"Promotion", "Add-On", "Router Type",
	    		  					"Radius Account", "Radius Passwd", "VOIP Account", "VOIP Passwd", 
	    		  					"WIFI SSID", "WIFI Key", "Profile"};
	      // Create some data
	      // Create a new table instance
	      //table = new JTable( dataValues, columnNames );
	      
	      model = new DefaultTableModel();
	      model.setColumnIdentifiers(columnNames);
	      //model.insertRow(iTableRowCount, new Object[] { iTableRowCount++, "name", "age", "name", "age"});

	      table = new JTable(model);
	      /*
	      for (int count = 0; count < 10; count++){
	            model.insertRow(count, new Object[] { count+1, "name", "age", "name", "age"});
	      }*/
	      table.setRowHeight(25);
	      table.setPreferredScrollableViewportSize(table.getPreferredSize());
	      table.setFillsViewportHeight(true);
	      table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			// Add the table to a scrolling pane
	      scroller1 = new JScrollPane( table );
	      scroller1.setPreferredSize(new Dimension(1000, 285));
	      scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      panel2.add( scroller1);
	      
	      JPanel panelButton = new JPanel();
	      panelButton.setLayout(new FlowLayout());
	      panel2.add(panelButton);
	      btnAddRouter = new Button("Add More Router");   // construct Button
	      btnAddRouter.setFont(new Font("Sans Serif", Font.BOLD, 20));
	      panelButton.add(btnAddRouter);
	      panelButton.add(Box.createRigidArea(new Dimension(120,0)));
	      panel2.add(Box.createRigidArea(new Dimension(120,0)));
	      btnConfigAddOn = new Button("Start to Configure");   // construct Button
	      btnConfigAddOn.setFont(new Font("Sans Serif", Font.BOLD, 20));
	      //btnConfigAddOn.setEnabled(false);
	      panelButton.add(btnConfigAddOn);
	      panelButton.add(Box.createRigidArea(new Dimension(120,0)));
	      /*
	      btnConfigNoAddOn = new Button("Configure Router No Add-ON");   // construct Button
	      btnConfigNoAddOn.setFont(new Font("Sans Serif", Font.BOLD, 20));
	      panelButton.add(btnConfigNoAddOn);
	      */
	      btnExit = new Button("Exit Execution");   // construct Button
	      btnExit.setFont(new Font("Sans Serif", Font.BOLD, 20));
	      panelButton.add(btnExit);                    // "super" Frame adds Button

	 
	      /*	*/
	      logOutput = new JTextArea(20, 70);
	      logOutput.setEditable(false);
	      scroller2 = new JScrollPane(logOutput);
          scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
          scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      DefaultCaret caret = (DefaultCaret)logOutput.getCaret();
	      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	      panel2.add(scroller2); 

	      
	      btnExit.addActionListener(this);
	      
	      btnAddRouter.addActionListener(new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	        	// display/center the jdialog when the button is pressed
	        	final JDialog d = new JDialog(frame, "Add new Emersion ID", true);
	        	d.setLocationRelativeTo(rootPanel);
	        	/*		*/
	        	Dimension parentSize = rootPanel.getSize(); 
	        	Point p = frame.getLocation(); 
	            //d.setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
	            
	            JPanel messagePane = new JPanel();
	            adslTextField = new JTextField("", 16);
	            adslTextField.setCaretPosition(0);
	            messagePane.add(new JLabel("Emersion ID: e.g. 1448625"));
	            messagePane.add(adslTextField);
	            lbCPInfoRead = new JLabel("");
	            messagePane.add(lbCPInfoRead);
		  	    d.add(messagePane,BorderLayout.CENTER);  
		  	    
	            JPanel buttonPane = new JPanel();
	            JButton button = new JButton("Add"); 
	            buttonPane.add(button); 
	            button.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent e)
	            	{
	            		ArrayList<String> ret = DBConfRouter.readADSLUserInfoFromDatabse(adslTextField.getText());
	            		String RetState = ret.get(0).toString();
	            		String RetVOIP = "";
	            		
	            		wlog(ret.toString());
	            		if (ret.size() >= 10) {
	            			RetVOIP = ret.get(8).toString();
	            		}
	            		
	            		if (RetState.equalsIgnoreCase("Success") && !RetVOIP.equalsIgnoreCase("None")) {
	            			countAll ++;
	            			if (countAll == 10) {
	            				btnAddRouter.setEnabled(false);
	            			}
	            			tfCount.setText(countSuccess + "/" + countAll);
	            			wlog("Emersion " + adslTextField.getText() + " has been added successfully");
	            			colRouterType = calcRouterType(	ret.get(12).toString().toLowerCase(), ret.get(14).toString().toLowerCase(), 
	            											ret.get(15).toString().toLowerCase());
	            			model.insertRow(iTableRowCount++, 
	            					new Object[] { ret.get(1).toString(), ret.get(11).toString(), ret.get(2).toString(), ret.get(12).toString(), 
	            					ret.get(14).toString(), ret.get(15).toString(), colRouterType,
	            					ret.get(3).toString(), ret.get(4).toString(), ret.get(8).toString(), ret.get(9).toString(), 
	            					ret.get(5).toString(), ret.get(6).toString(), ret.get(7).toString()});
	            			d.dispose();
	            		}
	            		else if (RetState.equalsIgnoreCase("Success") && RetVOIP.equalsIgnoreCase("None")) {
	            			lbCPInfoRead.setText("Emersion " + adslTextField.getText() + " has no VOIP account!");
	            		}
	            		else if (RetState.equalsIgnoreCase("nc_customerInfo 0")) {
	            			lbCPInfoRead.setText("Emersion " + adslTextField.getText() + " does NOT exist!");
	            		}
	            		else if (RetState.equalsIgnoreCase("web_onlineOrderInfo 0")) {
	            			lbCPInfoRead.setText("Emersion " + adslTextField.getText() + " is NOT generated by online order!");
	            		}
	            		else if (RetState.equalsIgnoreCase("exception")) {
	            			lbCPInfoRead.setText("Exception: " + ret.get(1).toString());
	            		}
	  	    		}
	  	    	});
	            d.add(buttonPane, BorderLayout.SOUTH);
	            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	            //pack();
	            d.setSize(new Dimension(320, 150));
	        	d.setVisible(true);
	        }
	      });

	      btnConfigAddOn.addActionListener(new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	        	// display/center the jdialog when the button is pressed
	        	final JDialog d = new JDialog(frame, "Configure router", true);
	        	d.setLocationRelativeTo(rootPanel);
	        	/*		*/
	        	Dimension parentSize = rootPanel.getSize(); 
	        	Point p = frame.getLocation(); 
	            //d.setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
	        	
	        	int[] selection = table.getSelectedRows();
	        	JPanel messagePane = new JPanel();
	        	lbReachable = new JLabel("Be sure to reach 192.168.1.1 via ping!!!");
	        	boolean bReadyToGo = true;
	        	if (selection.length == 0) {
		            messagePane.add(new JLabel("For now no user has been selected!"));
		            bReadyToGo = false;
	        	}
	        	else {
	        		tableSelectedRow	= selection[0];
	        		colStatus 			= (String) model.getValueAt(tableSelectedRow, 0);
	        		colEmersionId  		= (String) model.getValueAt(tableSelectedRow, 1);
	        		colUserName  		= (String) model.getValueAt(tableSelectedRow, 2);
	        		colServiceType  	= (String) model.getValueAt(tableSelectedRow, 3);
	        		colPromotion  		= (String) model.getValueAt(tableSelectedRow, 4);
	        		colAddOn  			= (String) model.getValueAt(tableSelectedRow, 5);
	        		colRouterType 		= (String) model.getValueAt(tableSelectedRow, 6);
	        		colADSLName  		= (String) model.getValueAt(tableSelectedRow, 7);
	        		colADSLPassword  	= (String) model.getValueAt(tableSelectedRow, 8);
	        		colVoipName 		= (String) model.getValueAt(tableSelectedRow, 9);
	        		colVoipPassword 	= (String) model.getValueAt(tableSelectedRow, 10);
	        		colWifiSSID  		= (String) model.getValueAt(tableSelectedRow, 11);
	        		colWifiKey 		 	= (String) model.getValueAt(tableSelectedRow, 12);
	        		colProfile 			= (String) model.getValueAt(tableSelectedRow, 13);
	        		colBillingServer	= "billing3.novatel.com.au";
	        		if (colStatus.equalsIgnoreCase("Waiting")) {
			            messagePane.add(new JLabel("It's time to work for " + colEmersionId));
			            messagePane.add(lbReachable);
	        		}
	        		else {
	        			messagePane.add(new JLabel(colEmersionId + " has already been processed!"));
	        			bReadyToGo = false;
	        		}
	        	}
	        	d.add(messagePane,BorderLayout.CENTER);  
	        			  	    
		  	    JPanel buttonPane = new JPanel();
		  	    if (bReadyToGo) {
		            JButton buttonGo = new JButton("Go now"); 
		            buttonPane.add(buttonGo); 
		            buttonGo.addActionListener(new ActionListener() {
		            	public void actionPerformed(ActionEvent e)
		            	{
		            		InetAddress inet;
		            	    try {
								inet = InetAddress.getByName(ipAddress);
								if (!inet.isReachable(5000)) {
									lbReachable.setText("IP " + ipAddress + " is unreachable!");
			            	    }
								else {
									String retState = "";
									if (colServiceType.indexOf( "adsl" ) >= 0) {
										if (colRouterType.equalsIgnoreCase(standard_TPLink)) {
											retState = TD_W8960N_Transaction("adsl", colUserName, 
													colADSLName, colADSLPassword, 
													colWifiSSID, colWifiKey, colProfile);
										}
										else if (colRouterType.equalsIgnoreCase(premium_TPLink)) {
											if (colAddOn.indexOf( "domestic calls" ) >= 0 || colAddOn.indexOf( "international calls" ) >= 0) {
												retState = TD_VG3631_Transaction("adsl", "VoIP", colUserName, 
														colADSLName, colADSLPassword, 
														colWifiSSID, colWifiKey, colProfile, 
														colVoipName, colVoipPassword, colBillingServer);
											}
											else {
												retState = TD_VG3631_Transaction("adsl", "PSTN Preferred", colUserName, 
														colADSLName, colADSLPassword, 
														colWifiSSID, colWifiKey, colProfile, 
														colVoipName, colVoipPassword, colBillingServer);
											}
										}
									}
									else if (colServiceType.indexOf( "nbn" ) >= 0) {
										if (colRouterType.equalsIgnoreCase(standard_TPLink)) {
											retState = TD_W8960N_Transaction("nbn", colUserName, 
													colADSLName, colADSLPassword, 
													colWifiSSID, colWifiKey, colProfile);
										}
										else if (colRouterType.equalsIgnoreCase(premium_TPLink)) {
											if (colAddOn.indexOf( "domestic calls" ) >= 0 || colAddOn.indexOf( "international calls" ) >= 0) {
												retState = TD_VG3631_Transaction("nbn", "VoIP", colUserName, 
														colADSLName, colADSLPassword, 
														colWifiSSID, colWifiKey, colProfile, 
														colVoipName, colVoipPassword, colBillingServer);
											}
											else {
												retState = TD_VG3631_Transaction("nbn", "PSTN Preferred", colUserName, 
														colADSLName, colADSLPassword, 
														colWifiSSID, colWifiKey, colProfile, 
														colVoipName, colVoipPassword, colBillingServer);
											}
										}
									}
									
									if (retState.equalsIgnoreCase("Processed")) {
										countSuccess ++;
										model.setValueAt("Processed", tableSelectedRow, 0);
								        tfCount.setText(countSuccess + "/" + countAll);
										d.dispose();
										wlog("Emersion " + colEmersionId + " has been processed successfully");
									}
									else {
										wlog(retState);
										lbReachable.setText("Emersion " + colEmersionId + " failed to be processed!");
									}
								}
							} 
		            	    catch (Exception e1) {
		            	    	
							}
		  	    		}
		  	    	});
		  	    }
		  	    
		  	    JButton buttonExit = new JButton("Exit"); 
	            buttonPane.add(buttonExit); 
	            buttonExit.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent e)
	            	{
	            		
	            		d.dispose();
	  	    		}
	  	    	});
	            d.add(buttonPane, BorderLayout.SOUTH);
	            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	            //pack();
	            d.setSize(new Dimension(330, 150));
	        	d.setVisible(true);
	        }
	      });
	 
	      setTitle("Router Automated Configuration");  // "super" Frame sets title
	      //pack(); 
	      setSize(1500, 750);        // "super" Frame sets initial window size
	      setBackground( Color.GRAY );
	      
	      GraphicsConfiguration gc = getGraphicsConfiguration();
	      Rectangle bounds = gc.getBounds();
	      /*
	      setLocation((int) ((bounds.width / 2) - (size.getWidth() / 2)),
                  (int) ((bounds.height / 2) - (size.getHeight() / 2))); */
	      setLocation((int) ((bounds.width) - 1500 - 10),0);
	 
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
				return d.findElement(by).isDisplayed();
			}
		});
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
	
	
	public void sleep(int sec){
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
        
    private int randomWithRange(int min, int max) {
       int range = (max - min) + 1;     
       return (int)(Math.random() * range) + min;
    }
    
    private String TD_W8960N_Transaction(String colServiceType, String colUserName, 
    		String colADSLName, String colADSLPassword, 
    		String colWifiSSID, String colWifiKey, String colProfile) {
    	WebElement WebComponent = null;	
    	String retState = "";    	
    	
    	try {
    		
    		this.driver = new FirefoxDriver();
    		//this.sleep(randomWithRange(internalMin, internalMax));
        	this.driver.navigate().to("http://192.168.1.1");
    		this.sleep(2 * randomWithRange(internalMin, internalMax));
    		
    		driver.manage().window().maximize();
    		this.waitForLocator(By.id("userName")).sendKeys(LoginUsername);
    		this.waitForLocator(By.id("pcPassword")).sendKeys(LoginPassword);
    		this.waitForLocator(By.cssSelector("body > div.loginBox > div.panelThre > div > button")).click();	
    		this.sleep(randomWithRange(internalMin, internalMax));
    		
    		{
    			/*	Quick Setup	*/
    			driver.switchTo().frame("menufrm");
		      	this.waitForLocator(By.id("12")).click();
		    	this.sleep(randomWithRange(internalMin, internalMax));
		    	driver.switchTo().defaultContent();
		    	driver.switchTo().frame("basefrm");
		    	this.waitForLocator(By.cssSelector("body > center:nth-child(1) > form:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > center:nth-child(1) > input:nth-child(1)")).click();
		    	this.sleep(randomWithRange(internalMin, internalMax));
		    	
    			if (colServiceType == "adsl") {
	    			/*	ADSL WAN	*/
		    		this.waitForLocator(By.id("dslWan")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.cssSelector("body > center > table:nth-child(4) > tbody > tr > td > center > input:nth-child(2)")).click();
			    	this.sleep(randomWithRange(internalMin, internalMax));
		    		selectOption("country", "Australia");
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		selectOption("isp", "AAPT(Preferred PPPoE)");
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		
		    		this.waitForLocator(By.id("pppUserName")).clear();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pppUserName")).sendKeys(colADSLName);
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pppPassword")).clear();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pppPassword")).sendKeys(colADSLPassword);
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.cssSelector("body > center > form > table:nth-child(11) > tbody > tr:nth-child(2) > td > center > input:nth-child(3)")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
    			}
    			else if (colServiceType == "nbn") {
    				/*	Ethernet WAN	*/
    				this.waitForLocator(By.id("ethWan")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.cssSelector("body > center > table:nth-child(4) > tbody > tr > td > center > input:nth-child(2)")).click();
			    	this.sleep(randomWithRange(internalMin, internalMax));
			    	/*
		    		selectOption("country", "Australia");
		    		this.sleep(randomWithRange(internalMin, internalMax));*/
		    		selectOption("ntwkPrtcl", "PPPoE(PPP over Ethernet)");
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		
		    		this.waitForLocator(By.id("pppUserName")).clear();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pppUserName")).sendKeys(colADSLName);
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pppPassword")).clear();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pppPassword")).sendKeys(colADSLPassword);
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.cssSelector("body > center > form > table:nth-child(11) > tbody > tr:nth-child(2) > td > center > input:nth-child(3)")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
    			}
    			
	    		this.waitForLocator(By.cssSelector("#wlan_SSID > table > tbody > tr:nth-child(5) > td:nth-child(2) > input[type='text']")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("#wlan_SSID > table > tbody > tr:nth-child(5) > td:nth-child(2) > input[type='text']")).sendKeys(colWifiSSID);
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("#wlan_PSK_Key > table > tbody > tr:nth-child(1) > td:nth-child(2) > input[type='password']")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("#wlan_PSK_Key > table > tbody > tr:nth-child(1) > td:nth-child(2) > input[type='password']")).sendKeys(colWifiKey);
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("body > center > form > table:nth-child(8) > tbody > tr:nth-child(2) > td > center > input:nth-child(3)")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		
	    		this.waitForLocator(By.cssSelector("body > center > form > p > input:nth-child(5)")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.sleep(randomWithRange(internalMin, internalMax));
    		}
    		
    		
    		if (colServiceType == "nbn") {
				/*	Wireless ==> Basic	*/
    			driver.switchTo().defaultContent();
		    	driver.switchTo().frame("menufrm");
		    	this.waitForLocator(By.id("53")).click();
		    	this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("54")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		
	    		driver.switchTo().defaultContent();
		    	driver.switchTo().frame("basefrm");
	    		Select select = new Select(this.waitForLocator(By.cssSelector("#wlSecInfo > table:nth-child(2) > tbody > tr:nth-child(7) > td:nth-child(2) > select")));
	    	    select.selectByVisibleText("AUSTRALIA");    
	    	    this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("#divApply > table > tbody > tr:nth-child(2) > td > center > input[type='button']")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.driver.switchTo().alert().accept();
	    		this.sleep(randomWithRange(internalMin, internalMax));
			}
    		

	    	{
	    		/*	Advanced Setup ==> DNS	*/
	    		driver.switchTo().defaultContent();
		    	driver.switchTo().frame("menufrm");
		    	this.waitForLocator(By.id("13")).click();
		    	this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("42")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		
	    		driver.switchTo().defaultContent();
		    	driver.switchTo().frame("basefrm");
		    	this.waitForLocator(By.cssSelector("body > center > form > table:nth-child(2) > tbody > tr:nth-child(3) > td > table > tbody > tr:nth-child(1) > td > input[type='radio']")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("body > center > form > table:nth-child(2) > tbody > tr:nth-child(3) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type='text']")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("body > center > form > table:nth-child(2) > tbody > tr:nth-child(3) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type='text']")).sendKeys("103.26.62.218");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("body > center > form > table:nth-child(2) > tbody > tr:nth-child(3) > td > table > tbody > tr:nth-child(3) > td:nth-child(2) > input[type='text']")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("body > center > form > table:nth-child(2) > tbody > tr:nth-child(3) > td > table > tbody > tr:nth-child(3) > td:nth-child(2) > input[type='text']")).sendKeys("8.8.8.8");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("body > center > form > table:nth-child(4) > tbody > tr:nth-child(2) > td > center > input[type='button']")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.sleep(randomWithRange(internalMin, internalMax));
    		}
    		
    		    		
    		{
    			/*	Management ==> Access Control ==> Remote Access	*/
    			driver.switchTo().defaultContent();
		    	driver.switchTo().frame("menufrm");
		    	this.waitForLocator(By.id("65")).click();
		    	this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("74")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("76")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));

	    		driver.switchTo().defaultContent();
		    	driver.switchTo().frame("basefrm");
		    	this.waitForLocator(By.cssSelector("body > center > form > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type='checkbox']")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("body > center > form > table > tbody > tr:nth-child(7) > td > center > input[type='button']")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.sleep(randomWithRange(internalMin, internalMax));
    		}
    		
    		driver.switchTo().defaultContent();
	    	driver.switchTo().frame("menufrm");
	    	this.waitForLocator(By.id("79")).click();
    		this.sleep(randomWithRange(internalMin, internalMax));
    		this.driver.switchTo().alert().accept();
    		this.sleep(randomWithRange(internalMin, internalMax));
    		retState = "Processed";
    	} 
    	catch (Exception e) {
    		StringWriter sw = new StringWriter();
    		e.printStackTrace(new PrintWriter(sw));
    		String exceptionAsString = sw.toString();
    		retState = "Function TD_W8960N_Transaction() error: " + exceptionAsString;
    	} 
    	finally {
    		this.sleep(randomWithRange(internalMin, internalMax));
    		this.driver.close();
    	}
    	return retState;
    }
        
    
    private String TD_VG3631_Transaction(String colServiceType, String colAddOn, String colUserName, 
    		String colADSLName, String colADSLPassword, String colWifiSSID, String colWifiKey, String colProfile, 
    		String colVoipName, String colVoipPassword, String colBillingServer) {
    	WebElement WebComponent = null;	
    	String retState = "";    	
    	
    	try {
    		
    		this.driver = new FirefoxDriver();
    		//this.sleep(randomWithRange(internalMin, internalMax));
        	this.driver.navigate().to("http://192.168.1.1");
    		this.sleep(2 * randomWithRange(internalMin, internalMax));
    		
    		driver.manage().window().maximize();
    		this.waitForLocator(By.id("userName")).sendKeys(LoginUsername);
    		this.waitForLocator(By.id("pcPassword")).sendKeys(LoginPassword);
    		this.waitForLocator(By.id("loginBtn")).click();	
    		this.sleep(randomWithRange(internalMin, internalMax));
    		
    		
    		{	
 		      		this.waitForLocator(By.id("menu_net")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("menu_wan")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		
		    		if (colServiceType == "adsl") {
	    			/*	Network ==> WAN Settings	*/
		    		this.waitForLocator(By.cssSelector("span[class='a T_edit']")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		
		    		this.waitForLocator(By.id("username")).clear();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("username")).sendKeys(colADSLName);
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pwd")).clear();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pwd")).sendKeys(colADSLPassword);
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pwd2")).clear();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pwd2")).sendKeys(colADSLPassword);
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		selectOption("defaut_gateway", "pppoe_8_35_0");
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.cssSelector("input[class='button L T T_save']")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
    			}
    			else if (colServiceType == "nbn") {
    				/*	Network ==> WAN Deletion	*/
		    		this.waitForLocator(By.cssSelector("span[class='a T_del']")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.driver.switchTo().alert().accept();
		    		this.sleep(randomWithRange(internalMin, internalMax));
    				
    				/*	Network ==> EWAN Settings	*/
		    		this.waitForLocator(By.id("menu_ethwan")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("ethWan_en")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		selectOption("link_type", "PPPoE");
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		/*
		    		this.waitForLocator(By.id("username")).clear();
		    		this.sleep(randomWithRange(internalMin, internalMax));*/
		    		this.waitForLocator(By.id("username")).sendKeys(colADSLName);
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pwd")).sendKeys(colADSLPassword);
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.id("pwd2")).sendKeys(colADSLPassword);
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		this.waitForLocator(By.cssSelector("input[class='button L T T_save']")).click();
		    		this.sleep(randomWithRange(internalMin, internalMax));
    			}
	    		
	    		/*	Network ==> LAN Settings	*/
	    		this.waitForLocator(By.id("menu_lan")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("dnsserver1")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("dnsserver1")).sendKeys("103.26.62.218");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("dnsserver2")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("dnsserver2")).sendKeys("8.8.8.8");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("input[class='button L T T_save']")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
    		}
    		
    		
    		{
    			/*	Voice ==> SIP Account	*/
    			this.waitForLocator(By.id("menu_voip")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("menu_sipAc")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("add")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		
	    		this.waitForLocator(By.id("ProfileName")).sendKeys(colProfile);
	    		this.waitForLocator(By.id("Extension")).sendKeys(colVoipName);
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("AuName")).sendKeys(colVoipName);
	    		this.waitForLocator(By.id("Password")).sendKeys(colVoipPassword);
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("RegAddr")).clear();
	    		this.waitForLocator(By.id("RegAddr")).sendKeys(colBillingServer);
	    		WebComponent = this.waitForLocator(By.id("RegViaOB"));
	    		if(WebComponent.isSelected()){
	    			WebComponent.click();
	    	    }
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("saveBtn")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		
	    		/*	Voice ==> Digit Map	*/
    			this.waitForLocator(By.id("menu_digitMap")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("curmap")).clear();
	    		this.waitForLocator(By.id("saveBtn")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		
	    		/*	Voice ==> Dial Plan	*/
    			this.waitForLocator(By.id("menu_dialPlan")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("add")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		
	    		this.waitForLocator(By.id("Prefix")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("Prefix")).sendKeys("80011");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		selectOption("dest_sip", colProfile);
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		WebComponent = this.waitForLocator(By.id("dest_pstn"));
	    		if(WebComponent.isSelected()){
	    			WebComponent.click();
	    	    }
	    		this.waitForLocator(By.id("MaxLen")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("MaxLen")).sendKeys("20");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		selectOption("Operating_rule", "Replace Prefix");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("OP_number")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("OP_number")).sendKeys("0011");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		WebComponent = this.waitForLocator(By.id("LineEnable1"));
	    		if(WebComponent.isSelected()){
	    			WebComponent.click();
	    	    }
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		WebComponent = this.waitForLocator(By.id("LineEnable2"));
	    		if(!WebComponent.isSelected()){
	    			WebComponent.click();
	    	    }
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		WebComponent = this.waitForLocator(By.id("LineEnable3"));
	    		if(WebComponent.isSelected()){
	    			WebComponent.click();
	    	    }
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("saveBtn")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		
	    		/*	Voice ==> Emergency Call	*/
    			this.waitForLocator(By.id("menu_emgcCall")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		WebComponent = this.waitForLocator(By.id("enable0"));
	    		if(!WebComponent.isSelected()){
	    			WebComponent.click();
	    	    }
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("emgcNum0")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("emgcNum0")).sendKeys("000");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("save")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
    		}
    		
    		
    		{
    			/*	Voice ==> Phone Setup	*/
    			this.waitForLocator(By.id("menu_sipLS")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("phone2")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		if (colAddOn.equalsIgnoreCase("PSTN Preferred")) {
	    			selectOption("DPPNAME1", "PSTN Preferred");
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		selectOption("dppSelect1", "Auto");
		    		this.sleep(randomWithRange(internalMin, internalMax));
		    		
	    		}
	    		else if (colAddOn.equalsIgnoreCase("VoIP")) {
	    			selectOption("DPPNAME1", "VoIP");
		    		this.sleep(randomWithRange(internalMin, internalMax));
	    			selectOption("dppSelect1", colProfile);
		    		this.sleep(randomWithRange(internalMin, internalMax));
	    		}
	    		WebComponent = this.waitForLocator(By.id("pstnFallback1"));
	    		if(WebComponent.isSelected()){
	    			WebComponent.click();
	    	    }
		    	this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("Save")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
    		}
    		
    		
    		{
    			/*	Wireless ==> Basic Settings	*/
    			this.waitForLocator(By.id("menu_wireless")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.partialLinkText("Basic Settings")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));

	    		this.waitForLocator(By.id("ssid")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("ssid")).sendKeys(colWifiSSID);
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		selectOption("region", "Australia");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		WebComponent = this.waitForLocator(By.id("ap"));
	    		if(!WebComponent.isSelected()){
	    			WebComponent.click();
	    	    }
		    	this.sleep(randomWithRange(internalMin, internalMax));
		    	WebComponent = this.waitForLocator(By.id("ssidBroadcast"));
	    		if(!WebComponent.isSelected()){
	    			WebComponent.click();
	    	    }
		    	this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("input[class='button L T T_save']")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		
	    		/*	Wireless ==> Wireless Security	*/
	    		this.waitForLocator(By.partialLinkText("Wireless Security")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));

	    		selectOption("wpaAuthType", "Auto");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		selectOption("wpaCipher", "Auto");
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("pskSecret")).clear();
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.id("pskSecret")).sendKeys(colWifiKey);
	    		this.sleep(randomWithRange(internalMin, internalMax));
	    		this.waitForLocator(By.cssSelector("input[class='button L T T_save']")).click();
	    		this.sleep(randomWithRange(internalMin, internalMax));
    		}
    		
    		
    		this.sleep(randomWithRange(internalMin, internalMax));
    		this.waitForLocator(By.linkText("Logout"));
    		this.sleep(randomWithRange(internalMin, internalMax));
    		retState = "Processed";
    	} 
    	catch (Exception e) {
    		StringWriter sw = new StringWriter();
    		e.printStackTrace(new PrintWriter(sw));
    		String exceptionAsString = sw.toString();
    		retState = "Function TD_VG3631_Transaction() error: " + exceptionAsString;
    	} 
    	finally {
    		this.sleep(randomWithRange(internalMin, internalMax));
    		this.driver.close();
    	}
    	return retState;
    }
    
	private String calcRouterType(String serviceType, String promotion,  String addon){

		String routerType = "";
		if (addon.indexOf( "modem upgrade tplink" ) >= 0) {
			routerType = premium_TPLink;
		}
		else if (serviceType.indexOf( "lite" ) >= 0 && promotion.equalsIgnoreCase("monkey")) {
			if (addon.indexOf( "domestic calls2" ) >= 0 || addon.indexOf( "international calls2" ) >= 0) {
				routerType = premium_TPLink;
			}
			else {
				routerType = standard_TPLink;
			}
		}
		else if (serviceType.indexOf( "lite" ) >= 0 && !promotion.equalsIgnoreCase("monkey")) {
			routerType = standard_TPLink;
		}
		else if (serviceType.indexOf( "adsl" ) >= 0) {
			if (addon.indexOf( "domestic calls" ) >= 0 || addon.indexOf( "international calls" ) >= 0) {
				routerType = premium_TPLink;
			}
			else {
				routerType = standard_TPLink;
			}
		}
		else if (serviceType.indexOf( "nbn" ) >= 0) {
			if (addon.indexOf( "domestic calls" ) >= 0 || addon.indexOf( "international calls" ) >= 0) {
				routerType = premium_TPLink;
			}
			else {
				routerType = standard_TPLink;
			}
		}
		else if (serviceType.indexOf( "fibrex" ) >= 0) {
			if (addon.indexOf( "domestic calls" ) >= 0 || addon.indexOf( "international calls" ) >= 0) {
				routerType = premium_TPLink;
			}
			else {
				routerType = standard_TPLink;
			}
		}
		else {
			routerType = "wrong";
		}
        return routerType;
    }

    private void wlog(String log){
    	
        if (logOutput.getLineCount() > outputLineMAXCount) {
        	logOutput.setText(log);
        }
        else {
        	logOutput.append(log + "\n");
        	JScrollBar vertical = scroller2.getVerticalScrollBar();
        	vertical.setValue( vertical.getMaximum());
        }
    }
    
    public static void main(String[] args) {
		 
    	confRouter obj = new confRouter();	
	}

}
