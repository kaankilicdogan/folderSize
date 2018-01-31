"use strict";
// client side validation
$(document)
		.ready(
				function() {
					$('#fileSizeForm')
							.validate(
									{
										rules : {
											filesRow : {
												required : true,
												minlength : 2
											},
											clusterSizeRow : {
												required : true,
												min : 1,
												max : 1000000

											},
											folderCountRow : {
												required : true,
												min : 0,
												max : 50

											}
										},
										messages : {
											filesRow : {
												required : "The Files has to be filled.",
												minlength : "The Files has to be minimum 2 characters long. ({})"
											},
											clusterSizeRow : {
												required : "The cluster size has to be filled.",
												min : "The cluster size has to be minimum 1.",
												max : "The cluster size has to be maximum 1000000."
											},
											folderCountRow : {
												required : "The folder count has to be filled.",
												min : "The folder count has to be minimum 0.",
												max : "The folder count has to be maximum 50."
											}
										},
										errorElement : "em",
										errorPlacement : function(error,
												element) {
											// Add the `help-block` class to the
											// error element
											error.addClass("help-block");

											if (element.prop("type") === "checkbox") {
												error.insertAfter(element
														.parent("label"));
											} else {
												error.insertAfter(element);
											}
										},
										highlight : function(element,
												errorClass, validClass) {
											$(element).parents(".col-sm-5")
													.addClass("has-error")
													.removeClass("has-success");
										},
										unhighlight : function(element,
												errorClass, validClass) {
											$(element).parents(".col-sm-5")
													.addClass("has-success")
													.removeClass("has-error");
										}

									});
				});

var ajaxRequest = new AjaxRequest('POST', '/ajax/calculate', ajaxSuccessfulRequest,
		ajaxRequestFailed, 300);

// button clicked.
function submitButtonClicked() {
	document.getElementById("lblResult").innerHTML = "-";
	// validate form
	$("#fileSizeForm").validate();
	if ($("#fileSizeForm").valid()) {
		// make a ajax request
		ajaxRequest.makeRequest(getFormData());
	}
}

// get form data
function getFormData() {
	return {
		filesValue : document.getElementById("filesRow").value,
		clusterSizeValue : document.getElementById("clusterSizeRow").value,
		folderCountValue : document.getElementById("folderCountRow").value
	};
}

// ajax request
function AjaxRequest(httpMethod, url, successHandler, errorHandler, timeout) {

	var date = new Date();
	var offSet = date.getTimezoneOffset()
	return {
		httpMethod : httpMethod,
		url : url,
		successHandler : successHandler,
		errorHandler : errorHandler,
		timeout : timeout,
		makeRequest : function makeRequest(data) {
			$.ajax({
				type : this.httpMethod,
				contentType : "application/json; charset=utf-8;",
				url : this.url,
				beforeSend : function(request) {
					request.setRequestHeader("tz-accept", offSet);
				},
				data : JSON.stringify(data),
				timeout : this.timeout,
				success : function(response) {
					if (typeof (successHandler) != 'undefined') {
						successHandler(response.result);
					}
				},
				error : function(error) {
					// error object
					var requestErrorObject = error.responseJSON;
					// fault object
					var responseText = requestErrorObject.errorText;
					if (typeof (errorHandler) != 'undefined') {
						// call error handler
						errorHandler(responseText, this.httpMethod);
					}
				}
			});
		}
	}
}

// ajax error handler
function ajaxRequestFailed(error) {
	window.alert(error);
}

// ajax success handler
function ajaxSuccessfulRequest(data) {
	document.getElementById("lblResult").innerHTML = data;
}