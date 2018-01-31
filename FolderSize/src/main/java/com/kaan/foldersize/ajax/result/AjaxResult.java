package com.kaan.foldersize.ajax.result;

import javax.validation.ValidationException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * The Class AjaxResult. Every ajax request will have Ajax Result.
 * 
 * @author Kaan Kilicdogan
 */
public class AjaxResult {

	/** The successful. */
	private boolean successful;
	
	/** The error text. */
	private String errorText;
	
	/** The result. */
	private String result;
	
	/**
	 * Instantiates a new ajax result. 
	 * This constructer send request successfull information to client without any data.
	 */
	public AjaxResult() {
		this.successful = true;
		this.errorText = "";
		this.result = "";
	}
	
	/**
	 * Instantiates a new ajax result.
	 * This constructer send request successfull information to client with data.
	 *
	 * @param data the data result information
	 */
	public AjaxResult(String data) {
		this.successful = true;
		this.errorText = "";
		this.result = data;
	}
	
	/**
	 * Instantiates a new ajax result.
	 * This constructer send request failed information to client with error.
	 *
	 * @param exception the exception
	 */
	public AjaxResult(ValidationException exception) {
		this.successful = false;
		this.errorText = exception.getMessage();
		this.result = "";
	}
	
	/**
	 * Instantiates a new ajax result.
	 * This constructer send request failed information to client with validation error.
	 *
	 * @param bindingResult the binding result JSR-303 Bean Validation
	 */
	public AjaxResult(BindingResult bindingResult) {
		StringBuilder stringBuilder = new StringBuilder();
		for(ObjectError error : bindingResult.getAllErrors()) {
			stringBuilder.append(error instanceof FieldError ? ((FieldError) error).getField()
				: error.getObjectName());
			stringBuilder.append(" = ");
			stringBuilder.append(error.getDefaultMessage());
			stringBuilder.append(" ");
		}
		
		this.successful = false;
		this.errorText = stringBuilder.toString();
		this.result = "";
	}

	/**
	 * Checks if is successful.
	 *
	 * @return true, if is successful
	 */
	public boolean isSuccessful() {
		return successful;
	}

	/**
	 * Sets the successful.
	 *
	 * @param successful the new successful
	 */
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	/**
	 * Gets the error text.
	 *
	 * @return the error text
	 */
	public String getErrorText() {
		return errorText;
	}

	/**
	 * Sets the error text.
	 *
	 * @param errorText the new error text
	 */
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(String result) {
		this.result = result;
	}
}
