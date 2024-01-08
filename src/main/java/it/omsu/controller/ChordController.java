package it.omsu.controller;

import it.omsu.entity.Chord;
import it.omsu.entity.Progression;
import it.omsu.entity.User;
import it.omsu.service.ChordService;
import it.omsu.service.ChordServiceImpl;
import it.omsu.service.ProgressionService;
import it.omsu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class ChordController {
    private ChordService chordService;
    private UserService userService;
    private ProgressionService progressionService;

    public ChordController(ChordService chordService, UserService userService, ProgressionService progressionService) {
        this.chordService = chordService;
        this.userService = userService;
        this.progressionService = progressionService;
    }

    @PostMapping("/collector/create")
    public String createChord(@ModelAttribute("chordForm") @Valid Chord chordForm) {
        chordService.createChord(chordForm);
        return "redirect:/collector";
    }

    @GetMapping("/collector")
    public String getAllChords(Model model) {
        Long user = userService.getCurrentUserById();
        model.addAttribute("user", user);
        model.addAttribute("progressionForm", new Progression());
        model.addAttribute("chordForm", new Chord());
        model.addAttribute("allChords", chordService.getAllChords());
        return "collector";
    }

}
