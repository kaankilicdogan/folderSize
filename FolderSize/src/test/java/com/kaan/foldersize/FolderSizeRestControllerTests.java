package com.kaan.foldersize;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaan.foldersize.model.FolderSizeModel;
import com.kaan.foldersize.restcontroller.FolderSizeRestController;
import com.kaan.foldersize.service.FolderSizeService;

/**
 * The Class FolderSizeRestControllerTests.
 * 
 * @author Kaan Kilicdogan
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FolderSizeRestController.class)
public class FolderSizeRestControllerTests {

	/** The mvc. */
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private FolderSizeService folderSizeService;
	
	@Description("This tests folder size rest controller. Happy Path")
    @Test
    public void calculateTest() throws Exception {
    	FolderSizeModel folderSizeModel = new FolderSizeModel();
    	folderSizeModel.setClusterSizeValue(50);
    	folderSizeModel.setFolderCountValue(3);
    	folderSizeModel.setFilesValue("{0 55,0 47,1 86}");
    	given(this.folderSizeService.calculate(folderSizeModel)).willReturn("{48,14,0}");
    	
    	byte[] requestJson = toJson(folderSizeModel);
    	this.mvc.perform(post("/ajax/calculate").content(requestJson).accept(MediaType.ALL).contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andExpect(content()
    		      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Description("This tests folder size rest controller binding problem test.")
    @Test
    public void calculateBindingProblemTest() throws Exception {
    	FolderSizeModel folderSizeModel = new FolderSizeModel();    	
    	
    	byte[] requestJson = toJson(folderSizeModel);
    	this.mvc.perform(post("/ajax/calculate").content(requestJson).accept(MediaType.ALL).contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isBadRequest()).andExpect(content()
    		      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    /**
     * To json operation.
     *
     * @param r the object which will convert to JSON
     * @return the byte[] json result
     * @throws Exception the exception
     */
    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }
}
