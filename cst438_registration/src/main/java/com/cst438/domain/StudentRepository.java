package com.cst438.domain;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository <Student, Integer> {
	
	 Student findByEmail(String email); 
	 
	 Student findByStudent_id(int student_id);
	
	 Student[] findByNameStartsWith(String name);

}
