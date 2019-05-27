package com.global.netcubeBackend;

public class HooplaAgent {

	private String agent_id;
	private String agent_email;
	private String hoopla_name;
	
	private String current_24_months = "0";
	private String current_12_months = "0";
	private String current_0_months = "0";
	private String current_6_months = "0";
	
	private String mtd_sales = "0";
	private String mtd_conversion = "0";
	private String mtd_revenue = "0.00";
	
	private String previous_month_sales = "0";
	private String previous_month_conversion = "0";
	private String previous_month_revenue = "0.00";
	
	private String daily_sales_count = "0";
	private String enquiries = "0";
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	
	public HooplaAgent(String agent_id, String agent_email, String hoopla_name){
	      this.agent_id = agent_id;
	      this.agent_email = agent_email;
	      this.hoopla_name = hoopla_name;
	}
	
	public String get_agent_id() {
		return this.agent_id;
	}
	
	public void set_current_24_months(String current_24_months) {
		this.current_24_months = current_24_months;
	}
	
	public void set_current_12_months(String current_12_months) {
		this.current_12_months = current_12_months;
	}
	
	public void set_current_0_months(String current_0_months) {
		this.current_0_months = current_0_months;
	}
	
	public void set_current_6_months(String current_6_months) {
		this.current_6_months = current_6_months;
	}
	
	public void set_mtd_sales(String mtd_sales) {
		this.mtd_sales = mtd_sales;
	}
	
	public void set_mtd_revenue(String mtd_revenue) {
		this.mtd_revenue = mtd_revenue;
	}
	
	public void set_previous_month_sales(String previous_month_sales) {
		this.previous_month_sales = previous_month_sales;
	}
	
	public void set_previous_month_revenue(String previous_month_revenue) {
		this.previous_month_revenue = previous_month_revenue;
	}
	
	public void set_daily_sales_count(String daily_sales_count) {
		this.daily_sales_count = daily_sales_count;
	}
	
	/*		*/
	public String toString() {
		return 	agent_email + this.COMMA_DELIMITER + 
				current_24_months + this.COMMA_DELIMITER + 
				current_12_months + this.COMMA_DELIMITER + 
				current_0_months + this.COMMA_DELIMITER + 
				current_6_months + this.COMMA_DELIMITER + 
				mtd_sales + this.COMMA_DELIMITER + 
				mtd_conversion + this.COMMA_DELIMITER + 
				mtd_revenue + this.COMMA_DELIMITER + 
				daily_sales_count + this.COMMA_DELIMITER + 
				previous_month_sales + this.COMMA_DELIMITER + 
				previous_month_conversion + this.COMMA_DELIMITER + 
				previous_month_revenue + this.COMMA_DELIMITER + 
				enquiries;
		
		/*
		return 	this.agent_id + 
				" last_month (" + previous_month_sales + ")" +
				" today (" + daily_sales_count + ")" +
				" / this_month (" + current_24_months + ", " + current_12_months + ", " + current_0_months + ", " + current_6_months + ") " + 
				" / mtd_sales (" + mtd_sales + ")";*/
	} 
}
