package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@SpringBootApplication
public class DemosecuritytestApplication {


	@GetMapping("/")
	public String form() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse res) {
		session.invalidate();
		Cookie cookie = new Cookie("jsessionid", "");
		cookie.setMaxAge(0);
		res.addCookie(cookie);
		return "login";
	}

	@PostMapping("/login")
	public String submit(HttpSession session, @RequestParam String username, @RequestParam String password) {
		if (username.equalsIgnoreCase("user") && password.equalsIgnoreCase("pass") ) {
			session.setAttribute("user", username);
			return "secret";
		}
		return "login";
	}

	@GetMapping("/secret")
	public ModelAndView secret(HttpSession session) {

		//session.setAttribute("user", );
		if (session.getAttribute("user") != null) {
			return new ModelAndView("secret");
		}
		return new ModelAndView("login");
	}

	public static void main(String[] args) {
		SpringApplication.run(DemosecuritytestApplication.class, args);
	}
}
