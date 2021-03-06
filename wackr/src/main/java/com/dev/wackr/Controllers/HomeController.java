package com.dev.wackr.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
	@GetMapping("/display")
    public ModelAndView display() {
        return new ModelAndView("display");
    }

}