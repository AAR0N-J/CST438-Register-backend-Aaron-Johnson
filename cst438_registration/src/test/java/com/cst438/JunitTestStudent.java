package com.cst438;

import static com.cst438.test.utils.TestUtils.fromJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cst438.domain.Student;


@SpringBootTest
@AutoConfigureMockMvc
class JunitTestStudent {

	@Autowired
	private MockMvc mvc;

    @SuppressWarnings("null")
    @Test
    void addNewStudent() throws Exception {
		MockHttpServletResponse response;
		response = mvc.perform(
				MockMvcRequestBuilders
					.post("/newStudent/12345")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		assertEquals(200,response.getStatus());


		String result = response.getContentAsString();
		assertEquals("true",result);
		
		MockHttpServletResponse response1 = null;
		response = mvc.perform(
				MockMvcRequestBuilders
					.post("/newStudent/12346")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		assertEquals(200,response1.getStatus());


		String result1 = response1.getContentAsString();
		assertEquals("true",result1);
		
	}

    @Test
    void deleteStudent() throws Exception {
		MockHttpServletResponse response;
		response = mvc.perform(
				MockMvcRequestBuilders
					.post("/deleteStudent/12345")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		assertEquals(200,response.getStatus());

		String result = response.getContentAsString();
		assertEquals("true",result);
		
		
	}

    @Test
    void updateStudent() throws Exception {
		MockHttpServletResponse response;
		response = mvc.perform(
				MockMvcRequestBuilders
					.post("/updateStudent/12346?enrolled")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		assertEquals(200,response.getStatus());

		String result = response.getContentAsString();
		assertEquals("true",result);
		
	}


    @Test
    void getStudent() throws Exception {
		MockHttpServletResponse response;
		response = mvc.perform(
				MockMvcRequestBuilders
					.post("/getStudent/12346")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		assertEquals(200,response.getStatus());

		Student result = fromJsonString(response.getContentAsString(),Student.class);
		assertEquals(12345,result.getStudent_id());
		
	}

    @Test
    void getAllStudent() throws Exception {
		MockHttpServletResponse response;
		response = mvc.perform(
				MockMvcRequestBuilders
					.post("/getAllStudent")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		assertEquals(200,response.getStatus());

		int result =response.getContentLength();
		assertNotEquals(0,result);
		
	}
}
