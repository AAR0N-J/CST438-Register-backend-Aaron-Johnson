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
	
	@PostMapping("/student")
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
		return false;
	}
	
	@PostMapping("/deleteStudent")
	public Boolean deleteStudent(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("status_code") int statusCode ) {
		
		Student check = studentRepository.findByEmail(email);
		if (check != null) {
			studentRepository.delete(check);
			return true; 
		} 
		return false;
	}
	

}
