package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

@RestController
@CrossOrigin
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/newStudent")
	public Boolean addNewStudent(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("status_code") int statusCode ) {
		
		Student check = studentRepository.findByEmail(email);
		if (check == null) {
			Student newStudent = new Student();
			newStudent.setName(name);
			newStudent.setEmail(email);
			newStudent.setStatusCode(statusCode);
			studentRepository.save(newStudent);
			return true; 
		} 
		System.out.println("Student already exists");
		return false;
	}
	
	@PostMapping("/deleteStudent")
	public Boolean deleteStudent(@RequestParam("email") String email, @RequestParam("status_code") int statusCode ) {
		
		Student student = studentRepository.findByEmail(email);
		if (student != null) {
			studentRepository.delete(student);
			return true; 
		} 
		System.out.println("Student not found");
		return false;
	}
	
	@PostMapping("/updateStudent")
	public Boolean updateStudent(@RequestParam("studentId") int student_id, @RequestParam("email") String email, @RequestParam("status_code") int statusCode ) {
		
		Student student = studentRepository.findByID(student_id);
		if (student != null) {
			student.setEmail(email);
			student.setStatusCode(statusCode);
			return true; 
		} 
		System.out.println("Student not found");
		return false;
	}
	
	@GetMapping("/listStudent")
	public Iterable<Student> listStudent() {
		return studentRepository.findAll();
	}
	
	@GetMapping("/getStudent")
	public Student getStudent(@RequestParam("studentId") int student_id) {
		
		Student student = studentRepository.findByID(student_id);
		if (student != null) return student; 
		System.out.println("Student not found");
		return null;
	}
	

}
