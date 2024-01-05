package it.omsu.controller;

import it.omsu.entity.Chord;
import it.omsu.entity.Progression;
import it.omsu.service.ChordService;
import it.omsu.service.ProgressionService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProgressionController {

    private ProgressionService progressionService;

    public ProgressionController(ProgressionService progressionService) {
        this.progressionService = progressionService;
    }
    @PostMapping("/collector/createProgression")
    public String createChordProgression(@ModelAttribute("progressionForm") @Valid Progression progressionForm) {
        System.out.println("ASFLOFKAOFKAPOSFKAFPOAKPO");
        progressionService.createProgression(progressionForm);
        return "redirect:/collector";
    }
}
