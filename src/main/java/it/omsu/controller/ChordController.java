package it.omsu.controller;

import it.omsu.entity.Chord;
import it.omsu.entity.Progression;
import it.omsu.entity.User;
import it.omsu.repository.UserRepository;
import it.omsu.service.ChordService;
import it.omsu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.jose.jwk.JWK;
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
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static jakarta.servlet.SessionTrackingMode.URL;

@Controller
@AllArgsConstructor
public class ChordController {
    private ChordService chordService;
    private UserService userService;
    private UserRepository userRepository;


    @PostMapping("/collector/create")
    public String createChord(@ModelAttribute("chordForm") @Valid Chord chordForm) {
        chordForm.setChordUser(userService.getCurrentUser());
        chordForm.setIsPublic(false);
        chordService.createChord(chordForm);
        return "redirect:/collector";
    }

    @GetMapping("/collector")
    public String getAllChords(Model model) {
        String userId = userService.getUserId();
        if (userRepository.findById(userId).isEmpty()) {
            userRepository.save(new User());
        }
        List<Chord> chords = chordService.getPublicChords();
        if (userId != null) {
            User user = userService.findUserById(userId);
            user.setChords(chords);
            chords.addAll(userService.getChords(userId));
            Set<Chord> set = new HashSet<>(chords);
            chords = set.stream().distinct().collect(Collectors.toList());
        }
        model.addAttribute("allChords", chords);
        model.addAttribute("user", userId);
        model.addAttribute("progressionForm", new Progression());
        model.addAttribute("chordForm", new Chord());
        return "collector";
    }
}
