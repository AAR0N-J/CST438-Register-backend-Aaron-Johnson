package com.cst438.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
@CrossOrigin
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/newStudent")
	public Boolean addNewStudent(@RequestBody StudentDTO studentDTO) {
		
		Student check = studentRepository.findByEmail(studentDTO.email());
		if (check == null) {
			Student newStudent = new Student();
			newStudent.setName(studentDTO.name());
			newStudent.setEmail(studentDTO.email());
			newStudent.setStatusCode(studentDTO.status_code());
			studentRepository.save(newStudent);
			return true; 
		} 
		System.out.println("Student already exists");
		return false;
	}
	
	@PostMapping("/deleteStudent/{student_id}")
	public Boolean deleteStudent(@PathVariable String email, @PathVariable("status_code") int statusCode, @RequestParam Optional<Boolean> force) {
		
		Student student = studentRepository.findByEmail(email);
		if (student != null && student.getStatus().equals("enrolled")){
			System.out.println("Warning: Student is enrolled");
		}
		if (student != null) {
			studentRepository.delete(student);
			return true; 
		} 
		System.out.println("Student not found");
		return false;
	}
	
	@PutMapping("/updateStudent/{student_id}")
	public Boolean updateStudent(@PathVariable("studentId") int student_id, @PathVariable("email") String email, @PathVariable("status_code") int statusCode ) {
		
		Student student = studentRepository.findByStudentId(student_id);
		if (student != null) {
			student.setEmail(email);
			student.setStatusCode(statusCode);
			return true; 
		} 
		System.out.println("Student not found");
		return false;
	}
	
	@GetMapping("/listStudent")
	public List<Student> getAllStudents() {
		List<Student> students = (List<Student>) studentRepository.findAll();
		return students;
	}
	
	@GetMapping("/getStudent/{studentId}")
	public Student getStudent(@PathVariable("studentId") int student_id) {
		
		Student student = studentRepository.findByStudentId(student_id);
		if (student != null) return student; 
		System.out.println("Student not found");
		return null;
	}
	

}
