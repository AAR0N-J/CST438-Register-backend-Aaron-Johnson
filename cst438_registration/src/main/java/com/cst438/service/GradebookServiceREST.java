package com.cst438.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentDTO;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.FinalGradeDTO;

@Service
@ConditionalOnProperty(prefix = "gradebook", name = "service", havingValue = "rest")
@RestController
public class GradebookServiceREST implements GradebookService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${gradebook.url}")
	private static String gradebook_url;

	@Override
	public void enrollStudent(String student_email, String student_name, int course_id) {
		System.out.println("Start Message "+ student_email +" " + course_id);

		 EnrollmentDTO enrollmentDTO = new EnrollmentDTO(0, student_email, student_name, course_id);

		    // Define the URL for the Gradebook backend
		    String gradebookUrl = "https://example.com/api/enroll";

		    // Create HttpHeaders with the content type
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);

		    // Create an HttpEntity with the EnrollmentDTO and headers
		    HttpEntity<EnrollmentDTO> requestEntity = new HttpEntity<>(enrollmentDTO, headers);

		    // Create a RestTemplate instance
		    RestTemplate restTemplate = new RestTemplate();

		    // Send the POST request and get the response as EnrollmentDTO
		    EnrollmentDTO responseDTO = restTemplate.postForObject(gradebookUrl, requestEntity, EnrollmentDTO.class);
	}

	@Autowired
	EnrollmentRepository enrollmentRepository;
	/*
	 * endpoint for final course grades
	 */
	@PutMapping("/course/{course_id}")
	@Transactional
	public void updateCourseGrades( @RequestBody FinalGradeDTO[] grades, @PathVariable int course_id) {
		System.out.println("Grades received "+grades.length);

		for (FinalGradeDTO gradeDTO : grades) {
	        // Assuming you have student ID in FinalGradeDTO, and assuming Enrollment entity has courseId and studentId fields.
	        // You might need to adjust the code depending on your actual entity structure.
	        Enrollment enrollment = enrollmentRepository.findByEmailAndCourseId(gradeDTO.studentEmail(), course_id);

	        if (enrollment != null) {
	            // Update the grade in the enrollment record
	            enrollment.setCourseGrade(gradeDTO.grade());

	            // Save the updated enrollment record to the database
	            enrollmentRepository.save(enrollment);
	        }
		}
	}
}
