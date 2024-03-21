package it.omsu.controller;

import it.omsu.entity.Chord;
import it.omsu.entity.Progression;
import it.omsu.entity.User;
import it.omsu.service.ChordService;
import it.omsu.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ChordController {
    private ChordService chordService;
    private UserService userService;

    public ChordController(ChordService chordService, UserService userService) {
        this.chordService = chordService;
        this.userService = userService;
    }


    @PostMapping("/collector/create")
    public String createChord(@RequestParam String name, @RequestParam String userId) {
        User user = userService.findUserById(userId);
        Chord chord = new Chord();
        chord.setName(name);
        chord.setUser(user);
        chord.setIsPublic(false);
        chordService.createChord(chord);
        return "redirect:/collector";
    }

    @GetMapping("/collector")
    public String getAllChords(Model model) {

        List<Chord> chords = chordService.getPublicChords();

        String userId = userService.getCurrentUserById();
        if (userService.findUserById(userId) == null) {
            User newUser = new User();
            newUser.setId(userId);
            userService.saveUser(newUser);
        }
        chords.addAll(userService.getUserChords(userId)
        );
        for (Chord chord : chords) {
            System.out.println(chord.getName());
        }
        model.addAttribute("allChords", chords);
        model.addAttribute("userID", userId);
        model.addAttribute("progressionForm", new Progression());
        model.addAttribute("chordForm", new Chord());
        return "collector";
    }

}
