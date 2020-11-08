package ru.gb.trishkin.shop.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.trishkin.shop.aop.aspect.MeasureMethod;
import ru.gb.trishkin.shop.domain.User;
import ru.gb.trishkin.shop.dto.UserDto;
import ru.gb.trishkin.shop.service.UserService;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @MeasureMethod
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.getAll());
        return "usersList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String newUser(Model model){
        System.out.println("Called method newUser");
        model.addAttribute("user", new UserDto());
        return "user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/new")
    public String saveUser(UserDto dto, Model model){
        if(userService.save(dto)){
            return "redirect:/users";
        }
        else {
            model.addAttribute("user", dto);
            return "user";
        }
    }

    @RequestMapping("/reg")
    public String registration(Model model){
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping(value = "/reg")
    public String regNewUser(UserDto dto, Model model){
        if(userService.save(dto)){
            return "redirect:/home";
        }
        else {
            dto.setExist(true);
            model.addAttribute("user", dto);
            return "registration";
        }
    }

    @PostAuthorize("isAuthenticated() and #username == authentication.principal.username")
    @GetMapping("/{name}/roles")
    @ResponseBody
    public String getRoles(@PathVariable("name") String username){
        System.out.println("Called method getRoles");
        User byName = userService.findByName(username);
        return byName.getRole().name();
    }

    @MeasureMethod
    @PostAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal){
        if(principal == null){
            throw new RuntimeException("You are not authorize");
        }
        User user = userService.findByName(principal.getName());

        UserDto dto = UserDto.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
        model.addAttribute("user", dto);
        return "profile";
    }

    @PostAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    public String updateProfileUser(UserDto dto, Model model, Principal principal){
        if(principal == null
                || !Objects.equals(principal.getName(), dto.getUsername())){
            throw new RuntimeException("You are not authorize");
        }
        if(dto.getPassword() != null
                && !dto.getPassword().isEmpty()
                && !Objects.equals(dto.getPassword(), dto.getMatchingPassword())){
            model.addAttribute("user", dto);
            return "/users/profile";
        }

        userService.updateProfile(dto);
        return "redirect:/users/profile";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        System.out.println(userService.getById(id).getName());
        model.addAttribute("user", userService.getById(id));
        return "edit-users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public String modifyUser(User user) {
        userService.createOrSaveUser(user);
        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        System.out.println(userService.getById(id).getName());
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
