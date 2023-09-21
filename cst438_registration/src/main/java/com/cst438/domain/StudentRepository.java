package com.cst438.domain;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository <Student, Integer> {
	
	 Student findByEmail(String email); 
	 
	 Student findByStudentId(int student_id);
	
	 Student[] findByNameStartsWith(String name);

}
