package com.example.schoolmanager.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolmanager.service.StudentService;
import com.example.schoolmanager.model.Student;

@RestController
@RequestMapping("/api/students")
@CrossOrigin // cho phép frontend gọi
public class StudentController {

    @Autowired
    private StudentService service;

    private UUID parseUuid(String value) {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    //1. API thêm sinh viên
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        System.out.println("DEBUG - Creating student: name=" + student.getName() + ", email=" + student.getEmail());
        return service.addStudent(student);
    }
    //2. API xóa sinh viên
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id) {
        UUID uuid = parseUuid(id);
        if (uuid == null) {
            return "Invalid student ID.";
        }
        service.deleteStudent(uuid);
        return "Student with ID " + id + " has been deleted.";
    }
    //3. Tim kiếm sinh viên theo tên
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return service.findByName(name);
    }

    //4. API lấy sinh viên theo ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        UUID uuid = parseUuid(id);
        if (uuid == null) {
            return null;
        }
        return service.getStudentById(uuid);
    }

    //5. API lấy danh sách sinh viên
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }
    
    //6. API cập nhật sinh viên
    @PostMapping("/update/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) {
        UUID uuid = parseUuid(id);
        if (uuid == null) {
            return null;
        }
        Student existingStudent = service.getStudentById(uuid);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setAge(student.getAge());
            existingStudent.setEmail(student.getEmail());
            return service.addStudent(existingStudent);
        }
        return null;
    }

}