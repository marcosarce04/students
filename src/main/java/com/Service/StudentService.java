package com.Service;


import com.Dao.RestRepository;
import com.Entity.Student;
import com.Utilities.StringUtilities;
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
        return this.studentDao.findById(id);
    }

    public void removeStudentById(int id) {
        // aca puede ir logica de que si encuentra el estudiante lo devuelva sino devuelve un http error o algo asi
        this.studentDao.deleteById(id);
    }

    public void insertAndUpdateStudent(Student student) {
        Optional<Student> stu = studentDao.findById(student.getId());
        if (stu.isPresent()) {
            if (student.studentAttrAreCorrect()) {
                stu.get().setName(student.getName());
                stu.get().setCourse(student.getCourse());
                this.studentDao.save(stu.get());
            } else {

            }
        } else {
            if (student.studentAttrAreCorrect()) {
                this.studentDao.save(student);
            }
        }
    }

}
