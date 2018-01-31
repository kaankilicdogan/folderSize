package com.kaan.foldersize;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kaan.foldersize.controller.FolderSizeController;

/**
 * The Class FolderSizeApplicationTests.
 * 
 * @author Kaan Kilicdogan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FolderSizeApplicationTests {
	
	/** The folder size controller. */
	@Autowired
	private FolderSizeController folderSizeController;	
	 
	@Test
	public void contextLoads() {
		String result = folderSizeController.mainPage(null);
		 assertThat(result, is("main_page"));
	}

}
