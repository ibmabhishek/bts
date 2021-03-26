package com.ibm.bts;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bts.entity.Bug;
import com.ibm.bts.service.BugService;

@RestController
public class BugController {
	
	Logger logger = Logger.getLogger(BugController.class.getName());

	@Autowired
	BugService bugService;

	private void validateModel(Errors bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Something went wrong, Please retry !!");
		}
	}

	@PostMapping("/bug")
	@ResponseStatus(code = HttpStatus.CREATED)
	String createBug(@RequestBody @Valid Bug bug,BindingResult bindingResult) {
		validateModel(bindingResult);
		//System.out.println(bug); //TODO Replace sysout with Logger
		logger.setLevel(Level.WARNING);
		logger.log(Level.WARNING,bug.toString());
		return bugService.createBug(bug);
	}

}
