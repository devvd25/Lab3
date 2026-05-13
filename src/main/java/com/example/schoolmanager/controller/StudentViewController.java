package com.example.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.schoolmanager.service.StudentSyncService;

@Controller
public class StudentViewController {

    @Autowired
    private StudentSyncService syncService;

    @GetMapping("/")
    public String index() {
        return "index"; // index.html
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", syncService.getAllMerged());
        return "student"; // student.html
    }
}
