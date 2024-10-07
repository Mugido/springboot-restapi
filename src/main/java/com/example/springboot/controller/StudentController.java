package com.example.springboot.controller;

import com.example.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {


    ///http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1, "Uche", "Okoro");
//        return new ResponseEntity<>(student, HttpStatus.OK); OR
        return ResponseEntity.ok()
                .header("custom-header", "Uche")
                .body(student);
    }

    //http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Uche", "Okoro"));
        students.add(new Student(2, "Ada", "Ramatu"));
        students.add(new Student(3, "Vera", "Gagare"));
        students.add(new Student(4, "Emeka", "Enyiagu"));
        return ResponseEntity.ok(students);
    }

    //Spring boot REST API with Path Variable
    //{id} - URI Template Variable
    //http://localhost:8080/students/1
    //http://localhost:8080/students/1/uche/okoro
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName){
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);

//  FOR 1 pathvariable public Student studentPathVariable(@PathVariable int id){
//      return new Student(id, "Uche", "Okoro");
    }

    //Spring boot REST API with Request Param
    //http://localhost:8080/students/query?id=1
    //http://localhost:8080/students/query?id=1&firstName=Uche&lastName=Okoro
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName){
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);

// For 1 RequestParam   public Student studentRequestVariable(@RequestParam int id){
//    return new Student(id, "Uche", "Okoro");
    }

    //Spring boot REST API that handles HTTP POST Request using @PostMapping and @ResponseBody(creating new resource)
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
   public ResponseEntity <Student> createStudent(@RequestBody Student student){
       System.out.println(student.getId());
       System.out.println(student.getFirstName());
       System.out.println(student.getLastName());

       return new ResponseEntity<>(student, HttpStatus.CREATED);
   }

   // Spring Boot REST API that handles HTTP PUT Request(used for updating existing resource)
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return ResponseEntity.ok(student);
    }

    //Spring Boot REST API that handles HTTP DELETE Request(used for deleting existing resource)
    @DeleteMapping("{id}/delete")
    public ResponseEntity <String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
