package cm.gov.daf.sif.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import cm.gov.daf.sif.titrefoncier.model.Departement;
import cm.gov.daf.sif.titrefoncier.model.Departements;
import cm.gov.daf.sif.titrefoncier.service.DepartementService;
import cm.gov.daf.sif.titrefoncier.service.RegionService;
import cm.gov.daf.sif.web.utils.WebConstants;

/**
 * @author Albert
 */
@Controller
@SessionAttributes(types = Departement.class)
public class DepartementController {

	private final DepartementService departementService;
	private final RegionService regionService;

	@Autowired
	public DepartementController(DepartementService departementService, RegionService regionService) {
		this.departementService = departementService;
		this.regionService = regionService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/departements", method = RequestMethod.GET)
	public String search() {
		return "departements/list";
	}

	@RequestMapping(value = "/departements/data", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> searchJson(@RequestParam(value = "start", defaultValue = "0") Integer page,
			@RequestParam(value = "draw", defaultValue = "0") Integer draw,
			@RequestParam(value = "search[value]", defaultValue = "") String search) {

		Map<String, Object> data = new HashMap<String, Object>();

		int pageNumber = (page + 1) / WebConstants.PAGE_SIZE;

		Pageable pageable = new PageRequest(pageNumber, WebConstants.PAGE_SIZE);

		Page<Departement> results = this.departementService.find(search, pageable);

		data.put("data", results.getContent());
		data.put("draw", draw);
		data.put("recordsTotal", results.getTotalElements());
		data.put("recordsFiltered", results.getTotalElements());

		return data;
	}

	@RequestMapping(value = "/departements/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		Departement departement = new Departement();
		model.addAttribute(departement);
		model.addAttribute("regions", regionService.findAll());
		return "departements/createOrUpdate";
	}

	@RequestMapping(value = "/departements/new", method = RequestMethod.POST)
	public String processCreationForm(Model model, @Valid Departement departement, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "departements/createOrUpdate";
		} else {
			this.departementService.save(departement);
			status.setComplete();
			return "redirect:/departements/" + departement.getId();
		}
	}

	@RequestMapping(value = "/departements/{departementId}/edit", method = RequestMethod.GET)
	public String initUpdateOwnerForm(@PathVariable("departementId") int departementId, Model model) {
		Departement departement = this.departementService.findById(departementId);
		model.addAttribute(departement);
		model.addAttribute("regions", regionService.findAll());
		return "departements/createOrUpdate";
	}

	@RequestMapping(value = "/departements/{departementId}/edit", method = RequestMethod.PUT)
	public String processUpdateDepartementForm(@Valid Departement departement, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "departements/createOrUpdate";
		} else {
			this.departementService.save(departement);
			status.setComplete();
			return "redirect:/departements/{departementId}";
		}
	}

	@RequestMapping(value = "/departements/{departementId}", method = RequestMethod.DELETE)
	public String processDeleteDepartement(@PathVariable("departementId") int departementId) {
		Departement departement = departementService.findById(departementId);
		if (departement != null) {
			departementService.delete(departement);
		}
		return "redirect:/Departements";
	}

	@RequestMapping("/departements/{departementId}")
	public ModelAndView showDepartement(@PathVariable("departementId") int departementId) {
		ModelAndView mav = new ModelAndView("departements/view");
		mav.addObject(this.departementService.findById(departementId));
		return mav;
	}

	@RequestMapping(value = { "/departements.xml", "/departements.html" } , method = RequestMethod.GET)
    public @ResponseBody Departements showResourcesRegionsList() {
		Departements departements = new Departements();
		departements.getDepartementList().addAll(this.departementService.findAll());
        return departements;
    }

}
