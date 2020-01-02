package com.Marcos.Service;


import com.Marcos.Dao.RestRepository;
import com.Marcos.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    @Qualifier("RestRepositoryData")
    private RestRepository studentDao;

    public Iterable<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        // aca puede ir logica de que si encuentra el estudiante lo devuelva sino devuelve un http error o algo asi
        return this.studentDao.findById(id);
    }

    public void removeStudentById(int id) {
        // aca puede ir logica de que si encuentra el estudiante lo devuelva sino devuelve un http error o algo asi
        this.studentDao.deleteById(id);
    }

    public void insertAndUpdateStudent(Student student) {
        Optional<Student> s = studentDao.findById(student.getId());
        if (s.isPresent()) {
            if (student.getName() != null && !student.getName().isEmpty()) {
                s.get().setName(student.getName());
            }
            if (student.getCourse() != null && !student.getCourse().isEmpty()) {
                s.get().setCourse(student.getCourse());
            }
            this.studentDao.save(s.get());
        } else {
            this.studentDao.save(student);
        }
    }
}
