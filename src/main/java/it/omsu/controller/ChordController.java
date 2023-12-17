package it.omsu.controller;

import it.omsu.entity.Chord;
import it.omsu.entity.User;
import it.omsu.service.ChordService;
import it.omsu.service.ChordServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ChordController {
    private ChordService chordService;

    public ChordController(ChordService chordService) {
        this.chordService = chordService;
    }

//    @GetMapping("/collector")
//    public String collector(Model model) {
//        model.addAttribute("chordForm", new Chord());
//
//        return "collector";
//    }

    @PostMapping("/collector/create")
    public String createChord(@ModelAttribute("chordForm") @Valid Chord chordForm) {
        chordService.createChord(chordForm);
        return "redirect:/collector";
    }

    @GetMapping("/collector")
    public String getAllChords(Model model) {
        model.addAttribute("chordForm", new Chord());
        model.addAttribute("allChords", chordService.getAllChords());
        return "collector";
    }

    @PostMapping("/collector/update")
    public String updateChord(@ModelAttribute Chord chord) {
        chordService.updateChord(chord);
        return "redirect:/admin";
    }

    @PostMapping("/collector/delete/{id}")
    public String deleteChord(@PathVariable Long id) {
        chordService.deleteChord(id);
        return "redirect:/admin";
    }
}
