package com.kaan.foldersize.restcontroller;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaan.foldersize.ajax.result.AjaxResult;
import com.kaan.foldersize.model.FolderSizeModel;
import com.kaan.foldersize.service.FolderSizeService;

/**
 * The Class FolderSizeRestController.
 * 
 * @author Kaan Kilicdogan
 */
@RestController
@RequestMapping("/ajax")
public class FolderSizeRestController {

	/** The folder size service. */
	@Autowired
	private FolderSizeService folderSizeService;
	
	/**
	 * Calculate the waste cluster sizes for given folders.
	 *
	 * @param folderSize the folder size
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the response entity
	 */
	@PostMapping(value = "calculate", produces = "application/json", consumes = "application/json")
	public ResponseEntity<AjaxResult> calculate(@RequestBody @Valid FolderSizeModel folderSize, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(new AjaxResult(bindingResult));
		}
		
		try {
			String result = this.folderSizeService.calculate(folderSize);
			return new ResponseEntity<>(new AjaxResult(result), HttpStatus.OK);
		} catch (ValidationException ex) {
			// catch validation exceptions and create error result
			return ResponseEntity.badRequest().body(new AjaxResult(ex));
		}
	}
}
