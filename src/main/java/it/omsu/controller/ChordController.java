package it.omsu.controller;

import it.omsu.entity.Chord;
import it.omsu.entity.Progression;
import it.omsu.entity.User;
import it.omsu.service.ChordService;
import it.omsu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static jakarta.servlet.SessionTrackingMode.URL;

@Controller
public class ChordController {
    private ChordService chordService;
    private UserService userService;

    public ChordController(ChordService chordService, UserService userService) {
        this.chordService = chordService;
        this.userService = userService;
    }


    @PostMapping("/collector/create")
    public String createChord(@ModelAttribute("chordForm") @Valid Chord chordForm, HttpServletRequest request) {

        chordForm.setIsPublic(false);
        chordService.createChord(chordForm);
        return "redirect:/collector";
    }

    @GetMapping("/collector")
    public String getAllChords(Model model, Principal principal) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        Long userID = null;
        List<Chord> chords = chordService.getPublicChords();
        if (userID != null) {
            User user = userService.findUserById(userID);
            chords.addAll(user.getChords());
            Set<Chord> set = new HashSet<Chord>(chords);
            chords = set.stream().distinct().collect(Collectors.toList());
        }
        model.addAttribute("allChords", chords);
        model.addAttribute("user", userID);
        model.addAttribute("progressionForm", new Progression());
        model.addAttribute("chordForm", new Chord());
        return "collector";
    }
}
