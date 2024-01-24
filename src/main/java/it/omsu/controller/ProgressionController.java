package it.omsu.controller;

import it.omsu.entity.Progression;
import it.omsu.entity.User;
import it.omsu.service.ChordService;
import it.omsu.service.ProgressionService;
import it.omsu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProgressionController {

    private ProgressionService progressionService;
    private UserService userService;
    private final ChordService chordService;

    public ProgressionController(ProgressionService progressionService,
                                 UserService userService,
                                 ChordService chordService
    ) {
        this.progressionService = progressionService;
        this.userService = userService;
        this.chordService = chordService;
    }

    @PostMapping("/collector/createProgression")
    public String createChordProgression(@ModelAttribute("progressionForm") @Valid Progression progressionForm,
                                         @RequestParam("user") Long userId) {
        User user = userService.findUserById(userId);
        progressionForm.addUser(user);
        progressionService.createProgression(progressionForm);

        return "redirect:/collector";
    }

    @GetMapping("/progression/editor/{id}")
    public String getProgressionUpdatePage(
            @PathVariable("id") Long progressionId,
            Model model
    ) {
        Progression progression = progressionService.getProgressionById(progressionId);
        model.addAttribute("progression", progression);
        model.addAttribute("progressionId", progressionId);
        model.addAttribute("availableChords", chordService.getAllChords());
        return "progression/editor";
    }

    @PatchMapping("/progression/editor/{id}")
    public String updateProgression(
            @ModelAttribute("progression") @Valid Progression progression
    ) {
        progressionService.updateProgressionByTemplate(progression.getId(), progression);
        return "redirect:/profile";
    }
}


