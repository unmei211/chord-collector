package it.omsu.controller;

import it.omsu.entity.Chord;
import it.omsu.service.ChordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ChordController {
    private ChordService chordService;

    public ChordController(ChordService chordService) {
        this.chordService = chordService;
    }

    @PostMapping("/collector/create")
    public String createChord(@ModelAttribute Chord chord) {
        chordService.createChord(chord);
        return "redirect:/collector";
    }

    @GetMapping("/collector")
    public String getAllChords(Model model) {
        List<Chord> chords = chordService.getAllChords();
        model.addAttribute("chords", chords);
        return "collector";
    }

    @PostMapping("/collector/update")
    public String updateChord(@ModelAttribute Chord chord) {
        chordService.updateChord(chord);
        return "redirect:/collector/";
    }

    @PostMapping("/collector/delete/{id}")
    public String deleteChord(@PathVariable Long id) {
        chordService.deleteChord(id);
        return "redirect:/collector";
    }
}
