<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>
</head>
<body>

<div class="container">
 <div class="row">
 <div class="col">

<h1>Payment Management</h1>
<form id="formPayment" name="formPayment">
 Customer Name: 
 <input id="name" name="name" type="text" 
 class="form-control form-control-sm">
 <br> Card Type: 
 <input id="ctype" name="ctype" type="text" 
 class="form-control form-control-sm">
 <br> Card Number: 
 <input id="cnumber" name="cnumber" type="text" 
 class="form-control form-control-sm">
 <br> Expire month: 
 <input id="exmonth" name="exmonth" type="text" 
 class="form-control form-control-sm">
 <br> Expire Year: 
 <input id="exyear" name="exyear" type="text" 
 class="form-control form-control-sm">
 <br> CVN number: 
 <input id="cvn" name="cvn" type="text" 
 class="form-control form-control-sm">
 <br> Total payment: 
 <input id="tot" name="tot" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divPaymentsGrid">
<%
	Payment paymentObj =new Payment ();
 	out.print(paymentObj.readPayments()); 
 %>
</div>
</div> </div> </div> 

</body>
</html>