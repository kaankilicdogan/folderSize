package com.kaan.foldersize;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.validation.ValidationException;

import org.junit.Test;
import org.springframework.context.annotation.Description;

import com.kaan.foldersize.model.FolderSizeModel;
import com.kaan.foldersize.service.FolderSizeService;
import com.kaan.foldersize.service.imp.FolderSizeServiceImp;

/**
 * The Class FolderSizeBusinessTests.
 * 
 * @author Kaan Kilicdogan
 */
public class FolderSizeBusinessTests {
	
	@Description("This tests the happy path of the folder size business.")
	@Test()
	public void folderSizeHappyPath() {
		FolderSizeModel folderSizeModel = new FolderSizeModel();
		FolderSizeService folderSizeService = new FolderSizeServiceImp();
    	folderSizeModel.setClusterSizeValue(50);
    	folderSizeModel.setFolderCountValue(3);
    	folderSizeModel.setFilesValue("{0 55,0 47,1 86}");
    	
    	String result = folderSizeService.calculate(folderSizeModel);
    	assertThat(result, is("{48,14,0}"));
	}
	
	@Description("This tests the empty files.")
	@Test()
	public void folderSizeEmptyFiles() {
		FolderSizeModel folderSizeModel = new FolderSizeModel();
		FolderSizeService folderSizeService = new FolderSizeServiceImp();
    	folderSizeModel.setClusterSizeValue(50);
    	folderSizeModel.setFolderCountValue(3);
    	folderSizeModel.setFilesValue("{}");
    	
    	String result = folderSizeService.calculate(folderSizeModel);
    	assertThat(result, is("{0,0,0}"));
	}

	@Description("This tests the null files.")
	@Test(expected = ValidationException.class)
	public void folderSizeNullFiles() {
		FolderSizeModel folderSizeModel = new FolderSizeModel();
		FolderSizeService folderSizeService = new FolderSizeServiceImp();
    	folderSizeModel.setClusterSizeValue(50);
    	folderSizeModel.setFolderCountValue(3);
    	
    	folderSizeService.calculate(folderSizeModel);
	}
	
	@Description("This tests the invalid files.")
	@Test(expected = ValidationException.class)
	public void folderSizeInvalidFiles() {
		FolderSizeModel folderSizeModel = new FolderSizeModel();
		FolderSizeService folderSizeService = new FolderSizeServiceImp();
    	folderSizeModel.setClusterSizeValue(50);
    	folderSizeModel.setFolderCountValue(3);
    	folderSizeModel.setFilesValue("{111 1}");
    	
    	folderSizeService.calculate(folderSizeModel);    	
	}
}
