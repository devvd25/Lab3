package com.example.schoolmanager.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.service.StudentSyncService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin // cho phép frontend gọi
public class StudentController {

    @Autowired
    private StudentSyncService syncService;

    //1. API thêm sinh viên
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        System.out.println("DEBUG - Creating student: name=" + student.getName() + ", email=" + student.getEmail());
        return syncService.addStudent(student);
    }
    //2. API xóa sinh viên
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id) {
        syncService.deleteStudent(id);
        return "Student with ID " + id + " has been deleted.";
    }
    //3. Tim kiếm sinh viên theo tên
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return syncService.findByNameMerged(name);
    }

    //4. API lấy sinh viên theo ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return syncService.getStudentByIdMerged(id);
    }

    //5. API lấy danh sách sinh viên
    @GetMapping
    public List<Student> getAllStudents() {
        return syncService.getAllMerged();
    }
    
    //6. API cập nhật sinh viên
    @PostMapping("/update/{id}")
    public Student updateStudent(
        @PathVariable String id,
        @RequestBody Student student
    ) {
        return syncService.updateStudent(id, student);
    }

}