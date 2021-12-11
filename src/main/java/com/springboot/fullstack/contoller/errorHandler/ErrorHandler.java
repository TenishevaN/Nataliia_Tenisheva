package com.springboot.fullstack.contoller.errorHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

import org.springframework.validation.BindException;


@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex) {

        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorText", ex.getMessage());
        mav.setStatus(HttpStatus.NOT_ACCEPTABLE);
        mav.setViewName("error");
        mav.getModel().put("errorMessage", errorMessage);

        return mav;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleMethodEntityNotFoundException() {

        String message = "Object is not found";
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                message);
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorText", message);
        mav.setStatus(HttpStatus.NOT_FOUND);
        mav.setViewName("error");
        mav.getModel().put("errorMessage", errorMessage);

        return mav;
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleNullPointerException(NullPointerException ex) {
        String message = "There is no such object";
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorText", message);
        mav.setStatus(HttpStatus.BAD_REQUEST);
        mav.setViewName("error");
        mav.getModel().put("errorMessage", errorMessage);

        return mav;
    }

    @ExceptionHandler(CustomException.class)
    public ModelAndView handleCustomException(CustomException ex) {

        String message = ex.getTxtMessage();
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                message);
        ModelAndView mav = new ModelAndView();
        System.out.println("ex.getMessage() " + ex.getMessage());
        System.out.println("ex.getTxtMessage() " + message);
        System.out.println("errorMessage " + errorMessage);
        mav.addObject("errorText", message);
        mav.setStatus(HttpStatus.BAD_REQUEST);
        mav.setViewName("error");
        mav.getModel().put("errorMessage", errorMessage);

        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView ExceptionHandler(Exception ex) {

        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorText", "Error occur. Contact to administrator!");
        mav.setStatus(HttpStatus.NOT_ACCEPTABLE);
        mav.setViewName("error");
        mav.getModel().put("errorMessage", errorMessage);

        return mav;
    }
}
