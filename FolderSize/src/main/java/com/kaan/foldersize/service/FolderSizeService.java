package com.kaan.foldersize.service;

import com.kaan.foldersize.model.FolderSizeModel;

/**
 * The Interface FolderSizeService.
 * 
 * @author Kaan Kilicdogan
 */
public interface FolderSizeService {

	/**
	 * Calculate Waste cluster size for given folders.
	 *
	 * @param folderSizeModel the folder size model
	 * @return the string Waste cluster size string
	 */
	String calculate(FolderSizeModel folderSizeModel);
}
