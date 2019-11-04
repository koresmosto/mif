package com.stingion.makeitfine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by joe on 02.06.17.
 */
@Controller
public class StartUpController {

    enum AboutProjectInfo {
        Author {
            @Override
            public String details() {
                return "Stingion";
            }
        }, Purpose {
            @Override
            public String details() {
                return "Social Network for workers";
            }
        }, Stage {
            @Override
            public String details() {
                return "Development stage";
            }
        };

        public abstract String details();
    }

    enum Desc {
        Author(() -> "stingion@gmail.com"), Stage(() -> "development milestone");
        private String desc;

        Desc(Supplier<String> desc) {
            this.desc = desc.get();
        }

        public String description() {
            return desc;
        }
    }

    @GetMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("info")
    public @ResponseBody
    String greeting(@RequestParam(value = "details", required = false) String details) {
        var otherValue = "any value";
        switch (Optional.ofNullable(details).orElse(otherValue).toLowerCase()) {
            case "author":
                return AboutProjectInfo.Author.details() + " : " + Desc.Author.description();
            case "purpose":
                return AboutProjectInfo.Purpose.details();
            case "stage":
                return AboutProjectInfo.Stage.details() + " : " + Desc.Stage.description();
            default:
                return "Make it fine \"makeitfine\"";
        }
    }
}
