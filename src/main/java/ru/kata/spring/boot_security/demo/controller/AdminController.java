package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UserService uService;

	private RoleRepository roleRepository;

	@Autowired
	public AdminController(UserService userService, RoleRepository roleRepository) {
		this.uService = userService;
		this.roleRepository = roleRepository;
	}

	@GetMapping(value = "/allUsers")
	public String usersList(ModelMap model, Principal principal) {
		List<User> users = uService.getAllUsers();
		model.addAttribute("users", users);
		User user = (User) uService.loadUserByUsername(principal.getName());
		model.addAttribute("admin", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("roles", roleRepository.findAll());
		return "admin";
	}

	@PostMapping(value = "/saveUser")
	public String saveUser(@ModelAttribute("user")User user, BindingResult bindingResult) {
		uService.saveUser(user);
		return "redirect:/admin/allUsers";
	}

	@PostMapping(value = "/updateUser/{id}")
	public String updateUser(@PathVariable(name = "id") int id, @ModelAttribute("user")User user) {
		user.setId(id);
		uService.saveUser(user);
		return "redirect:/admin/allUsers";
	}

	@RequestMapping(value = "/deleteUser/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
		uService.deleteUser(id);
		return "redirect:/admin/allUsers";
	}
	
}