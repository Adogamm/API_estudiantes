package com.example.springboot.app.service;
import com.example.springboot.app.exception.ResourceNotFoundException;
import com.example.springboot.app.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.springboot.app.repository.StudentRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository StudentRepository;

    @Override
    public Student createStudent(Student Student) {
        return StudentRepository.save(Student);
    }

    @Override
    public Student updateStudent(Student Student) {
        Optional<Student> StudentDb = this.StudentRepository.findById(Student.getId());

        if (StudentDb.isPresent()){
            Student StudentUpdate = StudentDb.get();
            StudentUpdate.setId(Student.getId());
            StudentUpdate.setNombre(Student.getNombre());
            StudentUpdate.setApellido(Student.getApellido());
            StudentUpdate.setCorreo(Student.getCorreo());
            StudentUpdate.setTelefono(Student.getTelefono());
            StudentUpdate.setEdad(Student.getEdad());
            StudentUpdate.setCarrera(Student.getCarrera());
            StudentRepository.save(StudentUpdate);
            return StudentUpdate;
        }else {
            throw new ResourceNotFoundException("Record not found with id : " + Student.getId());
        }
    }

    @Override
    public List<Student> getAllStudent() {
        return this.StudentRepository.findAll();
    }

    @Override
    public Student getStudentById(long StudentId) {
        Optional<Student> StudentDb = this.StudentRepository.findById(StudentId);

        if (StudentDb.isPresent()) {
            return StudentDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + StudentId);
        }
    }

    @Override
    public void deleteStudent(long StudentId) {
        Optional<Student> StudentDb = this.StudentRepository.findById(StudentId);

        if (StudentDb.isPresent()) {
            this.StudentRepository.delete(StudentDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id: " + StudentId);
        }
    }
}
