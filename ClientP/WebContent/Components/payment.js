/**
 * 
 */
 $(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
 
 
 // SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validatePaymentForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "PaymentsAPI",
			type: type,
			data: $("#formPayment").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onPaymentSaveComplete(response.responseText, status);
			}
		});
});
		
		function onPaymentSaveComplete(response, status) {

	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divPaymentsGrid").html(resultSet.data);
		}

		else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidPaymentIDSave").val("");
	$("#formPayment")[0].reset();

}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val());
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#ctype").val($(this).closest("tr").find('td:eq(1)').text());
	$("#cnumber").val($(this).closest("tr").find('td:eq(2)').text());
	$("#exmonth").val($(this).closest("tr").find('td:eq(3)').text());
	$("#exyear").val($(this).closest("tr").find('td:eq(4)').text());
	$("#cvn").val($(this).closest("tr").find('td:eq(5)').text());
	$("#tot").val($(this).closest("tr").find('td:eq(6)').text());
});



// CLIENT-MODEL================================================================
function validatePaymentForm() {
	// name
	if ($("#name").val().trim() == "") {
		return "Insert customer name.";
	}
	// Cardtype
	if ($("#ctype").val().trim() == "") {
		return "Insert card type.";
	}
	
	
	// Card number-------------------------------
	if ($("#cnumber").val().trim() == "") {
		return "Insert card number .";
	}


	// is numerical value
	var tmpCnumber = $("#cnumber").val().trim();
	if (!$.isNumeric(tmpCnumber)) {
		return "Insert a numerical value for Card number.";
	}



	// convert to decimal price
	$("#cnumber").val(parseInt(tmpCnumber).toFixed(2));


	// Expire month-------------------------------
	if ($("#exmonth").val().trim() == "") {
		return "Insert expire month.";
	}


	// is numerical value
	var tmpExmonth = $("#exmonth").val().trim();
	if (!$.isNumeric(tmpExmonth)) {
		return "Insert a numerical value for expire month.";
	}



	// convert to decimal price
	$("#exmonth").val(parseInt(tmpExmonth).toFixed(2));
	
	
	// Exyear-------------------------------
	if ($("#exyear").val().trim() == "") {
		return "Insert Expire Year.";
	}


	// is numerical value
	var tmpExyear = $("#exyear").val().trim();
	if (!$.isNumeric(tmpExyear)) {
		return "Insert a numerical value for Expire year.";
	}



	// convert to decimal price
	$("#exyear").val(parseInt(tmpExyear).toFixed(2));


	// CVN-------------------------------
	if ($("#cvn").val().trim() == "") {
		return "Insert cvn number.";
	}


	// is numerical value
	var tmpCvn = $("#cvn").val().trim();
	if (!$.isNumeric(tmpCvn)) {
		return "Insert a numerical value for cvn.";
	}
	
	// convert to decimal price
	$("#cvn").val(parseInt(tmpCvn).toFixed(2));



	// Total-------------------------------
	if ($("#tot").val().trim() == "") {
		return "Insert Total.";
	}


	// is numerical value
	var tmpTot = $("#tot").val().trim();
	if (!$.isNumeric(tmpTot)) {
		return "Insert a numerical value for Total.";
	}



	// convert to decimal price
	$("#tot").val(parseDouble(tmpTot).toFixed(2));



	return true;
}


///REMOVE============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "PaymentsAPI",
			type: "DELETE",
			data: "id=" + $(this).data("id"),
			dataType: "text",
			complete: function(response, status) {
				onProductDeleteComplete(response.responseText, status);
			}


		});

});


function onPaymentDeleteComplete(response, status) {

	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divPaymentsGrid").html(resultSet.data);
		}

		else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	}

	else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	}

	else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();

	}

}

 