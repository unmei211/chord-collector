package it.omsu.controller;

import it.omsu.entity.Chord;
import it.omsu.service.ChordService;
import it.omsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminController {
    //    @Autowired
    private UserService userService;
    private ChordService chordService;

    public AdminController(UserService userService, ChordService chordService) {
        this.userService = userService;
        this.chordService = chordService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allChords", chordService.getAllChords());
        model.addAttribute("updateForm", new Chord());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId, @RequestParam(required = true, defaultValue = "") String action, Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @PostMapping("/admin/update/{id}")
    public String updateChord(@PathVariable Long id, @ModelAttribute("updateForm") @Valid String updateForm) {
        System.out.println("Admin");
        chordService.updateChord(id, updateForm);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteChord(@PathVariable Long id) {
        chordService.deleteChord(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }
}
