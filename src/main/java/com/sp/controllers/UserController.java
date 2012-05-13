package com.sp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sp.entities.SchoolPakUserDetails;
import com.sp.exceptions.DuplicateUsernameException;
import com.sp.services.SchoolPakUserDetailsService;

@Controller
public class UserController {

	@Autowired
	private SchoolPakUserDetailsService userService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model)
	{
		model.addAttribute("user", new SchoolPakUserDetails());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String executeRegistration(@ModelAttribute("user") @Valid SchoolPakUserDetails user, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "register";
		}
		try
		{
			userService.createUser(user);
		}
		catch(DuplicateUsernameException e)
		{
			result.addError(new FieldError("user", "username", e.getMessage()));
			return "register";
		}
		return "redirect:/";
	}
	
}
