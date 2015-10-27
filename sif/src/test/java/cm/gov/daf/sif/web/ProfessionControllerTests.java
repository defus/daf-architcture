package cm.gov.daf.sif.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.transaction.annotation.Transactional;

import org.hamcrest.Matchers;

/**
 * Tests de la profession.
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
	@Transactional
	public void testFindProfessionJsonWithResults() throws Exception {
		ResultActions actions = mockMvc.perform(get("/professions/data").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		actions.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.data[0].id").value(1)).andExpect(jsonPath("$.draw").value(0))
				.andExpect(jsonPath("$.recordsTotal").exists()).andExpect(jsonPath("$.recordsFiltered").exists());
	}

	@Test
	@Transactional
	public void testFindProfessionJsonWith2ResultsPaginated() throws Exception {
		ResultActions actions = mockMvc.perform(get("/professions/data?start=10").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		actions.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.data[0].id").value(11)).andExpect(jsonPath("$.recordsTotal").value(15))
				.andExpect(jsonPath("$.recordsFiltered").value(15)).andExpect(jsonPath("$.data", Matchers.hasSize(5)));
	}

	@Test
	@Transactional
	public void testFindProfessionJsonWith1ResultsSearchedPaginated() throws Exception {
		ResultActions actions = mockMvc
				.perform(get("/professions/data?start=0&search[value]=Agriculteur").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		actions.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.data[0].id").value(1)).andExpect(jsonPath("$.recordsTotal").value(1))
				.andExpect(jsonPath("$.recordsFiltered").value(1));
	}

	@Test
	@Transactional
	public void testFindProfession() throws Exception {
		mockMvc.perform(get("/professions").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(view().name("professions/list"));
	}

	@Test
	@Transactional
	public void testCreateProfessionShow() throws Exception {
		mockMvc.perform(get("/professions/new").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(view().name("professions/createOrUpdate"));
	}

	@Test
	@Transactional
	public void testCreateProfessionWithSuccess() throws Exception {
		mockMvc.perform(post("/professions/new").accept(MediaType.TEXT_HTML).param("libelle", "Mitoumba")
				.param("description", "Nana").param("typeProfession.id", "1").param("dateCreation", "23/09/2015")
				.param("salaireMin", "2390.90")).andExpect(status().is(302))
				.andExpect(view().name("redirect:/professions/16"));
	}

}
