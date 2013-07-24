package com.application.spring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.application.spring.forms.LoginForm;

@Controller
public class LoginDisplayController {

	private String successView;

	@Autowired
	private AuthenticationSuccessHandler successHandler;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/LoginDisplay.do", method = RequestMethod.GET)
	public String login(LoginForm loginForm) {
		return getSuccessView();

	}

	@RequestMapping(value = "/LoginDisplay.do", params = "loginError", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return getSuccessView();
	}

	/**
	 * An alternative way to authenticate instead of using
	 * {@link UsernamePasswordAuthenticationFilter}
	 * 
	 * @param request
	 * @param response
	 * @param loginForm
	 * @param result
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/LoginDisplay.do", method = RequestMethod.POST)
	public String customLogin(HttpServletRequest request, HttpServletResponse response, @Valid LoginForm loginForm, BindingResult result)
			throws IOException, ServletException {

		// do whatever validation you want to do
		String j_username = loginForm.getJ_username();
		String j_password = loginForm.getJ_password();

		// if (!j_username.equals(j_password)) {
		// result.reject("login.fail", "Bad username/password");
		// }
		if (result.hasErrors()) {
			return getSuccessView();
		}
		// authenticate the user
		Authentication authentication = new UsernamePasswordAuthenticationToken(j_username, j_password,
				AuthorityUtils.createAuthorityList("ROLE_USER"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		successHandler.onAuthenticationSuccess(request, response, authentication);
		return null;
	}

	@RequestMapping(value = "/LoginDisplay.do", params = "logout", method = RequestMethod.GET)
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
