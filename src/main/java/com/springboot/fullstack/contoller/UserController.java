package com.springboot.fullstack.contoller;

import com.springboot.fullstack.dto.UserDto;
import com.springboot.fullstack.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "listUsers";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{login}")
    public ModelAndView getUser(@PathVariable String login) {

        UserDto findedUser = userService.getUser(login);
        if (findedUser == null) {
            throw new NullPointerException();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", findedUser);
        modelAndView.setViewName("cardUser");
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public ModelAndView createUser(@Valid UserDto userDto) {
        UserDto newUser = userService.createUser(userDto);
        if (newUser == null) {
            throw new NullPointerException();
        }
        ModelAndView modelAndView = new ModelAndView(
                new RedirectView("/user", false));
        modelAndView.getModel().put("user", newUser);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/user/update")
    public ModelAndView updateUser(@Valid UserDto userDto) {
        System.out.println("userDto "+ userDto);
        UserDto updatedUser = userService.updateUser(userDto);
        System.out.println("updatedUser "+ updatedUser);
        if (updatedUser == null) {
            throw new NullPointerException();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", updatedUser);
        modelAndView.setViewName("cardUser");
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/delete/{id}")
    public ModelAndView deleteUser(@PathVariable String id) {

        try {
            userService.deleteUser(id);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("users", userService.listUsers());
        modelAndView.setViewName("listUsers");
        return modelAndView;
    }
}