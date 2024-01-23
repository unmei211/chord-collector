package it.omsu.controller;

import it.omsu.entity.Progression;
import it.omsu.entity.User;
import it.omsu.service.ProgressionService;
import it.omsu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class ProgressionController {

    private ProgressionService progressionService;
    private UserService userService;

    public ProgressionController(ProgressionService progressionService, UserService userService) {
        this.progressionService = progressionService;
        this.userService = userService;
    }

    @PostMapping("/collector/createProgression")
    public String createChordProgression(@ModelAttribute("progressionForm") @Valid Progression progressionForm,
                                         @RequestParam("user") Long userId) {
        User user = userService.findUserById(userId);
        progressionForm.addUser(user);
        progressionService.createProgression(progressionForm);

        return "redirect:/collector";
    }
}


