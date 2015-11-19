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
import cm.gov.daf.sif.titrefoncier.model.Requisition;
import cm.gov.daf.sif.titrefoncier.service.DepartementService;
import cm.gov.daf.sif.titrefoncier.service.RegionService;
import cm.gov.daf.sif.web.utils.WebConstants;

/**
 * @author Albert
 */
@Controller
@SessionAttributes(types = Requisition.class)
public class RequisitionController {

	public RequisitionController() {
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/requisitions", method = RequestMethod.GET)
	public String search() {
		return "requisitions/list";
	}

	@RequestMapping(value = "/requisitions/data", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> searchJson(@RequestParam(value = "start", defaultValue = "0") Integer page,
			@RequestParam(value = "draw", defaultValue = "0") Integer draw,
			@RequestParam(value = "search[value]", defaultValue = "") String search) {

		Map<String, Object> data = new HashMap<String, Object>();

		int pageNumber = (page + 1) / WebConstants.PAGE_SIZE;

		Pageable pageable = new PageRequest(pageNumber, WebConstants.PAGE_SIZE);



		return data;
	}

	@RequestMapping(value = "/requisitions/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		Requisition requisition = new Requisition();
		model.addAttribute(requisition);

		return "requisitions/createOrUpdate";
	}

	@RequestMapping(value = "/requisitions/new", method = RequestMethod.POST)
	public String processCreationForm(Model model, @Valid Requisition requisition, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "requisitions/createOrUpdate";
		} else {
			
			status.setComplete();
			return "redirect:/requisitions/" + requisition.getId();
		}
	}

	@RequestMapping(value = "/requisitions/{requisitionId}/edit", method = RequestMethod.GET)
	public String initUpdateOwnerForm(@PathVariable("requisitionId") int requisitionId, Model model) {
		
		return "requisitions/createOrUpdate";
	}

	@RequestMapping(value = "/requisitions/{requisitionId}/edit", method = RequestMethod.PUT)
	public String processUpdateDepartementForm(@Valid Requisition requisition, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "requisitions/createOrUpdate";
		} else {
			
			status.setComplete();
			return "redirect:/requisitions/{requisitionId}";
		}
	}

	@RequestMapping(value = "/requisitions/{requisitionId}", method = RequestMethod.DELETE)
	public String processDeleteDepartement(@PathVariable("requisitionId") int departementId) {

		return "redirect:/requisitions";
	}

	@RequestMapping("/requisitions/{requisitionId}")
	public ModelAndView showDepartement(@PathVariable("requisitionId") int requisitionId) {
		ModelAndView mav = new ModelAndView("requisitions/view");
		
		return mav;
	}

	@RequestMapping(value = { "/requisitions.xml", "/requisitions.html" } , method = RequestMethod.GET)
    public @ResponseBody Departements showResourcesRegionsList() {
		
		return null;
    }

}
