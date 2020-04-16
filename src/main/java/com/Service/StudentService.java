package com.Service;


import com.Dao.RestRepository;
import com.Entity.Student;
import com.Utilities.StringUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.HttpRetryException;
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
         Optional<Student> stu = this.studentDao.findById(id);
         if (stu.isPresent()) return stu;
         else throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Estudiante inexistente");
    }

    public void removeStudentById(int id) {
        if (studentDao.findById(id).isPresent()) this.studentDao.deleteById(id);
        else throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Estudiante inexistente");
    }

    public void insertAndUpdateStudent(Student student) {
        Optional<Student> stu = studentDao.findStudentByName(student.getName());
        if (stu.isPresent()) {
            if (student.studentAttrAreCorrect()) {
                stu.get().setName(student.getName());
                stu.get().setCourse(student.getCourse());
                this.studentDao.save(stu.get());
            } else throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falta Materia");
        } else {
            if (student.studentAttrAreCorrect()) this.studentDao.save(student);
            else throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falta Nombre o Materia");
        }
    }

}
