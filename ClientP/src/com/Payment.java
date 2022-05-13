package com;

import java.sql.*;

public class Payment {
	
	public Connection connect()
	{ 
	 Connection con = null; 

	 
	 try 
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/PAF", 
	 "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 	 
	 return con;
	}

	public String readPayments() 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
		 
	 if (con == null) 
	 { 
		 return "Error while connecting  to the database for reading."; 
	 } 
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Customer Name</th>   "
	 		+ "<th>Card Type</th> <th>Card Number</th> "
	 		+ "<th>Expire month</th> <th>Expire Year</th> "
	 		+ "<th>CVN number</th>"
	 		+ "<th>Total payment</th>  <th>Update</th><th>Remove</th></tr>"; 
	 String query = "select * from payment"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
		 String id = Integer.toString(rs.getInt("id")); 
		 String name = rs.getString("name");
		 String ctype = rs.getString("ctype");
		 String cnumber = Integer.toString(rs.getInt("cnumber"));
		 String exmonth = Integer.toString(rs.getInt("exmonth"));
		 String exyear = Integer.toString(rs.getInt("exyear"));
		 String cvn = Integer.toString(rs.getInt("cvn"));
		 String tot = Double.toString( rs.getDouble("tot")); 
		 
		 
	 // Add into the html table
	 output += "<tr><td><input id='hidPaymentIDUpdate'  name='hidPaymentIDUpdate'  type='hidden' value='" + id + "'>" + name + "</td>"; 
	 output += "<td>" + ctype + "</td>";
	 output += "<td>" + cnumber + "</td>"; 
	 output += "<td>" + exmonth + "</td>"; 
	 output += "<td>" + exyear + "</td>"; 
	 output += "<td>" + cvn + "</td>"; 
	 output += "<td>" + tot + "</td>"; 
	 
	 // buttons
	output += "<td><input name='btnUpdate'  type='button' value='Update'  class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove'  type='button' value='Remove'  class='btnRemove btn btn-danger'  data-ID='" + id + "'>" + "</td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while reading the payments."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output;
	 }
	
	
	public String insertPayment(String name,String ctype, String cnumber, String exmonth, String exyear, String cvn, String tot) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting  to the database for inserting."; 
			 } 
			 // create a prepared statement
			 String query = " insert into payment (`id`,`name`,`ctype`,`cnumber`,`exmonth`,`exyear`,`cvn`,`tot`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 preparedStmt.setInt(1, 0); 
					 preparedStmt.setString(2, name); 
					 preparedStmt.setString(3, ctype); 
					 preparedStmt.setInt(4, Integer.parseInt(cnumber));
					 preparedStmt.setInt(5, Integer.parseInt(exmonth));
					 preparedStmt.setInt(6, Integer.parseInt(exyear));
					 preparedStmt.setInt(7, Integer.parseInt(cvn));
					 preparedStmt.setDouble(8, Double.parseDouble(tot));
					 
					 
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 String newPayments = readPayments(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 newPayments + "\"}"; 
					 } 
					 catch (Exception e) 
					 { 
					 output = "{\"status\":\"error\", \"data\":  \"Error while inserting the payment.\"}"; 
					 System.err.println(e.getMessage()); 
					 } 
					 return output; 
					 }
	
	public String updatePayment(String id,String name,String ctype, String cnumber, String exmonth, String exyear, String cvn, String tot) 
	      { 
				String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
			 if (con == null) 
			 { 
					 return "Error while connecting  to the database for updating."; 
			 } 
			 // create a prepared statement
			 String query = "UPDATE payment SET  name=?,ctype=?,cnumber=?,exmonth=?,exyear=?,cvn=?,tot=? WHERE id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, ctype);
			 preparedStmt.setInt(3, Integer.parseInt(cnumber));
			 preparedStmt.setInt(4, Integer.parseInt(exmonth));
			 preparedStmt.setInt(5, Integer.parseInt(exyear));
			 preparedStmt.setInt(6, Integer.parseInt(cvn));
			 preparedStmt.setDouble(7, Double.parseDouble(tot));
			 preparedStmt.setInt(8, Integer.parseInt(id)); 
			 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newPayments = readPayments(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newPayments + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\":  \"Error while updating the payment.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		}
	
	public String deletePayment(String id) 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
		 
	 if (con == null) 
	 { 
		 return "Error while connecting to the database for deleting."; 
	 } 
	 
	 // create a prepared statement
	 String query = "delete from payment where id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newPayment = readPayments(); 
	 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\":  \"Error while deleting the payment.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
}
	

