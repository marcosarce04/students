package com.Marcos.Service;


import com.Marcos.Dao.StudentDao;
import com.Marcos.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

    @Autowired
    @Qualifier("fakeData")
    private StudentDao studentDao;

    public Collection<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public Student getStudentById(int id) {
        // aca puede ir logica de que si encuentra el estudiante lo devuelva sino devuelve un http error o algo asi
        return this.studentDao.getStudentById(id);
    }

    public void removeStudentById(int id) {
        // aca puede ir logica de que si encuentra el estudiante lo devuelva sino devuelve un http error o algo asi
        this.studentDao.removeStudentById(id);
    }

    public void updateStudent(Student student) {
        this.studentDao.updateStudent(student);
    }

    public void insertStudent(Student student) {
        this.studentDao.insertStudent(student);
    }
}
