package com.Controller;

import com.Entity.Student;
import com.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Iterable<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStudentById(@PathVariable("id") int id) {
        this.studentService.removeStudentById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertAndUpdateStudent(@RequestBody Student student) {
        this.studentService.insertAndUpdateStudent(student);
    }

}
