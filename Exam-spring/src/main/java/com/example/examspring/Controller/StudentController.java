package com.example.examspring.Controller;

import com.example.examspring.Model.Student;
import com.example.examspring.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    @GetMapping("/get")
    public ResponseEntity getStudent(){

        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body("Student added");
    }
    @PutMapping("update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id , @Valid@RequestBody Student student,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }if(studentService.updateStudent(id,student)){
            return ResponseEntity.status(HttpStatus.OK).body("Student updated");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){
        if(studentService.deleteStudent(id)){
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");

    }
    @GetMapping("/getByName/{name}")
    public ResponseEntity getByName(@PathVariable String name){
        if(studentService.getByName(name)==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("name not found");
        }else return ResponseEntity.status(HttpStatus.OK).body(studentService.getByName(name));

    }
}
