package com.example.schoolmanager.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.respository.local.StudentRepository;
import com.example.schoolmanager.respository.online.OnlineStudentRepository;

@Service
public class StudentSyncService {

    @Autowired
    private StudentRepository localRepository;

    @Autowired
    private OnlineStudentRepository onlineRepository;

    public Student addStudent(Student student) {
        if (student.getId() == null || student.getId().isEmpty()) {
            student.setId(java.util.UUID.randomUUID().toString());
        }
        Student localSaved = localRepository.save(student);
        Student onlineCopy = new Student(
            localSaved.getId(),
            localSaved.getName(),
            localSaved.getAge(),
            localSaved.getEmail(),
            localSaved.getGender()
        );
        onlineRepository.save(onlineCopy);
        return localSaved;
    }

    public void deleteStudent(String id) {
        if (localRepository.existsById(id)) {
            localRepository.deleteById(id);
        }
        if (onlineRepository.existsById(id)) {
            onlineRepository.deleteById(id);
        }
    }

    public List<Student> getAllMerged() {
        return mergeById(localRepository.findAll(), onlineRepository.findAll());
    }

    public List<Student> findByNameMerged(String name) {
        return mergeById(
            localRepository.findByNameContainingIgnoreCase(name),
            onlineRepository.findByNameContainingIgnoreCase(name)
        );
    }

    public Student getStudentByIdMerged(String id) {
        Student localStudent = localRepository.findById(id).orElse(null);
        if (localStudent != null) {
            return localStudent;
        }
        return onlineRepository.findById(id).orElse(null);
    }

    public Student updateStudent(String id, Student student) {
        Student localEntity = localRepository.findById(id).orElse(null);
        Student onlineEntity = onlineRepository.findById(id).orElse(null);
        if (localEntity == null && onlineEntity == null) {
            return null;
        }

        if (localEntity == null) {
            localEntity = new Student(
                id,
                student.getName(),
                student.getAge(),
                student.getEmail(),
                student.getGender()
            );
        } else {
            localEntity.setName(student.getName());
            localEntity.setAge(student.getAge());
            localEntity.setEmail(student.getEmail());
            localEntity.setGender(student.getGender());
        }

        if (onlineEntity == null) {
            onlineEntity = new Student(
                id,
                student.getName(),
                student.getAge(),
                student.getEmail(),
                student.getGender()
            );
        } else {
            onlineEntity.setName(student.getName());
            onlineEntity.setAge(student.getAge());
            onlineEntity.setEmail(student.getEmail());
            onlineEntity.setGender(student.getGender());
        }

        localRepository.save(localEntity);
        onlineRepository.save(onlineEntity);
        return localEntity;
    }

    private List<Student> mergeById(List<Student> local, List<Student> online) {
        Map<String, Student> merged = new LinkedHashMap<>();
        for (Student student : local) {
            if (student.getId() != null) {
                merged.put(student.getId(), student);
            }
        }
        for (Student student : online) {
            String id = student.getId();
            if (id != null && !merged.containsKey(id)) {
                merged.put(id, student);
            }
        }
        return new ArrayList<>(merged.values());
    }
}
