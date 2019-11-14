/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.controller.exceptionhandling;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = {Error.class, Exception.class, RuntimeException.class})
  public ModelAndView handleIOException(HttpServletRequest request, Exception e) {
    logger.error(e.getMessage(), e);

    ModelAndView mav = new ModelAndView("error/generic_error");
    mav.addObject("datetime", new Date());
    mav.addObject("exception", e);
    mav.addObject("url", request.getRequestURL());

    return mav;
  }
}
