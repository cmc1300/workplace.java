package com.global.netcubeBackend;

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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class netcubeBackend extends JFrame implements ActionListener {

	public WebDriver driver = null;
	
	private static int internalWaitTime = 180;  
	private static int internalMin = 2;
	private static int internalMax = 2;
	private static int internalBetweenLoop = 1;	
	private static int internalFor500Customers = 60;	
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Calendar cal = null;
	private int numEmersionPerPage = 100;
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
	
	private static int internalWaitTime_websiteFetchEchoSignContract = 300;  
	private Date date_websiteFetchEchoSignContract;
	private int interval_websiteFetchEchoSignContract;
	private boolean enabled_websiteFetchEchoSignContract = false;
	
	private Date date_websiteSyncEmersionIdList;
	private int interval_websiteSyncEmersionIdList;
	private boolean enabled_websiteSyncEmersionIdList = false;
	public String LoginUsernameSyncEmersionIdList = "indika.kumara@retail.netcube.com.au";
	public String LoginPasswordSyncEmersionIdList = "Welcome1";
	
	private Date date_javaPCTempFilesCleanup;
	private int interval_javaPCTempFilesCleanup;
	private boolean enabled_javaPCTempFilesCleanup 	= false;
	private String javaPCTempFilesHomeFolder		= "C:\\Users\\Administrator\\AppData\\Local\\Temp";
	private int toBeRemoved_javaPCTempFilesCleanup 	= 3600;  
	
	private Date date_websiteSyncEmersionPayment;
	private int interval_websiteSyncEmersionPayment;
	private boolean enabled_websiteSyncEmersionPayment = false;
	
	private Date date_javaHooplaMonitorUpdate;
	private int interval_javaHooplaMonitorUpdate;
	private boolean enabled_javaHooplaMonitorUpdate = false;
	private int 	save_previous_month_year		= 0;
	private String 	save_previous_month_month		= "";
	private String 	save_previous_month_name		= "";
	private String 	save_current_year_month_date	= "";
	private Map<String, HooplaAgent> list_of_agents = new HashMap<String, HooplaAgent>();
	private static int LEVEL_MONTHLY_UPDATE	= 1;
	private static int LEVEL_DAILY_UPDATE 	= 2;
	private static int LEVEL_HOURLY_UPDATE 	= 3;
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	 
	   /** Constructor to setup GUI components and event handling */
	   public netcubeBackend () {
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
		      check1.setEnabled(false);
		      //check1.setSize(new Dimension(30, 30));
		      panelInfo1.add(check1);
		      lblCount1 = new Label("Fetch Signed Echo-Sign Contracts");  // construct Label
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
		      lblCount2 = new Label("Synchronize Emersion Customer List");  // construct Label
		      panelInfo2.add(lblCount2);                    // "super" Frame adds Label
		      tfCount2 = new TextField("0", 4); // construct TextField
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
		      lblCount3 = new Label("PC Disk Cleanup of Temporary Files");  // construct Label
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
		      lblCount4 = new Label("Synchronize Emersion Customer Payment");  // construct Label
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
		      lblCount5 = new Label("Hoopla Monitor Update");  // construct Label
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
	        		enabled_websiteFetchEchoSignContract = true;
	        	}
	        	else {
	        		enabled_websiteFetchEchoSignContract = false;
	        	}
	        	if(check2.isSelected() ){
	        		enabled_websiteSyncEmersionIdList = true;
	        	}
	        	else {
	        		enabled_websiteSyncEmersionIdList = false;
	        	}
	        	if(check3.isSelected() ){
	        		enabled_javaPCTempFilesCleanup = true;
	        	}
	        	else {
	        		enabled_javaPCTempFilesCleanup = false;
	        	}
	        	if(check4.isSelected() ){
	        		enabled_websiteSyncEmersionPayment = true;
	        	}
	        	else {
	        		enabled_websiteSyncEmersionPayment = false;
	        	}
	        	if(check5.isSelected() ){
	        		enabled_javaHooplaMonitorUpdate = true;
	        	}
	        	else {
	        		enabled_javaHooplaMonitorUpdate = false;
	        	}
	        	JOptionPane.showMessageDialog(null, "New configuration has been successfully applied");
	        	btnApplyConfiguration.setEnabled(false);
	        }
	      });
	 
	      setTitle("NetCube-Hub Backend System");  // "super" Frame sets title
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
		boolean flag = new WebDriverWait(driver, internalWaitTime).until(new ExpectedCondition<Boolean>() {
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

    
    public void sendGmailWebsiteFetchEchoSignContract(String subject, String content) {
    	 
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
			/*		*/
			String[] recipients = {
									"jerry.xu@novatel.com.au", 
									"boson.huang@novatel.com.au", 
									"lucy@novatel.com.au", 
									"kane.yang@novatel.com.au",
									"marshall.ma@novatel.com.au",
									"sales-callback@netcube.com.au"
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
    		logFileContent = logFileContent + "\n" + "\t" + Arrays.toString(e.getStackTrace());
    		wlog(1, logFileContent);
		}
	}
    
    
    private void getDatabaseSetting(){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	ArrayList<String> objArrayList = null;
		
        try {
        	
        	objArrayList = DBNetcubeBackend.readTaskParametersFromDatabse("websiteFetchEchoSignContract");
        	if ( objArrayList != null ) {
        		interval_websiteFetchEchoSignContract = Integer.parseInt(objArrayList.get(2));
        		date_websiteFetchEchoSignContract = dateFormat.parse(objArrayList.get(3));
        		check1.setEnabled(true);
        		wlog(1, "websiteFetchEchoSignContract interval is " + interval_websiteFetchEchoSignContract + ", last execution time is " + date_websiteFetchEchoSignContract);
        	}
        	else {
        		check1.setEnabled(false);
        	}
        	
        	objArrayList = DBNetcubeBackend.readTaskParametersFromDatabse("websiteSyncEmersionIdList");
        	if ( objArrayList != null ) {
        		interval_websiteSyncEmersionIdList = Integer.parseInt(objArrayList.get(2));
        		date_websiteSyncEmersionIdList = dateFormat.parse(objArrayList.get(3));
        		check2.setEnabled(true);
        		wlog(2, "websiteSyncEmersionIdList interval is " + interval_websiteSyncEmersionIdList + ", last execution time is " + date_websiteSyncEmersionIdList);
        	}
        	else {
        		check2.setEnabled(false);
        	}
        	
        	objArrayList = DBNetcubeBackend.readTaskParametersFromDatabse("javaPCTempFilesCleanup");
        	if ( objArrayList != null ) {
        		interval_javaPCTempFilesCleanup = Integer.parseInt(objArrayList.get(2));
        		date_javaPCTempFilesCleanup = dateFormat.parse(objArrayList.get(3));
        		check3.setEnabled(true);
        		wlog(3, "javaPCTempFilesCleanup interval is " + interval_javaPCTempFilesCleanup + ", last execution time is " + date_javaPCTempFilesCleanup);
        	}
        	else {
        		check3.setEnabled(false);
        	}
        	
        	objArrayList = DBNetcubeBackend.readTaskParametersFromDatabse("websiteSyncEmersionPayment");
        	if ( objArrayList != null ) {
        		interval_websiteSyncEmersionPayment = Integer.parseInt(objArrayList.get(2));
        		date_websiteSyncEmersionPayment = dateFormat.parse(objArrayList.get(3));
        		check4.setEnabled(true);
        		wlog(4, "websiteSyncEmersionPayment interval is " + interval_websiteSyncEmersionPayment + ", last execution time is " + date_websiteSyncEmersionPayment);
        	}
        	else {
        		check4.setEnabled(false);
        	}
        	
        	objArrayList = DBNetcubeBackend.readTaskParametersFromDatabse("javaHooplaMonitorUpdate");
        	if ( objArrayList != null ) {
        		interval_javaHooplaMonitorUpdate = Integer.parseInt(objArrayList.get(2));
        		date_javaHooplaMonitorUpdate = dateFormat.parse(objArrayList.get(3));
        		check5.setEnabled(true);
        		wlog(5, "javaHooplaMonitorUpdate interval is " + interval_javaHooplaMonitorUpdate + ", last execution time is " + date_javaHooplaMonitorUpdate);
        		DBNetcubeBackend.init_javaHooplaMonitorUpdate(list_of_agents);
        		//wlog(5, "\n" + list_of_agents.toString());
        		
        		Iterator entries = list_of_agents.entrySet().iterator();
        		while (entries.hasNext()) {
        		  Entry thisEntry = (Entry) entries.next();
        		  Object key = thisEntry.getKey();
        		  Object value = thisEntry.getValue();
        		  //wlog(5, value.toString());
        		}
        	}
        	else {
        		check5.setEnabled(false);
        		wlog(5, "javaHooplaMonitorUpdate initialization failed");
        	}
        }
        catch (ParseException ex)  {
        }
	}
    
    
    private void websiteFetchEchoSignContract() {
    	WebElement WebComponent 		= null;	
    	String logFileContent 			= "";
    	int waitLoopCount 				= 0;
    	long diff 						= 0;
    	Boolean isPresent 				= false;
    	Iterator<JSONObject> iterator	= null;
    	int tableLoopCount				= 0;
    	int statisticsCount				= 0;
    	JSONObject loopObject			= null;
    	DateFormat dateFormat 			= new SimpleDateFormat("yyyy-MMM-dd");
    	String string_OUT_FOR_SIGNATURE	= "";
    	String table_OUT_FOR_SIGNATURE	= "";
    	String string_OUT_FOR_APPROVAL 	= "";
    	String table_OUT_FOR_APPROVAL 	= "";
    	String string_DCS_Signature		= "";
    	String table_DCS_Signature		= "";
    	String string_DCS_Approval		= "";
    	String table_DCS_Approval		= "";
    	
    	try {
    		logFileContent = "\n" + (new Date()).toString();
    		
    		this.driver = new FirefoxDriver();
    		this.sleep(randomWithRange(internalMin, internalMax));
        	this.driver.navigate().to("http://api.netcube.com.au/projects/netcube/echosign/getDocument.php");
    		this.sleep(randomWithRange(internalMin, internalMax));
    		//driver.manage().window().maximize();
    		
    		waitLoopCount = 0;
    		isPresent = driver.findElements(By.name("result")).size() > 0;
			while (!isPresent && waitLoopCount < internalWaitTime_websiteFetchEchoSignContract) {
				isPresent = driver.findElements(By.name("result")).size() > 0;
				waitLoopCount ++;
				this.sleep(1);
			}
			
			if (!isPresent) {
				WebComponent = this.driver.findElement(By.cssSelector("body"));
				logFileContent = WebComponent.getText().trim();
				wlog(1, "Adobe Page Body: " + logFileContent);        		
			}
			else if (isPresent) {
				WebComponent 					= this.driver.findElement(By.name("result"));
				logFileContent 					= WebComponent.getText().trim();
				JSONObject json 				= (JSONObject) (new JSONParser()).parse(logFileContent);
				String json_result 				= (String) json.get("result");
				
				if (json_result.equalsIgnoreCase("OK")) {
					JSONArray json_OUT_FOR_APPROVAL = (JSONArray) json.get("OUT_FOR_APPROVAL");
					string_OUT_FOR_APPROVAL 		= "Adobe Contracts waiting to be APPROVED";
					table_OUT_FOR_APPROVAL 			= 	"<h2>Adobe Contracts waiting to be APPROVED</h2>" +
														"<table style='width:100%' border='1' style='display: block'>" +
															"<tr>" +
																"<th>Index</th>" +
																"<th>orderNumber</th>" +
																"<th>customerId</th>" +
																"<th>fullName</th>" +
																"<th>email</th>" +
																"<th>dealer</th>" +
																"<th>documentCategory</th>" +
																"<th>documentStatus</th>" +
																//"<th>documentKey</th>" +
															"</tr>";
					iterator 						= json_OUT_FOR_APPROVAL.iterator();
					tableLoopCount					= 0;
					while (iterator.hasNext()) {
						loopObject					= iterator.next();
						tableLoopCount ++;
						statisticsCount ++;
						
						string_OUT_FOR_APPROVAL 	= string_OUT_FOR_APPROVAL + "\n" + loopObject.toString();
						String orderNumber			= (String) loopObject.get("orderNumber");
						String customerId			= (String) loopObject.get("customerId");
						String fullName				= (String) loopObject.get("fullName");
						String email				= (String) loopObject.get("email");
						String dealer				= (String) loopObject.get("dealer");
						String documentCategory		= (String) loopObject.get("documentCategory");
						String documentStatus		= (String) loopObject.get("documentStatus");
						String documentKey			= (String) loopObject.get("documentKey");
							
						table_OUT_FOR_APPROVAL		+=	"<tr>" +
															"<td>" + tableLoopCount + "</td>" +
															"<td>" + orderNumber + "</td>" +
															"<td>" + customerId + "</td>" +
															"<td>" + fullName + "</td>" +
															"<td>" + email + "</td>" +
															"<td>" + dealer + "</td>" +
															"<td>" + documentCategory + "</td>" +
															"<td>" + documentStatus + "</td>" +
															//"<td>" + documentKey + "</td>" +
														"</tr>";
					}
					table_OUT_FOR_APPROVAL += "</table>";
					
					
					JSONArray json_OUT_FOR_SIGNATURE= (JSONArray) json.get("OUT_FOR_SIGNATURE");
					string_OUT_FOR_SIGNATURE		= "Adobe Contracts waiting to be SIGNED";
					table_OUT_FOR_SIGNATURE 		= 	"<h2>Adobe Contracts waiting to be SIGNED</h2>" +
														"<table style='width:100%' border='1' style='display: block'>" +
															"<tr>" +
																"<th>Index</th>" +
																"<th>orderNumber</th>" +
																"<th>customerId</th>" +
																"<th>fullName</th>" +
																"<th>email</th>" +
																"<th>dealer</th>" +
																"<th>documentCategory</th>" +
																"<th>documentStatus</th>" +
																//"<th>documentKey</th>" +
															"</tr>";
					iterator 						= json_OUT_FOR_SIGNATURE.iterator();
					tableLoopCount					= 0;
					while (iterator.hasNext()) {
						loopObject					= iterator.next();
						tableLoopCount ++;
						statisticsCount ++;
						
						string_OUT_FOR_SIGNATURE 	= string_OUT_FOR_SIGNATURE + "\n" + loopObject.toString();
						String orderNumber			= (String) loopObject.get("orderNumber");
						String customerId			= (String) loopObject.get("customerId");
						String fullName				= (String) loopObject.get("fullName");
						String email				= (String) loopObject.get("email");
						String dealer				= (String) loopObject.get("dealer");
						String documentCategory		= (String) loopObject.get("documentCategory");
						String documentStatus		= (String) loopObject.get("documentStatus");
						String documentKey			= (String) loopObject.get("documentKey");
							
						table_OUT_FOR_SIGNATURE +=	"<tr>" +
														"<td>" + tableLoopCount + "</td>" +
														"<td>" + orderNumber + "</td>" +
														"<td>" + customerId + "</td>" +
														"<td>" + fullName + "</td>" +
														"<td>" + email + "</td>" +
														"<td>" + dealer + "</td>" +
														"<td>" + documentCategory + "</td>" +
														"<td>" + documentStatus + "</td>" +
														//"<td>" + documentKey + "</td>" +
													"</tr>";
					}
					table_OUT_FOR_SIGNATURE += "</table>";
				}
			}
			
			
    		this.sleep(randomWithRange(internalMin, internalMax));
			if (this.driver != null) {
    			this.driver.close();
    			this.driver = null;
    		}
			this.driver = new FirefoxDriver();
    		this.sleep(randomWithRange(internalMin, internalMax));
        	this.driver.navigate().to("http://api.netcube.com.au/projects/netcube/docusign/getDocument.php");
    		this.sleep(randomWithRange(internalMin, internalMax));
    		
    		waitLoopCount = 0;
    		isPresent = driver.findElements(By.name("result")).size() > 0;
			while (!isPresent && waitLoopCount < internalWaitTime_websiteFetchEchoSignContract) {
				isPresent = driver.findElements(By.name("result")).size() > 0;
				waitLoopCount ++;
				this.sleep(1);
			}
			
			if (!isPresent) {
				WebComponent = this.driver.findElement(By.cssSelector("body"));
				logFileContent = WebComponent.getText().trim();
				wlog(1, "DocuSign Page Body: " + logFileContent);        		
			}
			else if (isPresent) {
				WebComponent 					= this.driver.findElement(By.name("result"));
				logFileContent 					= WebComponent.getText().trim();
				JSONObject json 				= (JSONObject) (new JSONParser()).parse(logFileContent);
				String json_result 				= (String) json.get("result");
			
				if (json_result.equalsIgnoreCase("OK")) {
					JSONArray json_DCS_Approval = (JSONArray) json.get("DCS_Approval");
					string_DCS_Approval 		= "DocuSign Contracts waiting to be APPROVED";
					table_DCS_Approval 			= 	"<h2>DocuSign Contracts waiting to be APPROVED</h2>" +
													"<table style='width:100%' border='1' style='display: block'>" +
														"<tr>" +
															"<th>Index</th>" +
															"<th>orderNumber</th>" +
															"<th>customerId</th>" +
															"<th>fullName</th>" +
															"<th>email</th>" +
															"<th>dealer</th>" +
															"<th>documentCategory</th>" +
															"<th>documentStatus</th>" +
															//"<th>documentKey</th>" +
														"</tr>";
					if (json_DCS_Approval != null) {
						iterator 					= json_DCS_Approval.iterator();
						tableLoopCount				= 0;
						while (iterator.hasNext()) {
							loopObject				= iterator.next();
							tableLoopCount ++;
							statisticsCount ++;
							
							string_DCS_Approval 	= string_DCS_Approval + "\n" + loopObject.toString();
							String orderNumber		= (String) loopObject.get("orderNumber");
							String customerId		= (String) loopObject.get("customerId");
							String fullName			= (String) loopObject.get("fullName");
							String email			= (String) loopObject.get("email");
							String dealer			= (String) loopObject.get("dealer");
							String documentCategory	= (String) loopObject.get("documentCategory");
							String documentStatus	= (String) loopObject.get("documentStatus");
							String documentKey		= (String) loopObject.get("documentKey");
								
							table_DCS_Approval		+=	"<tr>" +
															"<td>" + tableLoopCount + "</td>" +
															"<td>" + orderNumber + "</td>" +
															"<td>" + customerId + "</td>" +
															"<td>" + fullName + "</td>" +
															"<td>" + email + "</td>" +
															"<td>" + dealer + "</td>" +
															"<td>" + documentCategory + "</td>" +
															"<td>" + documentStatus + "</td>" +
															//"<td>" + documentKey + "</td>" +
														"</tr>";
						}
					}
					table_DCS_Approval += "</table>";
					
					JSONArray json_DCS_Signature= (JSONArray) json.get("DCS_Signature");
					string_DCS_Signature		= "DocuSign Contracts waiting to be SIGNED";
					table_DCS_Signature	 		= 	"<h2>DocuSign Contracts waiting to be SIGNED</h2>" +
													"<table style='width:100%' border='1' style='display: block'>" +
														"<tr>" +
															"<th>Index</th>" +
															"<th>orderNumber</th>" +
															"<th>customerId</th>" +
															"<th>fullName</th>" +
															"<th>email</th>" +
															"<th>dealer</th>" +
															"<th>documentCategory</th>" +
															"<th>documentStatus</th>" +
															//"<th>documentKey</th>" +
														"</tr>";
					if (json_DCS_Signature != null) {
						iterator 					= json_DCS_Signature.iterator();
						tableLoopCount				= 0;
						while (iterator.hasNext()) {
							loopObject				= iterator.next();
							tableLoopCount ++;
							statisticsCount ++;
							
							string_DCS_Signature	= string_DCS_Signature + "\n" + loopObject.toString();
							String orderNumber		= (String) loopObject.get("orderNumber");
							String customerId		= (String) loopObject.get("customerId");
							String fullName			= (String) loopObject.get("fullName");
							String email			= (String) loopObject.get("email");
							String dealer			= (String) loopObject.get("dealer");
							String documentCategory	= (String) loopObject.get("documentCategory");
							String documentStatus	= (String) loopObject.get("documentStatus");
							String documentKey		= (String) loopObject.get("documentKey");
								
							table_DCS_Signature		+=	"<tr>" +
															"<td>" + tableLoopCount + "</td>" +
															"<td>" + orderNumber + "</td>" +
															"<td>" + customerId + "</td>" +
															"<td>" + fullName + "</td>" +
															"<td>" + email + "</td>" +
															"<td>" + dealer + "</td>" +
															"<td>" + documentCategory + "</td>" +
															"<td>" + documentStatus + "</td>" +
															//"<td>" + documentKey + "</td>" +
														"</tr>";
						}
					}
					table_DCS_Signature			+= "</table>";
				}
			}
			
			
			tfCount1.setText("" + statisticsCount);
 			wlog(1, "\n" + (new Date()).toString() + 
 					"\n" + string_DCS_Signature + 
 					"\n" + string_OUT_FOR_SIGNATURE +
 					"\n" + string_DCS_Approval + 
 					"\n" + string_OUT_FOR_APPROVAL);
 			ArrayList<String> arrayListTask = DBNetcubeBackend.getTableFieldFromDatabse("websiteFetchEchoSignContract", "planLastEmailSentTime");
    		if (arrayListTask != null) {
    			wlog(1, "Function websiteFetchEchoSignContract() planLastEmailSentTime: " + arrayListTask.toString());
    			Date date = formatter.parse(arrayListTask.get(1));
    			diff = ((new Date()).getTime() - formatter.parse(arrayListTask.get(1)).getTime()) / 1000;
    		}
    		else {
    			wlog(1, "Function websiteFetchEchoSignContract() planLastEmailSentTime: null");
    		}
    		if (arrayListTask == null || diff > 24*60*60) {
    			Calendar cal = Calendar.getInstance();
	    		cal.set(Calendar.HOUR_OF_DAY,9);
				cal.set(Calendar.MINUTE,0);
				cal.set(Calendar.SECOND,0);
				cal.set(Calendar.MILLISECOND,0);
				DBNetcubeBackend.setTableFieldFromDatabse("websiteFetchEchoSignContract", "planLastEmailSentTime", cal.getTime());
    	    	sendGmailWebsiteFetchEchoSignContract(	dateFormat.format(new Date()) + ": Online order contracts which have not been signed or approved", 
    	    											table_DCS_Signature + table_OUT_FOR_SIGNATURE + table_DCS_Approval + table_OUT_FOR_APPROVAL);
    		}
    	} 
    	catch (Exception e) {
    		//e.printStackTrace();
    		logFileContent = logFileContent + "\n" + "\t" + Arrays.toString(e.getStackTrace());
    		wlog(1, logFileContent);
    	} 
    	finally{
     		this.sleep(randomWithRange(internalMin, internalMax));
    		if (this.driver != null) {
    			this.driver.close();
    			this.driver = null;
    		}
    	}
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
	
	
	private String getPaginationCSS(int loopCount,int loopCountInAll) {
    	String result 		= "";
    	String text 		= "";
    	boolean isPresent	= false;
    	
    	
    	for (int i = 1; i <= 20; i++) {
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
	    
	private void websiteSyncEmersionIdListV2() {
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
    	String pagination		= "";
    	String[] parts 			= null;
    	String id = "", internalName = "", externalName = "", periodLength = "", periodsInAdvance = "", accessFee = "", taxType = "";
    	String buyOrSell = "", status = "", servicePlanCount = "", saleableInPortal = "", createdDate = "", saleableFrom = "";
    	String latestPackageID	= "", latestServiceID = "";
    	String serviceType = "", serviceTypeCategory = "", serviceTypeBillingType = "";
    	Boolean packageFound	= false;
    	Boolean serviceFound	= false;
    	Boolean notSyncAllItems	= true;
    	String resultUpdateRelatedTables = "";
    	
    	try {
			wlog(2,"\n" + (new Date()).toString());
    		logFileContent = "\n" + (new Date()).toString();
    		
    		this.driver = new FirefoxDriver();
    		this.sleep(randomWithRange(internalMin, internalMax));
        	this.driver.navigate().to("https://cumulus.emersion.com.au/customer/account/list");
    		this.sleep(randomWithRange(internalMin, internalMax));
    		this.waitForLocator(By.id("username")).sendKeys(LoginUsernameSyncEmersionIdList);
    		this.sleep(randomWithRange(internalMin, internalMax));
			this.waitForLocator(By.id("password")).sendKeys(LoginPasswordSyncEmersionIdList);
			this.sleep(randomWithRange(internalMin, internalMax));
			this.waitForLocator(By.cssSelector(".button > input:nth-child(1)")).click();	
			this.sleep(randomWithRange(internalMin, internalMax));

			
			try {
				this.waitForLocator(By.cssSelector("#menu-top > div:nth-child(5)")).click();	
				this.sleep(randomWithRange(internalMin, internalMax));
				this.waitForLocator(By.cssSelector("#menu-bottom > div:nth-child(5) > a:nth-child(1)"));
				this.sleep(randomWithRange(internalMin, internalMax));
				this.waitForLocator(By.cssSelector("#menu-bottom > div:nth-child(5) > a:nth-child(1)")).click();	
				this.sleep(randomWithRange(internalMin, internalMax));
				this.waitForLocator(By.cssSelector("#content > div > div.tabs > ol > li.current > a"));
				
				
				latestPackageID = DBNetcubeBackend.websiteSyncEmersionIdListV2GetLatestRecord("em_packagePlan");
				id = driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(1) > td:nth-child(1) > div")).getText();
				if (notSyncAllItems && latestPackageID.equals(id)) {
					packageFound 		= true;
				}
				else {
					packageFound		= false;
				}
				
				loopCount = 1;
				while (!packageFound && loopCount <= 20) {
					id 					= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(1) > div")).getText();
					internalName 		= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(2)")).getText();
					externalName 		= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(3)")).getText();
					periodLength 		= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(4)")).getText();
					periodsInAdvance 	= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(5)")).getText();
					accessFee 			= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(6)")).getText();
					taxType 			= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(7)")).getText();
					buyOrSell 			= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(8)")).getText();
					status 				= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(9)")).getText();
					servicePlanCount 	= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(13)")).getText();
					saleableInPortal 	= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(10)")).getText();
					createdDate 		= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(11)")).getText();
					saleableFrom 		= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(12)")).getText();
					createdDate 		= toDatabaseTableDate(createdDate);
					saleableFrom 		= toDatabaseTableDate(saleableFrom);
					
					if (notSyncAllItems && latestPackageID.equals(id)) {
						packageFound 	= true;
					}
					else {
						/*		*/
						DBNetcubeBackend.websiteSyncEmersionIdListV2InsertNewPackage (id, internalName, externalName, 
								periodLength, periodsInAdvance, accessFee, 
								taxType, buyOrSell, status, 
								servicePlanCount, saleableInPortal, createdDate, saleableFrom);
						wlog(2,"New package plan: " + id + "/" + internalName + "/" + externalName 
								+ "/" + periodLength + "/" + periodsInAdvance + "/" + accessFee 
								+ "/" + taxType + "/" + buyOrSell + "/" + status 
								+ "/" + servicePlanCount + "/" + saleableInPortal + "/" + createdDate + "/" + saleableFrom);
						loopCount ++;
					}
				}
				
				loopCountInAll = 1;
				while (!packageFound && loopCountInAll <= 100) {
					css = getPaginationCSS(loopCount, loopCountInAll);
					if (css.isEmpty()) {
						break;
					}
					wlog(2, "Package page " + loopCount + " / " + css + ": " + driver.findElement(By.cssSelector(css)).getText());
					this.waitForLocator(By.cssSelector(css)).click();	
					this.sleep(randomWithRange(internalMin, internalMax));
					loopCountInAll ++;
					this.waitForLocator(By.cssSelector("#tab_content > div > div.listtable > div > div > div.pagination-pages"));
										
					id = driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(1) > td:nth-child(1) > div")).getText();
					if (notSyncAllItems && latestPackageID.equals(id)) {
						packageFound 		= true;
					}
					else {
						packageFound		= false;
					}
					
					loopCount = 1;
					while (!packageFound && loopCount <= 20) {
						id 					= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(1) > div")).getText();
						internalName 		= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(2)")).getText();
						externalName 		= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(3)")).getText();
						periodLength 		= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(4)")).getText();
						periodsInAdvance 	= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(5)")).getText();
						accessFee 			= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(6)")).getText();
						taxType 			= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(7)")).getText();
						buyOrSell 			= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(8)")).getText();
						status 				= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(9)")).getText();
						servicePlanCount 	= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(13)")).getText();
						saleableInPortal 	= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(10)")).getText();
						createdDate 		= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(11)")).getText();
						saleableFrom 		= driver.findElement(By.cssSelector("#package > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(12)")).getText();
						createdDate 		= toDatabaseTableDate(createdDate);
						saleableFrom 		= toDatabaseTableDate(saleableFrom);
						
						if (notSyncAllItems && latestPackageID.equals(id)) {
							packageFound = true;
						}
						else {
							/*		*/
							DBNetcubeBackend.websiteSyncEmersionIdListV2InsertNewPackage (id, internalName, externalName, 
									periodLength, periodsInAdvance, accessFee, 
									taxType, buyOrSell, status, 
									servicePlanCount, saleableInPortal, createdDate, saleableFrom);
							wlog(2,"New package plan: " + id + "/" + internalName + "/" + externalName 
									+ "/" + periodLength + "/" + periodsInAdvance + "/" + accessFee 
									+ "/" + taxType + "/" + buyOrSell + "/" + status 
									+ "/" + servicePlanCount + "/" + saleableInPortal + "/" + createdDate + "/" + saleableFrom);
							loopCount ++;
						}
					}
				}
				
				
				this.sleep(randomWithRange(internalMin, internalMax));
				this.waitForLocator(By.cssSelector("#content > div > div.tabs > ol > li:nth-child(2) > a")).click();	
				this.sleep(randomWithRange(internalMin, internalMax));
				this.waitForLocator(By.cssSelector("#servicePlan > tbody > tr:nth-child(1) > td:nth-child(1) > div > a"));
				
				latestServiceID = DBNetcubeBackend.websiteSyncEmersionIdListV2GetLatestRecord("em_servicePlan");
				id = driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(1) > td:nth-child(1) > div > a")).getText();
				if (notSyncAllItems && latestServiceID.equals(id)) {
					serviceFound 		= true;
				}
				else {
					serviceFound		= false;
				}
				
				loopCount = 1;
				while (!serviceFound && loopCount <= 20) {
					id 					= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(1) > div")).getText();
					internalName 		= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(2)")).getText();
					externalName 		= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(3)")).getText();
					serviceType		 	= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(4)")).getText();
					serviceTypeCategory	= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(5)")).getText();
					serviceTypeBillingType = driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(6)")).getText();
					periodLength 		= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(7)")).getText();
					accessFee 			= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(8)")).getText();
					taxType 			= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(9)")).getText();
					buyOrSell 			= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(10)")).getText();
					status 				= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(11)")).getText();
					createdDate 		= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(12)")).getText();
					saleableFrom 		= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(13)")).getText();
					createdDate 		= toDatabaseTableDate(createdDate);
					saleableFrom 		= toDatabaseTableDate(saleableFrom);
					
					if (notSyncAllItems && latestServiceID.equals(id)) {
						serviceFound 	= true;
					}
					else {
						/*		*/
						DBNetcubeBackend.websiteSyncEmersionIdListV2InsertNewService (id, internalName, externalName, 
								serviceType, serviceTypeCategory, periodLength, 
								accessFee, taxType, buyOrSell, 
								status, createdDate, saleableFrom);
						wlog(2,"New service plan: " + id + "/" + internalName + "/" + externalName 
								+ "/" + serviceType + "/" + serviceTypeCategory + "/" + periodLength 
								+ "/" + accessFee + "/" + taxType + "/" + buyOrSell
								+ "/" + status + "/" + createdDate + "/" + saleableFrom);
						loopCount ++;
					}
				}
				
				loopCountInAll = 1;
				while (!serviceFound && loopCountInAll <= 100) {
					css = getPaginationCSS(loopCount, loopCountInAll);
					if (css.isEmpty()) {
						break;
					}
					wlog(2, "Service page " + loopCount + " / " + css + ": " + driver.findElement(By.cssSelector(css)).getText());
					this.waitForLocator(By.cssSelector(css)).click();	
					this.sleep(randomWithRange(internalMin, internalMax));
					loopCountInAll ++;
					this.waitForLocator(By.cssSelector("#tab_content > div > div.listtable > div > div > div.pagination-pages"));
					
					loopCount = 1;
					while (!serviceFound && loopCount <= 20) {
						id 					= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(1) > div")).getText();
						internalName 		= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(2)")).getText();
						externalName 		= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(3)")).getText();
						serviceType		 	= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(4)")).getText();
						serviceTypeCategory	= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(5)")).getText();			
						serviceTypeBillingType	= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(6)")).getText();	
						periodLength 		= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(7)")).getText();
						accessFee 			= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(8)")).getText();
						taxType 			= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(9)")).getText();
						buyOrSell 			= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(10)")).getText();
						status 				= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(11)")).getText();
						createdDate 		= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(12)")).getText();
						saleableFrom 		= driver.findElement(By.cssSelector("#servicePlan > tbody > tr:nth-child(" + loopCount + ") > td:nth-child(13)")).getText();
						createdDate 		= toDatabaseTableDate(createdDate);
						saleableFrom 		= toDatabaseTableDate(saleableFrom);
						
						if (notSyncAllItems && latestServiceID.equals(id)) {
							serviceFound 	= true;
						}
						else {
							/*		*/
							DBNetcubeBackend.websiteSyncEmersionIdListV2InsertNewService (id, internalName, externalName, 
									serviceType, serviceTypeCategory, periodLength, 
									accessFee, taxType, buyOrSell, 
									status, createdDate, saleableFrom);
							wlog(2,"New service plan: " + id + "/" + internalName + "/" + externalName 
									+ "/" + serviceType + "/" + serviceTypeCategory + "/" + periodLength 
									+ "/" + accessFee + "/" + taxType + "/" + buyOrSell
									+ "/" + status + "/" + createdDate + "/" + saleableFrom);
							loopCount ++;
						}
					}
				}
				
				this.sleep(randomWithRange(internalMin, internalMax));
			}
			catch (Exception e) {
	    		wlog(2, (new Date()).toString() + ": " + Arrays.toString(e.getStackTrace()));
	    		this.sleep(randomWithRange(internalMin, internalMax));
			}	
			
			
			loopCountInAll = 0;
			try {
				this.waitForLocator(By.id("searchform_search")).click();
				this.sleep(randomWithRange(internalMin, internalMax));
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
				this.sleep(randomWithRange(internalMin, internalMax));
				
				this.waitForLocator(By.id("accounts_filter_submit")).click();	
				this.waitForLocator(By.cssSelector(".pagination-rows > a:nth-child(1)"));
				this.sleep(randomWithRange(internalMin, internalMax));
				//this.waitForLocator(By.cssSelector(".pagination-rows > a:nth-child(4)")).click();	
				this.waitForLocator(By.cssSelector(".pagination-rows > a:nth-child(2)")).click();
				this.sleep(randomWithRange(internalMin, internalMax));
				this.waitForLocator(By.cssSelector(".pagination-pages"));
				pagination 		= driver.findElement(By.cssSelector(".pagination-pages")).getText();
				parts 			= pagination.trim().split(" ");
				loopCountInAll 	= Integer.parseInt(parts[parts.length - 2]);
				wlog(2,"pagination-pages: " + pagination + " ===> " + loopCountInAll);
				this.sleep(randomWithRange(internalMin, internalMax));
				
				WebComponent 	= driver.findElement(By.cssSelector("#content > div > div.listtable > span"));
				parts 			= WebComponent.getText().trim().split(" ");
				emersionCount 	= Integer.parseInt(parts[0].trim());
				
				WebComponent 	= driver.findElement(By.cssSelector(".pagination-pages > a:nth-child(6)"));
				this.waitForLocator(By.cssSelector(".pagination-pages > a:nth-child(6)")).click();	
				this.sleep(randomWithRange(internalMin, internalMax));
				this.waitForLocator(By.cssSelector(".pagination-pages"));
				pagination 		= driver.findElement(By.cssSelector(".pagination-pages")).getText();
				parts 			= pagination.trim().split(" ");
				loopCountInAll = Integer.parseInt(parts[parts.length - 1]);
			}
			catch (Exception e) {
	    		wlog(2, (new Date()).toString() + ": " + Arrays.toString(e.getStackTrace()));
			}
			finally {
				wlog(2,emersionCount + " emersion records found / " + loopCountInAll + " pages found");
				wlog(2,"==================================================");
				
				this.waitForLocator(By.id("searchform_search")).click();	
				this.sleep(randomWithRange(internalMin, internalMax));
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
				this.sleep(randomWithRange(internalMin, internalMax));
				
				this.waitForLocator(By.id("accounts_filter_submit")).click();	
				this.waitForLocator(By.cssSelector("#content > div > div.listtable > div > div > div.pagination-rows > a:nth-child(1)"));
				this.sleep(randomWithRange(internalMin, internalMax));
				//this.waitForLocator(By.cssSelector("#content > div > div.listtable > div > div > div.pagination-rows > a:nth-child(4)")).click();	
				this.waitForLocator(By.cssSelector("#content > div > div.listtable > div > div > div.pagination-rows > a:nth-child(2)")).click();	
				this.sleep(randomWithRange(internalMin, internalMax));
				this.waitForLocator(By.cssSelector(".pagination-pages"));
				this.sleep(randomWithRange(internalMin, internalMax));
			}
			
			//Database.resetWebsiteSyncEmersionIdList();
			intCount2 	= 0;
			loopCount	= 1;
			wlog(2,"Customer page scan loop count: " + loopCount + " / " + (new Date()).toString());
			this.sleep(randomWithRange(internalMin, internalMax));
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
				}
				resultUpdateRelatedTables	= DBNetcubeBackend.websiteSyncEmersionIdListTUpdateRelatedTables(accountId, accountStatus, accountName, accountPhone, accountMobile, accountEmail, accountCreated);
				if (!resultUpdateRelatedTables.isEmpty()) {
					wlog(2,"Emersion customer(" + accountId + "/" + accountName + "\n\t" + resultUpdateRelatedTables);
				}
				websiteSyncEmersionIdList 	= DBNetcubeBackend.websiteSyncEmersionIdList(accountId, accountStatus, accountName, accountEmail, accountCreated);
				if ( !websiteSyncEmersionIdList.isEmpty() ) {
					wlog(2,"Emersion customer(" + i + "/" + accountStatus + "/" + accountName + "): " + websiteSyncEmersionIdList);
				}
				else {
					//wlog(2,"Emersion customer(" + i + "/" + accountStatus + "/" + accountName + "): " + accountId);
				}
				intCount2 ++;
				tfCount2.setText("" + intCount2);
			}
			
			while (loopCount < loopCountInAll) {
				this.sleep(randomWithRange(internalMin, internalMax));
				css = getPaginationCSS(loopCount, loopCountInAll);
				if (css.isEmpty()) {
					break;
				}
				wlog(2, "Customer page " + loopCount + " / " + css + ": " + driver.findElement(By.cssSelector(css)).getText());
				this.waitForLocator(By.cssSelector(css)).click();	
				this.sleep(randomWithRange(internalMin, internalMax));
				this.waitForLocator(By.cssSelector(".pagination-pages"));
				wlog(2,"Customer page scan loop count: " + (loopCount + 1) + " / " + (new Date()).toString());
				this.sleep(randomWithRange(internalMin, internalMax));
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
						}
						resultUpdateRelatedTables	= DBNetcubeBackend.websiteSyncEmersionIdListTUpdateRelatedTables(accountId, accountStatus, accountName, accountPhone, accountMobile, accountEmail, accountCreated);
						if (!resultUpdateRelatedTables.isEmpty()) {
							wlog(2,"Emersion customer(" + accountId + "/" + accountName + "\n\t" + resultUpdateRelatedTables);
						}
						websiteSyncEmersionIdList	= DBNetcubeBackend.websiteSyncEmersionIdList(accountId, accountStatus, accountName, accountEmail, accountCreated);
						if ( !websiteSyncEmersionIdList.isEmpty() ) {
							wlog(2,"Emersion customer(" + (i + loopCount*numEmersionPerPage) + "/" + accountStatus + "/" + accountName + "): " + websiteSyncEmersionIdList);
						}
						else {
							//wlog(2,"Emersion customer(" + (i + loopCount*numEmersionPerPage) + "/" + accountStatus + "/" + accountName + "): " + accountId);
						}
					}
					intCount2 ++;
					tfCount2.setText("" + intCount2);
				}
				loopCount++;
			}
			
			websiteSyncEmersionIdList = DBNetcubeBackend.websiteSyncEmersionIdList("394423", "Active", "Boson Huang", "indika.kumara@netcube.com.au", "9 Jan 2013");
			wlog(2, websiteSyncEmersionIdList);
    		wlog(2, "==================================================");
			wlog(2, "Execute Emersion Customer Status Statistics: " + 
					DBNetcubeBackend.websiteSyncEmersionIdListTStatusStatistics() + "\n" + 
					"==================================================");
    	} 
    	catch (Exception e) {
    		//e.printStackTrace();
    		logFileContent = (new Date()).toString() + "\n" + "\t" + Arrays.toString(e.getStackTrace());
    		wlog(2, logFileContent);
    	} 
    	finally{
    		if (this.driver != null) {
    			this.sleep(randomWithRange(internalMin, internalMax));
    			this.waitForLocator(By.cssSelector(".header_info_right > a:nth-child(1) > strong:nth-child(1)")).click();	
    			this.sleep(randomWithRange(internalMin, internalMax));
    			this.driver.close();
    			this.driver = null;
    		}
    	}
	}
	
	
	private String rmdir(final File folder) {
		// check if folder file is a real folder
		String result = "";
		
		if (folder.isDirectory()) {
			File[] list = folder.listFiles();
			if (list != null) {
	              for (int i = 0; i < list.length; i++) {
	                  File tmpF = list[i];
	                  if (tmpF.isDirectory()) {
	                      rmdir(tmpF);
	                  }
	                  tmpF.delete();
	              }
			}
			if (!folder.delete()) {
				result = "NOK";
			}
			else {
				result = "OK";
			}
		}
		else {
				result = "FILE";
		}
		return result;
	}
	
	
	private void javaPCTempFilesCleanup() {
    	String logFileContent 	= "";
    	File subFolder 			= null;
    	File f 					= null;
    	long diff 				= 0;
    	String[] fileNames		= null;
    	String result 			= "";
    	String prefix			= "anonymous";		//anonymous	TCD
    	String folderName		= "";
    	
    	try {
    		logFileContent = "\n" + (new Date()).toString() + "\n";
    		wlog(3, "\n" + (new Date()).toString());
    		intCount3 = 0;
    		tfCount3.setText("" + intCount3);
    		
    		folderName = javaPCTempFilesHomeFolder + "\\1";
    		f = new File(folderName);
    		if (f.exists() && f.isDirectory()) {
	    		fileNames  = (new File(folderName)).list();
	    		for(String name : fileNames) {
	    			subFolder = new File(folderName + "\\" + name);
	    			diff = Math.abs((new Date()).getTime() - subFolder.lastModified()) / 1000;
	    			//wlog(3, name + ": " + subFolder + ": " + diff + "/" + toBeRemoved_javaPCTempFilesCleanup);
	    		    if (subFolder.isDirectory() && name.startsWith(prefix) && diff > toBeRemoved_javaPCTempFilesCleanup) {
	    		    	intCount3 ++;
	    		    	tfCount3.setText("" + intCount3);
	    		    	result 			= rmdir(subFolder);
	    		        logFileContent 	= logFileContent + name + ": " + result+ ": " + diff + "/" + toBeRemoved_javaPCTempFilesCleanup + "\n";
	    		        wlog(3, name + ": " + result+ ": " + diff + "/" + toBeRemoved_javaPCTempFilesCleanup);
	    		        this.sleep(randomWithRange(internalMin, internalMax));
	    		    }
	    		}
    		}
    		
    		folderName = javaPCTempFilesHomeFolder + "\\2";
    		f = new File(folderName);
    		if (f.exists() && f.isDirectory()) {
	    		fileNames  = (new File(folderName)).list();
	    		for(String name : fileNames) {
	    			subFolder = new File(folderName + "\\" + name);
	    			diff = Math.abs((new Date()).getTime() - subFolder.lastModified()) / 1000;
	    			//wlog(3, name + ": " + subFolder + ": " + diff + "/" + toBeRemoved_javaPCTempFilesCleanup);
	    		    if (subFolder.isDirectory() && name.startsWith(prefix) && diff > toBeRemoved_javaPCTempFilesCleanup) {
	    		    	intCount3 ++;
	    		    	tfCount3.setText("" + intCount3);
	    		    	result 			= rmdir(subFolder);
	    		        logFileContent 	= logFileContent + name + ": " + result+ ": " + diff + "/" + toBeRemoved_javaPCTempFilesCleanup + "\n";
	    		        wlog(3, name + ": " + result+ ": " + diff + "/" + toBeRemoved_javaPCTempFilesCleanup);
	    		        this.sleep(randomWithRange(internalMin, internalMax));
	    		    }
	    		}
    		}
    	} 
    	catch (Exception e) {
    		//e.printStackTrace();
    		logFileContent = logFileContent + "\n" + "\t" + Arrays.toString(e.getStackTrace());
    		wlog(3, logFileContent);
    	} 
    	finally{
    		this.sleep(randomWithRange(internalMin, internalMax));
    	}
    }
	
	
	private void websiteSyncEmersionPayment() {
		WebElement WebComponent	= null;	
    	String logFileContent 	= "", strDate = "", strTime = "";
    	String customerId		= "";
    	String paymentAmount	= "";
    	String paymentTransId	= "";
    	String cardType			= "";
    	String cardName			= "";
    	String cardNumber		= "";
    	String cardExpiry		= "";
    	String cardCVV			= "";
    	String paymentMethod	= "Credit Card";
    	String paymentNote		= "";
    	String id 				= "";
    	int autoPayment;
    	ArrayList<String> objArrayList = null;
    	
    	try {
    		
            objArrayList = DBNetcubeBackend.websiteSyncEmersionPayment();
            if ( objArrayList == null ) {
            	return;
            }

            customerId 		= objArrayList.get(0);
  	    	paymentAmount 	= objArrayList.get(1);
  	    	paymentTransId	= objArrayList.get(2);
  	    	cardType 		= objArrayList.get(3);
  	    	cardName 		= toTitleCase(objArrayList.get(4));
  	    	cardNumber 		= objArrayList.get(5);
  	    	cardExpiry 		= objArrayList.get(6);
  	    	cardCVV 		= objArrayList.get(7);
  	    	autoPayment 	= Integer.parseInt(objArrayList.get(8));
  	    	id 				= objArrayList.get(12);
    		
			wlog(4,"\n" + (new Date()).toString());
    		logFileContent = "\n" + (new Date()).toString();
			
    		this.driver = new FirefoxDriver();
    		this.sleep(randomWithRange(internalMin, internalMax));
        	this.driver.navigate().to("https://cumulus.emersion.com.au/customer/account/list");
    		this.sleep(randomWithRange(internalMin, internalMax));
    		this.waitForLocator(By.id("username")).sendKeys(LoginUsernameSyncEmersionIdList);
    		this.sleep(randomWithRange(internalMin, internalMax));
			this.waitForLocator(By.id("password")).sendKeys(LoginPasswordSyncEmersionIdList);
			this.sleep(randomWithRange(internalMin, internalMax));
			this.waitForLocator(By.cssSelector(".button > input:nth-child(1)")).click();	
			this.sleep(randomWithRange(internalMin, internalMax));

			this.waitForLocator(By.id("search_term")).sendKeys(customerId);
			this.sleep(randomWithRange(internalMin, internalMax));
			this.waitForLocator(By.id("searchform_search")).click();	
			this.sleep(5 * randomWithRange(internalMin, internalMax));
			String WebComponentString = ".tabs > ol:nth-child(1) > li:nth-child(18) > a:nth-child(1)";
			this.waitForLocator(By.cssSelector(WebComponentString));
			WebComponent = this.driver.findElement(By.cssSelector(WebComponentString));
			if (WebComponent.getText().trim().equalsIgnoreCase("Payment Methods")) {
				WebComponentString = ".tabs > ol:nth-child(1) > li:nth-child(19) > a:nth-child(1)";
				this.waitForLocator(By.cssSelector(WebComponentString));
				WebComponent = this.driver.findElement(By.cssSelector(WebComponentString));
			}
			this.waitForLocator(By.cssSelector(WebComponentString)).click();
			this.sleep(randomWithRange(internalMin, internalMax));
			this.waitForLocator(By.cssSelector("div.tabs:nth-child(2) > ol:nth-child(1) > li:nth-child(2) > a:nth-child(1)")).click();
			this.sleep(randomWithRange(internalMin, internalMax));
			
			this.waitForLocator(By.id("list_filter_TOTAL")).clear();
    		this.sleep(randomWithRange(internalMin, internalMax));
    		this.waitForLocator(By.id("list_filter_TOTAL")).sendKeys(paymentAmount);
			this.sleep(randomWithRange(internalMin, internalMax));
			this.waitForLocator(By.id("list_filter_CREDIT_REFERENCE")).sendKeys(paymentTransId);
			this.sleep(randomWithRange(internalMin, internalMax));
			this.selectOption("list_filter_PAYMENT_METHOD", paymentMethod);
    		this.sleep(randomWithRange(internalMin, internalMax));
    		if (cardType.equals("Master Card")) {
    			cardType = "MasterCard";
    		}
    		this.selectOption("list_filter_subtype", cardType);
    		this.sleep(randomWithRange(internalMin, internalMax));
    		strDate = driver.findElement(By.id("list_filter_CREDIT_DATE")).getAttribute("value");//   .getText();
    		strTime = driver.findElement(By.id("list_filter_CREDIT_DATE_time")).getAttribute("value");//   .getText();
    		//wlog(4,"Date/Time: " + strDate + "/" + strTime);
    		this.waitForLocator(By.id("list_filter_VANITY_CREDIT_DATE")).sendKeys(strDate);
			this.sleep(randomWithRange(internalMin, internalMax));
			this.waitForLocator(By.id("list_filter_VANITY_CREDIT_DATE_time")).clear();
    		this.sleep(randomWithRange(internalMin, internalMax));
    		this.waitForLocator(By.id("list_filter_VANITY_CREDIT_DATE_time")).sendKeys(strTime);
			this.sleep(randomWithRange(internalMin, internalMax));
			paymentNote = cardName + ":" + cardNumber + ":" + cardExpiry + ":" + cardCVV;
			if (autoPayment == 1) {
				paymentNote = "Update payment info: " + paymentNote;
			}
			else if (autoPayment == 0) {
				paymentNote = "Ignore payment info: " + paymentNote;
			}
			this.waitForLocator(By.id("list_filter_CREDIT_NOTE")).sendKeys(paymentNote);
			this.sleep(randomWithRange(internalMin, internalMax));
			this.waitForLocator(By.id("list_filter_submit")).click();	
			this.sleep(2 * randomWithRange(internalMin, internalMax));
			this.waitForLocator(By.cssSelector("button.ui-button-text-only:nth-child(2)")).click();	
			this.sleep(3 * randomWithRange(internalMin, internalMax));

			DBNetcubeBackend.updateWebsiteSyncEmersionPayment(id);
    		if ( autoPayment == 1 ) {
    			sendGmailWebsiteSyncEmersionPayment(objArrayList);
            }
    		intCount4 ++;
	    	tfCount4.setText("" + intCount4);
	    	String strAutoPayment = "";
	    	if (autoPayment == 1) {
	    		strAutoPayment = "SAVE";
	    	}
	    	else {
	    		strAutoPayment = "NONE";
	    	}
			wlog(4,"=======================================================================");
			wlog(4,"Customer: " + customerId + ":" + cardType + ":" + autoPayment + ":" + cardName + ":" + cardNumber + ":" + cardExpiry + ":" + cardCVV);
			wlog(4,"=======================================================================");
			this.sleep(2 * randomWithRange(internalMin, internalMax));
    	} 
    	catch (Exception e) {
    		//e.printStackTrace();
    		logFileContent = logFileContent + "\n" + "\t" + Arrays.toString(e.getStackTrace());
    		wlog(4, logFileContent);
    	} 
    	finally{
    		if (this.driver != null) {
    			this.sleep(randomWithRange(internalMin, internalMax));
    			this.waitForLocator(By.cssSelector(".header_info_right > a:nth-child(1) > strong:nth-child(1)")).click();	
    			this.sleep(randomWithRange(internalMin, internalMax));
    			this.driver.close();
    			this.driver = null;
    		}
    	}
	}
	
	
	public void sendGmailWebsiteSyncEmersionPayment(ArrayList<String> objArrayList) {
   	 
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
			
			String[] recipients = {"jerry.xu@novatel.com.au", "boson.huang@novatel.com.au", "matt.xue@novatel.com.au"};
			//String[] recipients = {"jerry.xu@novatel.com.au"};
			javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++)
			{
			    addressTo[i] = new javax.mail.internet.InternetAddress(recipients[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.TO, addressTo); 
			
			String customerId 		= objArrayList.get(0);
			String paymentAmount 	= objArrayList.get(1);
			String paymentTransId	= objArrayList.get(2);
			String cardType 		= objArrayList.get(3);
			String cardName 		= toTitleCase(objArrayList.get(4));
			String cardNumber 		= objArrayList.get(5);
			String cardExpiry 		= objArrayList.get(6);
			String cardCVV 			= objArrayList.get(7);
			int autoPayment 		= Integer.parseInt(objArrayList.get(8));
			String customerName		= toTitleCase(objArrayList.get(9));
			String paymentUnit		= objArrayList.get(10);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date paymentTime = null;
			try {
				paymentTime = format.parse(objArrayList.get(11));
			} 
			catch (ParseException e) {
			}
			String saveCardInfo		= "";
			if (autoPayment == 1) {
				saveCardInfo = "Yes";
			}
			else if (autoPayment == 0) {
				saveCardInfo = "No";
			}
			String content=
					"<html>" +
						"<head>" +
							"<title>Please update Emersion automated payment on due day for " + customerId + "/" + customerName + "</title>" +
						"</head>" +
						"<body>" +
							"Hi Finance Team,<br/><br/>" +
							"Please keep informed that following customer has paid his/her invoice(s) online just now. Please update Emersion automated payment on due day. Thank you!<br/><br/>" +
	            "<table style=\"border:1px solid black;text-align:left\">" +
	              "<tr style=\"border:1px solid black;text-align:left\">" +
	                "<td style=\"border:1px solid black;text-align:left\">Customer Name</td>" +
	                "<td style=\"border:1px solid black;text-align:left\">" + customerName + "</td>"+
	              "</tr>" +
	              "<tr style=\"border:1px solid black;text-align:left\">" +
	                "<td style=\"border:1px solid black;text-align:left\">Account Number</td>" +
	                "<td style=\"border:1px solid black;text-align:left\">" + customerId + "</td>" +
	              "</tr>" +
	              "<tr style=\"border:1px solid black;text-align:left\">" + 
	                "<td style=\"border:1px solid black;text-align:left\">Paid Amount</td>" +
	                "<td style=\"border:1px solid black;text-align:left\">" + paymentUnit + paymentAmount + "</td>" +
	              "</tr>" +
	              "<tr style=\"border:1px solid black;text-align:left\">" +
	                "<td style=\"border:1px solid black;text-align:left\">Transaction ID</td>" +
	                "<td style=\"border:1px solid black;text-align:left\">" + paymentTransId + "</td>" +
	              "</tr>" +
	              "<tr style=\"border:1px solid black;text-align:left\">" +
	              "<td style=\"border:1px solid black;text-align:left\">Card Type</td>" +
	              "<td style=\"border:1px solid black;text-align:left\">" + cardType + "</td>" +
	              "</tr>" +
	              "<tr style=\"border:1px solid black;text-align:left\">" +
	                "<td style=\"border:1px solid black;text-align:left\">Card Name</td>" +
	                "<td style=\"border:1px solid black;text-align:left\">" + cardName + "</td>" +
	              "</tr>" +
	              "<tr style=\"border:1px solid black;text-align:left\">" +
	                "<td style=\"border:1px solid black;text-align:left\">Card Number</td>" +
	                "<td style=\"border:1px solid black;text-align:left\">" + cardNumber + "</td>" +
	              "</tr>" +
	              "<tr style=\"border:1px solid black;text-align:left\">" +
	                "<td style=\"border:1px solid black;text-align:left\">Expiry Date</td>" +
	                "<td style=\"border:1px solid black;text-align:left\">" + cardExpiry + "</td>" +
	              "</tr>" +
	              "<tr style=\"border:1px solid black;text-align:left\">" +
	                "<td style=\"border:1px solid black;text-align:left\">CVV</td>" +
	                "<td style=\"border:1px solid black;text-align:left\">" + cardCVV + "</td>" +
	              "</tr>" +
	              "<tr style=\"border:1px solid black;text-align:left\">" +
	                "<td style=\"border:1px solid black;text-align:left\">Paid Time</td>" +
	                "<td style=\"border:1px solid black;text-align:left\">" + paymentTime.toString() + "</td>" +
	              "</tr>" +
	              "<tr style=\"border:1px solid black;text-align:left\">" +
	                "<td style=\"border:1px solid black;text-align:left\">Set up automated payment on due day?</td>" +
	                "<td style=\"border:1px solid black;text-align:left\">" + saveCardInfo + "</td>" +
	              "</tr>" +
	            "</table><br />" +
							"<br />" +
						"</body>" +
					"</html>";
			message.setSubject("Please update Emersion automated payment on due day for " + customerId + "/" + customerName);
			message.setContent(content, "text/html; charset=utf-8");
			//message.setText(content);
 
			Transport.send(message);
		} 
		catch (MessagingException e) {
			String logFileContent = "\n" + (new Date()).toString();
    		logFileContent = logFileContent + "\n" + "\t" + Arrays.toString(e.getStackTrace());
    		wlog(1, logFileContent);
		}
	}
	
	public void sendGmailJavaHooplaMonitorUpdate(String hoopla_email_time_stamp, String hoopla_csv_file) {
   	 
		final String username = "jerry.xu@novatel.com.au";
		final String password = "xgp_950254";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.user", username);
		props.put("mail.password", password);
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply@netcube.com.au"));
			
			String[] recipients = {
									"5wp43ghmamfa@upload.hoopla.net"
									//, "cmc1300xu@gmail.com"
								};
			javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++)
			{
			    addressTo[i] = new javax.mail.internet.InternetAddress(recipients[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.TO, addressTo); 
			message.setSubject("Hoopla matrix file - " + hoopla_email_time_stamp);
			message.setText(hoopla_csv_file);
				
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("Hoopla matrix file content: " + hoopla_csv_file, "text/html");
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			MimeBodyPart attachPart = new MimeBodyPart();
			File f = new File(hoopla_csv_file);
	        //wlog(5, "File exists: " + hoopla_csv_file + " " + f.exists());
			 
			try {
                attachPart.attachFile(hoopla_csv_file);
            } 
			catch (Exception ex) {
                ex.printStackTrace();
            }
			multipart.addBodyPart(attachPart);

	        message.setContent(multipart);
 
			Transport.send(message);
		} 
		catch (Exception e) {
			String logFileContent = "\n" + (new Date()).toString();
    		logFileContent = logFileContent + "\n" + "\t" + Arrays.toString(e.getStackTrace());
    		wlog(5, logFileContent);
		}
	}
	
	public void javaHooplaMonitorUpdate() {
		String logFileContent 		= "";
		
		FileWriter fileWriter 		= null;
		String agentInfoLine		= "";
    	
    	Iterator entries			= null;
    	Entry thisEntry				= null;
    	Calendar cal				= null;
    	int previous_month_year		= 0;
    	String previous_month_month	= "";
    	String previous_month_name	= "";
    	String previous_month		= "";
    	String current_year_month	= "";
    	String current_year_month_date = "";
    	String hoopla_email_time_stamp = "";
    	HooplaAgent hooplaAgent		= null;
    	String agent_id 			= "";
    	String agent_email 			= ""; 
    	String hoopla_name 			= "";
    	
    	try {
    		logFileContent = "\n" + (new Date()).toString() + "\n";
    		wlog(5, "\n" + (new Date()).toString());
    		intCount5 = 0;
    		tfCount5.setText("" + intCount5);
    		
    		cal = Calendar.getInstance();
    		current_year_month		= new SimpleDateFormat("yyyyMM").format(cal.getTime());
    		current_year_month_date	= new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    		previous_month_year		= Integer.parseInt(new SimpleDateFormat("yyyy").format(cal.getTime()));
    		hoopla_email_time_stamp	= new SimpleDateFormat("yyyyMMddhhmm").format(cal.getTime());
     		
     		cal.add(Calendar.MONTH ,-1);
    		previous_month_name 	= new SimpleDateFormat("MMMMM").format(cal.getTime());
    		previous_month_month	= new SimpleDateFormat("MM").format(cal.getTime());
    		if (previous_month_month.equalsIgnoreCase("12")) {
    			previous_month_year	= previous_month_year - 1;
    		}
    		previous_month			= previous_month_year + previous_month_month;
    		
			fileWriter = new FileWriter(System.getProperty("user.dir") + "/hoopla/" + current_year_month_date + ".csv");
			fileWriter.append("Email,24 Months,12 Months,0 Months,6 Months,Sales MTD,Conversion MTD,Revenue MTD,Daily Sales Count,Sales Prev,Conversion Prev,Revenue Prev,Enquiries");
    		
    		if (save_previous_month_name.isEmpty() || !save_previous_month_name.equalsIgnoreCase(previous_month_name)) {
    			wlog(5, "change save_previous_month_name from " + save_previous_month_year + save_previous_month_month + 
    					" to " + previous_month_year + previous_month_month);
    			save_previous_month_name 	= previous_month_name;
    			save_previous_month_month	= previous_month_month;
    			save_previous_month_year	= previous_month_year;
    			wlog(5, "change save_current_year_month_date from " + save_current_year_month_date + " to " + current_year_month_date);
    			save_current_year_month_date = current_year_month_date;
    			
    			entries = list_of_agents.entrySet().iterator();
	    		while (entries.hasNext()) {
	    			thisEntry 		= (Entry) entries.next();
	    			agent_email 	= (String) thisEntry.getKey();
	    			hooplaAgent 	= (HooplaAgent) thisEntry.getValue();
	    			agentInfoLine	= DBNetcubeBackend.javaHooplaMonitorUpdate(LEVEL_MONTHLY_UPDATE, agent_email, hooplaAgent, previous_month, current_year_month, current_year_month_date);
	    			wlog(5, agentInfoLine);
	    			fileWriter.append(NEW_LINE_SEPARATOR + agentInfoLine);
	    		}
    		}
    		else if (save_current_year_month_date.isEmpty() || !save_current_year_month_date.equalsIgnoreCase(current_year_month_date)) {
    			wlog(5, "change save_current_year_month_date from " + save_current_year_month_date + " to " + current_year_month_date);
    			save_current_year_month_date = current_year_month_date;
    			
    			entries = list_of_agents.entrySet().iterator();
	    		while (entries.hasNext()) {
	    			thisEntry 		= (Entry) entries.next();
	    			agent_email 	= (String) thisEntry.getKey();
	    			hooplaAgent 	= (HooplaAgent) thisEntry.getValue();
	    			agentInfoLine	= DBNetcubeBackend.javaHooplaMonitorUpdate(LEVEL_DAILY_UPDATE, agent_email, hooplaAgent, previous_month, current_year_month, current_year_month_date);
	    			wlog(5, agentInfoLine);
	    			fileWriter.append(NEW_LINE_SEPARATOR + agentInfoLine);
	    		}
    		}
    		else if (!save_current_year_month_date.isEmpty() && save_current_year_month_date.equalsIgnoreCase(current_year_month_date)) {
    			wlog(5, "Hourly update: " + current_year_month_date);
     			entries = list_of_agents.entrySet().iterator();
	    		while (entries.hasNext()) {
	    			thisEntry 		= (Entry) entries.next();
	    			agent_email 	= (String) thisEntry.getKey();
	    			hooplaAgent 	= (HooplaAgent) thisEntry.getValue();
	    			agentInfoLine	= DBNetcubeBackend.javaHooplaMonitorUpdate(LEVEL_HOURLY_UPDATE, agent_email, hooplaAgent, previous_month, current_year_month, current_year_month_date);
	    			wlog(5, agentInfoLine);
	    			fileWriter.append(NEW_LINE_SEPARATOR + agentInfoLine);
	    		}
	    	}
    		
    		fileWriter.flush();
    		fileWriter.close();
    		sendGmailJavaHooplaMonitorUpdate(hoopla_email_time_stamp, System.getProperty("user.dir") + "/hoopla/" + current_year_month_date + ".csv");
    	} 
    	catch (Exception e) {
    		logFileContent = logFileContent + "\n" + "\t" + Arrays.toString(e.getStackTrace());
    		wlog(5, logFileContent);
    	} 
    	finally{
    		this.sleep(randomWithRange(internalMin, internalMax));
    	}
	}
	
	public static String toTitleCase(String givenString) {
		/*
	    String[] arr = givenString.split(" ");
	    StringBuffer sb = new StringBuffer();
	    String temp = "";

	    for (int i = 0; i < arr.length; i++) {
	    	temp = arr[i].trim();
	    	if (temp.length() > 1) {
		    	wlog(4, temp);
	    		sb.append(Character.toUpperCase(temp.charAt(0))).append(temp.substring(1)).append(" ");
	    	}
	    	else {
	    		sb.append(Character.toUpperCase(temp.charAt(0)) + " ");
	    	}
	    }   
	    wlog(4, sb.toString().trim());
	    return sb.toString().trim(); */
		
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
    
    
	public static void main(String[] args) {
		 
		Date date 								= null;
		long diff 								= 0;
		DBNetcubeBackend obj_DBNetcubeBackend	= new DBNetcubeBackend();
    	netcubeBackend obj 						= new netcubeBackend();	
    	obj.getDatabaseSetting();
		
		while ( true ) {
			try {
				if (obj.enabled_javaPCTempFilesCleanup) {
					date = new Date();
					diff = date.getTime() - obj.date_javaPCTempFilesCleanup.getTime() - obj.interval_javaPCTempFilesCleanup * 1000;
					if (diff > 0) {		//if (Math.abs(diff) < 500 || diff > 0) 
						obj.wlog(0, date.toString() + " javaPCTempFilesCleanup since " + obj.date_javaPCTempFilesCleanup.toString());// + ": " + diff + "/" + obj.interval_javaPCTempFilesCleanup);
						obj.cal = Calendar.getInstance();
						obj.cal.set(Calendar.MINUTE,0);
						obj.cal.set(Calendar.SECOND,0);
						obj.cal.set(Calendar.MILLISECOND,0);
						obj.date_javaPCTempFilesCleanup = obj.cal.getTime();
						DBNetcubeBackend.resetLastExecutedTime("javaPCTempFilesCleanup", obj.date_javaPCTempFilesCleanup);
						obj.javaPCTempFilesCleanup();
					}
				}
				
				if (obj.enabled_websiteFetchEchoSignContract) {
					date = new Date();
					diff = date.getTime() - obj.date_websiteFetchEchoSignContract.getTime() - obj.interval_websiteFetchEchoSignContract * 1000;
					if (diff > 0) {
						obj.wlog(0, date.toString() + " websiteFetchEchoSignContract since " + obj.date_websiteFetchEchoSignContract.toString());// + ": " + diff + "/" + obj.interval_websiteFetchEchoSignContract);
						obj.cal = Calendar.getInstance();
						obj.cal.set(Calendar.SECOND,0);
						obj.cal.set(Calendar.MILLISECOND,0);
						obj.date_websiteFetchEchoSignContract = obj.cal.getTime();
						DBNetcubeBackend.resetLastExecutedTime("websiteFetchEchoSignContract", obj.date_websiteFetchEchoSignContract);
						obj.websiteFetchEchoSignContract();
					}
				}
				
				if (obj.enabled_websiteSyncEmersionIdList) {
					date = new Date();
					diff = date.getTime() - obj.date_websiteSyncEmersionIdList.getTime() - obj.interval_websiteSyncEmersionIdList * 1000;
					if (diff > 0) {
						obj.wlog(0, date.toString() + " websiteSyncEmersionIdList since " + obj.date_websiteSyncEmersionIdList.toString());// + ": " + diff + "/" + obj.interval_websiteSyncEmersionIdList);
						obj.cal = Calendar.getInstance();
						obj.cal.set(Calendar.HOUR_OF_DAY,7);
						obj.cal.set(Calendar.MINUTE,15);
						obj.cal.set(Calendar.SECOND,0);
						obj.cal.set(Calendar.MILLISECOND,0);
						obj.date_websiteSyncEmersionIdList = obj.cal.getTime();
						DBNetcubeBackend.resetLastExecutedTime("websiteSyncEmersionIdList", obj.date_websiteSyncEmersionIdList);
						obj.websiteSyncEmersionIdListV2();
					}
				}
				
				if (obj.enabled_websiteSyncEmersionPayment) {
					date = new Date();
					diff = date.getTime() - obj.date_websiteSyncEmersionPayment.getTime() - obj.interval_websiteSyncEmersionPayment * 1000;
					if (diff > 0) {
						//obj.wlog(0, date.toString() + " websiteSyncEmersionPayment since " + obj.date_websiteSyncEmersionPayment.toString());// + ": " + diff + "/" + obj.interval_websiteSyncEmersionPayment);
						obj.cal = Calendar.getInstance();
						obj.cal.set(Calendar.MILLISECOND,0);
						obj.date_websiteSyncEmersionPayment = obj.cal.getTime();
						DBNetcubeBackend.resetLastExecutedTime("websiteSyncEmersionPayment", obj.date_websiteSyncEmersionPayment);
						obj.websiteSyncEmersionPayment();
					}
				}
				
				if (obj.enabled_javaHooplaMonitorUpdate) {
					date = new Date();
					diff = date.getTime() - obj.date_javaHooplaMonitorUpdate.getTime() - obj.interval_javaHooplaMonitorUpdate * 1000;
					if (diff > 0) {
						obj.cal = Calendar.getInstance();
						obj.cal.set(Calendar.MILLISECOND,0);
						obj.date_javaHooplaMonitorUpdate = obj.cal.getTime();
						DBNetcubeBackend.resetLastExecutedTime("javaHooplaMonitorUpdate", obj.date_javaHooplaMonitorUpdate);
						obj.javaHooplaMonitorUpdate();
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