package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String listUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@PostMapping("/add")
	public String addUser(@RequestParam String name, @RequestParam String email) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		userService.saveUser(user);
		return "redirect:/users";
	}

	@PostMapping("/update")
	public String updateUser(@RequestParam Long id, @RequestParam String name, @RequestParam String email) {
		User user = userService.getUserById(id);
		if (user != null) {
			user.setName(name);
			user.setEmail(email);
			userService.updateUser(user);
		}
		return "redirect:/users";
	}

	@PostMapping("/delete")
	public String deleteUser(@RequestParam Long id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}
}