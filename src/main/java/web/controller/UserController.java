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
		System.out.println("userUpdate");
		userService.updateUser(user);
		return "redirect:/users";
	}

	@PostMapping("/edit/{id}")
	public String deleteUser(@RequestParam int id) {
		System.out.println("id: " + id);
		System.out.println("userdel: " + userService.getUserById(id));
		userService.deleteUser(id);
		return "redirect:/users";
	}
}