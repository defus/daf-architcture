package cm.gov.daf.sif.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test class for the UserResource REST controller.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/business-config.xml", "classpath:spring/tools-config.xml",
		"classpath:spring/mvc-core-config.xml" })
@WebAppConfiguration
@ActiveProfiles("spring-data-jpa")
public class ProfessionControllerTests {

	@Autowired
	private ProfessionController professionController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(professionController).build();
	}

	@Test
	public void testFindProfessionJsonWithResults() throws Exception {
		ResultActions actions = mockMvc.perform(get("/professions/data").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		actions.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.data[0].id").value(1)).andExpect(jsonPath("$.draw").value(0))
				.andExpect(jsonPath("$.recordsTotal").exists()).andExpect(jsonPath("$.recordsFiltered").exists());
	}

	@Test
	public void testFindProfessionJsonWith2ResultsPaginated() throws Exception {
		ResultActions actions = mockMvc.perform(get("/professions/data?start=0").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		actions.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.data[0].id").value(1)).andExpect(jsonPath("$.recordsTotal").value(6))
				.andExpect(jsonPath("$.recordsFiltered").value(6));
	}

	@Test
	public void testFindProfessionJsonWith1ResultsSearchedPaginated() throws Exception {
		ResultActions actions = mockMvc
				.perform(get("/professions/data?start=0&search[value]=Agriculteur").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		actions.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.data[0].id").value(1)).andExpect(jsonPath("$.recordsTotal").value(1))
				.andExpect(jsonPath("$.recordsFiltered").value(1));
	}

	@Test
	public void testFindProfession() throws Exception {
		ResultActions actions = mockMvc.perform(get("/professions").accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk());
		actions.andExpect(view().name("professions/list"));
	}

}
