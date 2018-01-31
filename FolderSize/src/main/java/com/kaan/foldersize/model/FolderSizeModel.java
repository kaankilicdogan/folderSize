package com.kaan.foldersize.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Class FolderSizeModel.
 * 
 * @author Kaan Kilicdogan
 */
public class FolderSizeModel {

	/** The files value. Gives which folder has this amount of files */
	@NotEmpty
	@Size(min=2)
	private String filesValue;
	
	/** The cluster size value. */
	@NotNull
	@Min(1)
	@Max(1000000)
	private int clusterSizeValue;
	
	/** The folder count value. */
	@NotNull
	@Min(0)
	@Max(50)
	private int folderCountValue;

	/**
	 * Gets the files value.
	 *
	 * @return the files value
	 */
	public String getFilesValue() {
		return filesValue;
	}

	/**
	 * Sets the files value.
	 *
	 * @param filesValue the new files value
	 */
	public void setFilesValue(String filesValue) {
		this.filesValue = filesValue;
	}

	/**
	 * Gets the cluster size value.
	 *
	 * @return the cluster size value
	 */
	public int getClusterSizeValue() {
		return clusterSizeValue;
	}

	/**
	 * Sets the cluster size value.
	 *
	 * @param clusterSizeValue the new cluster size value
	 */
	public void setClusterSizeValue(int clusterSizeValue) {
		this.clusterSizeValue = clusterSizeValue;
	}

	/**
	 * Gets the folder count value.
	 *
	 * @return the folder count value
	 */
	public int getFolderCountValue() {
		return folderCountValue;
	}

	/**
	 * Sets the folder count value.
	 *
	 * @param folderCountValue the new folder count value
	 */
	public void setFolderCountValue(int folderCountValue) {
		this.folderCountValue = folderCountValue;
	}
}
