package com.example.examspring.Controller;

import com.example.examspring.Model.Teacher;
import com.example.examspring.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    @GetMapping("/get")
    public ResponseEntity getTeacher(){
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeachers());
    }
    @PostMapping("/add")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(HttpStatus.OK).body("teacher added");

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable String id , @Valid @RequestBody Teacher teacher,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }if(teacherService.updateTeacher(id,teacher)){
            return ResponseEntity.status(HttpStatus.OK).body("teacher updated");
        }else return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id ){
        if(teacherService.deleteTeacher(id)){
            return ResponseEntity.status(HttpStatus.OK).body("teacher deleted");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");

    }
    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable String id){
        if(teacherService.getById(id)==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
        }return ResponseEntity.status(HttpStatus.OK).body(teacherService.getById(id));

    }
}
