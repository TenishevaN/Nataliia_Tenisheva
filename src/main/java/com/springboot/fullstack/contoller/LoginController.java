package com.springboot.fullstack.contoller;

import com.springboot.fullstack.contoller.errorHandler.CustomException;
import com.springboot.fullstack.model.User;
import com.springboot.fullstack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import util.Security;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/login")
    public ModelAndView CheckLogin(User user) {

        String login = user.getLogin();
        String password = user.getPassword();
        String errorMessage;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            log.error("errorMessage --> " + errorMessage);
            throw new CustomException(errorMessage);
        }

        User findedUser = userRepository.findByLogin(login);
        log.info("Found in DB: user --> " + findedUser);

        String decryptedPassword = Security.decrypt(findedUser.getPassword());
        if (findedUser == null || !password.equals(decryptedPassword)) {
            errorMessage = "Cannot find user with such login/password";
            log.error("errorMessage --> " + errorMessage);
            throw new CustomException(errorMessage);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }
}
