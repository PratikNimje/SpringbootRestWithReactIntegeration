package com.app.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class TestEmployeeRestController {

	private static final Logger log =LoggerFactory.getLogger(TestEmployeeRestController.class);	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@Disabled
	public void testSaveEmployee()throws Exception{

		//1. Create Dummy Http request
		MockHttpServletRequestBuilder request =MockMvcRequestBuilders.post("/rest/employees/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"ename\":\"pratik\",\"edept\":\"Java\",\"esal\":2.23}")
				;

		//2 Execute request and get result
		MvcResult result =mockMvc.perform(request).andReturn();

		//3 Read response object from mvc result
		MockHttpServletResponse response =result.getResponse();

		//4 validate assert detail
		assertEquals(HttpStatus.OK.value(), response.getStatus());

		// if it is not saved
		if(!response.getContentAsString().contains("Employee Saved")) {
			fail();
		}


	}
	@Test
	@Disabled
	public void testGetAllEmployee()throws Exception {
		//1 create dummy request object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get("/rest/employees/all");

		//2 Execute request and get result
		MvcResult result =	mockMvc.perform(request).andReturn();

		//3 read response object from result
		MockHttpServletResponse response =	result.getResponse();

		//4 Validate response assert data
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("application/json",response.getContentType());
		String resBody =  response.getContentAsString();

		if(resBody==null || resBody.length()==0) {
			fail();
		}
	}

	@Test
	@Disabled
	public void testDeleteEmployeeExist()throws Exception {
		//1 create one dummy request object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.delete("/rest/employees/delete/1");

		//2 execute request and get result
		MvcResult result =	mockMvc.perform(request).andReturn();

		//3 Read response object from result
		MockHttpServletResponse response=	result.getResponse();
		//4 validate assert details
		//expected status and actual status
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if (!response.getContentAsString().contains("successfully deleted")) {
			fail();
		}

	}
	@Test
	@Disabled
	public void testDeleteNotExist() throws Exception{
		//1 create one dummy request object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/rest/employees/delete/202");

		//2 execute request and get result
		MvcResult result =     mockMvc.perform(request).andReturn();
		//3 Read response object from result
		MockHttpServletResponse response =    result.getResponse();
		//4 Validate response (assert data)
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		if(!response.getContentAsString().contains(" Not Exist")) {
			fail();
		}

	}

	@Test
	@Disabled
	public void testUpdateEmployee()throws Exception {
		//1 create dummy request object
		MockHttpServletRequestBuilder request =MockMvcRequestBuilders
				.put("/rest/employees/update/5")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"ename\":\"rhidant\",\"edept\":\"boot\",\"esal\":3.3}");
		log.info("Object is created");
		//2 execute request and get result
		MvcResult result =mockMvc.perform(request).andReturn();
		log.info("Result get back");
		//3 Read response object from result
		MockHttpServletResponse response =result.getResponse();
		log.info("response gated");
		//4 validate response (assert data)
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		log.info("Success");
		if (!response.getContentAsString().contains("Employee Updated")) {
			fail();
			log.error("error");
		}

	}
	@Test
	@Disabled
	public void testUpdateEmployeeIdNotExist()throws Exception {
		//1 create dummy request object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put("/rest/employees/update/43")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"ename\":\"rhidant\",\"edept\":\"ms\",\"esal\":3.3}");

		//2 execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();

		//3 read Response object from result
		MockHttpServletResponse response =result.getResponse();


		//4 validate response(assert data)
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		if(!response.getContentAsString().contains("Employee Not Exist")) {
			fail();
		}
	}

	@Test
	 @Disabled 
	public void testGetOneEmployee() throws Exception {
		//1 create one dummy request object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get("/rest/employees/one/5");

		//2 execute request and get result
		MvcResult result =	mockMvc.perform(request).andReturn();

		//3 Read response object from result
		MockHttpServletResponse response =   result.getResponse();
		//4 validate response
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		/*
		 * if(!response.getContentAsString().contains("Employee Exist")) { fail(); }
		 */
	}
	@Test
	@Disabled
	public void testGetOneEmployeeNotExist()throws Exception {
		//1 create dummy request object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get("/rest/employees/one/6");

		//2 execute request and get result
		MvcResult result =	mockMvc.perform(request).andReturn();

		//3 read response object from result
		MockHttpServletResponse response=result.getResponse();

		//4 validate response
		assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
		if(!response.getContentAsString().contains("Employee Not Exist")) {
			fail();
		}
	}

}
