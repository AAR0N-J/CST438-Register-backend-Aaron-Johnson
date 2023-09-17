package com.cst438;

import static com.cst438.test.utils.TestUtils.fromJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cst438.controller.StudentController;


@SpringBootTest
@AutoConfigureMockMvc
public class JunitTestStudent {

	@Autowired
	private MockMvc mvc;

	@Test
	public void addNewStudent() throws Exception {
		MockHttpServletResponse response;
		response = mvc.perform(post("/newStudent").
                          accept(MediaType.APPLICATION_JSON)).
                          andReturn().
                          getResponse();
		StudentController s = fromJsonString(
                          response.getContentAsString(), 
                          StudentController.class);
		
	}
	
	@Test
	public void deleteStudent() throws Exception {
		MockHttpServletResponse response;
		response = mvc.perform(post("/deleteStudent").
                          accept(MediaType.APPLICATION_JSON)).
                          andReturn().
                          getResponse();
		StudentController s = fromJsonString(
                          response.getContentAsString(), 
                          StudentController.class);
		
		
	}
	
	@Test
	public void updateStudent() throws Exception {
		MockHttpServletResponse response;
		response = mvc.perform(post("/updateStudent").
                          accept(MediaType.APPLICATION_JSON)).
                          andReturn().
                          getResponse();
		StudentController s = fromJsonString(
                          response.getContentAsString(), 
                          StudentController.class);
		
		
	}
	
	@Test
	public void getAllStudents() throws Exception {
		MockHttpServletResponse response;
		response = mvc.perform(post("/listStudent").
                          accept(MediaType.APPLICATION_JSON)).
                          andReturn().
                          getResponse();
		StudentController s = fromJsonString(
                          response.getContentAsString(), 
                          StudentController.class);
		
		
	}
	
	@Test
	public void getStudent() throws Exception {
		MockHttpServletResponse response;
		response = mvc.perform(post("/getStudent").
                          accept(MediaType.APPLICATION_JSON)).
                          andReturn().
                          getResponse();
		StudentController s = fromJsonString(
                          response.getContentAsString(), 
                          StudentController.class);
		
		
	}
}
