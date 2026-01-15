package com.example.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.schoolmanager.service.StudentService;

@Controller
public class StudentViewController {

    @Autowired
    private StudentService service;

    @GetMapping("/")
    public String index() {
        return "index"; // index.html
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", service.getAll());
        return "student"; // student.html
    }
}
