package com.global.javaSimulatorSet;

import java.awt.*;   
import java.awt.event.*; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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





import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class javaSimulatorSet extends JFrame implements ActionListener {

	public WebDriver driver = null;
	
	private int waitTime = 180;  
	private static int internalBetweenLoop = 1;	
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Calendar cal = null;
	private int outputLineMAXCount 	= 5000; 
	
	private JFrame frame;
	private JPanel rootPanel;
	private JCheckBox check1 = null, check2 = null, check3 = null, check4 = null, check5 = null;
	private int intCount1 = 0, intCount2 = 0, intCount3 = 0, intCount4 = 0, intCount5 = 0;
	private Label lblCount1 = null, lblCount2 = null, lblCount3 = null, lblCount4 = null, lblCount5 = null;    
	private TextField tfCount1 = null, tfCount2 = null, tfCount3 = null, tfCount4 = null, tfCount5 = null; 
	private Button btnApplyConfiguration, btnExit;   
	private JTextArea logOutput1, logOutput2, logOutput3, logOutput4, logOutput5, logOutput;
	private JScrollPane scrollerOutput1, scrollerOutput2, scrollerOutput3, scrollerOutput4, scrollerOutput5, scrollerOutput;
	private DefaultCaret caret1, caret2, caret3, caret4, caret5, caret;
	
	
	private static int internalMin_websiteICTManagerSync 	= 4;
	private static int internalMax_websiteICTManagerSync	= 5;
	private Date date_websiteICTManagerSync 				= null;
	private int interval_websiteICTManagerSync 				= 0;
	private int count_websiteICTManagerSync 				= 1;
	private boolean enabled_websiteICTManagerSync			= false;
	private String url_websiteICTManagerSync 				= "http://192.168.1.64/login.asp?url=index.asp";
	private String username_websiteICTManagerSync			= "manager";
	private String password_websiteICTManagerSync			= "t5Pp9EJ%";

	
	private static int internalMin_taskDueDiligenceSync 	= 4;
	private static int internalMax_taskDueDiligenceSync		= 5;
	private Date date_taskDueDiligenceSync 					= null;
	private int interval_taskDueDiligenceSync 				= 0;
	private int count_taskDueDiligenceSync	 				= 1;
	private boolean enabled_taskDueDiligenceSync			= false;
	private String url_sync_taskDueDiligenceSync 			= "http://api.netcube.com.au/projects/netcube/wamp/sync_latest_table_em_customerListing.php";
	private String url_contract_taskDueDiligenceSync 		= "http://api.netcube.com.au/projects/netcube/echosign/getEchosignContractBasedOnEmersion.php";
	private String url_download_taskDueDiligenceSync 		= "http://api.netcube.com.au/projects/netcube/echosign/downloadEchosignContractBasedOnEmersion.php";
	private ArrayList<String> array_contract_taskDueDiligenceSync	= null;
	private int size_array_contract_taskDueDiligenceSync 	= 0;
	private int count_array_contract_taskDueDiligenceSync 	= 0;
	private ArrayList<String> array_download_taskDueDiligenceSync	= null;
	private int size_array_download_taskDueDiligenceSync 	= 0;
	private int count_array_download_taskDueDiligenceSync 	= 0;
	private String onlineOrderTable_taskDueDiligenceSync 	= "web_due_diligence_info";
	
	private static int internalMin_taskCheckForMaxCalls 	= 4;
	private static int internalMax_taskCheckForMaxCalls		= 5;
	private Date date_taskCheckForMaxCalls 					= null;
	private int interval_taskCheckForMaxCalls 				= 0;
	private int count_taskCheckForMaxCalls	 				= 1;
	private boolean enabled_taskCheckForMaxCalls			= false;
	private String url_taskCheckForMaxCalls	 				= "http://api.netcube.com.au/projects/netcubehub/billingServerMonitor";
	
	private static int internalMin_websiteSyncEmersionIdList= 2;
	private static int internalMax_websiteSyncEmersionIdList= 3;
	private int numEmersionPerPage 							= 200;
	private Date date_websiteSyncEmersionIdList 			= null;
	private int interval_websiteSyncEmersionIdList 			= 0;
	private boolean enabled_websiteSyncEmersionIdList 		= false;
	public String LoginUsernameSyncEmersionIdList 			= "jerry.xu@blisstel.com.au";
	public String LoginPasswordSyncEmersionIdList 			= "Welcome999";
	 
	   /** Constructor to setup GUI components and event handling */
	   public javaSimulatorSet () {
		  frame = new JFrame("NetCube-Hub Backend System");
		  frame.getContentPane().setLayout(new FlowLayout());
	         // "super" Frame sets its layout to FlowLayout, which arranges the components
	         //  from left-to-right, and flow to next row from top-to-bottom.
	      
	      rootPanel = (JPanel) frame.getContentPane();
	      rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
	      add(rootPanel);
	      
	      JPanel panelForAllTables = new JPanel();
	      panelForAllTables.setLayout(new BoxLayout(panelForAllTables, BoxLayout.X_AXIS));
	      rootPanel.add(panelForAllTables);
	      
	      JPanel panelOnLeftSide = new JPanel();
	      panelOnLeftSide.setLayout(new BoxLayout(panelOnLeftSide, BoxLayout.Y_AXIS));
	      panelForAllTables.add(panelOnLeftSide);
	      JPanel panelOnRightSide = new JPanel();
	      panelOnRightSide.setLayout(new BoxLayout(panelOnRightSide, BoxLayout.Y_AXIS));
	      panelForAllTables.add(panelOnRightSide);
	      
	      
	      {
	    	  JPanel panel1 = new JPanel();
		      panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		      panelOnLeftSide.add(panel1);
		      
		      JPanel  panelInfo1 = new JPanel(new FlowLayout()); 	 
		      check1 = new JCheckBox("");
		      //check1.setSize(new Dimension(30, 30));
		      panelInfo1.add(check1);
		      lblCount1 = new Label("ICT Manager Sync");  // construct Label
		      panelInfo1.add(lblCount1);                    // "super" Frame adds Label
		      tfCount1 = new TextField("0", 4); // construct TextField
		      tfCount1.setEditable(false);       // set to read-only
		      panelInfo1.add(tfCount1);                     // "super" Frame adds tfCount

		      logOutput1 = new JTextArea(18, 70);
		      logOutput1.setEditable(false);
		      scrollerOutput1 = new JScrollPane(logOutput1);
	          scrollerOutput1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	          scrollerOutput1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		      caret1 = (DefaultCaret)logOutput1.getCaret();
		      caret1.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		      
		      panel1.add( panelInfo1);
		      panel1.add( scrollerOutput1);
	      }
	      
	      {
		      JPanel panel2 = new JPanel();
		      panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		      panelOnRightSide.add(panel2);
		      
		      JPanel  panelInfo2 = new JPanel(new FlowLayout()); 	 
		      check2 = new JCheckBox("");
		      check2.setEnabled(false);
		      panelInfo2.add(check2);
		      lblCount2 = new Label("Due Diligence Sync");  // construct Label
		      panelInfo2.add(lblCount2);                    // "super" Frame adds Label
		      tfCount2 = new TextField("0", 20); // construct TextField
		      tfCount2.setEditable(false);       // set to read-only
		      panelInfo2.add(tfCount2);                     // "super" Frame adds tfCount
		      
		      logOutput2 = new JTextArea(18, 70);
		      logOutput2.setEditable(false);
		      scrollerOutput2 = new JScrollPane(logOutput2);
	          scrollerOutput2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	          scrollerOutput2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		      caret2 = (DefaultCaret)logOutput2.getCaret();
		      caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		      
		      panel2.add( panelInfo2);
		      panel2.add( scrollerOutput2);
	      }

	      {
		      JPanel panel3 = new JPanel();
		      panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		      panelOnLeftSide.add(panel3);
		      
		      JPanel  panelInfo3 = new JPanel(new FlowLayout()); 	 
		      check3 = new JCheckBox("");
		      check3.setEnabled(false);
		      panelInfo3.add(check3);
		      lblCount3 = new Label("Check MAX Outgoing Calls");  // construct Label
		      panelInfo3.add(lblCount3);                    // "super" Frame adds Label
		      tfCount3 = new TextField("0", 4); // construct TextField
		      tfCount3.setEditable(false);       // set to read-only
		      panelInfo3.add(tfCount3);                     // "super" Frame adds tfCount
		      
		      logOutput3 = new JTextArea(18, 70);
		      logOutput3.setEditable(false);
		      scrollerOutput3 = new JScrollPane(logOutput3);
	          scrollerOutput3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	          scrollerOutput3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		      caret3 = (DefaultCaret)logOutput3.getCaret();
		      caret3.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		      panel3.add( panelInfo3);
		      panel3.add( scrollerOutput3);
	      }
	      
	      {
		      JPanel panel4 = new JPanel();
		      panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		      panelOnRightSide.add(panel4);
		      
		      JPanel  panelInfo4 = new JPanel(new FlowLayout()); 	 
		      check4 = new JCheckBox("");
		      check4.setEnabled(false);
		      panelInfo4.add(check4);
		      lblCount4 = new Label("Synchronize Emersion Customer List");  // construct Label
		      panelInfo4.add(lblCount4);                    // "super" Frame adds Label
		      tfCount4 = new TextField("0", 4); // construct TextField
		      tfCount4.setEditable(false);       // set to read-only
		      panelInfo4.add(tfCount4);                     // "super" Frame adds tfCount
		      
		      logOutput4 = new JTextArea(18, 70);
		      logOutput4.setEditable(false);
		      scrollerOutput4 = new JScrollPane(logOutput4);
	          scrollerOutput4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	          scrollerOutput4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		      caret4 = (DefaultCaret)logOutput4.getCaret();
		      caret4.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		      
		      panel4.add( panelInfo4);
		      panel4.add( scrollerOutput4);
	      }
	      
	      {
		      JPanel panel5 = new JPanel();
		      panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
		      //rootPanel.add(panel5);
		      panelOnLeftSide.add(panel5);
		      
		      JPanel  panelInfo5 = new JPanel(new FlowLayout()); 	 
		      check5 = new JCheckBox("");
		      check5.setEnabled(false);
		      panelInfo5.add(check5);
		      lblCount5 = new Label("Not used");  // construct Label
		      panelInfo5.add(lblCount5);                    // "super" Frame adds Label
		      tfCount5 = new TextField("0", 4); // construct TextField
		      tfCount5.setEditable(false);       // set to read-only
		      panelInfo5.add(tfCount5);                     // "super" Frame adds tfCount
		      
		      logOutput5 = new JTextArea(18, 70);
		      logOutput5.setEditable(false);
		      scrollerOutput5 = new JScrollPane(logOutput5);
	          scrollerOutput5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	          scrollerOutput5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		      caret5 = (DefaultCaret)logOutput5.getCaret();
		      caret5.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		      
		      panel5.add( panelInfo5);
		      panel5.add( scrollerOutput5);
	      }
	      
	      {
		      JPanel panelButton = new JPanel();
		      panelButton.setLayout(new FlowLayout());
		      //panelOnRightSide.add(panelButton);
		      btnApplyConfiguration = new Button("Apply Configuration");   
		      btnApplyConfiguration.setFont(new Font("Sans Serif", Font.BOLD, 20));
		      panelButton.add(btnApplyConfiguration);         
		      btnApplyConfiguration.setEnabled(false);
		      panelButton.add(Box.createRigidArea(new Dimension(120,0)));
		      btnExit = new Button("Exit Execution");   
		      btnExit.setFont(new Font("Sans Serif", Font.BOLD, 20));
		      panelButton.add(btnExit);                    // "super" Frame adds Button

	    	  JPanel panel6 = new JPanel();
	    	  panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
		      //rootPanel.add(panel5);
	    	  panelOnRightSide.add(panel6);
	    	  panel6.add(panelButton);                     // "super" Frame adds tfCount
		      
		      logOutput = new JTextArea(17, 70);
		      logOutput.setEditable(false);
		      scrollerOutput = new JScrollPane(logOutput);
	          scrollerOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	          scrollerOutput.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		      caret = (DefaultCaret)logOutput.getCaret();
		      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		      //rootPanel.add(scrollerOutput); 
		      panel6.add(scrollerOutput); 
	      }
	      
	      check1.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent actionEvent) {
	              AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	              boolean selected = abstractButton.getModel().isSelected();
	              btnApplyConfiguration.setEnabled(true);
	            }
	      });
	      check2.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent actionEvent) {
	              AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	              boolean selected = abstractButton.getModel().isSelected();
	              btnApplyConfiguration.setEnabled(true);
	            }
	      });
	      check3.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent actionEvent) {
	              AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	              boolean selected = abstractButton.getModel().isSelected();
	              btnApplyConfiguration.setEnabled(true);
	            }
	      });
	      check4.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent actionEvent) {
	              AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	              boolean selected = abstractButton.getModel().isSelected();
	              btnApplyConfiguration.setEnabled(true);
	            }
	      });
	      check5.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent actionEvent) {
	              AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	              boolean selected = abstractButton.getModel().isSelected();
	              btnApplyConfiguration.setEnabled(true);
	            }
	      });
	      btnExit.addActionListener(this);
	      btnApplyConfiguration.addActionListener(new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	        	if(check1.isSelected() ){
	        		enabled_websiteICTManagerSync = true;
	        	}
	        	else {
	        		enabled_websiteICTManagerSync = false;
	        	}
	        	
	        	if(check2.isSelected() ){
	        		enabled_taskDueDiligenceSync = true;
	        	}
	        	else {
	        		enabled_taskDueDiligenceSync = false;
	        	}
	        	if(check3.isSelected() ){
	        		enabled_taskCheckForMaxCalls = true;
	        	}
	        	else {
	        		enabled_taskCheckForMaxCalls = false;
	        	}
	        	
	        	if(check4.isSelected() ){
	        		enabled_websiteSyncEmersionIdList = true;
	        	}
	        	else {
	        		enabled_websiteSyncEmersionIdList = false;
	        	}
	        	/*
	        	if(check5.isSelected() ){
	        		enabled_javaHooplaMonitorUpdate = true;
	        	}
	        	else {
	        		enabled_javaHooplaMonitorUpdate = false;
	        	}*/
	        	JOptionPane.showMessageDialog(null, "New configuration has been successfully applied");
	        	btnApplyConfiguration.setEnabled(false);
	        }
	      });
	 
	      setTitle("Java Simulator Set");  // "super" Frame sets title
	      //pack(); 
	      //setSize(1800, 1200);        // "super" Frame sets initial window size
	      setBackground( Color.GRAY );
	      
	      GraphicsConfiguration gc = getGraphicsConfiguration();
	      Rectangle bounds = gc.getBounds();
	      /*
	      setLocation((int) ((bounds.width / 2) - (size.getWidth() / 2)),
                  (int) ((bounds.height / 2) - (size.getHeight() / 2))); */
	      setLocation((int) ((bounds.width) - 1600 + 8),0);
	      pack(); 
	      setVisible(true);         // "super" Frame shows
	   }
	   
	   /** ActionEvent handler - Called back upon button-click. */
	   @Override
	   public void actionPerformed(ActionEvent evt) {
		   System.exit(0);
	   }
	
	public void sleep(int sec){
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	public void selectOption(String id, String text) {       
        Select select = new Select(this.waitForLocator(By.id(id)));
        select.selectByVisibleText(text);        
    }
        
    private int randomWithRange(int min, int max) {
       int range = (max - min) + 1;     
       return (int)(Math.random() * range) + min;
    }
        
    
    public WebElement waitForLocator(final By by) {
		boolean flag = new WebDriverWait(driver, waitTime).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				//wlog("======!"+d.findElement(by).isDisplayed()+"!=======");
				return d.findElement(by).isDisplayed();
			}
		});
		WebElement element = null;
		if(flag){
			element = driver.findElement(by);
		}
		return element;
	}
    
    
    private void wlog(int win, String log){
    	JScrollBar vertical = null;
    	
    	if ( win == 0) {
    		if (logOutput.getLineCount() > outputLineMAXCount) {
	        	logOutput.setText(log);
	        }
	        else {
	        	logOutput.append(log + "\n");
	        	vertical = scrollerOutput.getVerticalScrollBar();
	        	vertical.setValue( vertical.getMaximum());
	        }
    	}
    	else if ( win == 1) {
    		if (logOutput1.getLineCount() > outputLineMAXCount) {
	        	logOutput1.setText(log);
	        }
	        else {
	        	logOutput1.append(log + "\n");
	        	vertical = scrollerOutput1.getVerticalScrollBar();
	        	vertical.setValue( vertical.getMaximum());
	        }
    	}
    	else if ( win == 2) {
    		if (logOutput2.getLineCount() > outputLineMAXCount) {
	        	logOutput2.setText(log);
	        }
	        else {
	        	logOutput2.append(log + "\n");
	        	vertical = scrollerOutput2.getVerticalScrollBar();
	        	vertical.setValue( vertical.getMaximum());
	        }
    	}
    	else if ( win == 3) {
    		if (logOutput3.getLineCount() > outputLineMAXCount) {
	        	logOutput3.setText(log);
	        }
	        else {
	        	logOutput3.append(log + "\n");
	        	vertical = scrollerOutput3.getVerticalScrollBar();
	        	vertical.setValue( vertical.getMaximum());
	        }
    	}
    	else if ( win == 4) {
    		if (logOutput4.getLineCount() > outputLineMAXCount) {
	        	logOutput4.setText(log);
	        }
	        else {
	        	logOutput4.append(log + "\n");
	        	vertical = scrollerOutput4.getVerticalScrollBar();
	        	vertical.setValue( vertical.getMaximum());
	        }
    	}
    	else if ( win == 5) {
    		if (logOutput5.getLineCount() > outputLineMAXCount) {
	        	logOutput5.setText(log);
	        }
	        else {
	        	logOutput5.append(log + "\n");
	        	vertical = scrollerOutput5.getVerticalScrollBar();
	        	vertical.setValue( vertical.getMaximum());
	        }
    	}
    }

    
    private void getDatabaseSetting(){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	ArrayList<String> objArrayList = null;
		
        try {
        	
        	objArrayList = DBNetCubeHub.readTaskParametersFromDatabse("websiteICTManagerSync");
        	if ( objArrayList != null ) {
        		interval_websiteICTManagerSync = Integer.parseInt(objArrayList.get(2));
        		date_websiteICTManagerSync = dateFormat.parse(objArrayList.get(3));
        		check1.setEnabled(true);
        		wlog(1, "websiteICTManagerSync interval is " + interval_websiteICTManagerSync + ", last execution time is " + date_websiteICTManagerSync);
        	}
        	else {
        		check1.setEnabled(false);
        	}
        	
        	objArrayList = DBNetCubeHub.readTaskParametersFromDatabse("taskDueDiligenceSync");
        	if ( objArrayList != null ) {
        		interval_taskDueDiligenceSync = Integer.parseInt(objArrayList.get(2));
        		date_taskDueDiligenceSync = dateFormat.parse(objArrayList.get(3));
        		check2.setEnabled(true);
        		wlog(2, "taskDueDiligenceSync interval is " + interval_taskDueDiligenceSync + ", last execution time is " + date_taskDueDiligenceSync);
        	}
        	else {
        		check2.setEnabled(false);
        	}
        	
        	objArrayList = DBNetCubeHub.readTaskParametersFromDatabse("taskCheckForMaxCalls");
        	if ( objArrayList != null ) {
        		interval_taskCheckForMaxCalls = Integer.parseInt(objArrayList.get(2));
        		date_taskCheckForMaxCalls = dateFormat.parse(objArrayList.get(3));
        		check3.setEnabled(true);
        		wlog(3, "taskCheckForMaxCalls interval is " + interval_taskCheckForMaxCalls + ", last execution time is " + date_taskCheckForMaxCalls);
        	}
        	else {
        		check3.setEnabled(false);
        	}
        	
        	objArrayList = DBNetCubeHub.readTaskParametersFromDatabse("websiteSyncBlisstelEmersionList");
        	if ( objArrayList != null ) {
        		interval_websiteSyncEmersionIdList	= Integer.parseInt(objArrayList.get(2));
        		date_websiteSyncEmersionIdList 		= dateFormat.parse(objArrayList.get(3));
        		check4.setEnabled(true);
        		wlog(4, "websiteSyncBlisstelEmersionList interval is " + interval_websiteSyncEmersionIdList + ", last execution time is " + date_websiteSyncEmersionIdList);
        	}
        	else {
        		check4.setEnabled(false);
        	}
        }
        catch (ParseException ex)  {
        }
	}
    
    
    private void func_websiteICTManagerSync() {
    	WebElement WebComponent = null;	
    	String WebComponentText = "";
    	String listedAccounts = null;
    	String indexOfAccount = null;
    	String listedrecords = null;
    	Date date = new Date();
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		wlog(1, "\n" + count_websiteICTManagerSync++ + " round check up: (" + dateFormat.format(date) + ")");    
		intCount1 ++;
    	tfCount1.setText("" + intCount1);
    	
    	try {
    		this.driver = new FirefoxDriver();
    		this.sleep(randomWithRange(internalMin_websiteICTManagerSync, internalMax_websiteICTManagerSync));
        	this.driver.navigate().to(url_websiteICTManagerSync);
    		this.sleep(randomWithRange(internalMin_websiteICTManagerSync, internalMax_websiteICTManagerSync));
    			
    		//driver.manage().window().maximize();
    		this.waitForLocator(By.id("uname")).sendKeys(username_websiteICTManagerSync);
    		this.waitForLocator(By.id("pswd")).sendKeys(password_websiteICTManagerSync);
    		this.waitForLocator(By.id("loginbutton")).click();	
    		this.sleep(randomWithRange(internalMin_websiteICTManagerSync, internalMax_websiteICTManagerSync));
    		
    		DecimalFormat df = new DecimalFormat("0.00");
    		df.setMaximumFractionDigits(2);
    		WebComponent = this.driver.findElement(By.id("valueMemSystem"));
    		WebComponentText = WebComponent.getText().trim();
    		if (!WebComponentText.isEmpty()) {
    			WebComponentText = WebComponentText.replace(" Mb", "");
	    		WebComponentText = WebComponentText.replace("(", "");
	    		WebComponentText = WebComponentText.replace(")", "");
	    		WebComponentText = WebComponentText.trim();
	    		wlog(1, "Memory Usage: " + WebComponentText + " Mb");
	    		float f = Float.parseFloat(WebComponentText);
	    		wlog(1, "Memory Usage: " + WebComponentText + " Mb(" + df.format(f * 100 /64) + "%)");
	    		if (f > 57.6) {
	    			String text = "ICT Manager Server(192.168.1.64) Memory Usage " + df.format(f * 100 /64) + "% is in great risk";
	    			sendGmail_websiteICTManagerSync(text);
	    		}
    		}
    		
    		
    		this.waitForLocator(By.id("MHUsers")).click();	
    		this.waitForLocator(By.id("MUsers")).click();	
    		this.sleep(randomWithRange(internalMin_websiteICTManagerSync, internalMax_websiteICTManagerSync));
    		WebComponent = this.driver.findElement(By.cssSelector("#listcontents"));
    		listedAccounts = WebComponent.getText().trim();
    		wlog(1, "=========================================");
    		wlog(1, listedAccounts);
    		wlog(1, "=========================================");
    		
    		String[] listedAccountsArray = listedAccounts.split("\n");
    		for (String account : listedAccountsArray) {
    			account = account.trim();
    			if ( account.isEmpty() ) {
    				continue;
    			}
    			try {
	    			indexOfAccount = DBICTManager.getIndexOfAccount(account);
	    			wlog(1, "Account: " + account + " Index:" + indexOfAccount);
	    			this.waitForLocator(By.partialLinkText(account)).click();	
	    			this.sleep(randomWithRange(internalMin_websiteICTManagerSync, internalMax_websiteICTManagerSync));
	    			this.waitForLocator(By.id("Tabevents")).click();	
	    			this.sleep(randomWithRange(internalMin_websiteICTManagerSync, internalMax_websiteICTManagerSync));
	    			this.waitForLocator(By.id("eventreport"));
	    			WebComponent = this.driver.findElement(By.id("eventreport"));
	    			listedrecords = WebComponent.getText().trim();
	    			websiteICTManagerSync_account(account, indexOfAccount, listedrecords);
	        		wlog(1, "=========================================");
    			}
    			catch (Exception e1) {
    	    		wlog(1, "Function oneTransaction() internal loop error: " + Arrays.toString(e1.getStackTrace()));
    	    		wlog(1, "=========================================");
    	    		this.sleep(randomWithRange(internalMin_websiteICTManagerSync, internalMax_websiteICTManagerSync));
    	    	} 
    		}
    		
    		this.waitForLocator(By.id("MHLogout")).click();
    		this.sleep(randomWithRange(internalMin_websiteICTManagerSync, internalMax_websiteICTManagerSync));
    		this.driver.close();
    	} 
    	catch (Exception e) {
    		wlog(1, "Function oneTransaction() external loop error: " + Arrays.toString(e.getStackTrace()));
    		//this.waitForLocator(By.id("MHLogout")).click();
    		this.sleep(randomWithRange(internalMin_websiteICTManagerSync, internalMax_websiteICTManagerSync));
    		this.driver.close();
    	} 
    }
    
    private void websiteICTManagerSync_account(String account, String indexOfAccount, String listedrecords) {
    	
    	String tag = null;
    	String comments = null;
    	String time = null; String hour,minute,second,dateTag = null;
    	String date = null;	String year,month,day = null;
    	String weekday = null;
    	String retunString = null;
    	int counterInserted = 0,countDuplicated = 0,countDBError = 0;
    	
    	String[] listedrecordsArray = listedrecords.split("\n");
    	
		for (String record : listedrecordsArray) {
			record = record.trim();
			if (record.equalsIgnoreCase("Description Time")) {
				continue;
			}
			else if (record.equalsIgnoreCase("None found")) {
				continue;
			}
			
			record = record.replaceFirst("User " + account, "").trim();
			String[] recordArray = record.split(" ");
			
			tag = recordArray[0].trim();
			
			time = recordArray[recordArray.length-1].trim();
			String[] timeArray = time.split(":");
			hour = timeArray[0];
			minute = timeArray[1];
			second = timeArray[2].substring(0, 2);
			dateTag = timeArray[2].substring(2);
			if (dateTag.equalsIgnoreCase("pm")) {
				if(!hour.equalsIgnoreCase("12")) {
					hour = Integer.parseInt(hour) + 12 + "";
				}
			}
			time = hour + ":" + minute + ":" + second;
			
			date = recordArray[recordArray.length-2].trim();
			String[] dateArray = date.split("/");
			year = dateArray[2];
			month = dateArray[1];
			day = dateArray[0];
			date = year + "-" + month + "-" + day;
			
			weekday = recordArray[recordArray.length-3].trim();
			
			record = record.replaceFirst(tag, "").trim();
			int indexOfWeekday = record.lastIndexOf(weekday);
			comments = record.substring(0, indexOfWeekday).trim();
				
			retunString = DBICTManager.insertRecordIgnoreDuplication(indexOfAccount,tag,weekday,date,time,comments);
			if (retunString.equalsIgnoreCase("inserted")) {
				counterInserted++;
			}
			else if (retunString.equalsIgnoreCase("duplicated")) {
				countDuplicated++;
			}
			else {
				countDBError++;
			}
		}
		wlog(1, counterInserted + " records inserted, " + countDuplicated + " records duplicated, " + countDBError + " records failed");
    }
    
    
    public void func_taskDueDiligenceSync() {
    	
    	WebElement WebComponent = null;	
    	String emersionId = "", responseText = "";
    	int waitLoopCount = 0, succLoopCount = 0;
    	Boolean isPresent = false;
    	
    	try {
    		if (array_contract_taskDueDiligenceSync == null && array_download_taskDueDiligenceSync == null) {
	    		wlog(2, "\n" + onlineOrderTable_taskDueDiligenceSync + ": " + (new Date()).toString());
	    		wlog(2, "==============================================================================================================");
	    		
	    		this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
	    		this.driver = new FirefoxDriver();
	    		this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
				this.driver.navigate().to(url_sync_taskDueDiligenceSync);
				this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
				
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
				if (this.driver != null) {
	    			this.driver.close();
	    			this.driver = null;
	    		}
				wlog(2, (new Date()).toString().substring(4, 19) + ":  " + responseText);
				
				array_contract_taskDueDiligenceSync			= DBNetCubeHub.getEchosignContractBasedOnEmersion();
				size_array_contract_taskDueDiligenceSync	= array_contract_taskDueDiligenceSync.size();
				count_array_contract_taskDueDiligenceSync 	= 0;
	    		wlog(2, "Table " + onlineOrderTable_taskDueDiligenceSync + " contract size: " + size_array_contract_taskDueDiligenceSync);
	    		
	    		array_download_taskDueDiligenceSync	 		= DBNetCubeHub.downloadEchosignContractBasedOnEmersion();
	    		size_array_download_taskDueDiligenceSync 	= array_download_taskDueDiligenceSync.size();
	    		count_array_download_taskDueDiligenceSync 	= 0;
	    		wlog(2, "Table " + onlineOrderTable_taskDueDiligenceSync + " download size: " + size_array_download_taskDueDiligenceSync);
	    		wlog(2, "==============================================================================================================");
    		}

    		if (	count_array_contract_taskDueDiligenceSync == size_array_contract_taskDueDiligenceSync && 
    				count_array_download_taskDueDiligenceSync == size_array_download_taskDueDiligenceSync) {
    			array_contract_taskDueDiligenceSync			= null;
    			array_download_taskDueDiligenceSync	 		= null;
    			return;
    		}
    		else if (count_array_download_taskDueDiligenceSync < size_array_download_taskDueDiligenceSync) {
    			emersionId = array_download_taskDueDiligenceSync.get(count_array_download_taskDueDiligenceSync ++);
    			try {
	    			this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
	        		this.driver = new FirefoxDriver();
	        		this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
	    			this.driver.navigate().to(url_download_taskDueDiligenceSync + "?emersionId=" + emersionId);
	    			this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
	    			
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
	    			
	    			wlog(2, emersionId + ": " + (new Date()).toString().substring(4, 19) + "  " + responseText);
	    			tfCount2.setText(count_array_download_taskDueDiligenceSync + "/" + size_array_download_taskDueDiligenceSync + " orders processed");
	    			this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
	    			this.driver.close(); 	
	    			this.driver = null;
    			}
    			catch (Exception e) {
     	    		if (this.driver != null) {
    	    			this.driver.close();
    	    			this.driver = null;
    	    		}
     	    		wlog(2, "Function func_taskDueDiligenceSync() download internal error: " + Arrays.toString(e.getStackTrace()));
    	    		this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
    	    		count_array_download_taskDueDiligenceSync = count_array_download_taskDueDiligenceSync -1;
    	    	} 
    		}
    		else if (count_array_contract_taskDueDiligenceSync < size_array_contract_taskDueDiligenceSync) {
    			emersionId = array_contract_taskDueDiligenceSync.get(count_array_contract_taskDueDiligenceSync ++);
    			try {
	    			this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
	        		this.driver = new FirefoxDriver();
	        		this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
	    			this.driver.navigate().to(url_contract_taskDueDiligenceSync + "?emersionId=" + emersionId);
	    			this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
	    			
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
	    			
	    			wlog(2, emersionId + ": " + (new Date()).toString().substring(4, 19) + "  " + responseText);
	    			tfCount2.setText(count_array_contract_taskDueDiligenceSync + "/" + size_array_contract_taskDueDiligenceSync + " orders processed");
	    			this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
	    			this.driver.close(); 	
	    			this.driver = null;
    			}
    			catch (Exception e) {
     	    		if (this.driver != null) {
    	    			this.driver.close();
    	    			this.driver = null;
    	    		}
     	    		wlog(2, "Function func_taskDueDiligenceSync() contract internal error (" + count_array_contract_taskDueDiligenceSync + "): " + Arrays.toString(e.getStackTrace()));
    	    		this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
    	    		count_array_contract_taskDueDiligenceSync = count_array_contract_taskDueDiligenceSync -1;
    	    	} 
    		}
    	} 
    	catch (Exception e1) {
    		wlog(2, "Function func_taskDueDiligenceSync() external error: " + Arrays.toString(e1.getStackTrace()));
    		this.sleep(randomWithRange(internalMin_taskDueDiligenceSync, internalMax_taskDueDiligenceSync));
    		if (this.driver != null) {
    			this.driver.close();
    			this.driver = null;
    		}
    	} 
    }
    
    
    public void func_taskCheckForMaxCalls() {
    	
    	WebElement WebComponent = null;	
    	String emersionId 		= "", responseText = "";
    	String subject	 		= "", content = "";
    	int waitLoopCount 		= 0, succLoopCount = 0;
    	Boolean isPresent 		= false;
    	long diff 				= 0;
    	int loopCount			= 0;
    	DateFormat dateFormat 	= new SimpleDateFormat("yyyy-MM-dd");
    	Calendar cal 			= null;
    	
    	try {
    		wlog(3, "\ntaskCheckForMaxCalls: " + (new Date()).toString());
    		wlog(3, "==============================================================================================================");
    		this.driver = null;
    		
    		ArrayList<String> arrayListTask = DBNetCubeHub.getTableFieldFromDatabse("taskCheckForMaxCalls", "planLastEmailSentTime");
    		if (arrayListTask != null) {
    			wlog(3, "Function func_taskCheckForMaxCalls() planLastEmailSentTime: " + arrayListTask.toString());
    			Date date = formatter.parse(arrayListTask.get(1));
    			diff = ((new Date()).getTime() - formatter.parse(arrayListTask.get(1)).getTime()) / 1000;
    		}
    		else {
    			wlog(3, "Function func_taskCheckForMaxCalls() planLastEmailSentTime: null");
    		}
    		
    		if (arrayListTask == null || diff > 24*60*60) {
	    		cal = Calendar.getInstance();
	    		cal.set(Calendar.HOUR_OF_DAY,9);
				cal.set(Calendar.MINUTE,0);
				cal.set(Calendar.SECOND,0);
				cal.set(Calendar.MILLISECOND,0);
				DBNetCubeHub.setTableFieldFromDatabse("taskCheckForMaxCalls", "planLastEmailSentTime", cal.getTime());
				
				this.driver = new FirefoxDriver();
	    		this.sleep(randomWithRange(internalMin_taskCheckForMaxCalls, internalMax_taskCheckForMaxCalls));
	    		String enddate = dateFormat.format(new Date());
	    		Calendar cal_tmp = Calendar.getInstance();
	    		cal_tmp.add(Calendar.DATE, -1);    
	    		String startdate = dateFormat.format(cal_tmp.getTime());
	    		//wlog(3, "Function startdate=" + startdate + "&enddate=" + enddate);
	        	this.driver.navigate().to(url_taskCheckForMaxCalls + "?startdate=" + startdate + "&enddate=" + enddate);
	    		this.sleep(randomWithRange(internalMin_taskCheckForMaxCalls, internalMax_taskCheckForMaxCalls));
	    			
	    		isPresent = driver.findElements(By.name("subject")).size() > 0;
				while (!isPresent && loopCount < 100) {
					isPresent = driver.findElements(By.name("subject")).size() > 0;
					loopCount ++;
					this.sleep(1);
				}
	    		
				WebComponent = this.driver.findElement(By.name("subject"));
				subject = WebComponent.getAttribute("value");
				wlog(3, "Function subject: " + subject);
				WebComponent = this.driver.findElement(By.name("content"));
				content = WebComponent.getAttribute("value");
				//wlog(3, "Function content: " + content);
				sendGmail_taskCheckForMaxCalls(subject, content);
    		}
    		
    		cal = Calendar.getInstance();
    		if ((arrayListTask == null || diff > 24*60*60) && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
	    		this.sleep(randomWithRange(internalMin_taskCheckForMaxCalls, internalMax_taskCheckForMaxCalls));
	    		String enddate = dateFormat.format(new Date());
	    		Calendar cal_tmp = Calendar.getInstance();
	    		cal_tmp.add(Calendar.DATE, -7);    
	    		String startdate = dateFormat.format(cal_tmp.getTime());
	    		//wlog(3, "Function startdate=" + startdate + "&enddate=" + enddate);
	        	this.driver.navigate().to(url_taskCheckForMaxCalls + "?cycle=weekly&startdate=" + startdate + "&enddate=" + enddate);
	    		this.sleep(randomWithRange(internalMin_taskCheckForMaxCalls, internalMax_taskCheckForMaxCalls));
	    			
	    		isPresent = driver.findElements(By.name("subject")).size() > 0;
				while (!isPresent && loopCount < 100) {
					isPresent = driver.findElements(By.name("subject")).size() > 0;
					loopCount ++;
					this.sleep(1);
				}
	    		
				WebComponent = this.driver.findElement(By.name("subject"));
				subject = WebComponent.getAttribute("value");
				wlog(3, "Function subject: " + subject);
				WebComponent = this.driver.findElement(By.name("content"));
				content = WebComponent.getAttribute("value");
				//wlog(3, "Function content: " + content);
				sendGmail_taskCheckForMaxCalls(subject, content);
    		}
    		
    	} 
    	catch (Exception e1) {
    		wlog(3, "Function func_taskCheckForMaxCalls() external error: " + Arrays.toString(e1.getStackTrace()));
    	} 
    	finally {
    		if (this.driver != null) {
    			this.driver.close();
    			this.sleep(randomWithRange(internalMin_taskCheckForMaxCalls, internalMax_taskCheckForMaxCalls));
    			this.driver = null;
    		}
    		tfCount3.setText((count_taskCheckForMaxCalls++) + "");
    		this.sleep(randomWithRange(internalMin_taskCheckForMaxCalls, internalMax_taskCheckForMaxCalls));
    		wlog(3, "==============================================================================================================");
    	}
    }
    
    
    private void websiteSyncBlisstelEmersionList() {
		WebElement WebComponent	= null;	
    	String logFileContent 	= "", cssSelector = "", websiteSyncEmersionIdList = "", css = "";
    	Boolean isPresent 		= false;
    	int loopCount			= 0;
    	int loopCountInAll		= 0;
    	int emersionCount		= 0;
    	String accountId		= "";
    	String accountStatus	= "";
    	String accountName		= "";
    	String accountEmail		= "";
    	String accountPhone		= "";
    	String accountMobile	= "";
    	String accountCreated	= "";
    	String activeService	= "";
    	String activePurchase	= "";
    	String accountBalance	= "";
    	String pagination		= "";
    	String[] parts 			= null;
    	String resultUpdateRelatedTables = "";
    	
    	try {
			wlog(4,"\n" + (new Date()).toString());
    		logFileContent = "\n" + (new Date()).toString();
    		
    		this.driver = new FirefoxDriver();
    		this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
        	this.driver.navigate().to("https://cumulus.emersion.com.au/customer/account/list");
    		this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
    		this.waitForLocator(By.id("username")).sendKeys(LoginUsernameSyncEmersionIdList);
    		this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
			this.waitForLocator(By.id("password")).sendKeys(LoginPasswordSyncEmersionIdList);
			this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
			this.waitForLocator(By.cssSelector(".button > input:nth-child(1)")).click();	
			this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));

			/*		*/	
			loopCountInAll = 0;
			try {
				this.waitForLocator(By.id("searchform_search")).click();
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				this.waitForLocator(By.id("accounts_filter_statusSelect"));
				final String[] textOptions = {	"Preactive", "Active", "Inactive", "Cancelled", "Suspended", 
												"Restricted by Parent", "System Suspended", "Suspended - Billing and Ordering"};
				final WebElement element = driver.findElement(By.id("accounts_filter_statusSelect"));
				final Select dropdown = new Select(element);
				final List<WebElement> options = dropdown.getOptions();
				final Actions builder = new Actions(driver);
				final boolean isMultiple = dropdown.isMultiple();
				if (isMultiple) {
				    dropdown.deselectAll();
				}
				builder.keyDown(Keys.CONTROL);
				for (String textOption : textOptions) {
				    for (WebElement option : options) {
				        final String optionText = option.getText().trim();
				        if (optionText.equalsIgnoreCase(textOption)) {
				            if (isMultiple) {
				                if (!option.isSelected()) {
				                    builder.click(option);
				                }
				            } else {
				                option.click();
				            }
				            break;
				        }
				    }
				}
				builder.keyUp(Keys.CONTROL).build().perform();
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				
				this.waitForLocator(By.id("accounts_filter_submit")).click();	
				this.waitForLocator(By.cssSelector(".pagination-rows > a:nth-child(1)"));
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				this.waitForLocator(By.cssSelector(".pagination-rows > a:nth-child(4)")).click();	
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				this.waitForLocator(By.cssSelector(".pagination-pages"));
				pagination 		= driver.findElement(By.cssSelector(".pagination-pages")).getText();
				parts 			= pagination.trim().split(" ");
				loopCountInAll 	= Integer.parseInt(parts[parts.length - 2]);
				wlog(4,"pagination-pages: " + pagination + " ===> " + loopCountInAll);
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				
				WebComponent 	= driver.findElement(By.cssSelector("#content > div > div.listtable > span"));
				parts 			= WebComponent.getText().trim().split(" ");
				emersionCount 	= Integer.parseInt(parts[0].trim());
				
				WebComponent 	= driver.findElement(By.cssSelector(".pagination-pages > a:nth-child(6)"));
				this.waitForLocator(By.cssSelector(".pagination-pages > a:nth-child(6)")).click();	
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				this.waitForLocator(By.cssSelector(".pagination-pages"));
				pagination 		= driver.findElement(By.cssSelector(".pagination-pages")).getText();
				parts 			= pagination.trim().split(" ");
				loopCountInAll = Integer.parseInt(parts[parts.length - 1]);
			}
			catch (Exception e) {
	    		wlog(4, (new Date()).toString() + ": " + Arrays.toString(e.getStackTrace()));
			}
			finally {
				wlog(4,emersionCount + " emersion records found / " + loopCountInAll + " pages found");
				wlog(4,"==================================================");
				
				this.waitForLocator(By.id("searchform_search")).click();	
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				this.waitForLocator(By.id("accounts_filter_statusSelect"));
				final String[] textOptions = {	"Preactive", "Active", "Inactive", "Cancelled", "Suspended", 
						"Restricted by Parent", "System Suspended", "Suspended - Billing and Ordering"};
				final WebElement element = driver.findElement(By.id("accounts_filter_statusSelect"));
				final Select dropdown = new Select(element);
				final List<WebElement> options = dropdown.getOptions();
				final Actions builder = new Actions(driver);
				final boolean isMultiple = dropdown.isMultiple();
				if (isMultiple) {
				    dropdown.deselectAll();
				}
				builder.keyDown(Keys.CONTROL);
				for (String textOption : textOptions) {
				    for (WebElement option : options) {
				        final String optionText = option.getText().trim();
				        if (optionText.equalsIgnoreCase(textOption)) {
				            if (isMultiple) {
				                if (!option.isSelected()) {
				                    builder.click(option);
				                }
				            } else {
				                option.click();
				            }
				            break;
				        }
				    }
				}
				builder.keyUp(Keys.CONTROL).build().perform();
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				
				this.waitForLocator(By.id("accounts_filter_submit")).click();	
				this.waitForLocator(By.cssSelector("#content > div > div.listtable > div > div > div.pagination-rows > a:nth-child(1)"));
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				this.waitForLocator(By.cssSelector("#content > div > div.listtable > div > div > div.pagination-rows > a:nth-child(4)")).click();	
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				this.waitForLocator(By.cssSelector(".pagination-pages"));
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
			}
			
			
			//Database.resetWebsiteSyncEmersionIdList();
			intCount4 	= 0;
			loopCount	= 1;
			wlog(4,"Page scan loop count: " + loopCount + " / " + (new Date()).toString());
			this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
			/*		*/
			for (int i = 1; i <= numEmersionPerPage; i++) {
				if (i % 2 == 0) {
					WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
					accountId		= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(6) > div:nth-child(1)"));
					accountStatus	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(2) > div:nth-child(1) > a"));
					accountName		= WebComponent.getText();
					
					WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(8) > div:nth-child(1)"));
					accountPhone	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(9) > div:nth-child(1)"));
					accountMobile	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(10) > div:nth-child(1)"));
					accountEmail	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(11) > div:nth-child(1)"));
					accountCreated	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(13) > div:nth-child(1)"));
					activeService	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(14) > div:nth-child(1)"));
					activePurchase	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(17) > div:nth-child(1)"));
					accountBalance	= WebComponent.getText();
				}
				else {
					WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
					accountId		= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(6) > div:nth-child(1)"));
					accountStatus	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(2) > div:nth-child(1) > a"));
					accountName		= WebComponent.getText();
					
					WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(8) > div:nth-child(1)"));
					accountPhone	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(9) > div:nth-child(1)"));
					accountMobile	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(10) > div:nth-child(1)"));
					accountEmail	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(11) > div:nth-child(1)"));
					accountCreated	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(13) > div:nth-child(1)"));
					activeService	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(14) > div:nth-child(1)"));
					activePurchase	= WebComponent.getText();
					WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(17) > div:nth-child(1)"));
					accountBalance	= WebComponent.getText();
				}
				websiteSyncEmersionIdList 	= DBNetCubeHub.websiteSyncEmersionIdListTUpdateRelatedTables(
						accountId, accountStatus, 
						accountName, accountPhone, 
						accountMobile, accountEmail, 
						accountCreated, activeService, 
						activePurchase, accountBalance);
				if ( !websiteSyncEmersionIdList.isEmpty() ) {
					wlog(4,"Emersion customer(" + i + "/" + accountStatus + "/" + accountName + "): " + websiteSyncEmersionIdList);
				}
				else {
					//wlog(4,"Emersion customer(" + i + "/" + accountStatus + "/" + accountName + "): " + accountId);
				}
				intCount4 ++;
				tfCount4.setText("" + intCount4);
			}
			
			
			/*		*/
			while (loopCount < loopCountInAll) {
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				css = getPaginationCSS(loopCount, loopCountInAll);
				if (css.isEmpty()) {
					break;
				}
				wlog(4, "Page " + loopCount + " / " + css + ": " + driver.findElement(By.cssSelector(css)).getText());
				this.waitForLocator(By.cssSelector(css)).click();	
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				this.waitForLocator(By.cssSelector(".pagination-pages"));
				wlog(4,"Page scan loop count: " + (loopCount + 1) + " / " + (new Date()).toString());
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
				for (int i = 1; i <= numEmersionPerPage; i++) {
					if (i % 2 == 0) {
						cssSelector = "tr.even:nth-child(" + i + ") > td:nth-child(1) > div:nth-child(1) > a:nth-child(1)";
					}
					else {
						cssSelector = "tr.odd:nth-child(" + i + ") > td:nth-child(1) > div:nth-child(1) > a:nth-child(1)";
					}
					isPresent = driver.findElements(By.cssSelector(cssSelector)).size() > 0;
					if (!isPresent) {
						break;
					}
					else {
						if (i % 2 == 0) {
							WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
							accountId		= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(6) > div:nth-child(1)"));
							accountStatus	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(2) > div:nth-child(1) > a"));
							accountName		= WebComponent.getText();
							
							WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(8) > div:nth-child(1)"));
							accountPhone	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(9) > div:nth-child(1)"));
							accountMobile	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(10) > div:nth-child(1)"));
							accountEmail	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(11) > div:nth-child(1)"));
							accountCreated	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(13) > div:nth-child(1)"));
							activeService	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(14) > div:nth-child(1)"));
							activePurchase	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.even:nth-child(" + i + ") > td:nth-child(17) > div:nth-child(1)"));
							accountBalance	= WebComponent.getText();
						}
						else {
							WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
							accountId		= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(6) > div:nth-child(1)"));
							accountStatus	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(2) > div:nth-child(1) > a"));
							accountName		= WebComponent.getText();

							WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(8) > div:nth-child(1)"));
							accountPhone	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(9) > div:nth-child(1)"));
							accountMobile	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(10) > div:nth-child(1)"));
							accountEmail	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(11) > div:nth-child(1)"));
							accountCreated	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(13) > div:nth-child(1)"));
							activeService	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(14) > div:nth-child(1)"));
							activePurchase	= WebComponent.getText();
							WebComponent 	= driver.findElement(By.cssSelector("tr.odd:nth-child(" + i + ") > td:nth-child(17) > div:nth-child(1)"));
							accountBalance	= WebComponent.getText();
						}
						websiteSyncEmersionIdList	= DBNetCubeHub.websiteSyncEmersionIdListTUpdateRelatedTables(
								accountId, accountStatus, 
								accountName, accountPhone, 
								accountMobile, accountEmail, 
								accountCreated, activeService, 
								activePurchase, accountBalance);
						if ( !websiteSyncEmersionIdList.isEmpty() ) {
							wlog(4,"Emersion customer(" + (i + loopCount*numEmersionPerPage) + "/" + accountStatus + "/" + accountName + "): " + websiteSyncEmersionIdList);
						}
						else {
							//wlog(4,"Emersion customer(" + (i + loopCount*numEmersionPerPage) + "/" + accountStatus + "/" + accountName + "): " + accountId);
						}
					}
					intCount4 ++;
					tfCount4.setText("" + intCount4);
				}
				loopCount++;
			}
			
			websiteSyncBlisstelEmersionUploadReference();
			websiteSyncBlisstelEmersionCustomerServiceRef();
    	} 
    	catch (Exception e) {
    		//e.printStackTrace();
    		logFileContent = (new Date()).toString() + "\n" + "\t" + Arrays.toString(e.getStackTrace());
    		wlog(4, logFileContent);
    	} 
    	finally{
    		wlog(4,"==================================================");
    		if (this.driver != null) {
    			this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
    			this.waitForLocator(By.cssSelector(".header_info_right > a:nth-child(1) > strong:nth-child(1)")).click();	
    			this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
    			this.driver.close();
    			this.driver = null;
    		}
    	}
	}
    
    
    private void websiteSyncBlisstelEmersionUploadReference() {
		WebElement WebComponent			= null;	
    	Boolean isLoopTerminate			= true;
    	ArrayList<String> objArrayList	= new ArrayList<String>();
		String status					= "";
		String customerId				= "";
		String customerStatus			= "";
		String upload_Reference			= "";
		String export_Reference			= "";
    	
    	try {
    		
    		objArrayList	= DBNetCubeHub.getMaximumBlisstelEmersionId();
			status			= objArrayList.get(0);
			customerId		= objArrayList.get(1);
			customerStatus	= objArrayList.get(2);
			if ( status.equalsIgnoreCase("NOK") ) {
				isLoopTerminate	= false;
			}
			else if ( status.equalsIgnoreCase("Exception") ) {
				wlog(4, "Exception happened: " + customerId);
			}
			else if ( status.equalsIgnoreCase("OK")) {
				
			}
			
			wlog(4,"==================================================");
			while (isLoopTerminate) {
				upload_Reference			= "";
				export_Reference			= "";
				
				if ( status.equalsIgnoreCase("OK") && !customerStatus.equalsIgnoreCase("Preactive")) {
					this.waitForLocator(By.id("search_term")).clear();
					this.sleep(1);
					this.waitForLocator(By.id("search_term")).sendKeys(customerId);
					this.sleep(1);
					this.waitForLocator(By.id("searchform_search")).click();	
		    		this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
					
					this.waitForLocator(By.cssSelector("#Tabs > div > div.tabs > ol > li:nth-child(3) > a")).click();	
					this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
					
					this.waitForLocator(By.cssSelector("div.column:nth-child(4) > div:nth-child(8)"));	
					upload_Reference 		= driver.findElement(By.cssSelector("div.column:nth-child(4) > div:nth-child(8)")).getText();
					upload_Reference		= upload_Reference.replaceAll("Upload Reference:", "").trim();
					export_Reference		= driver.findElement(By.cssSelector("div.column:nth-child(4) > div:nth-child(9)")).getText();
					export_Reference		= export_Reference.replaceAll("Export Reference:", "").trim();
				}
				
				wlog(4,"Emersion customer(" + customerId + "/" + customerStatus + ") upload_Reference(" + upload_Reference + ") export_Reference(" + export_Reference + ")");
				DBNetCubeHub.setWebsiteSyncEmersionIdListTUploadReference(customerId, export_Reference);
				
				objArrayList	= DBNetCubeHub.getMaximumBlisstelEmersionId();
				status			= objArrayList.get(0);
				customerId		= objArrayList.get(1);
				customerStatus	= objArrayList.get(2);
				if ( status.equalsIgnoreCase("NOK") ) {
					isLoopTerminate	= false;
				}
				else if ( status.equalsIgnoreCase("Exception") ) {
					wlog(4, "Exception happened: " + customerId);
					isLoopTerminate	= false;
				}
				else if ( status.equalsIgnoreCase("OK")) {
					
				}
				this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
			}
    	} 
    	catch (Exception e) {
    		wlog(4, (new Date()).toString() + "\n" + "\t" + Arrays.toString(e.getStackTrace()));
    	} 
    	finally{
    		wlog(4,"==================================================");
    	}
	}
    
    
    private void websiteSyncBlisstelEmersionCustomerServiceRef() {
		WebElement WebComponent			= null;	
    	Boolean isLoopTerminate			= true;
    	ArrayList<String> objArrayList	= new ArrayList<String>();
		String status					= "";
		String service_number			= "";
		String customer_service_ref		= "";
		String customerId				= "";
		String customerStatus			= "";
		String balance					= "";
		String export_Reference			= "";
		boolean isPresent				= false;
		boolean isPresent1				= false;
		boolean isPresent2				= false;
    	
    		
		objArrayList				= DBNetCubeHub.getBlisstelAAPTCustomerServiceRef();
		status						= objArrayList.get(0);
		customer_service_ref		= objArrayList.get(1);
		service_number				= objArrayList.get(2);
		if ( status.equalsIgnoreCase("NOK") ) {
			isLoopTerminate	= false;
		}
		else if ( status.equalsIgnoreCase("Exception") ) {
			isLoopTerminate	= false;
			wlog(4, "Exception happened: " + customer_service_ref);
		}
		else if ( status.equalsIgnoreCase("OK")) {
			
		}
		
		wlog(4,"==================================================");
		while (isLoopTerminate) {
			customerId				= "0";
			customerStatus			= "";
			export_Reference		= "";
			balance					= "";
			
			if ( status.equalsIgnoreCase("OK") && !customer_service_ref.isEmpty()) {
					
			    try {
					this.waitForLocator(By.id("search_term")).clear();
					this.sleep(1);
					this.waitForLocator(By.id("search_term")).sendKeys("0" + customer_service_ref);
					this.sleep(1);
					selectOption("searchby", "Service Identifier");
					this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
					this.waitForLocator(By.id("searchform_search")).click();	
		    		this.sleep(2 * randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
		    		
		    		isPresent 				= driver.findElements(By.cssSelector("#Tabs > div > div.tabs > ol > li:nth-child(3) > a")).size() > 0;
		    		if (isPresent) {
		    			this.waitForLocator(By.cssSelector("#Tabs > div > div.tabs > ol > li:nth-child(3) > a")).click();	
						this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
						this.waitForLocator(By.cssSelector("#Tabs > div > div.tabs > ol > li:nth-child(3) > a")).click();	
						this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
	
						customerId				= driver.findElement(By.cssSelector(".customer_details")).getText();
						customerId				= customerId.substring(customerId.indexOf('(') + 1, customerId.indexOf(',')).trim();
						customerId				= customerId.replaceAll("Account ID :", "").trim();
						
						balance					= driver.findElement(By.cssSelector(".customer_details")).getText();
						balance					= balance.substring(balance.indexOf("Balance :") + "Balance :".length() + 1, balance.indexOf(')')).trim();
						balance					= balance.replaceAll("CR \\$", "CR");
						balance					= balance.replaceAll("\\$", "");
						balance					= balance.trim();
						
						isPresent1 				= driver.findElements(By.cssSelector("#service > tbody > tr > td:nth-child(1) > div")).size() > 0;
						isPresent2 				= driver.findElements(By.cssSelector("#Tabs > div:nth-child(1) > div:nth-child(1) > ol:nth-child(1) > li:nth-child(3) > a:nth-child(1)")).size() > 0;
						if (isPresent1) {
							customerId		 	= driver.findElement(By.cssSelector("#service > tbody > tr > td:nth-child(1) > div")).getText();
							export_Reference	= driver.findElement(By.cssSelector("#service > tbody > tr > td:nth-child(3) > div")).getText();
						}
						else if (isPresent2) {
							this.waitForLocator(By.cssSelector("#Tabs > div:nth-child(1) > div:nth-child(1) > ol:nth-child(1) > li:nth-child(3) > a:nth-child(1)")).click();	
							this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
							
							this.waitForLocator(By.cssSelector("div.column:nth-child(4) > div:nth-child(8)"));	
							customerStatus			= driver.findElement(By.cssSelector("div.form:nth-child(3) > div:nth-child(3) > div:nth-child(2)")).getText();
							customerStatus			= customerStatus.replaceAll("Account Status:", "").trim();
							export_Reference		= driver.findElement(By.cssSelector("div.column:nth-child(4) > div:nth-child(9)")).getText();
							export_Reference		= export_Reference.replaceAll("Export Reference:", "").trim();
						}
		    		}
			   	}
			   	catch (Exception e) {
			   		wlog(4, (new Date()).toString() + "\n" + "\t" + Arrays.toString(e.getStackTrace()));
			   	} 
			   	finally{
			   	}

			}
				
			wlog(4,"Emersion customer(" + customerId + "/" + customerStatus + ") balance(" + balance + ") export_Reference(" + export_Reference + ")");
			DBNetCubeHub.websiteSyncEmersionIdUpdate_blis_aapt_service_internet(
					service_number, customer_service_ref, 
					customerId, customerStatus, 
					balance, export_Reference);
			
			objArrayList			= DBNetCubeHub.getBlisstelAAPTCustomerServiceRef();
			status					= objArrayList.get(0);
			customer_service_ref	= objArrayList.get(1);
			service_number			= objArrayList.get(2);
			if ( status.equalsIgnoreCase("NOK") ) {
				isLoopTerminate	= false;
			}
			else if ( status.equalsIgnoreCase("Exception") ) {
				wlog(4, "Exception happened: " + customer_service_ref);
				isLoopTerminate	= false;
			}
			else if ( status.equalsIgnoreCase("OK")) {
				
			}
			this.waitForLocator(By.cssSelector("#menu-bottom > div:nth-child(2) > a.selected")).click();
			this.sleep(randomWithRange(internalMin_websiteSyncEmersionIdList, internalMax_websiteSyncEmersionIdList));
		}
   		wlog(4,"==================================================");
	}
    
    
    private String getPaginationCSS(int loopCount,int loopCountInAll) {
    	String result 		= "";
    	String text 		= "";
    	boolean isPresent	= false;
    	
    	
    	for (int i = 1; i <= 16; i++) {
    		result = ".pagination-pages > a:nth-child(" + i + ")";
	    	isPresent = driver.findElements(By.cssSelector(result)).size() > 0;
	    	if (isPresent) {
	    		text = driver.findElement(By.cssSelector(result)).getText();
	    		if (text.equalsIgnoreCase(">")) {
	    			return result;
	    		}
	    	}
    	}
    	
    	return "";
    }
    
    
	public static String toDatabaseTableDate(String date) {
		String convertedDate = "";
		DateFormat format1 = new SimpleDateFormat("d MMMM yyyy");
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = format1.parse(date);
			convertedDate = format2.format(date1);
		}
		catch (Exception e) {
    		
		}	
		return convertedDate;
	}
	
	
	public static String toTitleCase(String givenString) {

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
    
	
	public void sendGmail_websiteICTManagerSync(String content) {
     	 
		final String username = "jerry.xu@novatel.com.au";
		final String password = "xgp_950254";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply@netcube.com.au"));
			
			String[] recipients = {	"jerry.xu@novatel.com.au", 
									"boson.huang@novatel.com.au", 
									"mujeeb.formoli@novatel.com.au", 
									"kane.yang@novatel.com.au"};
			//String[] recipients = {"jerry.xu@novatel.com.au"};
			javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++)
			{
			    addressTo[i] = new javax.mail.internet.InternetAddress(recipients[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.TO, addressTo); 
			
			message.setSubject(content);
			message.setText(content);
 
			Transport.send(message);
		} 
		catch (MessagingException e) {
			String logFileContent = "\n" + (new Date()).toString();
    		logFileContent = logFileContent + "\n" + "    " + Arrays.toString(e.getStackTrace());
    		wlog(1, logFileContent);
		}
	}
    
	
	public void sendGmail_taskCheckForMaxCalls(String subject, String content) {
    	 
		final String username = "jerry.xu@novatel.com.au";
		final String password = "xgp_950254";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply@netcube.com.au"));
			
			/*		
			String[] recipients = {
				"jerry.xu@novatel.com.au"
			}; */
			/*		*/
			String[] recipients =	{
				"jerry.xu@novatel.com.au", 
				"dan.cheng@novatel.com.au",
				"marshall.ma@novatel.com.au",
				"Sorn.Krishanachinda@novatel.com.au",
				"hongbin.zhang@novatel.com.au",
				"mujeeb.formoli@novatel.com.au",
				"noc@novatel.com.au"
			};
			javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++)
			{
			    addressTo[i] = new javax.mail.internet.InternetAddress(recipients[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.TO, addressTo); 
			message.setContent(content, "text/html; charset=utf-8");
			message.setSubject(subject);
 
			Transport.send(message);
		} 
		catch (MessagingException e) {
			String logFileContent = "\n" + (new Date()).toString();
    		logFileContent = logFileContent + "\n" + "    " + Arrays.toString(e.getStackTrace());
    		wlog(3, logFileContent);
		}
	}
	
    	
	public static void main(String[] args) {
		 
 		DBNetCubeHub obj_DBNetCubeHub 	= new DBNetCubeHub();
 		DBICTManager obj_DBICTManager	= new DBICTManager();
 		javaSimulatorSet obj 			= new javaSimulatorSet();	
    	obj.getDatabaseSetting();
		Date date = null;
		long diff = 0;
		
		while ( true ) {
			try {
				if (obj.enabled_taskCheckForMaxCalls) {
					date = new Date();
					diff = date.getTime() - obj.date_taskCheckForMaxCalls.getTime() - obj.interval_taskCheckForMaxCalls * 1000;
					if (diff > 0) {
						obj.wlog(0, date.toString() + " taskCheckForMaxCalls since " + obj.date_taskCheckForMaxCalls.toString());// + ": " + diff + "/" + taskCheckForMaxCalls);
						obj.cal = Calendar.getInstance();
						//obj.cal.set(Calendar.SECOND,0);
						obj.cal.set(Calendar.MILLISECOND,0);
						obj.date_taskCheckForMaxCalls = obj.cal.getTime();
						DBNetCubeHub.resetLastExecutedTime("taskCheckForMaxCalls", obj.date_taskCheckForMaxCalls);
						obj.func_taskCheckForMaxCalls();
					}
				}
				
				if (obj.enabled_websiteICTManagerSync) {
					date = new Date();
					diff = date.getTime() - obj.date_websiteICTManagerSync.getTime() - obj.interval_websiteICTManagerSync * 1000;
					if (diff > 0) {
						obj.wlog(0, date.toString() + " websiteICTManagerSync since " + obj.date_websiteICTManagerSync.toString());// + ": " + diff + "/" + obj.interval_websiteICTManagerSync);
						obj.cal = Calendar.getInstance();
						//obj.cal.set(Calendar.SECOND,0);
						obj.cal.set(Calendar.MILLISECOND,0);
						obj.date_websiteICTManagerSync = obj.cal.getTime();
						DBNetCubeHub.resetLastExecutedTime("websiteICTManagerSync", obj.date_websiteICTManagerSync);
						obj.func_websiteICTManagerSync();
					}
				}
				
				if (obj.enabled_taskDueDiligenceSync) {
					date = new Date();
					diff = date.getTime() - obj.date_taskDueDiligenceSync.getTime() - obj.interval_taskDueDiligenceSync * 1000;
					if (diff > 0) {
						//obj.wlog(0, date.toString() + " taskDueDiligenceSync since " + obj.date_taskDueDiligenceSync.toString());// + ": " + diff + "/" + obj.interval_taskDueDiligenceSync);
						obj.cal = Calendar.getInstance();
						//obj.cal.set(Calendar.SECOND,0);
						obj.cal.set(Calendar.MILLISECOND,0);
						obj.date_taskDueDiligenceSync = obj.cal.getTime();
						DBNetCubeHub.resetLastExecutedTime("taskDueDiligenceSync", obj.date_taskDueDiligenceSync);
						obj.func_taskDueDiligenceSync();
					}
				}
				
				if (obj.enabled_websiteSyncEmersionIdList) {
					date = new Date();
					diff = date.getTime() - obj.date_websiteSyncEmersionIdList.getTime() - obj.interval_websiteSyncEmersionIdList * 1000;
					if (diff > 0) {
						obj.wlog(0, date.toString() + " websiteSyncEmersionIdList since " + obj.date_websiteSyncEmersionIdList.toString());// + ": " + diff + "/" + obj.interval_websiteSyncEmersionIdList);
						obj.cal = Calendar.getInstance();
						obj.cal.set(Calendar.HOUR_OF_DAY,6);
						obj.cal.set(Calendar.MINUTE,30);
						obj.cal.set(Calendar.SECOND,0);
						obj.cal.set(Calendar.MILLISECOND,0);
						obj.date_websiteSyncEmersionIdList = obj.cal.getTime();
						DBNetCubeHub.resetLastExecutedTime("websiteSyncBlisstelEmersionList", obj.date_websiteSyncEmersionIdList);
						obj.websiteSyncBlisstelEmersionList();
					}
				}
								
			    obj.sleep(internalBetweenLoop);
			}
			catch (Exception e) {
	    		obj.wlog(0, "Function main() error: " + Arrays.toString(e.getStackTrace()));
			}
			
	    }
	}

}