package com.stingion.makeitfine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by joe on 02.06.17.
 */
@Controller
public class StartUpController {

    @GetMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("projectName")
    public @ResponseBody
    String greeting() {
        return "Make it fine <makeitfine>";
    }
}
