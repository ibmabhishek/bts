package com.ibm.bts;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	/**
	 * method to create a bug
	 * 
	 * @param bug
	 * @param bindingResult
	 * @return details of the bug created
	 */

	@PostMapping("/bug")
	@ResponseStatus(code = HttpStatus.CREATED)
	String createBug(@RequestBody @Valid Bug bug, BindingResult bindingResult) {
		validateModel(bindingResult);
		logger.setLevel(Level.WARNING);
		logger.log(Level.WARNING, bug.toString());
		return bugService.createBug(bug);
	}

	/**
	 * method to search all the bugs
	 * 
	 * @return all the bugs
	 */

	@GetMapping("/bug")
	List<Bug> getBugs() {
		return bugService.getBugs();
	}

	/**
	 * method to search for a particular bug
	 * 
	 * @param bugId
	 * @return zero or matching bug
	 */

	@GetMapping("/bug/{id}")
	Optional<Bug> getBug(@PathVariable("id") String bugId) {
		return bugService.getOrder(bugId);

	}
	
	/**
	 * method to update a particular bug using bugId
	 * @param bug
	 * @param bindingResult
	 * @param bugId
	 */
	
	@PutMapping("bug/{id}")
	void updateBug(@RequestBody @Valid Bug bug, BindingResult bindingResult, @PathVariable("id") String bugId ) {
		validateModel(bindingResult);
		bug.setId(bugId);
		bugService.updateBug(bug);
	}
	
	/**
	 * method to delete a particular bug using bugId
	 * @param bugId
	 */
	
	@DeleteMapping("bug/{id}")
	void deleteBug(@PathVariable("id") String bugId) {
		bugService.deleteBug(bugId);
	}
}
