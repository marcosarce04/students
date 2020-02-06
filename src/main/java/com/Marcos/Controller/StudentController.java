package com.Marcos.Controller;

import com.Marcos.Entity.Student;
import com.Marcos.Service.StudentService;
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

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Student> getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id) {
        this.studentService.removeStudentById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertAndUpdateStudent(@RequestBody Student student) {
        this.studentService.insertAndUpdateStudent(student);
    }

}
