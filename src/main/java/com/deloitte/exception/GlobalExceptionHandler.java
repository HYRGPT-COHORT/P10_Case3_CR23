package com.deloitte.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    

    /**
     * Handle NullPointerException
     */
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleNullPointerException(NullPointerException ex, HttpServletRequest request) {
        logger.error("NullPointerException at URL: {}", request.getRequestURL(), ex);
        
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMessage", "❌ A null pointer exception occurred. Please contact support.");
        mav.addObject("url", request.getRequestURL());
        return mav;
    }

    /**
     * Handle all other exceptions
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex, HttpServletRequest request) {
        logger.error("Exception occurred at URL: {}", request.getRequestURL(), ex);
        
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMessage", "❌ " + ex.getMessage());
        mav.addObject("url", request.getRequestURL());
        return mav;
    }
}
