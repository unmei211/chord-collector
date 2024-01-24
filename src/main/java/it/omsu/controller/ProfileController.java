package it.omsu.controller;

import it.omsu.entity.*;
import it.omsu.service.ProgressionService;
import it.omsu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class ProfileController {

    UserService userService;
    ProgressionService progressionService;

    public ProfileController(UserService userService, ProgressionService progressionService) {
        this.userService = userService;
        this.progressionService = progressionService;
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Long id = userService.getCurrentUserById();
        User user = userService.findUserById(id);
        Set<Progression> progressions = user.getProgressions();
        System.out.println("Progressions: " + progressions);
        model.addAttribute("progressions", progressions);
        return "profile";
    }

    @PostMapping("/profile/deleteProgression/{id}")
    public String deleteChord(@PathVariable Long id) {
        progressionService.deleteProgression(id);
        return "redirect:/profile";
    }

    @PatchMapping("/profile/updateProgression")
    public String updateChord() {
        System.out.println("lol");
        return "redirect:/profile";
    }

}
