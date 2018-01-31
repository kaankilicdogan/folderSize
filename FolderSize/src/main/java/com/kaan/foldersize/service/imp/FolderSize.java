package com.kaan.foldersize.service.imp;

/**
 * The Class FolderSize which Top Coder request.
 */
public class FolderSize {
	
	/**
	 * Calculate waste cluster size.
	 *
	 * @param files the files
	 * @param folderCount the folder count
	 * @param clusterSize the cluster size
	 * @return the int[] folder waste cluster size array.
	 */
	public int[] calculateWaste(String[] files, int folderCount, int clusterSize) {
	    
        int[] fileWasteSize = new int[folderCount];
       
        for(String file : files) {
            String[] fileInformation = file.split(" ");
            int fileNumber = Integer.parseInt(fileInformation[0]);
            int wasteSize = getWasteSize(Integer.parseInt(fileInformation[1]),clusterSize);
            wasteSize += fileWasteSize[fileNumber];
            fileWasteSize[fileNumber] = wasteSize;
        }
        
        return fileWasteSize;
    }
    
    /**
     * Gets the waste size of given file size.
     *
     * @param fileSize the file size
     * @param clusterSize the cluster size
     * @return the waste size
     */
    private int getWasteSize(int fileSize, int clusterSize){
        if(clusterSize > fileSize) {        	
        	return clusterSize - fileSize;    
        } else {
        	int fileModuleSize = fileSize % clusterSize;
            return clusterSize - fileModuleSize;
        }
    }

}
