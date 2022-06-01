package com.example.springboot.app.controller;

import com.example.springboot.app.exception.Mensaje;
import com.example.springboot.app.model.Student;
import com.example.springboot.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService StudentService;

    @GetMapping("/listaStudents")
    public ResponseEntity<?> getAllStudent(){
        List<Student> lista = StudentService.getAllStudent();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new Mensaje("Sin estudiantes en la base de datos"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(StudentService.getAllStudent());
    }

    @GetMapping("/detalleStudent/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        return ResponseEntity.ok().body(this.StudentService.getStudentById(id));
    }

    @PostMapping("/creaStudent")
    public ResponseEntity<Student> createStudent(@RequestBody Student Student) {
        return ResponseEntity.ok().body(this.StudentService.createStudent(Student));
    }


    @PutMapping("/actualizaStudent/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student Student){
        Student.setId(id);
        return ResponseEntity.ok().body(this.StudentService.updateStudent(Student));
    }

    @DeleteMapping("/eliminaStudent/{id}")
    public HttpStatus deleteStudent(@PathVariable long id){
        this.StudentService.deleteStudent(id);
        return HttpStatus.OK;
    }
}
