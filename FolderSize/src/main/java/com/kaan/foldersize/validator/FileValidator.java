package com.kaan.foldersize.validator;

/**
 * The Class FileValidator.
 * 
 * @author Kaan Kilicdogan
 */
public final class FileValidator {

	private static final String VALIDATION_STRING = "[0-9]{1,2}[ ]{1}[0-9]{1,47}";
	
	/**
	 * Instantiates a new file validator.
	 */
	private FileValidator() {
		throw new IllegalStateException(" File Validator class");
	}
	
	/**
	 * Checks if is valid.
	 *
	 * @param fileValue the file value
	 * @return true, if is valid
	 */
	public static boolean isValid(String fileValue) {
		return fileValue.matches(VALIDATION_STRING) ? true : false;
	}
}
