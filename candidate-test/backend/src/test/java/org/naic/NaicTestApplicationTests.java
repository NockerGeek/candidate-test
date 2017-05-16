package org.naic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.naic.model.UserProfile;
import org.naic.service.UserProfileService;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class NaicTestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@MockBean
	private UserProfileService profileService;

	UserProfile mockProfile = new UserProfile("rbarr","password","Rob","Barr","nockergeek@gmail.com");

	@Test
	public void getTest() throws Exception {
		Mockito.when(
				profileService.getUserProfile(1L)).thenReturn(mockProfile);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/users").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"firstName\":\"Rob\",\"middleInit\":\"W\",\"lastName\":\"Barr\",\"email\":\"nockergeek@gmail.com\",\"address1\":\"709 Walnut St.\",\"address2\":\"\",\"city\":\"Greenwood\",\"state\":\"MO\",\"zip\":\"64034\",\"phone\":\"8168134385\"}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void emailTest() throws Exception {
		Mockito.when(
				profileService.getUserProfile(1L)).thenReturn(mockProfile);

		mockProfile.setEmail("");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/users", mockProfile).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "400";

		JSONAssert.assertEquals(expected, Integer.toString(result.getResponse().getStatus()), false);

	}

}
