
package com.application.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginDisplayController {


	private String successView;

	@RequestMapping(value = "/LoginDisplay.do", method = RequestMethod.GET)
	public String login(ModelMap model) {

		return getSuccessView();

	}

	@RequestMapping(value = "/LoginDisplay.do" ,params = "loginError", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return getSuccessView();
	}

	@RequestMapping(value = "/LoginDisplay.do", params="logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		return "Welcome";
	}

	public String getSuccessView() {

		return successView;
	}

	public void setSuccessView(String successView) {

		this.successView = successView;
	}

}
