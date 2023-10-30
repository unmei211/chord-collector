package it.omsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/collector")
public class CollectorController {
    @GetMapping()
    public String collector(ModelMap model) {
        System.out.println("Hello");
        return "collector";
    }
}
