package cat.institutmarianao.controller;

import java.io.IOException;

import org.springframework.ui.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

// TODO - Configure Spring element and add mappings
public class LoginController {
	public String check(HttpServletRequest request) throws ServletException, IOException {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin/orders";
		}
		return "redirect:/users/orders";
	}

	public String login() {
		return "login";
	}

	public String loginFailed(Model model) {
		model.addAttribute("error", "true");
		return "login";
	}
}
