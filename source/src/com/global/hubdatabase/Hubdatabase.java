package com.global.hubdatabase;

import java.awt.*;   
import java.awt.event.*; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
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


public class Hubdatabase extends Frame implements ActionListener {

	public WebDriver driver;
	
	public int waitTime = 100;

	public String LoginUsername = "marshall.ma@novatel.com.au";
	public String LoginPassword = "marshall123";
	
	public static int internalMin = 4;
	public static int internalMax = 5;
	public static int internalBetweenLoop = 8;	
	
	public int count_processed		= 1;
	public int count_maximum		= 0;
	private boolean paused 			= false;
		
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private Label lblCount;    // Declare component Label
	private TextField tfCount; // Declare component TextField
	private Button btnPaused, btnExit;   // Declare component Button
	private int outputLineMAXCount 	= 10000;    	
	private JTextArea logOutput		= null;
	private JScrollPane scroller 	= null;
	private String responseText 	= "";
	//private String URL				= "http://api.netcube.com.au/projects/netcube/emersionAPI/table_em_customer_refresh.php";
	private String URL				= "http://api.netcube.com.au/projects/netcube/emersionAPI/table_em_customer_refreshTest.php";
	 
	   /** Constructor to setup GUI components and event handling */
	   public Hubdatabase () {
	      setLayout(new FlowLayout());
	         // "super" Frame sets its layout to FlowLayout, which arranges the components
	         //  from left-to-right, and flow to next row from top-to-bottom.
	      
	      JPanel rootPanel = new JPanel();
	      rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
	      add(rootPanel);
	      
	      JPanel  panel1 = new JPanel(new FlowLayout()); 
	      rootPanel.add(panel1);  
	 
	      lblCount = new Label("Log infomation Lines");  // construct Label
	      panel1.add(lblCount);                    // "super" Frame adds Label
	      tfCount = new TextField("0 / 0", 12); // construct TextField
	      tfCount.setEditable(false);       // set to read-only
	      panel1.add(tfCount);                     // "super" Frame adds tfCount
	      
	     
	      JPanel panel2 = new JPanel();
	      panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	      rootPanel.add(panel2);
	 
	      logOutput = new JTextArea(30, 70);
	      logOutput.setEditable(false);
	      scroller = new JScrollPane(logOutput);
          scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
          scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      DefaultCaret caret = (DefaultCaret)logOutput.getCaret();
	      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	      panel2.add(scroller); 
	      
	      JPanel panelButton = new JPanel();
	      panelButton.setLayout(new FlowLayout());
	      rootPanel.add(panelButton);
	      btnPaused = new Button("Pause Execution   ");   
	      btnPaused.setFont(new Font("Sans Serif", Font.BOLD, 20));
	      panelButton.add(btnPaused);         
	      panelButton.add(Box.createRigidArea(new Dimension(200,0)));
	      btnExit = new Button("Exit Execution");   
	      btnExit.setFont(new Font("Sans Serif", Font.BOLD, 20));
	      panelButton.add(btnExit);     
	      
	      btnPaused.addActionListener(new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	        	paused = !paused;
	        	if (paused) {
	        		btnPaused.setLabel("Resume Execution");
	        	}
	        	else {
	        		btnPaused.setLabel("Pause Execution");
	        	}
	        }
	      });
	      btnExit.addActionListener(this);
	         // Clicking Button source fires ActionEvent
	         // btnCount registers this instance as ActionEvent listener
	 
	      setTitle("Import data into NetCube-Hub");  // "super" Frame sets title
	      //setSize(800, 600);        // "super" Frame sets initial window size
	      
	      GraphicsConfiguration gc = getGraphicsConfiguration();
	      Rectangle bounds = gc.getBounds();
	      /*
	      setLocation((int) ((bounds.width / 2) - (size.getWidth() / 2)),
                  (int) ((bounds.height / 2) - (size.getHeight() / 2))); */
	      setLocation((int) ((bounds.width) - 800 - 10),60);
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
    
    
    public void sendGmailToCustomer(String content) {
   	 
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
			
			String[] recipients =	{	
										"jerry.xu@novatel.com.au", 
										"boson.huang@novatel.com.au"
										//, "indika.kumara@novatel.com.au"
									};
			javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++)
			{
			    addressTo[i] = new javax.mail.internet.InternetAddress(recipients[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.TO, addressTo); 
			
			message.setSubject("Emersion customers which fail to be synchronized");
			message.setText(content);
 
			Transport.send(message);
		} 
		catch (MessagingException e) {
			String logFileContent = "\n" + (new Date()).toString();
    		logFileContent = logFileContent + "\n" + "    " + Arrays.toString(e.getStackTrace());
    		wlog(logFileContent);
		}
	}
        
    
    private boolean oneTransaction(int count, Date date, String customerId, String customerStatus, String customerName) {
    	WebElement WebComponent = null;	
    	boolean bReturn 		= false;
    	Boolean isPresent 		= false;
    	int loopCount			= 0;
    	
    	try {
    		this.driver = new FirefoxDriver();
    		//driver.manage().window().setPosition(new org.openqa.selenium.Point(10,800));
    		this.sleep(randomWithRange(internalMin, internalMax));
        	this.driver.navigate().to(URL + "?account=" + customerId);
    		this.sleep(randomWithRange(internalMin, internalMax));
    			
    		//driver.manage().window().maximize();
    		//this.waitForLocator(By.name("result"));
    		isPresent = driver.findElements(By.name("result")).size() > 0;
			while (!isPresent && loopCount < 60) {
				isPresent = driver.findElements(By.name("result")).size() > 0;
				loopCount ++;
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
			
    		if (responseText.indexOf("processed successfully") > 0) {
    			responseText = responseText.replaceAll(" has", " (" + customerStatus + " - " + customerName + ") has");
    			wlog(String.format("%04d", count) + ": " + responseText);
    			bReturn = true;
    		}
    		else {
    			responseText = (new Date()).toString() + "\n" + "\t" + responseText;
    			wlog(String.format("%04d", count) + ": " + "Customer " + customerId + " (" + customerStatus + " - " + customerName + ") wrong - " + responseText);
    			bReturn = false;
    		}
    		
    		//paused = true; btnPaused.setLabel("Resume Execution");
    		this.sleep(randomWithRange(internalMin, internalMax));
     		while (paused) {
    			this.sleep(1);
    		}
    		this.driver.close();
    	} 
    	catch (Exception e) {
    		//e.printStackTrace();
    		responseText 	= "Customer " + customerId + " fails for " + Arrays.toString(e.getStackTrace());
    		wlog(String.format("%04d", count) + ": exception case - " + responseText);
    		bReturn 		= false;
    		this.sleep(randomWithRange(internalMin, internalMax));
    		//this.driver.close();
    	} 
    	return bReturn;
    }
    
    private void wlog(String log){
    	
        // Display the counter value on the TextField tfCount
        tfCount.setText(count_processed++ + " / " + count_maximum); 
        if (logOutput.getLineCount() > outputLineMAXCount) {
        	logOutput.setText(log);
        }
        else {
        	logOutput.append(log + "\n");
        	JScrollBar vertical = scroller.getVerticalScrollBar();
        	vertical.setValue( vertical.getMaximum());
        }
    }
    
    
	public static void main(String[] args) {
		 
		ArrayList<String> objArrayList	= new ArrayList<String>();
		String status					= "";
		String customerId				= "";
		String customerStatus			= "";
		String customerName				= "";
		DBHubdatabase obj_DBHubdatabase	= new DBHubdatabase();
		Hubdatabase obj 				= new Hubdatabase();
		String temp 					= "";
	    	
		try {
			while ( true ) {
				objArrayList	= DBHubdatabase.getMaximumEmersionId();
				status			= objArrayList.get(0);
				customerId		= objArrayList.get(1);
				customerStatus	= objArrayList.get(2);
				customerName	= objArrayList.get(3);
				//obj.wlog("getMaximumEmersionId: " + customerId);
				if ( status.equalsIgnoreCase("NOK") ) {
					//nothing to do for now
				}
				else if ( status.equalsIgnoreCase("Exception") ) {
					obj.wlog("Exception happened: " + customerId);
				}
				else if ( status.equalsIgnoreCase("OK")) {
					if (Integer.parseInt(customerId) > 394423) {
						Date date = new Date();
						if (obj.oneTransaction(obj.count_processed, date, customerId + "", customerStatus, customerName)) {
							temp = DBHubdatabase.setEmersionProcessStatus(Integer.parseInt(customerId), "processed", obj.responseText);
							//obj.wlog(temp);
						}
						else {
							temp = DBHubdatabase.setEmersionProcessStatus(Integer.parseInt(customerId), "wrong", obj.responseText.replaceAll("'", ""));
							//obj.wlog(customerId + " wrong " + temp + "\n");
						}
					}
					else if (Integer.parseInt(customerId) == 394423) {
						obj.count_maximum	= obj.count_processed;
						obj.count_processed = 1;
						DBHubdatabase.setEmersionProcessStatus(Integer.parseInt(customerId), "processed", "Only to indicate that it is the end of this loop.");
						String getFailedSynchronizationTasks = DBHubdatabase.getFailedSynchronizationTasks();
						obj.sendGmailToCustomer("Emersion customers which fail to be synchronized:\n" + getFailedSynchronizationTasks);
						obj.wlog("Emersion customers which fail to be synchronized:\n" + getFailedSynchronizationTasks + "\n");
					}
				}
				
			    obj.sleep(internalBetweenLoop);
		    }
		 } 
		 catch (Exception e) {
    		obj.wlog("Function main() error: " + Arrays.toString(e.getStackTrace()));
		 }    	
	}

}
