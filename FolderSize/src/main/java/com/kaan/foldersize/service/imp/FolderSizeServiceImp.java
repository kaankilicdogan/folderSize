package com.kaan.foldersize.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import com.kaan.foldersize.model.FolderSizeModel;
import com.kaan.foldersize.service.FolderSizeService;
import com.kaan.foldersize.validator.FileValidator;

/**
 * The Class FolderSizeServiceImp.
 * 
 * @author Kaan Kilicdogan
 */
@Service
public class FolderSizeServiceImp implements FolderSizeService {

	/* (non-Javadoc)
	 * @see com.kaan.foldersize.service.FolderSizeService#calculate(com.kaan.foldersize.model.FolderSizeModel)
	 */
	@Override
	public String calculate(FolderSizeModel folderSizeModel) {
		if(folderSizeModel.getFilesValue() == null || folderSizeModel.getFilesValue().isEmpty()) {
			throw new ValidationException("Files is empty.");
		}
		
		String tempFilesString = folderSizeModel.getFilesValue().substring(1,folderSizeModel.getFilesValue().length()-1);
		List<String> fileList = new ArrayList<>(Arrays.asList(tempFilesString.split(",")));
		
		if(!tempFilesString.trim().isEmpty()) {
			for(String file : fileList) {
				if(!FileValidator.isValid(file)) {
					// this part is not valid
					throw new ValidationException("File value is not valid. Value : " + file);
				}
			}
		} else {
			fileList.clear();
		}
		
		
		// I dont make FOLDERSIZE as Service. Because I want to keep that file as Top Coder says.
		FolderSize folderSize = new FolderSize();
		
		int[] result = folderSize.calculateWaste(fileList.toArray(new String[fileList.size()]), folderSizeModel.getFolderCountValue(), folderSizeModel.getClusterSizeValue());
		
		StringBuilder resultStringBuilder = new StringBuilder();
		resultStringBuilder.append('{');
		for(int i = 0; i< result.length; i++) {
			if (i > 0) {
				resultStringBuilder.append(',');
			}
			resultStringBuilder.append(result[i]);			
		}
		resultStringBuilder.append('}');
		
		return resultStringBuilder.toString();
	}

}
