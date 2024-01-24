package it.omsu.controller;

import it.omsu.entity.Chord;
import it.omsu.entity.Progression;
import it.omsu.entity.User;
import it.omsu.service.ChordService;
import it.omsu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ChordController {
    private ChordService chordService;
    private UserService userService;

    public ChordController(ChordService chordService, UserService userService) {
        this.chordService = chordService;
        this.userService = userService;
    }


    @PostMapping("/collector/create")
    public String createChord(@ModelAttribute("chordForm") @Valid Chord chordForm, @RequestParam("user") Long userId) {
        User user = userService.findUserById(userId);
        chordForm.setUser(user);
        chordForm.setPublic(false);
        chordService.createChord(chordForm);
        return "redirect:/collector";
    }

    @GetMapping("/collector")
    public String getAllChords(Model model) {
        Long userID = userService.getCurrentUserById();

        List<Chord> chords = chordService.getPublicChords();
        if (userID != null) {
            User user = userService.findUserById(userID);
            chords.addAll(user.getChords());
        }
        model.addAttribute("allChords", chords);
        model.addAttribute("user", userID);
        model.addAttribute("progressionForm", new Progression());
        model.addAttribute("chordForm", new Chord());
        return "collector";
    }

}
