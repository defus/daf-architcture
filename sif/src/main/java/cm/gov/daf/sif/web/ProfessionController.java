package cm.gov.daf.sif.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import cm.gov.daf.sif.model.Profession;
import cm.gov.daf.sif.model.Professions;
import cm.gov.daf.sif.service.ProfessionService;
import cm.gov.daf.sif.service.TypeProfessionService;
import cm.gov.daf.sif.web.utils.WebConstants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Albert
 */
@Controller
@SessionAttributes(types = Profession.class)
public class ProfessionController {

	private final ProfessionService professionService;
	private final TypeProfessionService typeProfessionService;

	@Autowired
	public ProfessionController(ProfessionService professionService, TypeProfessionService typeProfessionService) {
		this.professionService = professionService;
		this.typeProfessionService = typeProfessionService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/professions", method = RequestMethod.GET)
	public String search() {
		return "professions/list";
	}

	@RequestMapping(value = "/professions/data", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> searchJson(@RequestParam(value = "start", defaultValue = "0") Integer page,
			@RequestParam(value = "draw", defaultValue = "0") Integer draw,
			@RequestParam(value = "search[value]", defaultValue = "") String search) {

		Map<String, Object> data = new HashMap<String, Object>();

		Pageable pageable = new PageRequest(page, WebConstants.PAGE_SIZE);

		Page<Profession> results = this.professionService.find(search, pageable);

		data.put("data", results.getContent());
		data.put("draw", draw);
		data.put("recordsTotal", results.getTotalElements());
		data.put("recordsFiltered", results.getNumberOfElements());

		return data;
	}

	@RequestMapping(value = "/professions/new", method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		model.put("typeProfessions", typeProfessionService.findAll());
		return "professions/create";
	}

	@RequestMapping(value = "/professions/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid Profession profession, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "professions/createOrUpdateProfessionForm";
		} else {
			this.professionService.saveProfession(profession);
			status.setComplete();
			return "redirect:/professions/" + profession.getId();
		}
	}

	@RequestMapping(value = "/professions/{professionId}/edit", method = RequestMethod.GET)
	public String initUpdateOwnerForm(@PathVariable("professionId") int professionId, Model model) {
		Profession profession = this.professionService.findById(professionId);
		model.addAttribute(profession);
		model.addAttribute("typeProfessions", typeProfessionService.findAll());
		return "professions/createOrUpdateProfessionForm";
	}

	@RequestMapping(value = "/professions/{professionId}/edit", method = RequestMethod.PUT)
	public String processUpdateProfessionForm(@Valid Profession profession, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "professions/createOrUpdateProfessionForm";
		} else {
			this.professionService.saveProfession(profession);
			status.setComplete();
			return "redirect:/professions/{professionId}";
		}
	}

	/**
	 * Custom handler for displaying an owner.
	 *
	 * @param professionId
	 *            the ID of the profession to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@RequestMapping("/professions/{professionId}")
	public ModelAndView showProfession(@PathVariable("professionId") int professionId) {
		ModelAndView mav = new ModelAndView("professions/professionDetails");
		mav.addObject(this.professionService.findById(professionId));
		mav.addObject("typeProfessions", typeProfessionService.findAll());
		return mav;
	}

	@RequestMapping(value = { "/professions.xml", "/professions.html" })
	public String showProfessionList(Map<String, Object> model) {
		Professions professions = new Professions();
		// professions.getProfessionList().addAll(this.professionService.findAll());
		model.put("professions", professions);
		return "professions/professionsList";
	}

}
