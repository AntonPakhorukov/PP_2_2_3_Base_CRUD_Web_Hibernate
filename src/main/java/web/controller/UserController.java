package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
		return "UsersPage";
	}

//	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@GetMapping("/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "createUser";
	}


	@RequestMapping(method = RequestMethod.POST)
	public String createUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "createUser";
		}
		userService.saveUser(user);
		return "redirect:/users";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam(value = "id", required = false) int id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "updateUser";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult,
							 @RequestParam(value = "id") int id) {
		if (bindingResult.hasErrors()) {
			return "updateUser";
		}
		userService.updateUser(user);
		return "redirect:/users";
	}
//	@PostMapping("/update")
//	public String updateUser(@RequestParam int id, @RequestParam String name, @RequestParam String email) {
//		User user = userService.getUserById(id);
//		if (user != null) {
//			user.setName(name);
//			user.setEmail(email);
//			userService.updateUser(user);
//		}
//		return "redirect:/users";
//	}

	@PostMapping("/delete")
	public String deleteUser(@RequestParam int id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}
}