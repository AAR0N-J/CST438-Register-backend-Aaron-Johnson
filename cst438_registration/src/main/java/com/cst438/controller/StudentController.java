package com.cst438.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
@CrossOrigin
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@PostMapping("/student")
	public int addNewStudent(@RequestBody StudentDTO studentDTO) {
		Student check = studentRepository.findByEmail(studentDTO.email());
		
		if (check == null) {
			Student newStudent = new Student();
			newStudent.setName(studentDTO.name());
			newStudent.setEmail(studentDTO.email());
			newStudent.setStatusCode(studentDTO.status_code());
			newStudent.setStatus(studentDTO.status());
			studentRepository.save(newStudent);
			return newStudent.getStudent_id(); 
		} else { throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "student email already exists "+studentDTO.email()); }
	}
	
	@DeleteMapping("/student/{student_id}")
	public void deleteStudent(@PathVariable("studentId") int student_id,  @RequestParam("force") Optional<String> force) {
		
		Student student = studentRepository.findById(student_id).orElse(null);
		if (student != null){
			List<Enrollment> list = enrollmentRepository.findByStudentId(student_id);
			if (list.size()>0 && force.isEmpty()) { throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "student has enrollments");
			} else { studentRepository.deleteById(student_id); }
		} else { return; }
	}
	
	@PutMapping("/student/{student_id}")
	public void updateStudent(@PathVariable("studentId") int student_id, @RequestBody StudentDTO studentDTO) {
		Student student = studentRepository.findById(student_id).orElse(null);
		if (student == null) {
			throw  new ResponseStatusException( HttpStatus.NOT_FOUND, "student not found "+student_id);
		}
		// has email been changed, check that new email does not exist in database
		if (!student.getEmail().equals(studentDTO.email())) {
		// update name, email.  new email must not exist in database
			Student check = studentRepository.findByEmail(studentDTO.email());
			if (check != null) {
				// error.  email exists.
				throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "student email already exists "+studentDTO.email());
			}
		}
		student.setEmail(studentDTO.email());
		student.setName(studentDTO.name());
		student.setStatusCode(studentDTO.status_code());
		student.setStatus(studentDTO.status());
		studentRepository.save(student);
	}
	
	@GetMapping("/student")
	public List<Student> getAllStudents() {
		List<Student> students = (List<Student>) studentRepository.findAll();
		return students;
	}
	
	@GetMapping("/student/{studentId}")
	public StudentDTO getStudent(@PathVariable("studentId") int student_id) {
		
		Student student = studentRepository.findById(student_id).orElse(null);
		if (student != null) { 
			StudentDTO studentDTO = new StudentDTO(student.getStudent_id(), student.getName(), student.getEmail(), student.getStatusCode(), student.getStatus());
			return studentDTO; 
		} else { throw  new ResponseStatusException( HttpStatus.NOT_FOUND, "student not found "+student_id); }
	}
	

}
